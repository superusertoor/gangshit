package de.toor.gangshit.handler;

import de.toor.gangshit.exceptions.NoDJsFoundException;
import de.toor.gangshit.exceptions.TooManyDJsException;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicHandler {

    public static boolean isDj(Member member) {
        return new File("gangshit/" + member.getGuild().getId() + "/music/" + member.getId()).exists();
    }

    public static void removeDj(Member member) {
        new File("gangshit/" + member.getGuild().getId() + "/music/" + member.getId()).delete();
    }

    public static List<Member> getAvailableDJs(Guild guild) throws NoDJsFoundException {
        List<Member> list = new ArrayList<>();
        File file = new File("gangshit/" + guild.getId() + "/music/");
        for(File dj : file.listFiles()) {
            list.add(guild.getMemberById(dj.getName()));
        }
        if(list.size() == 0) {
            throw new NoDJsFoundException();
        }
        return list;
    }

    public static Integer getAvailableDJsCount(Guild guild) {
        List<Member> list = new ArrayList<>();
        File file = new File("gangshit/" + guild.getId() + "/music/");
        for(File dj : file.listFiles()) {
            list.add(guild.getMemberById(dj.getName()));
        }
        return list.size();
    }

    public static void addDj(Member member, String playlist) throws TooManyDJsException {
        if(!new File("gangshit/" + member.getGuild().getId() + "/music/" + member.getId()).exists()) {
            try {
                new File("gangshit/" + member.getGuild().getId() + "/music/" + member.getId()).createNewFile();
                if(getAvailableDJsCount(member.getGuild()) >= 5) {
                    throw new TooManyDJsException();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}