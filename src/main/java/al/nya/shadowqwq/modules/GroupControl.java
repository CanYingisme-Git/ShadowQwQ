package al.nya.shadowqwq.modules;

import al.nya.pluginextendsapi.modules.Module;
import al.nya.pluginextendsapi.modules.ModuleManager;
import al.nya.pluginextendsapi.modules.Priority;
import al.nya.shadowqwq.ShadowQwQ;
import al.nya.shadowqwq.utils.*;
import com.google.gson.Gson;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.GroupMessageEvent;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class GroupControl extends Module {
    public GroupControl() {
        super(ShadowQwQ.INSTANCE,"GroupControl", Priority.High);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof GroupMessageEvent){
            String message = MessageUtil.getMessage(((GroupMessageEvent) event).getMessage());
            String[] command = message.split(" ");
            if (command.length >= 2){
                for (Module module : ModuleManager.getModules()) {
                    if (command[0].equalsIgnoreCase("/"+module.getName())){
                        if (command[1].equalsIgnoreCase("disable")){
                            try {
                                disable(module,((GroupMessageEvent) event).getGroup());
                            } catch (IOException e) {
                                e.printStackTrace();
                                ((GroupMessageEvent) event).getGroup().sendMessage(e.toString());
                            }finally {
                                ((GroupMessageEvent) event).getGroup().sendMessage("已将群"+((GroupMessageEvent) event).getGroup().getId()+"的模块"+module.getName()+"状态设置为disable");
                            }
                        }else if (command[1].equalsIgnoreCase("enable")){
                            try {
                                enable(module,((GroupMessageEvent) event).getGroup());
                            } catch (IOException e) {
                                e.printStackTrace();
                                ((GroupMessageEvent) event).getGroup().sendMessage(e.toString());
                            }finally {
                                ((GroupMessageEvent) event).getGroup().sendMessage("已将群"+((GroupMessageEvent) event).getGroup().getId()+"的模块"+module.getName()+"状态设置为disable");
                            }
                        }
                    }
               }
            }
        }
    }
    public void disable(Module module,Group g) throws IOException {
        File file = new File("./modules.json");
        if (file.exists()){
            Modules modules = new Gson().fromJson(ACGUtil.readJson(file.getPath()),Modules.class);
            for (ModuleChild moduleChild : modules.modules) {
                if (moduleChild.name.equals(module.getName())){
                    for (ModuleGroup group : moduleChild.groups) {
                        if (group.id == g.getId()){
                            group.enable = false;
                            save(modules);
                            return;
                        }
                    }
                    ModuleGroup mg = new ModuleGroup();
                    mg.id = g.getId();
                    mg.enable = false;
                    moduleChild.groups.add(mg);
                    save(modules);
                    return;
                }
            }
            ModuleChild mc = new ModuleChild();
            mc.groups = new ArrayList<ModuleGroup>();
            mc.name = module.getName();
            ModuleGroup mg = new ModuleGroup();
            mg.id = g.getId();
            mg.enable = false;
            mc.groups.add(mg);
            modules.modules.add(mc);
            save(modules);
        }else {
            Modules modules = new Modules();
            ModuleChild mc = new ModuleChild();
            mc.groups = new ArrayList<ModuleGroup>();
            mc.name = module.getName();
            ModuleGroup mg = new ModuleGroup();
            mg.id = g.getId();
            mg.enable = false;
            mc.groups.add(mg);
            modules.modules = new ArrayList<ModuleChild>();
            modules.modules.add(mc);
            save(modules);
        }
    }
    public void enable(Module module,Group g) throws IOException {
        File file = new File("./modules.json");
        if (file.exists()){
            Modules modules = new Gson().fromJson(ACGUtil.readJson(file.getPath()),Modules.class);
            for (ModuleChild moduleChild : modules.modules) {
                if (moduleChild.name.equals(module.getName())){
                    for (ModuleGroup group : moduleChild.groups) {
                        if (group.id == g.getId()){
                            group.enable = false;
                            save(modules);
                            return;
                        }
                    }
                    ModuleGroup mg = new ModuleGroup();
                    mg.id = g.getId();
                    mg.enable = true;
                    moduleChild.groups.add(mg);
                    save(modules);
                    return;
                }
            }
            ModuleChild mc = new ModuleChild();
            mc.groups = new ArrayList<ModuleGroup>();
            mc.name = module.getName();
            ModuleGroup mg = new ModuleGroup();
            mg.id = g.getId();
            mg.enable = true;
            mc.groups.add(mg);
            modules.modules.add(mc);
            save(modules);
        }else {
            Modules modules = new Modules();
            ModuleChild mc = new ModuleChild();
            mc.groups = new ArrayList<ModuleGroup>();
            mc.name = module.getName();
            ModuleGroup mg = new ModuleGroup();
            mg.id = g.getId();
            mg.enable = true;
            mc.groups.add(mg);
            modules.modules = new ArrayList<ModuleChild>();
            modules.modules.add(mc);
            save(modules);
        }
    }
    public void save(Modules modules) throws IOException {
        File fileText = new File("./modules.json");
        // 向文件写入对象写入信息
        FileWriter fileWriter = new FileWriter(fileText);

        // 写文件
        fileWriter.write(new Gson().toJson(modules));
        // 关闭
        fileWriter.close();

    }
    public boolean isEnableGroup(Group group,Module module){
        if (!this.isEnable()) return true;
        File file = new File("./modules.json");
        if (file.exists()){
            Modules modules = new Gson().fromJson(ACGUtil.readJson(file.getPath()),Modules.class);
            for (ModuleChild moduleChild : modules.modules) {
                if(moduleChild.name.equals(module.getName())){
                    for (ModuleGroup moduleGroup : moduleChild.groups) {
                        if (moduleGroup.id == group.getId()){
                            return moduleGroup.enable;
                        }
                    }
                }
            }
            return true;
        }else {
            return true;
        }
    }
}
