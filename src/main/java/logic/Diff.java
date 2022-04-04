package logic;

import service.VersionController;

public class Diff extends Command {
    private final Revision firstRevision;
    private final Revision secondRevision;

    public Diff(Revision firstRevision, Revision secondRevision, VersionController controller) {
        super(secondRevision, controller);
        this.firstRevision = firstRevision;
        this.secondRevision = secondRevision;
    }

    @Override
    public String getName() {
        return "diff";
    }

    @Override
    public Message execute(String path) {
        return null;
    }
}
