package al.nya.shadowqwq.modules;

import al.nya.pluginextendsapi.modules.Module;
import al.nya.pluginextendsapi.modules.ModuleManager;
import al.nya.shadowqwq.ShadowQwQ;
import al.nya.shadowqwq.utils.ACGUtil;
import al.nya.shadowqwq.utils.MessageUtil;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.NormalMember;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.NewFriendRequestEvent;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.SingleMessage;
import net.mamoe.mirai.utils.ExternalResource;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

public class ShadowQwQCoreService extends Module {
    public ShadowQwQCoreService() {
        super(ShadowQwQ.INSTANCE,"ShadowQwQCoreService");
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof GroupMessageEvent){
            String message = MessageUtil.getMessage(((GroupMessageEvent) event).getMessage());
            if (message.split("")[0].equals("/")){
                String[] command = message.split(" ");
                if (command.length == 1){
                    if (command[0].equalsIgnoreCase("/help")){
                        sendHelp((GroupMessageEvent) event);
                    }
                    if (command[0].equalsIgnoreCase("/acgimage")){
                        //this.getPlugin().getLogger().info("ACGImage");
                        if(ModuleManager.getModule("ACGImage").isEnable()) {
                            sendACGImage((GroupMessageEvent) event);
                        }else {
                            ((GroupMessageEvent) event).getGroup().sendMessage("模块ACGImage为禁用状态");
                        }
                    }
                }else if (command.length ==2){
                    if (command[0].equalsIgnoreCase("/nudge")){
                        String unformattedTarget = command[1];
                        String formattedTarget;
                        if (unformattedTarget.contains("[mirai:at:")){
                            formattedTarget = unformattedTarget.replace("[mirai:at:","").replace("]","");
                        }else {
                            formattedTarget = unformattedTarget;
                        }
                        long target = 0;
                        try {
                            target = Long.parseLong(formattedTarget);
                        }catch (NumberFormatException e){
                            ((GroupMessageEvent) event).getGroup().sendMessage(e.toString());
                        }
                        for (NormalMember member : ((GroupMessageEvent) event).getGroup().getMembers()) {
                            if (member.getId() == target){
                                member.nudge().sendTo(((GroupMessageEvent) event).getGroup());
                            }
                        }
                    }
                }
            }
        }else if (event instanceof NewFriendRequestEvent){
            ((NewFriendRequestEvent) event).accept();
        }
    }
    private void sendHelp(GroupMessageEvent event){

    }
    private void sendACGImage(GroupMessageEvent event){
        Image image = event.getGroup().uploadImage(ExternalResource.create(new File(ACGUtil.download())));
        event.getGroup().sendMessage(image);
    }
}
