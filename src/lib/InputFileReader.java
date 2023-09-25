package lib;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class InputFileReader {
    public static String readFileAsString(String fileName)
            throws IOException
    {
        return new String(
                Files.readAllBytes(Paths.get(fileName)));
    }
}
