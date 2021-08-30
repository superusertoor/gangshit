package de.toor.gangshit.objects;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;

import java.awt.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserInfo {

    private EmbedBuilder embedBuilder = new EmbedBuilder().setColor(Color.PINK);

    public UserInfo(Member member) {
        User user = member.getUser();
        embedBuilder.setDescription("> " + member.getAsMention());
        embedBuilder.setThumbnail(user.getAvatarUrl());
        embedBuilder.addField("Status", "**"+ member.getOnlineStatus() + "**", true);
        final OffsetDateTime joined = member.getTimeJoined();
        final OffsetDateTime created = member.getTimeCreated();
        final String joinedString = joined.getDayOfMonth() + "." + joined.getMonthValue() + "." + joined.getYear() + " " + joined.getHour() + ":" + joined.getMinute();
        final String createdString = joined.getDayOfMonth() + "." + created.getMonthValue() + "." + created.getYear() + " " + created.getHour() + ":" + created.getMinute();
        embedBuilder.addField("Servermitglied seit", "```" + joinedString + "```", false);
        embedBuilder.addField("Discord User seit", "```" + createdString + "```", false);
        final StringBuilder roles = new StringBuilder();
        final List<Role> memberRoles = member.getRoles();
        if(member.getRoles().size() > 0) {
            List<String> rolesList = new ArrayList<>();
            memberRoles.forEach(r -> {
                rolesList.add(r.getAsMention());
            });
            roles.append(String.join(",", rolesList));
        }else {
            roles.append("```Keine Rollen```");
        }
        embedBuilder.addField("Rollen (" + memberRoles.size() + ")", roles.toString(), false);
    }

    public Message send(TextChannel textChannel){
        return textChannel.sendMessage(embedBuilder.build()).complete();
    }

}
