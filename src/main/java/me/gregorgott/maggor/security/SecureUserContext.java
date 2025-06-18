package me.gregorgott.maggor.security;

import me.gregorgott.maggor.InternalImplError;
import me.gregorgott.maggor.utils.AppConfiguration;

public class SecureUserContext {

    /**
     * Checks if the user is valid.
     *
     * @param password the cleartext password of the user
     * @return {@code true} if the user is valid, {@code false} otherwise
     * @throws InternalImplError if an internal error occurs while hashing the password
     */
    public static boolean isUserValid(String password, AppConfiguration appConfiguration) {
        return password.equals(appConfiguration.getPassword());
    }
}
