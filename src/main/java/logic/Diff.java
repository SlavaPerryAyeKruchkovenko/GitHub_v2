package logic;

import exstensions.ListExtensions;
import service.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Diff extends Command {
    private final Revision firstRevision;
    private final Revision secondRevision;

    public Diff(Revision firstRevision, Revision secondRevision, Revision revision, VersionController controller) {
        super(revision, controller);
        this.firstRevision = firstRevision;
        this.secondRevision = secondRevision;
    }

    @Override
    public String getName() {
        return "diff";
    }

    @Override
    public Message execute(String path) {
        File fileIO = new File(path);
        if (fileIO.exists() && this.controller.getProject() != null) {
            Project project = this.controller.getProject();
            if (project != null) {
                int id1 = this.firstRevision.getId();
                int id2 = this.secondRevision.getId();
                if (id2 >= id1) {
                    List<CommitDate> commits = project.getCommits().stream()
                            .filter(x -> x.revision.getId() > id1 && x.revision.getId() <= id2)
                            .collect(Collectors.toList());
                    String text = changesToString(commits);
                    return new Message(this.revision, text);
                } else {
                    throw new RuntimeException("first revision biggest second revision");
                }
            }
        }
        throw new RuntimeException("please init program");
    }

    private String changesToString(List<CommitDate> commits) {
        StringBuilder builder = new StringBuilder();
        List<FileChanges> changesCreated = new ArrayList<>();
        List<FileChanges> changesDeleted = new ArrayList<>();
        List<FileChanges> changesModified = new ArrayList<>();
        boolean haveChange = false;
        if (commits == null || commits.size() == 0) {
            return "No changes";
        } else {
            for (CommitDate commit : commits) {
                for (FileChanges changes : commit.getChanges()) {
                    if (changes.getState() == State.created) {
                        changesCreated.add(changes);
                    } else if (changes.getState() == State.deleted) {
                        changesDeleted.add(changes);
                    } else if (changes.getState() == State.modified) {
                        changesModified.add(changes);
                    }
                }
            }
            for (FileChanges changes : changesCreated) {
                if (!ListExtensions.contains(changesDeleted, changes)) {
                    haveChange = true;
                    builder.append(changes.toString() + "\n");
                } else {
                    changesDeleted = ListExtensions.remove(changesDeleted, changes);
                }
            }
            for (FileChanges changes : changesDeleted) {
                haveChange = true;
                builder.append(changes.toString() + "\n");
            }
            for (FileChanges changes : ListExtensions.distinct(changesModified)) {
                haveChange = true;
                builder.append(changes.toString() + "\n");
            }
        }
        return haveChange ? builder.toString() : "No changes";
    }
}
