package com.lguplus.homeshoppingmoa.config.properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 * application.yml 에 설정된 설정 외 추가 설정을 로드하여 설정을 통합합니다.
 */
// @Slf4j - Do not use lombok
// CALL in - /META-INF/spring.factories
public class PropertyLoader implements EnvironmentPostProcessor {

    private final Map<String, String> locations = new LinkedHashMap<>();

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        add(locations);
        if (!locations.isEmpty()) {
            locations.forEach((key, value) -> addYamlPropertiesToSpringEnvironment(environment, key, value));
        }
    }

    /*
     *
     * @param environment
     * @param resourceName
     * @param resourceLocation
     */
    private void addYamlPropertiesToSpringEnvironment(ConfigurableEnvironment environment, String resourceName, String resourceLocation) {
        try {
            Resource resource = new ClassPathResource(resourceLocation);
            YamlPropertySourceLoader yamlPropertySourceLoader = new YamlPropertySourceLoader();
            List<PropertySource<?>> yamlProperties = yamlPropertySourceLoader.load(resourceName, resource);
            yamlProperties.forEach(source -> environment.getPropertySources().addLast(source));
        }
        catch (IOException e) {
            throw new RuntimeException("Unable to load resource file", e);
        }
    }

    /*
     * 추가로 로드하여 통합할 환경설정 파일을 기술합니다.
     * @param locations
     */
    protected void add(Map<String, String> locations) {
        locations.put("bootstrap", "bootstrap.yml");
    }

}
