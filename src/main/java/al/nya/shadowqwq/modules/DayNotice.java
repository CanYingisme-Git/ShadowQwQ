package al.nya.shadowqwq.modules;

import al.nya.pluginextendsapi.modules.Module;
import al.nya.pluginextendsapi.modules.ModuleManager;
import al.nya.shadowqwq.ShadowQwQ;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.Event;

public class DayNotice extends Module {
    public DayNotice() {
        super(ShadowQwQ.INSTANCE,"DayNotice");
    }

    @Override
    public void onEvent(Event event) {

    }
    public boolean broadcast(Group g){
        if (!this.isEnable()) return false;
        GroupControl groupControl = (GroupControl) ModuleManager.getModule("GroupControl");
        if (groupControl.isEnableGroup(g,this)){
            return true;
        }else {
            return false;
        }
    }
}
