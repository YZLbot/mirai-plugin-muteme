package takeoff0518;

import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Handler {
    public static void onMessage(GroupMessageEvent g) {
        MessageChain message = g.getMessage();
        Member sender = g.getSender();
        Group group = g.getGroup();
        String content = message.contentToString();
        if (content.contains("muteme")) {
            if (checkPermission(g)) {
                int muteTime = getRandom(Config.INSTANCE.getMinTime(), Config.INSTANCE.getMaxTime());
                sender.mute(muteTime);
                group.sendMessage("被禁名称:" + g.getSender().getNick() + "\n被禁时间:" + muteTime + "秒\n解禁时间:" + formatDate(System.currentTimeMillis() + ((long) muteTime * 1000)));
            } else {
                group.sendMessage("可惜我不能禁言呢~");
            }
        }
        if (content.contains("我好了")) {
            if (checkPermission(g)) {
                sender.mute(30);
                group.sendMessage("不许好，憋回去！");
            } else {
                g.getGroup().sendMessage("好好好");
            }
        }
    }

    @NotNull("1970年01月01日00时00分00秒")
    private static String formatDate(long timeStamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH时mm分ss秒");
        Instant instant = Instant.ofEpochMilli(timeStamp);
        return formatter.format(instant.atZone(ZoneId.systemDefault()));
    }

    private static boolean checkPermission(GroupMessageEvent g) {
        return g.getSender().getPermission().getLevel() < g.getGroup().getBotPermission().getLevel();
    }

    private static int getRandom(int lBound, int rBound) {
        return new Random().nextInt(rBound - lBound + 1) + lBound;
    }
}
