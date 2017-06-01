package frogermcs.io.githubclient.utils.mockwebserver;

import java.io.InputStream;

/**
 * Used to parse InputStream to {@link Fixture} object
 * Any implementation should meet the rule above
 */
interface Parser {

    Fixture parse(InputStream inputStream);
}
