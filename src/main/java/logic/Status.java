package logic;

public class Status extends Command {

    public Status(Revision revision) {
        super(revision);
    }

    @Override
    public String getName() {
        return "status";
    }

    @Override
    public Message execute(String path) {
        return null;
    }
}
