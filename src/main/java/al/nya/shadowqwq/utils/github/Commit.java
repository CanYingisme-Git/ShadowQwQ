package al.nya.shadowqwq.utils.github;

public class Commit {
    String message;
    String id;
    Committer committer;

    public String getMessage() {
        return message;
    }

    public String getId() {
        return id;
    }

    public Committer getCommitter() {
        return committer;
    }
}
