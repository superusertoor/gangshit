package de.toor.gangshit.objects;

import de.toor.gangshit.server.Server;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Guild;

public class SetupHandler {

    private Server server;
    private boolean statsSetup;

    public SetupHandler(Server server) {
        this.server = server;
        this.statsSetup = false;
    }

    public boolean isStatsSetup() {
        return statsSetup;
    }

    public void createServerStats() {
        Guild guild = server.getGuild();
        Category category = guild.createCategory("stats").setPosition(0).complete();
        guild.createVoiceChannel("Online: " + server.getServerMemberCache().getOnlineMembers().size(), category).queue();
        guild.createVoiceChannel("Gesamt: " + server.getServerMemberCache().getMembers().size(), category).queue();
        guild.createVoiceChannel("Bots: " + server.getServerMemberCache().getBots().size(), category).queue();
    }
}
