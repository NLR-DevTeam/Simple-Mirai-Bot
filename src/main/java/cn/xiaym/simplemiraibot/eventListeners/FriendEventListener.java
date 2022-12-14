package cn.xiaym.simplemiraibot.eventListeners;

import cn.xiaym.simplemiraibot.BotMain;
import cn.xiaym.simplemiraibot.utils.Logger;
import cn.xiaym.simplemiraibot.utils.bot.ConfigUtil;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListenerHost;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.FriendMessagePostSendEvent;
import net.mamoe.mirai.event.events.MessageRecallEvent;

import java.util.Objects;

public class FriendEventListener implements ListenerHost {
    @EventHandler
    public void onFriendMessage(FriendMessageEvent event) {
        if (!ConfigUtil.getConfig().getBoolean("chatting.listenFriends")) return;

        String senderName = event.getSenderName();
        String message = event.getMessage().contentToString();
        long senderQQ = event.getSender().getId();

        int MessageID = BotMain.requestNewMessageID(event.getSource());

        Logger.info(String.format("私聊 - %s (%d) : %s",
                senderName, senderQQ, message), "[" + MessageID + "] ");
    }

    @EventHandler
    public void onFriendMessagePostSendEvent(FriendMessagePostSendEvent event) {
        String botName = event.getBot().getNick();
        String message = event.getMessage().contentToString();
        String targetName = event.getTarget().getNick();
        long targetId = event.getTarget().getId();

        int MessageID = BotMain.requestNewMessageID(Objects.requireNonNull(event.getReceipt()).getSource());

        Logger.info(String.format("%s -> %s (%d) : %s",
                botName, targetName, targetId, message), "[" + MessageID + "] ");
    }

    @EventHandler
    public void onFriendMessageRecallEvent(MessageRecallEvent.FriendRecall event) {
        if (!ConfigUtil.getConfig().getBoolean("chatting.listenFriends")) return;

        String targetName = event.getOperator().getNick();
        long targetId = event.getOperatorId();
        int messageId = BotMain.getMessageIDByMessageTime(event.getMessageTime());

        Logger.info(String.format("%s (%d) 撤回了私聊消息 [%d]",
                targetName, targetId, messageId));
    }
}