package fr.lmo.aoc2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AoCLMO {


    public abstract void run();


    private static String getDirectoryName(Class clazz) {
        return clazz.getSimpleName().toLowerCase();
    }

    private static String getFileName(String name) {
        String fileName = name;
        if (Boolean.parseBoolean(System.getProperty("test", "false"))) {
            fileName = fileName.concat("-test");
        }
        return fileName.concat(".txt");
    }

    private Path getPath(String name) {
        return Paths.get("target", "classes", getDirectoryName(getClass()), getFileName(name));
    }

    public Path getTestInputPath() {
        return getPath("input-test");
    }

    public Path getInputPath() {
        return getPath("input");
    }
    public String readFile(Path path) {
        try {
            return Files.readString(path);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    public <T> Stream<T> oneLineStream(Path path, String sep, Function<String, T> mapper) {
        return Arrays.stream(readFile(path).split(sep)).map(mapper);
    }

    public <T> List<T> oneLineList(Path path, String sep, Function<String, T> mapper) {
        return Arrays.stream(readFile(path).split(sep)).map(mapper).collect(Collectors.toList());
    }

    public <T> List<T> list(Path path, Function<String, T> mapper) {
        return stream(path, mapper).collect(Collectors.toList());
    }

    public Stream<String> stream(Path path) {
        try {
            return Files.lines(path);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    public List<String> list(Path path) {
        return stream(path).collect(Collectors.toList());
    }

    public <T> Stream<T> stream(Path path, Function<String, T> mapper) {
        try {
            return Files.lines(path).map(mapper);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    public static void main(String[] args) {
        try {
            String className = args[0];
            Class<AoCLMO> clazz = (Class<AoCLMO>) Class.forName(AoCLMO.class.getPackageName()+"."+className);
            AoCLMO instance = clazz.getDeclaredConstructor().newInstance();
            instance.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
