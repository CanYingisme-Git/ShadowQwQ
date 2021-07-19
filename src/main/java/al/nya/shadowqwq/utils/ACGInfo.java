package al.nya.shadowqwq.utils;

public class ACGInfo {
    private int error;
    private int result;
    private String img;
    public ACGInfo(int error,int result,String img){
        this.error = error;
        this.img = img;
        this.result = result;
    }

    public int getError() {
        return error;
    }

    public int getResult() {
        return result;
    }

    public String getImg() {
        return img;
    }
}
