package com.peluware.domain;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a sorting criterion for a specific property.
 * <p>
 * Each {@code Order} instance defines the property name to sort by
 * and the direction of sorting.
 * </p>
 *
 * @param property  the name of the property to sort by (e.g., "name", "createdAt").
 * @param direction the direction of sorting
 */
public record Order(@NotNull String property, @NotNull Direction direction) {

    /**
     * Direction of sorting: ascending or descending.
     */
    public enum Direction {
        ASC,
        DESC
    }

    public Order {
        if (property.isBlank()) {
            throw new IllegalArgumentException("Property name must not be null or blank.");
        }
    }

    /**
     * Creates an ascending {@code Order} for the given property.
     *
     * @param property the property to sort by
     * @return a new {@code Order} instance with ascending direction
     */
    public static @NotNull Order ascending(@NotNull String property) {
        return new Order(property, Direction.ASC);
    }

    /**
     * Creates a descending {@code Order} for the given property.
     *
     * @param property the property to sort by
     * @return a new {@code Order} instance with descending direction
     */
    public static @NotNull Order descending(@NotNull String property) {
        return new Order(property, Direction.DESC);
    }
}
