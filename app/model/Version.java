package model;

import play.Play;

public final class Version {

    public static final String VERSION = Play.configuration.getProperty("application.version");

    private Version() {
    }
}
