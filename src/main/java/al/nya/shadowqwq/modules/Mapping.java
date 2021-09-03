package al.nya.shadowqwq.modules;

import al.nya.pluginextendsapi.modules.Module;
import al.nya.shadowqwq.ShadowQwQ;
import al.nya.shadowqwq.utils.ACGUtil;
import al.nya.shadowqwq.utils.MessageUtil;
import al.nya.shadowqwq.utils.youdao.TranslateType;
import al.nya.shadowqwq.utils.youdao.YouDaoTranslate;
import com.google.gson.Gson;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.MessageChain;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Mapping extends Module {
    private List<String> supportVersion = new ArrayList<String>();
    public Mapping() {
        super(ShadowQwQ.INSTANCE,"MappingBot");
        supportVersion.add("1.8.9");
    }

    @Override
    public void onEvent(Event event) {
        MessageChain messages = null;
        if (event instanceof MessageEvent){
            if (event instanceof GroupMessageEvent){
                messages = ((GroupMessageEvent) event).getMessage();
            }else if (event instanceof FriendMessageEvent){
                messages = ((FriendMessageEvent) event).getMessage();
            }
            if (messages == null) return;
            String msg = MessageUtil.getMessage(messages);
            if (msg.split("")[0].equals("/")){
                String[] msgs = msg.split(" ");
                if (msgs.length >= 2){
                    if (msgs[0].equalsIgnoreCase("/mapping")){
                        if (msgs[1].equalsIgnoreCase("help")){

                        }else if (msgs[1].equalsIgnoreCase("/srg")){

                        }
                    }
                }
            }
        }
    }
}
