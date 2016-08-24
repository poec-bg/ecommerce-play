package controllers;


import model.Room;
import play.libs.F;
import play.libs.F.Either;
import play.libs.F.Promise;
import play.mvc.Http.WebSocketClose;
import play.mvc.Http.WebSocketEvent;
import play.mvc.WebSocketController;

import static play.libs.F.Matcher.ClassOf;
import static play.libs.F.Matcher.Equals;
import static play.mvc.Http.WebSocketEvent.SocketClosed;
import static play.mvc.Http.WebSocketEvent.TextFrame;

public class Chats extends WebSocketController {

    public static void test(String name) {
        outbound.send("Hello %s!", name);
    }

    public static void join(String name) {

        Room room = Room.get();

        // Socket connected, join the chat room
        F.EventStream<Room.Event> roomMessagesStream = room.join(name);

        while(inbound.isOpen()) {
            // Wait for an event (either something coming on the inbound socket channel, or Room messages)
            Either<WebSocketEvent,Room.Event> e = await(Promise.waitEither(
                    inbound.nextEvent(),
                    roomMessagesStream.nextEvent()
            ));

            // Case: name typed 'quit'
            for(String nameMessage: TextFrame.and(Equals("quit")).match(e._1)) {
                room.leave(name);
                outbound.send("quit:ok");
                disconnect();
            }

            // Case: TextEvent received on the socket
            for(String nameMessage: TextFrame.match(e._1)) {
                room.say(name, nameMessage);
            }

            // Case: Someone joined the room
            for(Room.Join joined: ClassOf(Room.Join.class).match(e._2)) {
                outbound.send("join:%s", joined.name);
            }

            // Case: New message on the chat room
            for(Room.Message message: ClassOf(Room.Message.class).match(e._2)) {
                outbound.send("message:%s:%s", message.name, message.text);
            }

            // Case: Someone left the room
            for(Room.Leave left: ClassOf(Room.Leave.class).match(e._2)) {
                outbound.send("leave:%s", left.name);
            }

            // Case: The socket has been closed
            for(WebSocketClose closed: SocketClosed.match(e._1)) {
                room.leave(name);
                disconnect();
            }
        }
    }
}
