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
        return System.getenv("DB_NAME");
    }

    public String getDbPort() {
        return System.getenv("DB_PORT");
    }

    public String getFooter() {
        return properties.getProperty("login.footer", "");
    }

    public String getPassword() {
        return properties.getProperty("user.password", "maggor");
    }

    public void setPassword(String password) {
        properties.setProperty("user.password", password);
    }

    public String getLoginTitle() {
        return properties.getProperty("login.title", "Welcome to Maggor");
    }

    public String getLoginSubtitle() {
        return properties.getProperty("login.subtitle", "Share beautiful moments");
    }

    public String getDatabaseRootUser() {
        return System.getenv("DB_USERNAME");
    }

    public char[] getDatabaseRootPassword() {
        return System.getenv("DB_PASSWORD").toCharArray();
    }

    public String getTopicsTitle() {
        return properties.getProperty("topics.title", "Chapters");
    }

    public String getTopicsHeader() {
        return properties.getProperty("topics.header", "Chapters");
    }

    public String getTopicsUnderline() {
        return properties.getProperty("topics.underline", "The most beautiful chapters with you");
    }

    public String getTopicsToolbarText() {
        return properties.getProperty("topics.toolbar.text", "For you");
    }

    public String getTopicsToolbarLogout() {
        return properties.getProperty("topics.toolbar.logout", "Goodbye");
    }

    public String getTopicsTableEmptyMessage() {
        return properties.getProperty("topics.table.emptymessage", "Little bit empty here");
    }

    public String getTopicsCreateTitle() {
        return properties.getProperty("topics.create.title", "Creating a new chapter");
    }

    public String getTopicsCreateName() {
        return properties.getProperty("topics.create.name", "Chapter name");
    }

    public String getTopicsCreateNameRequiredMessage() {
        return properties.getProperty("topics.create.name.requiredmessage", "Please give a name");
    }

    public String getTopicsCreateNamePlaceholder() {
        return properties.getProperty("topics.create.name.placeholder", "Our chapter");
    }

    public String getTopicsCreatePassword() {
        return properties.getProperty("topics.create.password", "Password");
    }

    public String getTopicsCreatePasswordPlaceholder() {
        return properties.getProperty("topics.create.password.placeholder", "Your password");
    }

    public String getTopicsCreateButton() {
        return properties.getProperty("topics.create.button", "Create chapter");
    }

    public String getTopicsProtectionHeader() {
        return properties.getProperty("topics.protection.header", "Secret chapter");
    }

    public String getTopicsProtectionPassword() {
        return properties.getProperty("topics.protection.password", "Password");
    }

    public String getTopicsProtectionRequiredMessage() {
        return properties.getProperty("topics.protection.requiredmessage", "Please give a password");
    }

    public String getTopicsProtectionPlaceholder() {
        return properties.getProperty("topics.protection.placeholder", "Your password...");
    }

    public String getTopicsProtectionOpenButton() {
        return properties.getProperty("topics.propection.openbutton", "Open chapter");
    }

    public String getTopicsTableColumnChaptersName() {
        return properties.getProperty("topics.table.column.chapters.name", "Chapters");
    }

    public String getTopicsTableColumnActionsName() {
        return properties.getProperty("topics.table.column.actions.name", "Actions");
    }

    public String getTopicsTableColumnActionsView() {
        return properties.getProperty("topics.table.column.actions.view", "view chapter");
    }

    public String getTopicsTableColumnActionsDelete() {
        return properties.getProperty("topics.table.column.actions.delete", "delete chapter");
    }
}
