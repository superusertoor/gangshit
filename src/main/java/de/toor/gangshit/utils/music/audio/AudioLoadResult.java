package de.toor.gangshit.utils.music.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class AudioLoadResult implements AudioLoadResultHandler {

    private final String uri;
    private final MusicController controller;
    private TextChannel textChannel;
    private EmbedBuilder embedBuilder = new EmbedBuilder().setColor(Color.decode("0x8b8d9e"));
    private Member member;

    public AudioLoadResult(MusicController controller, TextChannel textChannel, Member member, String uri) {
        this.uri = uri;
        this.controller = controller;
        this.textChannel = textChannel;
        this.member = member;
    }

    @Override
    public void trackLoaded(AudioTrack audioTrack) {
        controller.getTrackManager().queue(audioTrack, member, textChannel);
    }

    @Override
    public void playlistLoaded(AudioPlaylist audioPlaylist) {
        audioPlaylist.getTracks().forEach(track -> {
            controller.getTrackManager().queue(track, member, textChannel);
        });
    }

    @Override
    public void noMatches() {

    }

    @Override
    public void loadFailed(FriendlyException e) {
        textChannel.sendMessage("Der angegebene Song konnte nicht gespielt werden").queue();
    }
}
