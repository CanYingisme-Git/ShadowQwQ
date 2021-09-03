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

    }
}
