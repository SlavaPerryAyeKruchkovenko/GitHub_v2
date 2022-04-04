package service;

import logic.Revision;

import java.util.ArrayList;
import java.util.List;

public class CommitDate {
    private List<FileChanges> changes;
    public Revision revision;
    private List<FileInfo> lessFiles;
    private String comment;

    public CommitDate(){

    }
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
