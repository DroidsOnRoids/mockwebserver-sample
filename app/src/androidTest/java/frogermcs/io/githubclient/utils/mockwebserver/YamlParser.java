package frogermcs.io.githubclient.utils.mockwebserver;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

class YamlParser implements Parser {

    @Override
    public Fixture parse(InputStream inputStream) {
        return new Yaml().loadAs(inputStream, Fixture.class);
    }

}
