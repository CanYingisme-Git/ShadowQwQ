package al.nya.shadowqwq.modules;

import al.nya.pluginextendsapi.modules.Module;
import al.nya.shadowqwq.ShadowQwQ;
import al.nya.shadowqwq.runnable.DayNotice;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;

public class ShadowQwQTest extends Module {
    public ShadowQwQTest() {
        super(ShadowQwQ.INSTANCE,"ShadowQwQTest");
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof FriendMessageEvent){
            if (((FriendMessageEvent) event).getMessage().get(1).toString().split("")[0].equals("/")) {
                String[] command = ((FriendMessageEvent) event).getMessage().get(1).toString().split(" ");
                if (((FriendMessageEvent) event).getSender().getId() == Long.parseLong("3192799549"))
                if (command.length == 1){
                    if (command[0].equalsIgnoreCase("/daytest")){
                        new DayNotice().notice();
                    }
                }
            }
        }
    }
}
