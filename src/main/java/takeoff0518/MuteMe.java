package takeoff0518;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.At;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public final class MuteMe extends JavaPlugin {
    public static final MuteMe INSTANCE = new MuteMe();

    private MuteMe() {
        super(new JvmPluginDescriptionBuilder("takeoff.muteme", "0.1.1")
                .name("MuteMe")
                .author("takeoff0518")
                .build());
    }

    @Override
    public void onEnable() {
        this.reloadPluginConfig(Config.INSTANCE);
        getLogger().info("MuteMe 已成功加载！GitHub：https://github.com/Takeoff0518/mirai-plugin-muteme/");
        EventChannel<Event> eventChannel = GlobalEventChannel.INSTANCE.parentScope(this).filter(e -> {
            if (e instanceof MessageEvent) {
                return ((MessageEvent) e).getMessage().contains(At.Key);
            }
            return false;
        });
        eventChannel.subscribeAlways(GroupMessageEvent.class, g -> {
            if (g.getMessage().contentToString().contains("muteme")) {
                try {
                    int muteTime = Config.INSTANCE.getMinTime() + new Random().nextInt(Config.INSTANCE.getMaxTime());
                    g.getSender().mute(muteTime);
                    String message = "被禁名称:" + g.getSender().getNick() + "\n被禁时间:" + muteTime + "秒\n解禁时间:" + formatDate(System.currentTimeMillis() + ((long) muteTime * 1000));
                    g.getGroup().sendMessage(message);
                } catch (Exception exception) {
                    g.getGroup().sendMessage("出现了一点意外呢~");
                    exception.printStackTrace();
                }
            }
            if (g.getMessage().contentToString().contains("我好了")) {
                try {
                    g.getSender().mute(30);
                    g.getGroup().sendMessage("不许好，憋回去！");
                } catch (Exception exception) {
                    g.getGroup().sendMessage("出现了一点意外呢~");
                    exception.printStackTrace();
                }
            }
        });
    }

    @NotNull("1970年01月01日00时00分00秒")
    String formatDate(long timeStamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH时mm分ss秒");
        Instant instant = Instant.ofEpochMilli(timeStamp);
        return formatter.format(instant.atZone(ZoneId.systemDefault()));
    }
}