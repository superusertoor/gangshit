package de.toor.gangshit.utils;

import net.dv8tion.jda.api.entities.Member;
import sun.misc.Launcher;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

public class AvatarHandler {

    public static BufferedImage getAvatar(Member member) {
        URLConnection connection;
        BufferedImage profileImg = null;
        try {
            String str = member.getUser().getAvatarUrl() + "?size=512";
            connection = new URL(str).openConnection();
            System.out.println(str);
            connection.setRequestProperty("User-Agent", "bot emily-bot");
            try {
                profileImg = ImageIO.read(connection.getInputStream());
            } catch (Exception ignored) {
                profileImg = ImageIO.read(Objects.requireNonNull(Launcher.class.getClassLoader().getResource("default_profile.jpg")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return profileImg;
    }
}
