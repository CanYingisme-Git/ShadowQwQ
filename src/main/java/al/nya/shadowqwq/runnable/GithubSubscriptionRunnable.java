package al.nya.shadowqwq.runnable;

import al.nya.shadowqwq.ShadowQwQ;
import al.nya.shadowqwq.utils.ACGUtil;
import al.nya.shadowqwq.utils.github.RepoInfo;
import al.nya.shadowqwq.utils.github.SubInfo;
import com.google.gson.Gson;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.Mirai;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class GithubSubscriptionRunnable implements Runnable {
    private String API ="https://api.github.com/repos/";
    private String token="v32Eo2Tw+qWI/eiKW3D8ye7l19mf1NngRLushO6CumLMHIO1aryun0/Y3N3YQCv/TqzaO/TFHw4=";
    public static boolean waitForModule = false;
    @Override
    public void run() {
        if (!waitForModule){
            File subs = new File("./github/subs/subs");
            for (File file : subs.listFiles()) {
                if(!file.isDirectory()){
                    try {
                        ACGUtil.downLoadFromUrl(API+ file.getName().replace(".","/"),file.getName(),"./github/subs",token);
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                    Gson gson = new Gson();
                    RepoInfo repoInfo = gson.fromJson(ACGUtil.readJson("./github/subs/"+file.getName()),RepoInfo.class);
                    SubInfo subInfo = gson.fromJson(file.getPath(),SubInfo.class);
                    if (!subInfo.getRepoInfo().getPushed_at().equalsIgnoreCase(repoInfo.getPushed_at())){
                        subInfo.setRepoInfo(repoInfo);
                        StringBuilder sb = new StringBuilder();
                        sb.append("您订阅的Github仓库");
                        sb.append(subInfo.getRepoInfo().getName());
                        sb.append("在");
                        sb.append(subInfo.getRepoInfo().getPushed_at()+"提交了新的push");
                        ShadowQwQ.INSTANCE.getLogger().info(sb.toString());
                        for (Bot instance : Bot.getInstances()) {
                            for (Long subGroup : subInfo.getSubGroups()) {
                                instance.getGroup(subGroup).sendMessage(sb.toString());
                            }
                        }
                    }
                }
            }
        }
    }
}
