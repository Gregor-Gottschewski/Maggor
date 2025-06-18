package me.gregorgott.maggor.beans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import me.gregorgott.maggor.utils.AppConfiguration;

@Named
@ApplicationScoped
public class StaticValuesBean {
    @Inject
    private AppConfiguration appConfiguration;

    public String getFooter() {
        return appConfiguration.getFooter();
    }

    public String getLoginTitle() {
        return appConfiguration.getLoginTitle();
    }

    public String getLoginSubtitle() {
        return appConfiguration.getLoginSubtitle();
    }
}
