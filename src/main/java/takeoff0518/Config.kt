package takeoff0518

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object Config : AutoSavePluginConfig("config") {
    @ValueDescription("下界(s)")
    val minTime: Int by value(15)

    @ValueDescription("上界(s)")
    val maxTime: Int by value(600)
//    var enable: Array<Long> by value()
}
