package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exstensions.ListExtensions;
import logic.Command;
import logic.Message;
import logic.Revision;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class VersionController {
    private final String path;
    private Revision verse;
    private final String name;
    private Project project;
    private final String[] forbiddenNames;
    private final String filePath;

    public VersionController(String path, String name) {
        this.path = path;
        this.verse = new Revision(0);
        this.name = name;
        this.forbiddenNames = new String[]{".jar", name + ".json"};
        this.filePath = this.path + "\\" + name + ".json";
    }

    public String getFilePath() {
        return this.filePath;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        if (!project.isLegacy())
            this.project = project;
    }

    public String execute(String... args) {
        String[] corArgs = Arrays.stream(args).map(x -> x.toLowerCase(Locale.ROOT)).toArray(String[]::new);
        this.project = deserializeJson(this.filePath);
        updateVerse();

        Command cmd = Command.getCommand(corArgs, this.verse, this);
        Message msg = cmd.execute(this.filePath);

        this.verse = msg.getRevision();
        this.project.setRevision(this.verse);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(this.project);
            saveJson(json, this.filePath);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return msg.getText();
    }

    private void saveJson(String value, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(value);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    private Project deserializeJson(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                String json = Files.lines(Paths.get(filePath)).reduce("", String::concat);
                return mapper.readValue(json, Project.class);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }

        }
        return null;
    }

    public List<FileInfo> getAllFiles() {
        List<FileInfo> files = new ArrayList<>();
        File dir = new File(path);
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isFile()) {
                try {
                    if (!file.getName().contains(forbiddenNames[0]) && !file.getName().contains(forbiddenNames[1]))
                        files.add(new FileInfo(file.getName(), Files.readAllBytes(Paths.get(file.getName()))));
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
        return files;
    }

    public List<FileChanges> getChanges(CommitDate lessCommit, List<FileInfo> files) {
        List<FileChanges> changes = new ArrayList<>();
        for (FileInfo file : lessCommit.getLessFiles()) {
            if (ListExtensions.contains(files, file)) {
                int index = files.indexOf(file);
                if (index != -1) {
                    FileInfo lessfile = files.get(index);
                    if (!Arrays.equals(lessfile.getData(), file.getData()))
                        changes.add(new FileChanges(file, State.modified));
                }
            } else {
                changes.add(new FileChanges(file, State.deleted));
            }
        }
        for (FileInfo file : files)
            if (!ListExtensions.contains(lessCommit.getLessFiles(), file))
                changes.add(new FileChanges(file, State.created));
        return changes;
    }

    public void backup(CommitDate commit) {
        List<FileInfo> newFiles = commit.getLessFiles();
        List<FileInfo> oldFiles = getAllFiles();
        for (FileInfo file : newFiles) {
            File file1 = new File(file.getName());
            // change file's data
            if (ListExtensions.contains(oldFiles, file) && file1.exists()) {
                if (file1.canWrite() && file1.delete()) {
                    crateFile(file1, file);
                }
            }
            // create files
            else if (!file1.exists()) {
                crateFile(file1, file);
            }
        }
        for (FileInfo file : oldFiles) {
            File file1 = new File(file.getName());
            // delete files
            if (!ListExtensions.contains(newFiles, file) && file1.exists()) {
                file1.delete();
            }
        }
    }

    private void crateFile(File file1, FileInfo file) {
        try {
            if (file1.createNewFile()) {
                try (FileOutputStream fos = new FileOutputStream(file1.getName())) {
                    fos.write(file.getData());
                } catch (Exception ex) {
                    throw new RuntimeException(ex.getMessage());
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    private void updateVerse() {
        if (project != null) {
            this.verse = this.project.getCurrentRevision();
        }
    }
}
