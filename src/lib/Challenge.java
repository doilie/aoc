package lib;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class Challenge {
    private String fileContents = "";

    protected Challenge(String inputFileName) {
        try {
            this.fileContents = this.readFileAsString(inputFileName);
        } catch (IOException e) {
            System.err.println("Exception: " + e);
        }
    }

    private String readFileAsString(String fileName)
            throws IOException
    {
        final String inputFileLocation = "resources\\";
        return new String(Files.readAllBytes(Paths.get(inputFileLocation + fileName)));
    }

    protected String getFileContents() {
        return fileContents;
    }

    protected abstract void parseFile();
    public abstract void doOneStarSolution();
    public abstract void doTwoStarSolution();
}
