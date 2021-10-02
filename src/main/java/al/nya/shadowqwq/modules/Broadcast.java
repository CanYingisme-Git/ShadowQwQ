package al.nya.shadowqwq.modules;

import al.nya.pluginextendsapi.modules.Module;
import al.nya.pluginextendsapi.modules.ModuleManager;
import al.nya.shadowqwq.ShadowQwQ;
import al.nya.shadowqwq.utils.MessageUtil;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;

import java.util.Random;

public class Broadcast extends Module {
    public Broadcast() {
        super(ShadowQwQ.INSTANCE,"Broadcast");
    }
    @Override
    public void onEvent(Event event) {
        if (event instanceof FriendMessageEvent){
            if (((FriendMessageEvent) event).getSender().getId() == ShadowQwQ.SHADOW_ID){
                String[] command = MessageUtil.getMessage(((FriendMessageEvent) event).getMessage()).split(" ");
                if (command.length >= 2) {
                    if (command[0].equalsIgnoreCase("/broadcast")){
                        String message = MessageUtil.getMessage(((FriendMessageEvent) event).getMessage()).replace(command[0]+" ","");
                        for (Group group : ((FriendMessageEvent) event).getBot().getGroups()) {
                            GroupControl groupControl = (GroupControl) ModuleManager.getModule("GroupControl");
                            try {
                                if (groupControl.isEnableGroup(group,this)){
                                    try{
                                       group.sendMessage(message);
                                    }catch (Exception e){
                                      e.printStackTrace();
                                    }
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                      e.printStackTrace();
                                    }
                            }
                            }catch (Exception e){
                                ((FriendMessageEvent) event).getSender().sendMessage(e.getMessage());
                            }
                        }
                    }
                }
            }
        }
    }
}
