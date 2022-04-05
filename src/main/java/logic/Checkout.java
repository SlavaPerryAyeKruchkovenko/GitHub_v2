package logic;

import service.CommitDate;
import service.Project;
import service.VersionController;

import java.io.File;

public class Checkout extends Command {

    public Checkout(Revision revision, VersionController controller) {
        super(revision, controller);
    }

    @Override
    public String getName() {
        return "checkout";
    }

    @Override
    public Message execute(String path) {
        File fileIO = new File(path);
        if (fileIO.exists() && this.controller.getProject() != null) {
            Project project = this.controller.getProject();
            if (project != null) {
                Revision currentRevision = project.getCurrentRevision();
                //create fake commit
                Message msg = new Commit(currentRevision, "test", this.controller)
                        .execute(this.controller.getFilePath());
                //backup to need revision
                CommitDate commit = project.getCommit(this.revision);
                this.controller.backup(commit);
                //get a changes
                Command cmd = new Status(msg.getRevision(), this.controller);
                //delete fake commit
                project.removeCommit(msg.getRevision());
                return cmd.execute(this.controller.getFilePath());
            }
        }
        throw new RuntimeException("please init program");
    }

}
