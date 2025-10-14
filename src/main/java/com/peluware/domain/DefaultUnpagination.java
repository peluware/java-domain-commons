package com.peluware.domain;

/**
 * Concrete implementation of {@link Pagination} representing an unpaginated state.
 * <p>
 * This class always reports {@link #isPaginated()} as {@code false}.
 * Methods such as {@link #getOffset()}, {@link #next()}, {@link #previous()},
 * and {@link #first()} will throw {@link UnsupportedOperationException}.
 * </p>
 */
public class DefaultUnpagination implements Pagination {

    /**
     * Singleton instance for convenience.
     */
    public static final DefaultUnpagination INSTANCE = new DefaultUnpagination();

    /**
     * Private constructor to enforce singleton usage.
     */
    private DefaultUnpagination() {
    }

    /**
     * Indicates that this pagination instance does not support paginated operations.
     *
     * @return {@code false} always
     */
    @Override
    public boolean isPaginated() {
        return false;
    }

    /**
     * Returns the current page number.
     *
     * @return 0 always
     */
    @Override
    public int getNumber() {
        return 0;
    }

    /**
     * Returns the page size.
     *
     * @return 0 always
     */
    @Override
    public int getSize() {
        return 0;
    }

    /**
     * {@inheritDoc}
     * @throws UnsupportedOperationException always
     */
    @Override
    public long getOffset() {
        throw new UnsupportedOperationException("Unpaginated instance has no offset");
    }

    /**
     * {@inheritDoc}
     * @throws UnsupportedOperationException always
     */
    @Override
    public Pagination next() {
        throw new UnsupportedOperationException("Unpaginated instance cannot go to next page");
    }

    /**
     * {@inheritDoc}
     * @throws UnsupportedOperationException always
     */
    @Override
    public Pagination previous() {
        throw new UnsupportedOperationException("Unpaginated instance cannot go to previous page");
    }

    /**
     * {@inheritDoc}
     * @throws UnsupportedOperationException always
     */
    @Override
    public Pagination first() {
        throw new UnsupportedOperationException("Unpaginated instance cannot go to first page");
    }

    /**
     * {@inheritDoc}
     * @throws UnsupportedOperationException always
     */
    @Override
    public boolean hasPrevious() {
        throw new UnsupportedOperationException("Unpaginated instance has no previous page");
    }

    @Override
    public String toString() {
        return "Pagination[UNPAGINATED]";
    }
}
