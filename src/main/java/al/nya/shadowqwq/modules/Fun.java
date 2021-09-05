package al.nya.shadowqwq.modules;

import al.nya.pluginextendsapi.modules.Module;
import al.nya.pluginextendsapi.modules.ModuleManager;
import al.nya.shadowqwq.ShadowQwQ;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.GroupMessageEvent;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Fun extends Module {
    public Fun() {
        super(ShadowQwQ.INSTANCE,"Fun");
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof GroupMessageEvent){
            if (((GroupMessageEvent) event).getMessage().get(1).toString().split("")[0].equals("/")) {
                GroupControl groupControl = (GroupControl) ModuleManager.getModule("GroupControl");
                if (!groupControl.isEnableGroup(((GroupMessageEvent) event).getGroup(),this)){
                    return;
                }
                String[] command = ((GroupMessageEvent) event).getMessage().get(1).toString().split(" ");
                if (command.length == 3){
                    if (command[0].equalsIgnoreCase("/bmi")){
                        double height;
                        double weight;
                        try{
                            height = Double.parseDouble(command[1]);
                            height = height / 100;
                            weight = Double.parseDouble(command[2]);
                            height *= height;
                            double bmi = weight/height;
                            double truncatedDouble = BigDecimal.valueOf(bmi)
                                    .setScale(1, RoundingMode.HALF_UP)
                                    .doubleValue();
                            ((GroupMessageEvent) event).getGroup().sendMessage("你的BMI指数为:"+ truncatedDouble +"使用 /bmi [身高(cm)] [体重] full 获得分析数据");
                        }catch (Exception e){
                            e.printStackTrace();
                            ((GroupMessageEvent) event).getGroup().sendMessage(e.getMessage());
                        }
                    }
                }else if (command.length == 4){
                    if (command[0].equalsIgnoreCase("/bmi")){
                        double height;
                        double weight;
                        try{
                            height = Double.parseDouble(command[1]);
                            height = height / 100;
                            weight = Double.parseDouble(command[2]);
                            height *= height;
                            double bmi = weight/height;
                            double truncatedDouble = BigDecimal.valueOf(bmi)
                                    .setScale(1, RoundingMode.HALF_UP)
                                    .doubleValue();
                            if (command[3].equalsIgnoreCase("full")){
                                ((GroupMessageEvent) event).getGroup().sendMessage("你的BMI指数为:"+ truncatedDouble +"("+getBMIType(bmi)+")");
                            }else {
                                ((GroupMessageEvent) event).getGroup().sendMessage("你的BMI指数为:"+ truncatedDouble +"使用 /bmi [身高(cm)] [体重] full 获得分析数据");
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            ((GroupMessageEvent) event).getGroup().sendMessage(e.getMessage());
                        }
                    }
                }
            }
        }
    }
    public String getBMIType(double bmi){
        if (bmi < 18.5){
            return "体重过轻";
        }else if (bmi >= 18.5 && bmi < 24){
            return "体重正常";
        }else if (bmi >= 24 && bmi <27){
            return "过重";
        }else if (bmi >= 27 && bmi <30){
            return "轻度肥胖";
        }else if (bmi >= 30 && bmi <35){
            return "中度肥胖";
        }else if (bmi >= 35){
            return "重度肥胖";
        }
        return "?";
    }
}
