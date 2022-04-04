package service;

public class File {
    public final String name;
    public final Byte[] data;

    public File(String name, Byte[] data) {
        this.name = name;
        this.data = data;
    }
}
