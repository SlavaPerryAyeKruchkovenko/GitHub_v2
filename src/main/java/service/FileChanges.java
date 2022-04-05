package service;

public class FileChanges {
    private FileInfo file;
    private State state;

    public FileChanges() {

    }

    public FileChanges(FileInfo file, State state) {
        this.file = file;
        this.state = state;
    }

    public State getState() {
        return this.state;
    }

    public FileInfo getFile() {
        return this.file;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass().equals(this.getClass())) {
            FileInfo file = ((FileChanges) obj).getFile();
            return this.file.equals(file);
        }
        return false;
    }

    @Override
    public String toString() {
        String letter = "+ ";
        switch (this.state) {
            case created:
                letter = "+ ";
                break;
            case deleted:
                letter = "- ";
                break;
            case modified:
                letter = "* ";
                break;
            default:
                letter = "+ ";
        }
        String str = (this.state == State.deleted) ? "" : (" (" + this.file.getData().length + " bytes)");
        return this.state.name() + ":\n" + letter + this.file.getName() + str;
    }
}
