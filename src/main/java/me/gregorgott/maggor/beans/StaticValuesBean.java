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

    public String getTopicsTitle() {
        return appConfiguration.getTopicsTitle();
    }

    public String getTopicsHeader() {
        return appConfiguration.getTopicsHeader();
    }

    public String getTopicsUnderline() {
        return appConfiguration.getTopicsUnderline();
    }

    public String getTopicsToolbarText() {
        return appConfiguration.getTopicsToolbarText();
    }

    public String getTopicsToolbarLogout() {
        return appConfiguration.getTopicsToolbarLogout();
    }

    public String getTopicsTableEmptyMessage() {
        return appConfiguration.getTopicsTableEmptyMessage();
    }

    public String getTopicsCreateTitle() {
        return appConfiguration.getTopicsCreateTitle();
    }

    public String getTopicsCreateName() {
        return appConfiguration.getTopicsCreateName();
    }

    public String getTopicsCreateNameRequiredMessage() {
        return appConfiguration.getTopicsCreateNameRequiredMessage();
    }

    public String getTopicsCreateNamePlaceholder() {
        return appConfiguration.getTopicsCreateNamePlaceholder();
    }

    public String getTopicsCreatePassword() {
        return appConfiguration.getTopicsCreatePassword();
    }

    public String getTopicsCreatePasswordPlaceholder() {
        return appConfiguration.getTopicsCreatePasswordPlaceholder();
    }

    public String getTopicsCreateButton() {
        return appConfiguration.getTopicsCreateButton();
    }

    public String getTopicsProtectionHeader() {
        return appConfiguration.getTopicsProtectionHeader();
    }

    public String getTopicsProtectionPassword() {
        return appConfiguration.getTopicsProtectionPassword();
    }

    public String getTopicsProtectionRequiredMessage() {
        return appConfiguration.getTopicsProtectionRequiredMessage();
    }

    public String getTopicsProtectionPlaceholder() {
        return appConfiguration.getTopicsProtectionPlaceholder();
    }

    public String getTopicsProtectionOpenButton() {
        return appConfiguration.getTopicsProtectionOpenButton();
    }

    public String getTopicsTableColumnChaptersName() {
        return appConfiguration.getTopicsTableColumnChaptersName();
    }

    public String getTopicsTableColumnActionsName() {
        return appConfiguration.getTopicsTableColumnActionsName();
    }

    public String getTopicsTableColumnActionsView() {
        return appConfiguration.getTopicsTableColumnActionsView();
    }

    public String getTopicsTableColumnActionsDelete() {
        return appConfiguration.getTopicsTableColumnActionsDelete();
    }
}
