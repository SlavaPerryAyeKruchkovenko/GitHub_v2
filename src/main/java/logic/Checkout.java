package logic;

public class Checkout extends Command {

    public Checkout(Revision revision) {
        super(revision);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Message execute(String path) {
        return new Message(this.revision,"Initialized");
    }
}
