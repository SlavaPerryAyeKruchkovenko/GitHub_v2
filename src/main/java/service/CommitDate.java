package service;

import logic.Revision;

import java.util.ArrayList;
import java.util.List;

public class CommitDate {
    private final List<FileChanges> changes;
    public final Revision revision;
    private final List<FileInfo> lessFiles;
    private final String comment;

    public CommitDate(List<FileChanges> changes, Revision revision, List<FileInfo> lessFiles, String comment) {
        this.changes = new ArrayList<>();
        if (changes != null)
            this.changes.addAll(changes);
        this.revision = revision;
        this.lessFiles = new ArrayList<>();
        if (lessFiles != null)
            this.lessFiles.addAll(lessFiles);
        this.comment = comment;
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

    public String getComment() {
        return this.comment;
    }
}
