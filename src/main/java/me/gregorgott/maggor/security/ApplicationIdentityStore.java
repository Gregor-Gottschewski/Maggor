package me.gregorgott.maggor.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import me.gregorgott.maggor.utils.AppConfiguration;

import java.util.EnumSet;
import java.util.Set;

@ApplicationScoped
public class ApplicationIdentityStore implements IdentityStore {

    @Inject
    private AppConfiguration appConfiguration;

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public Set<ValidationType> validationTypes() {
        return EnumSet.of(ValidationType.VALIDATE);
    }

    public CredentialValidationResult validate(UsernamePasswordCredential credential) {
        String username = credential.getCaller();
        String password = credential.getPasswordAsString();

        if (SecureUserContext.isUserValid(password, appConfiguration)) {
            return new CredentialValidationResult(username, Set.of("users"));
        }

        return CredentialValidationResult.INVALID_RESULT;
    }
}
