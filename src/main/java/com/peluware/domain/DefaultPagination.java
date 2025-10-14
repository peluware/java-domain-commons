package com.peluware.domain;

/**
 * Concrete implementation of {@link AbstractPagination} representing a simple pagination
 * with a fixed page number and page size.
 * <p>
 * Instances of this class are immutable. The page number must be non-negative, and the page
 * size must be at least 1. This class always represents a paginated state, so {@link #isPaginated()}
 * returns {@code true}.
 * </p>
 */
public class DefaultPagination extends AbstractPagination {

    private final int number;
    private final int size;

    /**
     * Creates a new {@code DefaultPagination} instance with the specified page number and size.
     *
     * @param number the current page number (zero-based index), must be >= 0
     * @param size   the size of the page (number of elements per page), must be >= 1
     * @throws IllegalArgumentException if {@code number} is negative or {@code size} is less than 1
     */
    public DefaultPagination(int number, int size) {
        if (number < 0) {
            throw new IllegalArgumentException("Page number must not be negative");
        }
        if (size < 1) {
            throw new IllegalArgumentException("Page size must be at least 1");
        }
        this.number = number;
        this.size = size;
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation returns a new {@code DefaultPagination} instance with the given page number and size.
     * </p>
     */
    @Override
    protected Pagination create(int number, int size) {
        return new DefaultPagination(number, size);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumber() {
        return number;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSize() {
        return size;
    }
}
