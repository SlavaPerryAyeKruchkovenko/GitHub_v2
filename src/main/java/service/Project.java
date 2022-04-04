package service;

import logic.Revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Project {
    private Revision currentRevision;
    private final List<CommitDate> commits;
    private final List<FileInfo> lessFiles;

    public Project(Revision revision, List<FileInfo> files, CommitDate... commits) {
        this.currentRevision = revision;
        this.commits = new ArrayList<>();
        if (commits != null) {
            this.commits.addAll(Arrays.asList(commits));
        }
        this.lessFiles = new ArrayList<>();
        if (lessFiles != null) {
            this.lessFiles.addAll(files);
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

    public List<FileInfo> getLessFiles() {
        return lessFiles;
    }

    public void setRevision(Revision newRevision) {
        if (newRevision.id > 0)
            this.currentRevision = newRevision;
    }
}
