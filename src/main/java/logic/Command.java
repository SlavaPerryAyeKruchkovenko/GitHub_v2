package logic;

public abstract class Command {
    protected final Revision revision;

    public Command(Revision revision) {
        this.revision = revision;
    }

    public abstract String getName();

    public abstract Message execute(String path);

    public static Command getCommand(String[] args, Revision verse) {
        if (args.length >= 3) {
            if (args[0].equals(new Diff(verse, verse).getName())) {
                try {
                    int num1 = Integer.decode(args[1]);
                    int num2 = Integer.decode(args[2]);
                    return new Diff(new Revision(num1), new Revision(num2));
                } catch (Exception ex) {
                    throw new RuntimeException("incorrect parameter" + ex.getMessage());
                }
            }
            throw new RuntimeException("many parameters");
        } else if (args.length == 2) {
            if (args[0].equals(new Commit(verse, args[1]).getName())) {
                return new Commit(verse, args[1]);
            } else if (args[0].equals(new Checkout(verse).getName())) {
                try {
                    int num = Integer.decode(args[1]);
                    return new Checkout(new Revision(num));
                } catch (Exception ex) {
                    throw new RuntimeException(args[1] + " isn't number");
                }
            }
        } else if (args.length == 1) {
            if (args[0].equals(new Init(verse).getName())) {
                return new Init(verse);
            } else if (args[0].equals(new Log(verse).getName())) {
                return new Log(verse);
            } else if (args[0].equals(new Status(verse).getName())) {
                return new Status(verse);
            }
        } else throw new RuntimeException("empty parameter");

        throw new RuntimeException(args[0] + " parameter not found");
    }
}
