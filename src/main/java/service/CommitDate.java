package service;

import logic.Commit;
import logic.Revision;

import java.util.List;

public class CommitDate {
    private final List<FileChanges> changes;
    public final Revision revision;

    public CommitDate(List<FileChanges> changes,Revision revision){
        this.changes = changes;
        this.revision = revision;
    }
}
