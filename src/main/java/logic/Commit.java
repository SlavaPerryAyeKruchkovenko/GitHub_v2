package logic;

import service.*;

import java.io.File;
import java.util.List;

public class Commit extends Command {
    private final String message;

    public Commit(Revision revision, String name, VersionController controller) {
        super(revision, controller);
        this.message = name;
    }

    @Override
    public String getName() {
        return "commit";
    }

    @Override
    public Message execute(String path) {
        File fileIO = new File(path);
        if (fileIO.exists() && this.controller.getProject() != null) {
            List<FileInfo> files = controller.getAllFiles();
            Project project = this.controller.getProject();
            CommitDate lessCommit = project.getCommit(this.revision);
            List<FileChanges> changes = this.controller.getChanges(lessCommit, files);
            Revision newVerse = new Revision(this.revision.getId() + 1);
            CommitDate commit = new CommitDate(changes, newVerse, files, this.message);
            project.addCommit(commit);
            return new Message(newVerse, "Created Revision " + newVerse.getId());
        } else {
            throw new RuntimeException("please init program");
        }
    }


}
