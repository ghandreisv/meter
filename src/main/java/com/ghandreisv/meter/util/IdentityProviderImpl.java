package com.ghandreisv.meter.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IdentityProviderImpl implements IdentityProvider<String> {
    @Override
    public String createIdentity() {
        return UUID.randomUUID().toString();
    }
}
