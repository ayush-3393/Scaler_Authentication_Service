package com.auth.scalerauthservice.services.service_interfaces;

import com.auth.scalerauthservice.models.User;

import java.util.Optional;

public interface AuthService {
    Optional<String> login(User user);

    Optional<Boolean> validate(User user);
}
