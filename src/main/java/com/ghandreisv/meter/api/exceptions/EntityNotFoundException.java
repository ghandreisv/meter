package com.ghandreisv.meter.api.exceptions;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String id, String entityType) {
        super("The [" + entityType + "] with id '" + id + "' not found");
    }

    public EntityNotFoundException(String id, String entityType, String targetEntityType) {
        super("No [" + targetEntityType + "] found for [" + entityType + "] with id '" + id + "'");
    }
}
