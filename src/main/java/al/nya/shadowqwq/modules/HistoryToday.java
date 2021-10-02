package al.nya.shadowqwq.modules;

import al.nya.pluginextendsapi.modules.Module;
import al.nya.pluginextendsapi.modules.ModuleManager;
import al.nya.shadowqwq.ShadowQwQ;
import al.nya.shadowqwq.utils.ACGUtil;
import al.nya.shadowqwq.utils.HistoryTodayInfo;
import com.google.gson.Gson;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.GroupMessageEvent;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryToday extends Module {
    private String API2 = "https://www.ipip5.com/today/api.php?type=json";
    private String token="v32Eo2Tw+qWI/eiKW3D8ye7l19mf1NngRLushO6CumLMHIO1aryun0/Y3N3YQCv/TqzaO/TFHw4=";
    public HistoryToday() {
        super(ShadowQwQ.INSTANCE , "HistoryToday");
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof GroupMessageEvent){
            GroupControl groupControl = (GroupControl) ModuleManager.getModule("GroupControl");
            if (!groupControl.isEnableGroup(((GroupMessageEvent) event).getGroup(),this)){
                return;
            }
            if (((GroupMessageEvent) event).getMessage().get(1).toString().split("")[0].equals("/")) {
                String[] command = ((GroupMessageEvent) event).getMessage().get(1).toString().split(" ");
                if (command.length == 1){
                    if (command[0].equalsIgnoreCase("/history")){
                        StringBuilder sb = new StringBuilder();
                        Gson gson = new Gson();
                        SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMdd");
                        Date date = new Date(System.currentTimeMillis());
                        String dataS = formatter.format(date);
                        new File("./notice").mkdir();
                        try {
                            ACGUtil.downLoadFromUrl(API2,dataS +"Today.json","./notice");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        HistoryTodayInfo historyTodayInfo = gson.fromJson( ACGUtil.readJson("./notice/"+dataS+"Today.json"),HistoryTodayInfo.class);
                        sb.append("历史上的今天");
                        historyTodayInfo.result.forEach(R -> {
                            if (!(R.title.equals("感谢 www.ipip5.com 提供数据支持!")))
                                sb.append("\n").append(R.year).append("年 ").append(R.title);
                        });
                        ((GroupMessageEvent) event).getGroup().sendMessage(sb.toString());
                    }
                }
            }
        }
    }
}
