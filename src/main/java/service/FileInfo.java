package service;

import java.util.Objects;

public class FileInfo {
    private String name;
    private byte[] data;

    public FileInfo() {

    }

    public FileInfo(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass()) {
            FileInfo file = (FileInfo) obj;
            return Objects.equals(file.name, this.name);
        }
        return false;
    }
}
