package al.nya.shadowqwq.modules;

import al.nya.pluginextendsapi.modules.Module;
import al.nya.pluginextendsapi.modules.ModuleManager;
import al.nya.shadowqwq.ShadowQwQ;
import al.nya.shadowqwq.utils.ACGUtil;
import al.nya.shadowqwq.utils.MessageUtil;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.utils.ExternalResource;

import java.io.File;
import java.io.IOException;

public class ACGImage extends Module {
    public ACGImage() {
        super(ShadowQwQ.INSTANCE,"ACGImage");
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof GroupMessageEvent){
            GroupControl groupControl = (GroupControl) ModuleManager.getModule("GroupControl");
            if (!groupControl.isEnableGroup(((GroupMessageEvent) event).getGroup(),this)){
                return;
            }
            String message = MessageUtil.getMessage(((GroupMessageEvent) event).getMessage());
            if (message.equalsIgnoreCase("/acgimage")){
                Image image = null;
                try {
                    image = ((GroupMessageEvent) event).getGroup().uploadImage(ExternalResource.create(new File(ACGUtil.download())));
                    ((GroupMessageEvent) event).getGroup().sendMessage(image);
                } catch (IOException e) {
                    e.printStackTrace();
                    ((GroupMessageEvent) event).getGroup().sendMessage(e.getMessage());
                }
            }
        }
    }
}
