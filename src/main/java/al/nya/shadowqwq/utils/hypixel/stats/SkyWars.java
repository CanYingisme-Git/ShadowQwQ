package al.nya.shadowqwq.utils.hypixel.stats;

public class SkyWars {
    private int deaths;
    private int kills;
    private int souls;
    private int egg_thrown;
    private int wins;
    public SkyWars(int deaths,int kills,int souls,int egg_thrown,int wins){
        this.deaths = deaths;
        this.egg_thrown = egg_thrown;
        this.kills = kills;
        this.souls = souls;
        this.wins = wins;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getEgg_thrown() {
        return egg_thrown;
    }

    public int getKills() {
        return kills;
    }

    public int getSouls() {
        return souls;
    }

    public int getWins() {
        return wins;
    }
}
