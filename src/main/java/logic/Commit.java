package logic;

import service.FileInfo;
import service.Project;
import service.VersionController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Commit extends Command {
    private final String message;

    public Commit(Revision revision, String name, VersionController controller) {
        super(revision,controller);
        this.message = name;
    }

    @Override
    public String getName() {
        return "command";
    }

    @Override
    public Message execute(String path) {
        File fileIO = new File(path);
        if(fileIO.exists() && this.controller.getProject() != null){
            List<FileInfo> files = controller.getAllFiles();
            List<Commit> commits = new ArrayList<>();
            Project project = this.controller.getProject();

        }
        else{
            throw new RuntimeException("please init program");
        }
        return null;
    }
}
