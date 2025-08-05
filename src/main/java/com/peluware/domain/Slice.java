package com.peluware.domain;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * Represents a slice of a collection of items, typically used for partial pagination or segmented results.
 * <p>
 * Unlike a full {@code Page}, a {@code Slice} does not necessarily contain information about the total number
 * of items or total pages. It simply wraps a subset (or "slice") of data along with pagination and sorting metadata.
 *
 * @param <T> the type of elements in the content list
 */
@Getter
public class Slice<T> {

    /**
     * The unmodifiable list of items contained in this slice.
     */
    private final List<T> content;

    /**
     * The pagination information associated with this slice.
     */
    private final Pagination pagination;

    /**
     * The sorting information associated with this slice.
     */
    private final Sort sort;

    /**
     * Creates a new {@code Slice} instance with the given content, pagination, and sort configuration.
     * If {@code pagination} or {@code sort} are {@code null}, they will be replaced with unpaginated and unsorted defaults.
     *
     * @param content    the list of items in the slice, must not be {@code null}
     * @param pagination the pagination information (may be {@code null})
     * @param sort       the sorting information (may be {@code null})
     */
    public Slice(@NotNull List<T> content, Pagination pagination, Sort sort) {
        this.content = Collections.unmodifiableList(content);
        this.pagination = pagination != null ? pagination : Pagination.unpaginated();
        this.sort = sort != null ? sort : Sort.unsorted();
    }

    /**
     * Creates a new unpaginated and unsorted {@code Slice} instance with the given content.
     *
     * @param content the list of items in the slice, must not be {@code null}
     */
    public Slice(@NotNull List<T> content) {
        this(content, Pagination.unpaginated(), Sort.unsorted());
    }
}
