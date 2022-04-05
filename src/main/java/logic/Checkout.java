package logic;

import service.CommitDate;
import service.FileChanges;
import service.Project;
import service.VersionController;

import java.io.File;
import java.util.List;

public class Checkout extends Command {

    public Checkout(Revision revision, VersionController controller) {
        super(revision, controller);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Message execute(String path) {
        File fileIO = new File(path);
        if (fileIO.exists() && this.controller.getProject() != null) {
            Project project = this.controller.getProject();
            if (project != null) {
                CommitDate commit = project.getCommit(this.revision);
                String text = convertChanges(commit.getChanges());
                this.controller.backup(commit);
                return new Message(this.revision, text);
            }
        }
        throw new RuntimeException("please init program");
    }

    private String convertChanges(List<FileChanges> changes) {
        StringBuilder builder = new StringBuilder();
        if (changes == null || changes.size() == 0)
            return "No changes";
        else
            for (FileChanges change : changes)
                builder.append(change.toString());
        return builder.toString();
    }
}
