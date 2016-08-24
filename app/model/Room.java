package model;

import play.libs.F;

import java.util.List;

public class Room {

    final F.ArchivedEventStream<Event> chatEvents = new F.ArchivedEventStream<>(100);

    static Room instance = null;
    public static Room get() {
        if(instance == null) {
            instance = new Room();
        }
        return instance;
    }


    public F.EventStream<Event> join(String name) {
        chatEvents.publish(new Join(name));
        return chatEvents.eventStream();
    }


    public void leave(String name) {
        chatEvents.publish(new Leave(name));
    }


    public void say(String name, String text) {
        if(text == null || text.trim().equals("")) {
            return;
        }
        chatEvents.publish(new Message(name, text));
    }

    public F.Promise<List<F.IndexedEvent<Event>>> nextMessages(long lastReceived) {
        return chatEvents.nextEvents(lastReceived);
    }

    public List<Room.Event> archive() {
        return chatEvents.archive();
    }

    public static abstract class Event {

        final public String type;
        final public Long timestamp;

        public Event(String type) {
            this.type = type;
            this.timestamp = System.currentTimeMillis();
        }
    }

    public static class Join extends Event {

        final public String name;

        public Join(String name) {
            super("join");
            this.name = name;
        }
    }

    public static class Leave extends Event {

        final public String name;

        public Leave(String name) {
            super("leave");
            this.name = name;
        }
    }

    public static class Message extends Event {

        final public String name;
        final public String text;

        public Message(String name, String text) {
            super("message");
            this.name = name;
            this.text = text;
        }
    }

}
