package logic;

import service.*;

import java.io.File;
import java.util.List;

public class Status extends Command {

    public Status(Revision revision, VersionController controller) {
        super(revision, controller);
    }

    @Override
    public String getName() {
        return "status";
    }

    @Override
    public Message execute(String path) {
        File fileIO = new File(path);
        if (fileIO.exists() && this.controller.getProject() != null) {
            List<FileInfo> files = controller.getAllFiles();
            Project project = this.controller.getProject();
            CommitDate lessCommit = project.getCommit(this.revision);
            List<FileChanges> changes = this.controller.getChanges(lessCommit, files);
            String text = convertChanges(changes);
            return new Message(this.revision, text);
        } else {
            throw new RuntimeException("please init program");
        }
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
