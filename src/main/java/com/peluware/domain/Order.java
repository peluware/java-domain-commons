package com.peluware.domain;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a sorting criterion for a specific property.
 * <p>
 * Each {@code Order} instance defines the property name to sort by
 * and whether the sorting is in ascending or descending order.
 * </p>
 *
 * @param property  the name of the property to sort by (e.g., "name", "createdAt").
 * @param ascending {@code true} if the sorting is ascending, {@code false} if descending.
 */
public record Order(@NotNull String property, boolean ascending) {
    public Order {
        if (property.isBlank()) {
            throw new IllegalArgumentException("Property name must not be null or blank.");
        }
    }
}
