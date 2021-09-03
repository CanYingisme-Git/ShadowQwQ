package al.nya.shadowqwq.modules;

import al.nya.pluginextendsapi.modules.Module;
import al.nya.shadowqwq.ShadowQwQ;
import al.nya.shadowqwq.utils.ACGUtil;
import al.nya.shadowqwq.utils.MessageUtil;
import al.nya.shadowqwq.utils.youdao.TranslateResult;
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
import java.util.UUID;

public class Translate extends Module {
    private String api = "http://fanyi.youdao.com/translate?&doctype=json&type={type}&i={string}";
    private String token="v32Eo2Tw+qWI/eiKW3D8ye7l19mf1NngRLushO6CumLMHIO1aryun0/Y3N3YQCv/TqzaO/TFHw4=";
    public Translate() {
        super(ShadowQwQ.INSTANCE,"Translate");
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
                    String url = null;
                    if (msgs[0].equalsIgnoreCase("/en") || msgs[0].equalsIgnoreCase("/english")){
                        String replacedMessage = msg.replace("/en ","").replace("/english ","");
                        url = api.replace("{type}", TranslateType.ZH_CN2EN.toString()).replace("{string}",replacedMessage);
                    }else if (msgs[0].equalsIgnoreCase("/zh") || msgs[0].equalsIgnoreCase("/chinese") || msgs[0].equalsIgnoreCase("/cn")){
                        String replacedMessage = msg.replace("/zh ","").replace("/chinese ","").replace("/cn ","");
                        url = api.replace("{type}", TranslateType.EN2ZH_CN.toString()).replace("{string}",replacedMessage);
                    }else if (msgs[0].equalsIgnoreCase("/translate")){
                        String replacedMessage = msg.replace("/translate ","");
                        url = api.replace("{type}", TranslateType.AUTO.toString()).replace("{string}",replacedMessage);
                    }
                    if (url != null){
                        new File("./translate").mkdir();
                        String uuid = UUID.randomUUID().toString();
                        try {
                            ACGUtil.downLoadFromUrl(url, uuid+".json","./translate",token);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        String s = ACGUtil.readJson("./translate/"+uuid+".json");
                        Gson gson = new Gson();
                        YouDaoTranslate youDaoTranslate = gson.fromJson(s,YouDaoTranslate.class);
                        StringBuilder sb = new StringBuilder();
                        if (youDaoTranslate.errorCode == 0){
                            youDaoTranslate.translateResult.forEach(L1 -> {
                                L1.forEach(L2 -> {
                                    if (sb.length() == 0){
                                        sb.append(L2.src).append("的翻译结果:").append("\n").append(L2.tgt);
                                    }else {
                                        sb.append("\n").append(L2.src).append("的翻译结果:").append("\n").append(L2.tgt);
                                    }
                                });
                            });
                            if (event instanceof GroupMessageEvent){
                                ((GroupMessageEvent) event).getGroup().sendMessage(sb.toString());
                            }else if (event instanceof FriendMessageEvent){
                                ((FriendMessageEvent) event).getSender().sendMessage(sb.toString());
                            }
                        }else {
                            if (event instanceof GroupMessageEvent){
                                ((GroupMessageEvent) event).getGroup().sendMessage("接口错误:"+youDaoTranslate.errorCode+"("+youDaoTranslate.type+")");
                            }else if (event instanceof FriendMessageEvent){
                                ((FriendMessageEvent) event).getSender().sendMessage("接口错误:"+youDaoTranslate.errorCode+"("+youDaoTranslate.type+")");
                            }
                        }
                    }
                }
            }
        }
    }
}
