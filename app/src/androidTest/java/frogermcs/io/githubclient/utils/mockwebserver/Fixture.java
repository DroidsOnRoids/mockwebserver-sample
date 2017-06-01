package frogermcs.io.githubclient.utils.mockwebserver;

import java.io.InputStream;
import java.util.List;

/**
 * A value container that holds all information about the fixture file.
 */
class Fixture {

    public int statusCode;
    public String body;
    public List<String> headers;

    /**
     * Parse the given filename and returns the Fixture object.
     *
     * @param fileName filename should not contain extension or relative path. ie: login
     * @param parser   parser is required for parsing operation, it should not be null
     */
    static Fixture parseFrom(String fileName, Parser parser) {
        if (fileName == null) {
            throw new NullPointerException("File name should not be null");
        }
        String path = "fixtures/" + fileName + ".yaml";
        InputStream inputStream = openPathAsStream(path);
        Fixture result = parser.parse(inputStream);

        return result;
    }

    private static InputStream openPathAsStream(String path) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = loader.getResourceAsStream(path);

        if (inputStream == null) {
            throw new IllegalStateException("Invalid path: " + path);
        }

        return inputStream;
    }
}
