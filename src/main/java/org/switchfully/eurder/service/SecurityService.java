package org.switchfully.eurder.service;

import org.springframework.stereotype.Service;
import org.switchfully.eurder.domain.User;
import org.switchfully.eurder.domain.UserRepository;
import org.switchfully.eurder.exception.ForbiddenException;
import org.switchfully.eurder.exception.UnauthorizedException;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class SecurityService {
    private UserRepository userRepository;

    public SecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateAuthorization(String authorization, Feature feature) {
        if (authorization == null || !authorization.startsWith("Basic")) throw new UnauthorizedException();
        String encodedCredentials = authorization.substring(6);
        String decodedCredentials = new String(Base64.getDecoder().decode(encodedCredentials), StandardCharsets.UTF_8);

        if (!decodedCredentials.contains(":")) {
            throw new UnauthorizedException();
        }
        String[] parts = decodedCredentials.split(":", 2);

        String username = parts[0];
        String password = parts[1];
        User user = userRepository.getByEmail(username).orElseThrow(() -> new UnauthorizedException("Wrong credentials"));
        if (!user.doesPasswordMatch(password)) throw new UnauthorizedException("Wrong credentials");
        if (!user.canHaveAccessTo(feature)) throw new ForbiddenException("No acces");
        return;
    }
}