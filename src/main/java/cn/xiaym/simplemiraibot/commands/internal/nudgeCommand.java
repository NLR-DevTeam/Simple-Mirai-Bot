package cn.xiaym.simplemiraibot.commands.internal;

import cn.xiaym.simplemiraibot.BotMain;
import cn.xiaym.simplemiraibot.commands.Command;
import cn.xiaym.simplemiraibot.commands.CommandExecutor;
import cn.xiaym.simplemiraibot.utils.Logger;
import net.mamoe.mirai.contact.UserOrBot;
import org.jline.reader.Completer;
import org.jline.reader.impl.completer.StringsCompleter;

import java.util.ArrayList;
import java.util.Objects;

public class nudgeCommand extends Command implements CommandExecutor {
    public nudgeCommand() {
        super("nudge", "戳一戳群内成员.", "/nudge <整数: 成员 QQ>");
        setExecutor(this);
    }

    @Override
    public Completer getCommandCompleter(String label, ArrayList<String> args) {
        ArrayList<String> arr = new ArrayList<>();

        for (UserOrBot user : BotMain.getCurrentGroup().getMembers())
            arr.add(String.valueOf(user.getId()));

        return new StringsCompleter(arr);
    }

    public void onCommand(String input, ArrayList<String> args) {
        if (args.size() <= 1) {
            Logger.warning("使用方法: " + getUsage());
            return;
        }

        try {
            Objects.requireNonNull(
                            BotMain.getCurrentGroup()
                                    .get(Long.parseLong(args.get(1))))
                    .nudge()
                    .sendTo(BotMain.getCurrentGroup());
            Logger.info("戳一戳成功!");
        } catch (NumberFormatException ex) {
            Logger.warning("无法解析 QQ 号为整数.");
        } catch (NullPointerException ne) {
            Logger.warning("找不到该群成员.");
        } catch (Exception e) {
            Logger.info("戳一戳失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
