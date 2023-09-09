package takeoff0518;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.At;

import java.util.Random;

public final class MuteMe extends JavaPlugin {
    public static final MuteMe INSTANCE = new MuteMe();

    private MuteMe() {
        super(new JvmPluginDescriptionBuilder("takeoff.muteme", "0.1.0")
                .name("MuteMe")
                .author("takeoff0518")
                .build());
    }

    @Override
    public void onEnable() {
        this.reloadPluginConfig(Config.INSTANCE);
        getLogger().info("muteme load successful!");
        EventChannel<Event> eventChannel = GlobalEventChannel.INSTANCE.parentScope(this).filter(e -> {
            if (e instanceof MessageEvent) {
                return ((MessageEvent) e).getMessage().contains(At.Key);
            }
            return false;
        });
        eventChannel.subscribeAlways(GroupMessageEvent.class, g -> {
            if (g.getMessage().toString().equals("muteme")) {
                int muteTime = Config.INSTANCE.getMinTime() + new Random().nextInt(Config.INSTANCE.getMaxTime());
                getLogger().info("申请人：" + g.getSender().toString() + ",禁言时长：" + muteTime + "s");
                g.getSender().mute(muteTime);
                g.getGroup().sendMessage("禁言成功！\n申请人：" + g.getSender().toString() + "\n禁言时长：" + muteTime + "s");
            }
            if (g.getMessage().toString().equals("我好了")) {
                g.getSender().mute(10);
//                g.getGroup().sendMessage()
            }
        });


    }


}