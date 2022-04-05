package logic;

import service.VersionController;

public abstract class Command {
    protected final Revision revision;
    protected final VersionController controller;

    public Command(Revision revision, VersionController controller) {
        this.revision = revision;
        this.controller = controller;
    }

    public abstract String getName();

    public abstract Message execute(String path);

    public static Command getCommand(String[] args, Revision verse, VersionController controller) {
        if (args.length >= 3) {
            if (args[0].equals(new Diff(verse, verse, controller).getName())) {
                try {
                    int num1 = Integer.decode(args[1]);
                    int num2 = Integer.decode(args[2]);
                    return new Diff(new Revision(num1), new Revision(num2), controller);
                } catch (Exception ex) {
                    throw new RuntimeException("incorrect parameter" + ex.getMessage());
                }
            }
            throw new RuntimeException("many parameters");
        } else if (args.length == 2) {
            if (args[0].equals(new Commit(verse, args[1], controller).getName())) {
                return new Commit(verse, args[1], controller);
            } else if (args[0].equals(new Checkout(verse, controller).getName())) {
                try {
                    int num = Integer.decode(args[1]);
                    return new Checkout(new Revision(num), controller);
                } catch (Exception ex) {
                    throw new RuntimeException(args[1] + " isn't number");
                }
            }
        } else if (args.length == 1) {
            if (args[0].equals(new Init(verse, controller).getName()))
                return new Init(verse, controller);
            else if (args[0].equals(new Log(verse, controller).getName()))
                return new Log(verse, controller);
             else if (args[0].equals(new Status(verse, controller).getName()))
                return new Status(verse, controller);

        } else throw new RuntimeException("empty parameter");

        throw new RuntimeException(args[0] + " parameter not found");
    }
}
