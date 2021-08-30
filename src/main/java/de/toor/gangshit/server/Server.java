package de.toor.gangshit.server;

import de.toor.gangshit.objects.SetupHandler;
import de.toor.gangshit.server.members.ServerMemberCache;
import net.dv8tion.jda.api.entities.Guild;

import java.io.File;

public class Server {

    private String prefix;
    private File file;
    private Guild guild;
    private SetupHandler setupHandler;
    private ServerMemberCache serverMemberCache;

    public Server(Guild guild) {
        this.guild = guild;
        this.file = new File("gangshit/" + guild.getId() + "/");
        this.prefix = ".";
        this.setupHandler = new SetupHandler(this);
        this.serverMemberCache = new ServerMemberCache(this);
    }

    public ServerMemberCache getServerMemberCache() {
        return serverMemberCache;
    }

    public SetupHandler getSetupHandler() {
        return setupHandler;
    }

    public Guild getGuild() {
        return guild;
    }

    public String getPrefix() {
        return prefix;
    }
}