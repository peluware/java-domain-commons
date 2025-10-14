package com.peluware.domain;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Represents sorting information for a data request.
 * <p>
 * The {@code Sort} record defines a list of {@link Order} criteria
 * to be applied when retrieving or displaying data. This class supports
 * sorting by multiple fields in ascending or descending order.
 * </p>
 *
 * <p>
 * A {@code Sort} is considered "unsorted" if it contains no {@code Order} entries.
 * </p>
 *
 * @param orders the collection of sorting instructions
 */
public record Sort(Collection<Order> orders) implements Iterable<Order> {

    private static final Sort UNSORTED = new Sort(Collections.emptyList());

    /**
     * Checks if sorting has been defined.
     *
     * @return {@code true} if one or more orders are present
     */
    public boolean isSorted() {
        return !orders.isEmpty();
    }

    /**
     * Creates a {@code Sort} instance for a single property and direction.
     *
     * @param property  the property to sort by
     * @param ascending {@code true} for ascending order, {@code false} for descending
     * @return a new {@code Sort} with one order
     */
    @Contract("_, _ -> new")
    public static @NotNull Sort by(@NotNull String property, Order.Direction ascending) {
        return by(new Order(property, ascending));
    }

    /**
     * Creates a {@code Sort} instance from one or more {@link Order} entries.
     *
     * @param orders the sorting orders
     * @return a new {@code Sort} instance
     */
    @Contract("_ -> new")
    public static @NotNull Sort by(Order @NotNull ... orders) {
        return new Sort(List.of(orders));
    }

    /**
     * Creates a {@code Sort} instance from a collection of {@link Order} entries.
     *
     * @param orders the collection of sorting orders
     * @return a new {@code Sort} instance
     */
    @Contract("_ -> new")
    public static @NotNull Sort by(Collection<Order> orders) {
        return new Sort(List.copyOf(orders));
    }

    /**
     * Returns a static instance representing no sorting applied.
     *
     * @return an unsorted {@code Sort} instance
     */
    public static Sort unsorted() {
        return UNSORTED;
    }

    /**
     * Returns an iterator over the defined sorting orders.
     *
     * @return an {@link Iterator} for the collection of orders
     */
    @Override
    public @NotNull Iterator<Order> iterator() {
        return orders.iterator();
    }
}
