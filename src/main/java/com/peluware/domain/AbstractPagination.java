package com.peluware.domain;

/**
 * Abstract base class for pagination implementations that always have a valid page number and size.
 * <p>
 * This class provides default implementations for common pagination operations:
 * <ul>
 *     <li>{@link #getOffset()} – compute the number of elements to skip for the current page</li>
 *     <li>{@link #next()} – create a Pagination instance for the next page</li>
 *     <li>{@link #previous()} – create a Pagination instance for the previous page</li>
 *     <li>{@link #first()} – create a Pagination instance for the first page</li>
 *     <li>{@link #hasPrevious()} – check if a previous page exists</li>
 *     <li>{@link #toString()} – human-readable representation</li>
 * </ul>
 * </p>
 * <p>
 * Concrete subclasses must implement:
 * <ul>
 *     <li>{@link #getNumber()} – returning the current page number (zero-based)</li>
 *     <li>{@link #getSize()} – returning the page size</li>
 *     <li>{@link #create(int, int)} – factory method to create new instances</li>
 * </ul>
 * </p>
 */
public abstract class AbstractPagination implements Pagination {

    /**
     * Indicates that this pagination can always be applied.
     *
     * @return {@code true} always
     */
    @Override
    public final boolean isPaginated() {
        return true;
    }

    /**
     * Computes the offset in the collection based on the current page number and page size.
     *
     * @return the offset (number of elements to skip)
     */
    @Override
    public long getOffset() {
        return (long) getNumber() * getSize();
    }

    /**
     * Returns a new {@code Pagination} instance representing the next page.
     *
     * @return a new Pagination instance for the next page
     */
    @Override
    public Pagination next() {
        return create(getNumber() + 1, getSize());
    }

    /**
     * Returns a new {@code Pagination} instance representing the previous page.
     *
     * @return a new Pagination instance for the previous page
     * @throws UnsupportedOperationException if already at the first page (number == 0)
     */
    @Override
    public Pagination previous() {
        if (getNumber() <= 0) {
            throw new UnsupportedOperationException("Cannot go to previous page from the first page");
        }
        return create(getNumber() - 1, getSize());
    }

    /**
     * Returns a new {@code Pagination} instance representing the first page.
     *
     * @return a new Pagination instance for the first page
     */
    @Override
    public Pagination first() {
        return create(0, getSize());
    }

    /**
     * Checks if there is a previous page available.
     *
     * @return {@code true} if the current page number is greater than zero
     */
    @Override
    public boolean hasPrevious() {
        return getNumber() > 0;
    }

    /**
     * Returns a string representation of the pagination, including page number and page size.
     *
     * @return a string in the format {@code "Pagination[page=X, size=Y]"}
     */
    @Override
    public String toString() {
        return "Pagination[page=" + getNumber() + ", size=" + getSize() + "]";
    }

    /**
     * Factory method for creating a new Pagination instance with the given page number and size.
     * <p>
     * Subclasses must implement this method to return an instance of the concrete type.
     * </p>
     *
     * @param number the page number (zero-based index)
     * @param size   the page size (number of elements per page)
     * @return a new Pagination instance
     */
    protected abstract Pagination create(int number, int size);
}
