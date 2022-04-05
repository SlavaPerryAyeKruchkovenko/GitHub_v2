package logic;

import service.*;

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
            if(project != null){
                List<CommitDate> commits = project.getCommits();

                return null;
            }
        }
        throw new RuntimeException("please init program");
    }
}
