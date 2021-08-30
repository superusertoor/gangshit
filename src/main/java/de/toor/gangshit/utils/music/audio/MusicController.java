package de.toor.gangshit.utils.music.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import de.toor.gangshit.main.GangShit;
import net.dv8tion.jda.api.entities.Guild;

public class MusicController {

    private Guild guild;
    private AudioPlayer player;
    private TrackManager trackManager;

    public MusicController(Guild guild) {
        this.guild = guild;
        this.player = GangShit.audioPlayerManager.createPlayer();
        this.guild.getAudioManager().setSendingHandler(new AudioPlayerSendHandler(player));
        this.player.setVolume(10);
        this.trackManager = new TrackManager(player);
        player.addListener(trackManager);
    }

    public TrackManager getTrackManager() {
        return trackManager;
    }

    public AudioPlayer getPlayer() {
        return player;
    }

    public Guild getGuild() {
        return guild;
    }

}
