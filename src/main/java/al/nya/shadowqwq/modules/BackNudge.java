package al.nya.shadowqwq.modules;

import al.nya.pluginextendsapi.modules.Module;
import al.nya.shadowqwq.ShadowQwQ;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.util.ContactUtils;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.UserOrBot;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.NudgeEvent;
import net.mamoe.mirai.message.action.Nudge;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

public class BackNudge extends Module {
    public BackNudge() {
        super(ShadowQwQ.INSTANCE,"BackNudge");
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof NudgeEvent){
            if(((NudgeEvent) event).getSubject() != null && ((NudgeEvent) event).getTarget() == ((NudgeEvent) event).getBot() && ((NudgeEvent) event).getFrom() != ((NudgeEvent) event).getBot()){
                ((NudgeEvent) event).getFrom().nudge().sendTo(((NudgeEvent) event).getSubject());
            }
        }
    }
}
