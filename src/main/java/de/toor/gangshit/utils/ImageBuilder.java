package de.toor.gangshit.utils;

import de.toor.gangshit.objects.JoinLeaveImage;
import net.dv8tion.jda.api.entities.Member;

public class ImageBuilder {

    public static JoinLeaveImage getNewJoinLeaveImage(Member member, String str) {
        return new JoinLeaveImage(member, str);
    }

}
