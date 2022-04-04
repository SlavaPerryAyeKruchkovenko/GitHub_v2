package service;

import logic.Revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Project {
    private Revision currentRevision;
    private final List<CommitDate> commits;

    public Project(Revision revision, CommitDate... commits) {
        this.currentRevision = revision;
        this.commits = new ArrayList<>();
        if (commits != null) {
            this.commits.addAll(Arrays.asList(commits));
        }
    }

    public boolean isLegacy() {
        return this.currentRevision.id < 0;
    }

    public Revision getCurrentRevision() {
        return this.currentRevision;
    }

    public List<CommitDate> getCommits() {
        return this.commits;
    }

    public void setRevision(Revision newRevision) {
        if (newRevision.id > 0)
            this.currentRevision = newRevision;
    }

    public CommitDate getCommit(Revision verse) {
        for (CommitDate commit : commits)
            if (commit.revision.id == verse.id)
                return commit;
        throw new RuntimeException("Revision not found");
    }
}
