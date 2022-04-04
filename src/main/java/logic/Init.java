package logic;

import service.CommitDate;
import service.FileInfo;
import service.Project;
import service.VersionController;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Init extends Command {

    public Init(Revision revision, VersionController controller) {
        super(revision, controller);
    }

    @Override
    public String getName() {
        return "init";
    }

    @Override
    public Message execute(String path) {
        File file = new File(path);
        if (file.exists()) {
            throw new RuntimeException("file exist");
        } else {
            try {
                List<FileInfo> files = controller.getAllFiles();
                if (file.createNewFile()) {
                    Revision revision = new Revision(0);
                    CommitDate commit = new CommitDate(null, revision, files, this.getName());
                    Project project = new Project(revision, commit);
                    controller.setProject(project);
                    return new Message(new Revision(0), "Initialized");
                }
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        throw new RuntimeException("inccorect file");
    }
}
