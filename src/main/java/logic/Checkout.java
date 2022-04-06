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
                Revision currentRevision = project.getLastCommit().revision;
                //create fake commit
                currentRevision = new Commit(currentRevision, "test", this.controller)
                        .execute(this.controller.getFilePath()).getRevision();
                //backup to need revision
                CommitDate commit = project.getCommit(this.revision);
                this.controller.backup(commit);
                //get a changes
                Command cmd = new Status(currentRevision, this.controller);
                //delete fake commit
                Message msg = cmd.execute(this.controller.getFilePath());
                project.removeCommit(currentRevision);
                return new Message(this.revision, changeMistakes(msg.getText()));
            }
        }
        throw new RuntimeException("please init program");
    }

    private String changeMistakes(String text) {
        return text.replace("revision: 3", "revision: " + this.revision.getId());
    }

}
