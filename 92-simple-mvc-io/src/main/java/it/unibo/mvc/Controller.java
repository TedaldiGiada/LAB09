package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Controller {
    private File currentFile;
    public Controller() {
        currentFile = new File(System.getProperty("user.home") + 
            System.getProperty("file.separator") + System.getProperty("output.txt"));
    }
    public void setCurrentFile(File file) {
        if(file == null) {
            throw new IllegalArgumentException("The file is Empty.\n");
        } else {
            currentFile = file;
        }
    }
    public File getCurrentFile() {
        return currentFile;
    }
    public String getPathCurrentFile() {
        return currentFile.getPath();
    }
    public void saveFile(String text) throws IOException{
        try(PrintStream ps = new PrintStream(currentFile, StandardCharsets.UTF_8)) {
            ps.print(text);
        }
    }
}

