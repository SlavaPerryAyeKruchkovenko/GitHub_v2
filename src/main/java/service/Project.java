package service;

import logic.Revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Project {
    private Revision currentRevision;
    private final List<CommitDate> commits;
    private final List<File> lessFiles;

    public Project(Revision revision,File[] files, CommitDate... commits) {
        this.currentRevision = revision;
        this.commits = new ArrayList<>();
        this.commits.addAll(Arrays.asList(commits));
        this.lessFiles = new ArrayList<>();
        this.lessFiles.addAll(Arrays.asList(files));
    }

    public Revision getCurrentRevision() {
        return this.currentRevision;
    }

    public void setRevision(Revision newRevision) {
        if (newRevision.id > 0)
            this.currentRevision = newRevision;
    }
}
