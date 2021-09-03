package al.nya.shadowqwq.modules;

import al.nya.pluginextendsapi.modules.Module;
import al.nya.shadowqwq.ShadowQwQ;
import al.nya.shadowqwq.utils.ACGUtil;
import al.nya.shadowqwq.utils.hypixel.HypixelAPIInfo;
import com.google.gson.Gson;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.GroupMessageEvent;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class Hypixel extends Module {
    private String apiKey = "56281810-a5cb-404d-be0a-603a2c923cdf";
    private String token="v32Eo2Tw+qWI/eiKW3D8ye7l19mf1NngRLushOMHIO1aryun0/Y3N3YQCv/TqzaO/TFHw4=";
    public Hypixel() {
        super(ShadowQwQ.INSTANCE, "Hypixel");
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof GroupMessageEvent){
            if (((GroupMessageEvent) event).getMessage().get(1).toString().split("")[0].equals("/")) {
                String[] command = ((GroupMessageEvent) event).getMessage().get(1).toString().split(" ");
                if (command.length == 2){
                    if (command[0].equalsIgnoreCase("/hypixel")){
                        this.processInfo((GroupMessageEvent)event, getFullPlayerInfo(apiKey,command[1]));
                    }
                }
            }
        }
    }
    private void processInfo(GroupMessageEvent event,HypixelAPIInfo hypixelAPIInfo){
        StringBuilder sb = new StringBuilder();
        if (!hypixelAPIInfo.isSuccess()){
            sb.append("获取玩家信息失败");
            sb.append("\n"+hypixelAPIInfo.getCause());
            event.getGroup().sendMessage(sb.toString());
        }else {
            if (hypixelAPIInfo.getPlayerInfo() == null){
                sb.append("获取玩家信息失败");
                sb.append("\n服务器返回了空数据 可能是玩家不存在");
                event.getGroup().sendMessage(sb.toString());
            }else {
                sb.append(hypixelAPIInfo.getPlayerInfo().getDisplayname()+"的信息:");
                sb.append("\n上次上线时间");
                sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(hypixelAPIInfo.getPlayerInfo().getLastLogin()));
                sb.append("\n人品值");
                sb.append(hypixelAPIInfo.getPlayerInfo().getStats().getKarma());
                if (hypixelAPIInfo.getPlayerInfo().getStats().getSkyWars() != null) {
                    sb.append("\n空岛战争");
                    sb.append("\n击杀:");
                    sb.append(hypixelAPIInfo.getPlayerInfo().getStats().getSkyWars().getKills());
                    sb.append(" 死亡:");
                    sb.append(hypixelAPIInfo.getPlayerInfo().getStats().getSkyWars().getDeaths());
                    sb.append(" K/D:");
                    sb.append((double) hypixelAPIInfo.getPlayerInfo().getStats().getSkyWars().getKills() / (double) hypixelAPIInfo.getPlayerInfo().getStats().getSkyWars().getDeaths());
                    sb.append("\n扔出鸡蛋:");
                    sb.append(hypixelAPIInfo.getPlayerInfo().getStats().getSkyWars().getEgg_thrown());
                    sb.append(" 胜利场数:");
                    sb.append(hypixelAPIInfo.getPlayerInfo().getStats().getSkyWars().getWins());
                    sb.append("\n(全部数据不包含实验室模式)");
                }
                if (hypixelAPIInfo.getPlayerInfo().getStats().getBedwars() != null) {
                    sb.append("\n起床战争");
                    sb.append("\n击杀:");
                    sb.append(hypixelAPIInfo.getPlayerInfo().getStats().getBedwars().getKills_bedwars());
                    sb.append(" 死亡:");
                    sb.append(hypixelAPIInfo.getPlayerInfo().getStats().getBedwars().getDeaths_bedwars());
                    sb.append(" K/D:");
                    sb.append((double) hypixelAPIInfo.getPlayerInfo().getStats().getBedwars().getKills_bedwars() / (double) hypixelAPIInfo.getPlayerInfo().getStats().getBedwars().getDeaths_bedwars());
                    sb.append("\n游戏场数:");
                    sb.append(hypixelAPIInfo.getPlayerInfo().getStats().getBedwars().getGames_played_bedwars_1());
                    sb.append("\n金币数量:");
                    sb.append(hypixelAPIInfo.getPlayerInfo().getStats().getBedwars().getCoins());
                    sb.append("\n(全部数据不包含梦幻模式)");
                }
                event.getGroup().sendMessage(sb.toString());
            }

        }
    }
    public HypixelAPIInfo getFullPlayerInfo(String apiKey,String playerName){
        StringBuilder sb = new StringBuilder();
        sb.append("https://api.hypixel.net/player?key=");
        sb.append(apiKey);
        sb.append("&name=");
        sb.append(playerName);
        try {
            ACGUtil.downLoadFromUrl(sb.toString(),playerName.toUpperCase()+".json","./hypixel",token);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        HypixelAPIInfo hypixelAPIInfo = gson.fromJson( ACGUtil.readJson("./hypixel/"+playerName.toUpperCase()+".json"),HypixelAPIInfo.class);
        return hypixelAPIInfo;
    }
}
