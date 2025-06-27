package me.gregorgott.maggor.base;

import jakarta.inject.Inject;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import me.gregorgott.maggor.utils.AppConfiguration;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener(value = "Application startup")
public class ApplicationStartup implements ServletContextListener {

    private final Logger logger = Logger.getLogger(ApplicationStartup.class.getName());

    @Inject
    private AppConfiguration appConfiguration;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.log(Level.INFO, "Visit http://localhost:8080{0}", sce.getServletContext().getContextPath());
        init();
    }

    public void init() {
        logger.info("Maggor started");
        try {
            appConfiguration.loadFromFile(Path.of("/maggor/conf.prop"));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading properties", e);
        }
    }
}
