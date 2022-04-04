package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import logic.Command;
import logic.Message;
import logic.Revision;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VersionController {
    private final String path;
    private Revision verse;
    private final String name;
    private Project project;

    public VersionController(String path, String name) {
        this.path = path;
        this.verse = new Revision(0);
        this.name = name;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        if (!project.isLegacy())
            this.project = project;
    }

    public String execute(String... args) {
        String filePath = this.path + "\\" + name + ".json";
        this.project = serializeJson(filePath);

        Command cmd = Command.getCommand(args, this.verse, this);
        Message msg = cmd.execute(filePath);

        this.verse = msg.getRevision();
        project.setRevision(verse);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(project);
            saveJson(json, filePath);
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

    private Project serializeJson(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                String json = Files.lines(Paths.get(filePath)).reduce("", String::concat);
                return mapper.readValue(json, Project.class);
            } catch (Exception e) {
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
                    files.add(new FileInfo(file.getName(), Files.readAllBytes(Paths.get(file.getName()))));
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
        return files;
    }
}
