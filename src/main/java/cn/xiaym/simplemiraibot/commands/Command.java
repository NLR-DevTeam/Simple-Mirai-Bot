package cn.xiaym.simplemiraibot.commands;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jline.reader.Completer;
import org.jline.reader.impl.completer.NullCompleter;

import java.util.ArrayList;

/**
 * 插件命令类
 */
public class Command {
    private final String name;
    private final String description;
    private final String usage;
    private CommandExecutor executor;

    public Command(@NotNull String commandName, @NotNull String commandDescription, @Nullable String commandUsage) {
        this.name = commandName;
        this.description = commandDescription;
        this.usage = commandUsage;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUsage() {
        return usage;
    }

    public CommandExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(CommandExecutor executor) {
        this.executor = executor;
    }

    public Completer getCommandCompleter(String label, ArrayList<String> args) {
        return new NullCompleter();
    }
}
