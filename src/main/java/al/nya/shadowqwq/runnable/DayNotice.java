package al.nya.shadowqwq.runnable;

import al.nya.pluginextendsapi.modules.ModuleManager;
import al.nya.shadowqwq.utils.ACGUtil;
import al.nya.shadowqwq.utils.HistoryTodayInfo;
import al.nya.shadowqwq.utils.NoticeInfo;
import com.google.gson.Gson;
import net.mamoe.mirai.Bot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DayNotice {
    private String API ="https://v1.hitokoto.cn/";
    private String API2 = "https://www.ipip5.com/today/api.php?type=json";
    private String token="v32Eo2Tw+qWI/eiKW3D8ye7l19mf1NngRLushO6CumLMHIO1aryun0/Y3N3YQCv/TqzaO/TFHw4=";
    public void notice(){
        File file = new File("./notice");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(System.currentTimeMillis());
        String dataS = formatter.format(date);
        formatter= new SimpleDateFormat("yyyy-MM-dd");
        String dataR = formatter.format(date);
        file.mkdir();
        try {
            ACGUtil.downLoadFromUrl(API,dataS +".json","./notice",token);
            ACGUtil.downLoadFromUrl(API2,dataS +"Today.json","./notice",token);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        NoticeInfo noticeInfo = gson.fromJson( ACGUtil.readJson("./notice/"+dataS+".json"),NoticeInfo.class);
        HistoryTodayInfo historyTodayInfo = gson.fromJson( ACGUtil.readJson("./notice/"+dataS+"Today.json"),HistoryTodayInfo.class);
        StringBuilder sb = new StringBuilder();
        sb.append("今天是").append(dataR);
        sb.append("\n").append(noticeInfo.hitokoto).append(" -").append(noticeInfo.from);
        sb.append("\n").append("历史上的今天");
        historyTodayInfo.result.forEach(R ->{
            if (!(R.title.equals("感谢 www.ipip5.com 提供数据支持!")))
                sb.append("\n").append(R.year).append("年 ").append(R.title);
        });
        Bot.getInstances().forEach(B ->{
            B.getGroups().forEach(G -> {
                if (((al.nya.shadowqwq.modules.DayNotice)ModuleManager.getModule("DayNotice")).broadcast(G)){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try{
                    G.sendMessage(sb.toString());
                }catch (Exception e){
                    e.printStackTrace();
                }}
            });
        });
    }
}
