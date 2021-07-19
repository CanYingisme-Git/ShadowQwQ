package al.nya.shadowqwq.utils.github;

import java.util.List;

public class SubInfo {
    private RepoInfo repoInfo;
    private List<Long> subGroups;

    public List<Long> getSubGroups() {
        return subGroups;
    }

    public RepoInfo getRepoInfo() {
        return repoInfo;
    }

    public void setRepoInfo(RepoInfo repoInfo) {
        this.repoInfo = repoInfo;
    }

    public void setSubGroups(List<Long> subGroups) {
        this.subGroups = subGroups;
    }
}
