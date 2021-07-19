package al.nya.shadowqwq.utils.hypixel;

import al.nya.shadowqwq.ShadowQwQ;

import java.util.List;
import java.util.Map;

public class HypixelPlayerInfo {
    private  String _id;
    private Map<String, Long> achievements;
    private List<String> achievementsOneTime;
    private String displayname;
    private long firstLogin;
    private long lastLogin;
    private long networkExp;
    private HypixelStatsInfo stats;

    public HypixelPlayerInfo(String _id, Map<String, Long> achievements, List<String> achievementsOneTime, String displayname
                            , long firstLogin, long lastLogin, long networkExp, HypixelStatsInfo stats){
        this._id = _id;
        this.achievements = achievements;
        this.achievementsOneTime = achievementsOneTime;
        this.displayname = displayname;
        this.firstLogin = firstLogin;
        this.lastLogin = lastLogin;
        this.networkExp = networkExp;
        this.stats = stats;
    }

    public HypixelStatsInfo getStats() {
        return stats;
    }

    public List<String> getAchievementsOneTime() {
        return achievementsOneTime;
    }

    public long getFirstLogin() {
        return firstLogin;
    }

    public long getLastLogin() {
        return lastLogin;
    }

    public long getNetworkExp() {
        return networkExp;
    }

    public Map<String, Long> getAchievements() {
        return achievements;
    }

    public String get_id() {
        return _id;
    }

    public String getDisplayname() {
        return displayname;
    }
}
