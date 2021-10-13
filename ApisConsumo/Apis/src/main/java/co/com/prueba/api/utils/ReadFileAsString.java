package co.com.prueba.api.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFileAsString {

    private ReadFileAsString() {
        throw new IllegalStateException("Utility class");
    }

    public static String readFileAsString(String pathOfFile) throws IOException {
        return new String(Files.readAllBytes(Paths.get(pathOfFile)));
    }
}
