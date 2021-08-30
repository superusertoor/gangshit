package de.toor.gangshit.handler;

import net.dv8tion.jda.api.entities.Guild;

import java.io.File;

public class FileHandler {

    private static String general = "gangshit/";

    public static void createFiles(Guild guild) {
        if(!new File(general + guild.getId()).exists()) {
            System.out.println(1);
            new File(general + guild.getId()).mkdirs();
            new File(general + guild.getId() + "/music/").mkdir();
        }
    }

    public static void deleteFiles(Guild guild) {
        new File(general + guild.getId()).delete();
    }
}