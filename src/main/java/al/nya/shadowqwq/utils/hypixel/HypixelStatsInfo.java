package al.nya.shadowqwq.utils.hypixel;

import al.nya.shadowqwq.utils.hypixel.stats.BedWars;
import al.nya.shadowqwq.utils.hypixel.stats.SkyWars;

public class HypixelStatsInfo {
    private SkyWars SkyWars;
    private BedWars Bedwars;
    private long karma;
    public HypixelStatsInfo(SkyWars SkyWars){
        this.SkyWars = SkyWars;
    }

    public SkyWars getSkyWars() {
        return SkyWars;
    }

    public BedWars getBedwars() {
        return Bedwars;
    }

    public long getKarma() {
        return karma;
    }
}
