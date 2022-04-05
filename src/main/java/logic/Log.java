package logic;

import service.CommitDate;
import service.Project;
import service.VersionController;

import java.io.File;
import java.util.List;

public class Log extends Command {
    public Log(Revision revision, VersionController controller) {
        super(revision, controller);
    }

    @Override
    public String getName() {
        return "log";
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

    private String commitsToString(List<CommitDate> commits) {
        StringBuilder builder = new StringBuilder();
        if (commits == null)
            return "No change";
        else
            for (CommitDate commit : commits)
                if (commit.revision.getId() != 0)
                    builder.append(commit.toString() + "\n\n");
        return builder.toString();
    }
}
