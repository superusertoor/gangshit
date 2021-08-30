package de.toor.gangshit.events;

import de.toor.gangshit.objects.JoinLeaveImage;
import de.toor.gangshit.utils.ImageBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JoinQuitListener extends ListenerAdapter {

    @Override
    public void onGuildMemberLeave(GuildMemberLeaveEvent e) {
        final TextChannel textChannel = e.getGuild().getTextChannelById("865716618101522482");
        JoinLeaveImage joinImage = ImageBuilder.getNewJoinLeaveImage(e.getMember(), e.getMember().getUser().getAsTag() + " hat den Server verlassen");
        textChannel.sendFile(joinImage.toFile()).queue();
        joinImage.deleteFile();
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent e) {
        final TextChannel textChannel = e.getGuild().getTextChannelById("865716618101522482");
        JoinLeaveImage joinImage = ImageBuilder.getNewJoinLeaveImage(e.getMember(), e.getMember().getUser().getAsTag() + " hat den Server betreten");
        textChannel.sendFile(joinImage.toFile()).queue();
        joinImage.deleteFile();
    }
}
