package service;

public class FileChanges {
    private FileInfo file;
    private State state;
    public FileChanges(){

    }
    public FileChanges(FileInfo file,State state){
        this.file = file;
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public FileInfo getFile() {
        return file;
    }
}
