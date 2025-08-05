package com.peluware.domain;

/**
 * Represents pagination information for a data request.
 * <p>
 * This class encapsulates the page number and page size used
 * to retrieve a specific subset of data.
 * </p>
 *
 * <p>
 * A pagination is considered "unpaginated" if both {@code number}
 * and {@code size} are {@code null} or if {@code size} is not greater than zero.
 * </p>
 *
 * @param number the current page number (zero-based index), or {@code null} for unpaginated
 * @param size   the size of the page (number of elements per page), or {@code null} for unpaginated
 */
public record Pagination(Integer number, Integer size) {

    private static final Pagination UNPAGINATED = new Pagination(null, null);

    /**
     * Returns {@code true} if both {@code number} and {@code size} are set and {@code size > 0}.
     *
     * @return {@code true} if pagination is active
     */
    public boolean isPaginated() {
        return number != null && size != null && size > 0;
    }

    /**
     * Calculates the offset in the result set based on the page number and size.
     *
     * @return the offset (i.e., number of items to skip)
     * @throws UnsupportedOperationException if pagination is not enabled
     */
    public int offset() {
        assertPaginated();
        return Math.max(0, number() * size());
    }

    /**
     * Returns a new {@code Pagination} instance representing the next page.
     *
     * @return a new {@code Pagination} for the next page
     * @throws UnsupportedOperationException if pagination is not enabled
     */
    public Pagination next() {
        assertPaginated();
        return new Pagination(number + 1, size);
    }

    /**
     * Returns a new {@code Pagination} instance representing the previous page.
     *
     * @return a new {@code Pagination} for the previous page
     * @throws UnsupportedOperationException if pagination is not enabled or if already on the first page
     */
    public Pagination previous() {
        assertPaginated();
        if (number <= 0) {
            throw new UnsupportedOperationException("Cannot go to previous page from the first page");
        }
        return new Pagination(number - 1, size);
    }


    /**
     * Returns a new {@code Pagination} instance for the first page.
     * *
     * @return a new {@code Pagination} for the first page
     * @throws UnsupportedOperationException if pagination is not enabled
     * */
    public Pagination first() {
        assertPaginated();
        return new Pagination(0, size);
    }

    /**
     * Checks if there is a previous page available.
     *
     * @return {@code true} if the current page is greater than zero
     */
    public boolean hasPrevious() {
        assertPaginated();
        return number > 0;
    }

    private void assertPaginated() {
        if (!isPaginated()) {
            throw new UnsupportedOperationException("Pagination is not enabled");
        }
    }

    /**
     * Returns a static instance representing an unpaginated request.
     *
     * @return the unpaginated {@code Pagination} instance
     */
    public static Pagination unpaginated() {
        return UNPAGINATED;
    }

    @Override
    public String toString() {
        return isPaginated()
                ? "Pagination[page=" + number + ", size=" + size + "]"
                : "Pagination[UNPAGINATED]";
    }
}
