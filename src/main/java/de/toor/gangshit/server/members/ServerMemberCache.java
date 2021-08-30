package de.toor.gangshit.server.members;

import de.toor.gangshit.server.Server;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

import java.util.ArrayList;
import java.util.List;

public class ServerMemberCache {

    Server server;
    Guild guild;

    public ServerMemberCache(Server server) {
        this.server = server;
        this.guild = server.getGuild();
    }

    public List<Member> getOnlineMembers() {
        List<Member> list = new ArrayList<>();
        for(Member member : guild.getMembers()) {
            if (member.getOnlineStatus().equals(OnlineStatus.ONLINE) || member.getOnlineStatus().equals(OnlineStatus.DO_NOT_DISTURB) || member.getOnlineStatus().equals(OnlineStatus.IDLE)) {
                list.add(member);
            }
        }
        return list;
    }

    public List<Member> getMembers() {
        List<Member> list = new ArrayList<>();
        for(Member member : guild.getMembers()) {
            list.add(member);
        }
        return list;
    }

    public List<Member> getBots() {
        List<Member> list = new ArrayList<>();
        for(Member member : guild.getMembers()) {
            if (member.getUser().isBot()) {
                list.add(member);
            }
        }
        return list;
    }
}