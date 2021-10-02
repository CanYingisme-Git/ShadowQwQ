package al.nya.shadowqwq.utils.acg;

import java.util.List;

public class ACGInfo {
    private int status;
    private boolean more;
    private List<ImgInfo> list;
    private ImgData data;

    public List<ImgInfo> getList() {
        return list;
    }

    public int getStatus() {
        return status;
    }

    public boolean isMore() {
        return more;
    }

    public ImgData getData() {
        return data;
    }
}
