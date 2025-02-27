package util;

import java.net.URISyntaxException;

import start.Main;

public class Constant {
    public static String appTitle = "Electronic-Store-Epoka-2025";
    public static String baseLocation;

    static {
        try {
            baseLocation = Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            baseLocation = baseLocation.substring(0, baseLocation.lastIndexOf("/"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
