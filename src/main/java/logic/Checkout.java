package logic;

import service.CommitDate;
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
                List<CommitDate> commits = project.getCommits();
                String text = commitsToString(commits);
                return new Message(this.revision, text);
            }
        }
        throw new RuntimeException("please init program");
    }
}
