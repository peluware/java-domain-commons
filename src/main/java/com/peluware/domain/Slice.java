package com.peluware.domain;


import org.jspecify.annotations.NonNull;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * Represents a slice of a collection of items, typically used for partial pagination or segmented results.
 * <p>
 * Unlike a full {@code Page}, a {@code Slice} does not necessarily contain information about the total number
 * of items or total pages. It simply wraps a subset (or "slice") of data along with pagination and sorting metadata.
 *
 * @param <T> the type of elements in the content list
 */
public class Slice<T> {

    /**
     * The unmodifiable list of items contained in this slice.
     */
    protected final List<T> content;

    /**
     * The pagination information associated with this slice.
     */
    protected final Pagination pagination;

    /**
     * The sorting information associated with this slice.
     */
    protected final Sort sort;

    /**
     * Creates a new {@code Slice} instance with the given content, pagination, and sort configuration.
     * If {@code pagination} or {@code sort} are {@code null}, they will be replaced with unpaginated and unsorted defaults.
     *
     * @param content    the list of items in the slice, must not be {@code null}
     * @param pagination the pagination information (may be {@code null})
     * @param sort       the sorting information (may be {@code null})
     */
    public Slice(@NonNull List<T> content, Pagination pagination, Sort sort) {
        this.content = Collections.unmodifiableList(content);
        this.pagination = pagination != null ? pagination : Pagination.unpaginated();
        this.sort = sort != null ? sort : Sort.unsorted();
    }

    /**
     * Creates a new unpaginated and unsorted {@code Slice} instance with the given content.
     *
     * @param content the list of items in the slice, must not be {@code null}
     */
    public Slice(@NonNull List<T> content) {
        this(content, Pagination.unpaginated(), Sort.unsorted());
    }

    /**
     * Gets the unmodifiable list of items contained in this slice.
     *
     * @return the content list
     */
    public @NonNull List<T> getContent() {
        return content;
    }

    /**
     * Gets the pagination information associated with this slice.
     *
     * @return the pagination
     */
    public @NonNull Pagination getPagination() {
        return pagination;
    }

    /**
     * Gets the sorting information associated with this slice.
     *
     * @return the sort
     */
    public @NonNull Sort getSort() {
        return sort;
    }

    /**
     * Maps the content of this slice to a new type using the provided mapper function.
     *
     * @param mapper the function to apply to each element in the content list
     * @param <R>    the type of elements in the resulting slice
     * @return a new {@code Slice} containing the mapped content
     */
    public <R> Slice<R> map(@NonNull Function<? super T, R> mapper) {
        var mappedContent = content.stream()
                .map(mapper)
                .toList();
        return new Slice<>(mappedContent, pagination, sort);
    }
}
