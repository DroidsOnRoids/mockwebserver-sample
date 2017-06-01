package frogermcs.io.githubclient.tests.mock.server;

import frogermcs.io.githubclient.utils.mockwebserver.FixtureDispatcher;
import okhttp3.mockwebserver.MockWebServer;

/**
 * * Created by MS on 01/06/2017.
 */

public class TestRequestMapper {

    public static void setTestRequestMapper(MockWebServer server) {
        server.setDispatcher(new FixtureDispatcher("/users/") {
            {
                responseFixturesMap.put("afilipowicz", "user_200");
                responseFixturesMap.put("afilipowicz/repos", "repos_200");
            }
        });
    }
}
