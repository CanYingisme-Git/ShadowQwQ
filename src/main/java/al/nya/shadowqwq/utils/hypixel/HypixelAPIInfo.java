package al.nya.shadowqwq.utils.hypixel;

public class HypixelAPIInfo {
    private boolean success;
    private HypixelPlayerInfo player;
    private String cause;
    private boolean temp = false;
    public HypixelAPIInfo(boolean success,HypixelPlayerInfo player){
        this.success = success;
        this.player = player;
    }
    public HypixelAPIInfo(boolean success,String cause){
        this.success = success;
        this.cause = cause;
    }

    public HypixelPlayerInfo getPlayerInfo() {
        return player;
    }

    public String getCause() {
        return cause;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isTemp() {
        return temp;
    }

    public void setTemp(boolean temp) {
        this.temp = temp;
    }
}
