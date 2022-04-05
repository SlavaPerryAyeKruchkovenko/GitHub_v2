package service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import exstensions.ListExtensions;
import logic.Revision;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {
    private Revision currentRevision;
    private List<CommitDate> commits;

    public Project() {

    }

    public Project(Revision revision, List<CommitDate> commits) {
        this.currentRevision = revision;
        this.commits = new ArrayList<>();
        if (commits != null) {
            this.commits.addAll(commits);
        }
    }

    public boolean isLegacy() {
        return this.currentRevision.getId() < 0;
    }

    public Revision getCurrentRevision() {
        return this.currentRevision;
    }

    public List<CommitDate> getCommits() {
        return this.commits;
    }

    public void setRevision(Revision newRevision) {
        if (newRevision.getId() > 0)
            this.currentRevision = newRevision;
    }

    public CommitDate getCommit(Revision verse) {
        for (CommitDate commit : commits)
            if (commit.revision.getId() == verse.getId())
                return commit;
        throw new RuntimeException("Revision not found");
    }

    public void removeCommit(Revision verse) {
        this.commits = commits.stream()
                .filter(x -> x.revision.getId() != verse.getId())
                .collect(Collectors.toList());
    }

    public void addCommit(CommitDate commit) {
        if (commit != null && !ListExtensions.contains(commits, commit)) {
            int id = commit.revision.getId();
            this.commits = commits.stream().filter(x -> x.revision.getId() < id).collect(Collectors.toList());
            this.commits.add(commit);
        }
    }
}
