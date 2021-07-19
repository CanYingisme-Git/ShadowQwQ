package al.nya.shadowqwq.utils.github;

import java.util.List;

public class RepoInfo {
    private String message = "";
    private String name;
    private String pushed_at;
    private List<Long> subGroups;

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public String getPushed_at() {
        return pushed_at;
    }
}
