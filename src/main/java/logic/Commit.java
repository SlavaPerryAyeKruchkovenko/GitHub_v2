package logic;

import service.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Commit extends Command {
    private final String message;

    public Commit(Revision revision, String name, VersionController controller) {
        super(revision, controller);
        this.message = name;
    }

    @Override
    public String getName() {
        return "command";
    }

    @Override
    public Message execute(String path) {
        File fileIO = new File(path);
        if (fileIO.exists() && this.controller.getProject() != null) {
            List<FileInfo> files = controller.getAllFiles();
            Project project = this.controller.getProject();
            CommitDate lessCommit = project.getCommit(this.revision);
            List<FileChanges> changes = getChanges(lessCommit, files);
            Revision newVerse = new Revision(this.revision.id + 1);
            CommitDate commit = new CommitDate(changes, newVerse, files, this.message);
            return new Message(newVerse, "Created Revision " + newVerse.id);
        } else {
            throw new RuntimeException("please init program");
        }
    }

    private List<FileChanges> getChanges(CommitDate lessCommit, List<FileInfo> files) {
        List<FileChanges> changes = new ArrayList<>();
        for (FileInfo file : lessCommit.getLessFiles()) {
            if (files.contains(file)) {
                int index = files.indexOf(file);
                if (index != -1) {
                    FileInfo lessfile = files.get(index);
                    if (!Arrays.equals(lessfile.data, file.data)) {
                        changes.add(new FileChanges(file, State.update));
                    }
                }
            } else {
                changes.add(new FileChanges(file, State.delete));
            }
        }
        for (FileInfo file : files) {
            if (!lessCommit.getLessFiles().contains(file)) {
                changes.add(new FileChanges(file, State.add));
            }
        }
        return changes;
    }
}
