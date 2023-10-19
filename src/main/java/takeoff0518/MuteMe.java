package takeoff0518;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.At;

public final class MuteMe extends JavaPlugin {
    public static final MuteMe INSTANCE = new MuteMe();

    private MuteMe() {
        super(new JvmPluginDescriptionBuilder("takeoff.muteme", "0.2.0")
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
        eventChannel.subscribeAlways(GroupMessageEvent.class, Handler::onMessage);
    }
}