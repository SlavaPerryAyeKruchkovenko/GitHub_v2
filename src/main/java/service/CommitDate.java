package service;

import logic.Revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommitDate {
    private final List<FileChanges> changes;
    public final Revision revision;
    private final List<FileInfo> lessFiles;

    public CommitDate(List<FileChanges> changes, Revision revision, FileInfo... lessFiles) {
        this.changes = changes;
        this.revision = revision;
        this.lessFiles = new ArrayList<>();
        if (lessFiles != null)
            this.lessFiles.addAll(Arrays.asList(lessFiles));
    }

    public List<FileInfo> getLessFiles() {
        return this.lessFiles;
    }

    public Revision getRevision() {
        return this.revision;
    }

    public List<FileChanges> getChanges() {
        return this.changes;
    }
}
