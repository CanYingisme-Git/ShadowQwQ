package al.nya.shadowqwq.modules;

import al.nya.pluginextendsapi.modules.Module;
import al.nya.shadowqwq.ShadowQwQ;
import al.nya.shadowqwq.utils.ACGUtil;
import al.nya.shadowqwq.utils.MessageUtil;
import al.nya.shadowqwq.utils.github.RepoInfo;
import al.nya.shadowqwq.utils.github.SubInfo;
import com.google.gson.Gson;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.GroupMessageEvent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GithubSubscription extends Module {
    private String API ="https://api.github.com/repos/";
    private String token="v32Eo2Tw+qWI/eiKW3D8ye7l19mf1NngRLushO6CumLMHIO1aryun0/Y3N3YQCv/TqzaO/TFHw4=";
    public GithubSubscription() {
        super(ShadowQwQ.INSTANCE,"GithubSubscription");
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof GroupMessageEvent){
            String message = MessageUtil.getMessage(((GroupMessageEvent) event).getMessage());
            String[] command = message.split(" ");
            if (command.length == 3){
                if (command[0].equalsIgnoreCase("/github")){
                    if (command[1].equalsIgnoreCase("sub") || command[1].equalsIgnoreCase("subscription")){
                        new File("./github").mkdir();
                        new File("./github/subs").mkdir();
                        try {
                            ACGUtil.downLoadFromUrl(API+command[2],command[2].toUpperCase().replace("/","."),"./github/subs",token);
                        } catch (IOException e) {
                            e.printStackTrace();
                            ((GroupMessageEvent) event).getGroup().sendMessage(e.toString());
                        }
                        Gson gson = new Gson();
                        RepoInfo repoInfo = gson.fromJson(ACGUtil.readJson("./github/subs/"+command[2].toUpperCase().replace("/",".")),RepoInfo.class);
                        if (repoInfo.getMessage().equalsIgnoreCase("Not Found")){
                            ((GroupMessageEvent) event).getGroup().sendMessage("未找到库"+command[2]);
                            return;
                        }
                        new File("./github/subs/subs").mkdir();
                        if(new File("./github/subs/subs/"+command[2].toUpperCase().replace("/",".")).exists()){
                            SubInfo subInfo = gson.fromJson(ACGUtil.readJson("./github/subs/subs/"+command[2].toUpperCase().replace("/",".")),SubInfo.class);
                            for (Long subGroup : subInfo.getSubGroups()) {
                                if (subGroup == ((GroupMessageEvent) event).getGroup().getId()){
                                    ((GroupMessageEvent) event).getGroup().sendMessage("群"+((GroupMessageEvent) event).getGroup().getId()+"已经订阅了Github库:"+repoInfo.getName());
                                    return;
                                }
                            }
                            List<Long> longs = subInfo.getSubGroups();
                            longs.add(((GroupMessageEvent) event).getGroup().getId());
                            subInfo.setSubGroups(longs);
                        }else {
                            SubInfo subInfo = new SubInfo();
                            subInfo.setRepoInfo(repoInfo);
                            List<Long> longs = new ArrayList<Long>();
                            longs.add(((GroupMessageEvent) event).getGroup().getId());
                            subInfo.setSubGroups(longs);
                            try {
                                new File("./github/subs/subs/"+command[2].toUpperCase().replace("/",".")).createNewFile();
                                FileWriter fileWritter = new FileWriter("./github/subs/subs/"+command[2].toUpperCase().replace("/","."));
                                BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
                                bufferWritter.write(gson.toJson(subInfo));
                                bufferWritter.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                                ((GroupMessageEvent) event).getGroup().sendMessage(e.toString());
                            }
                        }
                        ((GroupMessageEvent) event).getGroup().sendMessage("为群"+((GroupMessageEvent) event).getGroup().getId()+"订阅了Github库:"+repoInfo.getName());
                    }else if (command[1].equalsIgnoreCase("unsub") || command[1].equalsIgnoreCase("unsubscription")){
                        Gson gson = new Gson();
                        if(new File("./github/subs/subs/"+command[2].toUpperCase().replace("/",".")).exists()){
                            SubInfo subInfo = gson.fromJson(ACGUtil.readJson("./github/subs/subs/"+command[2].toUpperCase().replace("/",".")),SubInfo.class);
                            int i = 0;
                            for (Long subGroup : subInfo.getSubGroups()) {
                                if (subGroup == ((GroupMessageEvent) event).getGroup().getId()){
                                    List<Long> groups = new ArrayList<Long>();
                                    groups.remove(i);
                                    subInfo.setSubGroups(groups);
                                    break;
                                }
                                i = i + 1;
                            }
                            if (i == (subInfo.getSubGroups().size()-1)){
                                ((GroupMessageEvent) event).getGroup().sendMessage("在订阅列表中没有:"+command[2]);
                                return;
                            }
                            try {
                                new File("./github/subs/subs/"+command[2].toUpperCase().replace("/",".")).createNewFile();
                                FileWriter fileWritter = new FileWriter("./github/subs/subs/"+command[2].toUpperCase().replace("/","."));
                                BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
                                bufferWritter.write(gson.toJson(subInfo));
                                bufferWritter.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                                ((GroupMessageEvent) event).getGroup().sendMessage(e.toString());
                            }
                            ((GroupMessageEvent) event).getGroup().sendMessage("已移除群"+((GroupMessageEvent) event).getGroup().getId()+"的Github库订阅:"+subInfo.getRepoInfo().getName());
                        }else {
                            ((GroupMessageEvent) event).getGroup().sendMessage("在订阅列表中没有:"+command[2]);
                        }
                    }
                }
            }
        }
    }
}
