package me.gregorgott.maggor.beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import me.gregorgott.maggor.security.SecureUserContext;
import me.gregorgott.maggor.utils.AppConfiguration;

import java.io.Serial;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Inject
    AppConfiguration appConfiguration;
    private String username;
    private String password;
    private boolean loggedIn;

    @PostConstruct
    public void init() {
        loggedIn = false;
    }

    public String login() {
        if (SecureUserContext.isUserValid(password, appConfiguration)) {
            loggedIn = true;
            return "topics";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nutzername oder Passwort falsch", "Bitter erneut probieren")
            );
            return null;
        }
    }

    public String logout() {
        loggedIn = false;
        username = null;
        password = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login";
    }

    public String register() {
        return "register";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}