package me.gregorgott.maggor.utils;

import jakarta.enterprise.context.ApplicationScoped;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Properties;
import java.util.logging.Logger;

@ApplicationScoped
public class AppConfiguration {
    private final Logger logger = Logger.getLogger(AppConfiguration.class.getName());
    private final Properties properties;

    public AppConfiguration() {
        properties = new Properties();
    }

    public void loadFromFile(Path path) throws IOException {
        logger.info("Load properties file...");
        try (InputStream input = new FileInputStream(path.toFile());
             Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8)) {
            properties.load(reader);
            logger.info("Properties loaded");
        }
    }

    public Properties getProperties() {
        return properties;
    }

    public String getDbName() {
        return properties.getProperty("db.name");
    }

    public String getDbPort() {
        return properties.getProperty("db.port");
    }

    public String getFooter() {
        return properties.getProperty("login.footer");
    }

    public String getPassword() {
        return properties.getProperty("user.password");
    }

    public void setPassword(String password) {
        properties.setProperty("user.password", password);
    }

    public String getLoginTitle() {
        return properties.getProperty("login.title");
    }

    public String getLoginSubtitle() {
        return properties.getProperty("login.subtitle");
    }

    public String getDatabaseRootUser() {
        return properties.getProperty("db.root.username");
    }

    public char[] getDatabaseRootPassword() {
        return properties.getProperty("db.root.password").toCharArray();
    }
}
