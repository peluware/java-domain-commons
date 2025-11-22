package com.peluware.domain;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;
import java.util.function.LongSupplier;

/**
 * Represents a fully paginated subset of a larger collection of items.
 * <p>
 * A {@code Page} extends {@link Slice} by including the total number of elements across all pages,
 * allowing clients to compute metadata such as the total number of pages.
 *
 * @param <T> the type of elements in the content list
 */
public class Page<T> extends Slice<T> {

    /**
     * The total number of elements across all pages.
     */
    private final long totalElements;

    /**
     * Creates a new {@code Page} with the given content, pagination, sort information, and total element count.
     *
     * @param content       the list of items in the current page (must not be {@code null})
     * @param pagination    the pagination information (nullable)
     * @param sort          the sorting information (nullable)
     * @param totalElements the total number of elements across all pages
     * @throws IllegalArgumentException if {@code totalElements} is less than {@code content.size()}
     */
    public Page(@NotNull List<T> content, Pagination pagination, Sort sort, long totalElements) {
        super(content, pagination, sort);
        if (totalElements < content.size()) {
            throw new IllegalArgumentException("Total elements must be greater than or equal to the size of the content.");
        }
        this.totalElements = totalElements;
    }

    /**
     * Creates a new {@code Page} with the given content and total element count, using unpaginated and unsorted defaults.
     *
     * @param content       the list of items in the current page (must not be {@code null})
     * @param totalElements the total number of elements
     */
    public Page(@NotNull List<T> content, long totalElements) {
        this(content, Pagination.unpaginated(), Sort.unsorted(), totalElements);
    }

    /**
     * Creates a new {@code Page} assuming the content list represents the full result set.
     * The total number of elements is assumed to be equal to the content size.
     *
     * @param content the list of items in the current page (must not be {@code null})
     */
    public Page(@NotNull List<T> content) {
        this(content, content.size());
    }

    /**
     * Get the total number of elements across all pages.
     *
     * @return the total number of elements
     */
    public long getTotalElements() {
        return totalElements;
    }

    /**
     * Returns the total number of pages based on the {@link DefaultPagination} configuration and total elements.
     * <p>
     * If the pagination is unpaginated or the page size is zero, it returns 1.
     *
     * @return the total number of pages
     */
    public int getTotalPages() {
        if (!getPagination().isPaginated()) {
            return 1;
        }
        int size = getPagination().getSize();
        return size == 0 ? 1 : (int) Math.ceil((double) totalElements / size);
    }

    /**
     * @param mapper the mapping function to apply to each element
     * @param <R>    the type of the resulting elements after mapping
     * @return a new {@code Page} instance with mapped content
     */
    @Override
    public <R> Page<R> map(@NotNull Function<? super T, R> mapper) {
        var mappedContent = content.stream()
                .map(mapper)
                .toList();
        return new Page<>(mappedContent, pagination, sort, totalElements);
    }

    /**
     * Creates a {@code Page} instance using a deferred supplier to calculate total elements when needed.
     * <p>
     * This is useful when computing the total element count is expensive and can be avoided in certain conditions.
     * <ul>
     *     <li>If pagination is not enabled, the page is returned with content size as total count.</li>
     *     <li>If the page size is greater than content size, and this is the first page, total count is assumed to be content size.</li>
     *     <li>Otherwise, the total is retrieved from the supplied {@code totalElementsSupplier}.</li>
     * </ul>
     *
     * @param content               the list of items in the current page (must not be {@code null})
     * @param pagination            the pagination configuration (nullable)
     * @param sort                  the sorting configuration (nullable)
     * @param totalElementsSupplier a deferred supplier for the total element count (must not be {@code null})
     * @return a {@code Page} instance
     */
    public static <T> Page<T> deferred(@NotNull List<T> content, Pagination pagination, Sort sort, @NotNull LongSupplier totalElementsSupplier) {
        if (pagination == null || !pagination.isPaginated()) {
            return new Page<>(content, pagination, sort, content.size());
        }

        if (pagination.getSize() > content.size()) {

            if (pagination.getOffset() == 0) {
                return new Page<>(content, pagination, sort, content.size());
            }
            if (!content.isEmpty()) {
                return new Page<>(content, pagination, sort, totalElementsSupplier.getAsLong());
            }

        }
        return new Page<>(content, pagination, sort, totalElementsSupplier.getAsLong());
    }

    /**
     * Shortcut for {@link #deferred(List, Pagination, Sort, LongSupplier)} with an unsorted default.
     *
     * @param content               the list of items in the current page (must not be {@code null})
     * @param pagination            the pagination configuration (nullable)
     * @param totalElementsSupplier a deferred supplier for the total element count (must not be {@code null})
     * @return a {@code Page} instance
     */
    public static <T> Page<T> deferred(@NotNull List<T> content, Pagination pagination, LongSupplier totalElementsSupplier) {
        return deferred(content, pagination, Sort.unsorted(), totalElementsSupplier);
    }

}
