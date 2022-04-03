package logic;

public class Init extends Command{

    public Init(Revision revision) {
        super(revision);
    }

    @Override
    public String getName() {
        return "init";
    }

    @Override
    public Message execute(String path) {
        return null;
    }
}
