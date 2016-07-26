package controllers.secure;

import exceptions.InvalidArgumentException;
import model.Client;
import org.apache.commons.lang.StringUtils;
import play.Logger;
import services.ClientService;

public class Security extends Secure.Security {

    public static boolean authenticate(String login, String password) {
        try {
            if (ClientService.get().authenticate(login, password)) {
                Logger.info("Security | authenticate : connexion de l'utilisateur [%s]", login);
                return true;
            }
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }
        Logger.warn("Security | authenticate : tentative de connexion de l'utilisateur inconnu [%s]", login);
        return false;
    }

    public static boolean check(String profile) {
        return true;
    }

    public static String connected() {
        return session.get("username");
    }

    public static boolean isConnected() {
        return session.contains("username");
    }

    public static Client connectedUser() {
        Client user = null;
        try {
            user = ClientService.get().getClientByEmail(connected());
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }
        if (user == null) {
            redirect("secure.Secure.logout");
        } else {
            renderArgs.put("connectedUser", user);
        }
        return user;
    }

    public static void onAuthenticated() {
        String url = flash.get("url");
        if (StringUtils.isEmpty(url)) {
            redirect("Application.index");
        }
        redirect(url);
    }

    public static void onDisconnect() {
        try {
            Client user = ClientService.get().getClientByEmail(Security.connected());
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }
        session.clear();
        response.removeCookie("rememberme");
        redirect("Application.index");
    }
}