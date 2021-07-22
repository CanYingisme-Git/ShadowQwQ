package al.nya.shadowqwq;

import al.nya.pluginextendsapi.modules.ModuleManager;
import al.nya.shadowqwq.modules.*;
import al.nya.shadowqwq.runnable.GithubSubscriptionRunnable;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * 使用 Java 请把
 * {@code /src/main/resources/META-INF.services/net.mamoe.mirai.console.plugin.jvm.JvmPlugin}
 * 文件内容改成 {@code org.example.mirai.plugin.JavaPluginMain} <br/>
 * 也就是当前主类全类名
 *
 * 使用 Java 可以把 kotlin 源集删除且不会对项目有影响
 *
 * 在 {@code settings.gradle.kts} 里改构建的插件名称、依赖库和插件版本
 *
 * 在该示例下的 {@link JvmPluginDescription} 修改插件名称，id 和版本等
 *
 * 可以使用 {@code src/test/kotlin/RunMirai.kt} 在 IDE 里直接调试，
 * 不用复制到 mirai-console-loader 或其他启动器中调试
 */

public final class ShadowQwQ extends JavaPlugin {
    public static final ShadowQwQ INSTANCE = new ShadowQwQ();
    public static final long SHADOW_ID = Long.parseLong("3192799549");
    private ShadowQwQ() {
        super(new JvmPluginDescriptionBuilder("al.nya.shadowqwq", "0.1.0")
                .info("Hi I'm a chat bot").name("ShadowQwQ")
                .build());
    }

    @Override
    public void onEnable() {
        ModuleManager.registerModule(new ShadowQwQCoreService());
        ModuleManager.registerModule(new ACGImage());
        ModuleManager.registerModule(new Hypixel());
        ModuleManager.registerModule(new BackNudge());
        ModuleManager.registerModule(new GithubSubscription());
        ModuleManager.registerModule(new Broadcast());
        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new GithubSubscriptionRunnable(),5,60, TimeUnit.SECONDS);
    }
}
