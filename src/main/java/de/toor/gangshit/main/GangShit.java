package de.toor.gangshit.main;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import de.toor.gangshit.commands.Commands;
import de.toor.gangshit.commands.MusicCommands;
import de.toor.gangshit.events.BotPingEvent;
import de.toor.gangshit.events.BotReadyEvent;
import de.toor.gangshit.events.BotRegisterGuildEvent;
import de.toor.gangshit.events.JoinQuitListener;
import de.toor.gangshit.events.music.ListeningPartyJoinEvent;
import de.toor.gangshit.events.music.ListeningPartyLeaveEvent;
import de.toor.gangshit.events.music.ListeningPartyReactionEvent;
import de.toor.gangshit.utils.music.ListeningParty;
import de.toor.gangshit.utils.music.audio.PlayerManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class GangShit {

    private static JDA jda;
    public static AudioPlayerManager audioPlayerManager;
    public static PlayerManager playerManager;

    public static ListeningParty listeningParty;

    public static void main(String[] args) throws LoginException {
        playerManager = new PlayerManager();
        audioPlayerManager = new DefaultAudioPlayerManager();

        jda = JDABuilder.createDefault("")
                .setChunkingFilter(ChunkingFilter.ALL)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .enableIntents(GatewayIntent.GUILD_PRESENCES)
                .enableCache(CacheFlag.ACTIVITY)
                .setAutoReconnect(true)
                .setActivity(Activity.listening("gangster gang"))
                .setStatus(OnlineStatus.IDLE)
                .addEventListeners(new ListeningPartyJoinEvent())
                .addEventListeners(new ListeningPartyReactionEvent())
                .addEventListeners(new ListeningPartyLeaveEvent())

                .addEventListeners(new BotPingEvent())
                .addEventListeners(new BotReadyEvent())
                .addEventListeners(new BotRegisterGuildEvent())


                .addEventListeners(new JoinQuitListener())


                .addEventListeners(new Commands())
                .addEventListeners(new MusicCommands())
                .build();

        AudioSourceManagers.registerRemoteSources(audioPlayerManager);
        audioPlayerManager.getConfiguration().setFilterHotSwapEnabled(true);

    }

    public static ListeningParty getListeningParty() {
        return listeningParty;
    }

    public static JDA getJda() {
        return jda;
    }
}