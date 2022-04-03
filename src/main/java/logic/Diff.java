package logic;

public class Diff extends Command {
    private final Revision firstRevision;
    private final Revision secondRevision;

    public Diff(Revision firstRevision, Revision secondRevision) {
        super(secondRevision);
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
