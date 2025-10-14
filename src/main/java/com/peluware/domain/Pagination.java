package com.peluware.domain;

/**
 * Represents pagination information for navigating through subsets of a collection.
 * <p>
 * Implementations provide the current page number and page size,
 * as well as utility methods to navigate between pages and compute offsets.
 * </p>
 * <p>
 * Methods such as {@link #getOffset()}, {@link #next()}, {@link #previous()}
 * and {@link #first()} may throw {@link UnsupportedOperationException} if the pagination
 * does not meet the required conditions. Use {@link #isPaginated()} to verify.
 * </p>
 */
public interface Pagination {

    /**
     * Returns the current page number (zero-based index).
     *
     * @return the current page number
     */
    int getNumber();

    /**
     * Returns the size of the page (number of elements per page).
     *
     * @return the page size
     */
    int getSize();

    /**
     * Returns {@code true} if pagination can be applied.
     *
     * @return {@code true} if pagination can be applied
     */
    boolean isPaginated();

    /**
     * Calculates the offset in the collection based on the current page number and page size.
     *
     * @return the offset (number of elements to skip)
     * @throws UnsupportedOperationException if {@link #isPaginated()} returns {@code false}
     */
    long getOffset();

    /**
     * Returns a new {@code Pagination} instance representing the next page.
     *
     * @return a new Pagination instance for the next page
     * @throws UnsupportedOperationException if {@link #isPaginated()} returns {@code false}
     */
    Pagination next();

    /**
     * Returns a new {@code Pagination} instance representing the previous page.
     *
     * @return a new Pagination instance for the previous page
     * @throws UnsupportedOperationException if {@link #isPaginated()} returns {@code false}
     *         or if already at the first page
     */
    Pagination previous();

    /**
     * Returns a new {@code Pagination} instance representing the first page.
     *
     * @return a new Pagination instance for the first page
     * @throws UnsupportedOperationException if {@link #isPaginated()} returns {@code false}
     */
    Pagination first();

    /**
     * Checks if there is a previous page available.
     *
     * @return {@code true} if the current page number is greater than zero
     * @throws UnsupportedOperationException if {@link #isPaginated()} returns {@code false}
     */
    boolean hasPrevious();


    /**
     * Returns a singleton instance representing an unpaginated state.
     * @return an unpaginated Pagination instance
     */
    static Pagination unpaginated() {
        return DefaultUnpagination.INSTANCE;
    }

    /**
     * Creates a new {@code Pagination} instance with the specified page number and size.
     * @param number the current page number (zero-based index)
     * @param size the size of the page (number of elements per page)
     * @return a new Pagination instance
     */
    static Pagination of(int number, int size) {
        return new DefaultPagination(number, size);
    }
}
