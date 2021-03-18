package com.milka.users;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "petsitter")
@Component
public class UserProperties {

    private boolean canBePetsitter;

    public boolean isCanBePetsitter() {
        return canBePetsitter;
    }

    public void setCanBePetsitter(boolean canBePetsitter) {
        this.canBePetsitter = canBePetsitter;
    }
}
