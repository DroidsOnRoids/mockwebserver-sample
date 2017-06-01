package frogermcs.io.githubclient.utils.mockwebserver;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;

public abstract class FixtureDispatcher extends Dispatcher {
    protected final Map<String, String> responseFixturesMap = new HashMap<>();
    protected final String pathPrefix;
    private final YamlParser parser = new YamlParser();

    protected FixtureDispatcher(String pathPrefix) {
        this.pathPrefix = pathPrefix;
    }

    @Override
    public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
        String responseFixtureName = getResponseFixtureName(request.getPath());
        return buildMockResponse(responseFixtureName);
    }

    @NonNull
    protected MockResponse buildMockResponse(String responseFixtureName) {
        Fixture fixture = Fixture.parseFrom(responseFixtureName, parser);

        MockResponse mockResponse = new MockResponse();
        if (fixture.statusCode != 0) {
            mockResponse.setResponseCode(fixture.statusCode);
        }
        if (fixture.body != null) {
            mockResponse.setBody(fixture.body);
        }
        if (fixture.headers != null) {
            for (String header : fixture.headers) {
                mockResponse.addHeader(header);
            }
        }
        return mockResponse;
    }

    private String getResponseFixtureName(final String path) {
        String infix = path.replace(pathPrefix, "");
        for (String key : responseFixturesMap.keySet()) {
            if (infix.startsWith(key)) {
                return responseFixturesMap.get(key);
            }
        }
        throw new IllegalArgumentException("Unexpected request path: " + path);
    }
}
