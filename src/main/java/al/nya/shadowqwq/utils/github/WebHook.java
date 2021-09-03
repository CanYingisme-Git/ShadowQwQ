package al.nya.shadowqwq.utils.github;

import java.util.List;

public class WebHook {
    RepoInfo repository;
    Pusher pusher;
    List<Commit> commits;
    String ref;

    public RepoInfo getRepository() {
        return repository;
    }

    public Pusher getPusher() {
        return pusher;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public String getRef() {
        return ref;
    }
}
