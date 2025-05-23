package com.wilterson.lru;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LRUCacheTest {

    private LRUCache<Integer, String> cache;

    @BeforeEach
    void setup() {
        cache = new LinkedHashMapLRUCache<>(3);
    }

    @Test
    void whenEmptyCache_thenItemShouldBeAdded() {

        // given

        // when
        cache.putValue(1, "My String");

        // then
        assertThat(cache.getValue(1)).isEqualTo("My String");
    }

    @Test
    void whenFullCache_thenOldestShouldBeEvicted() {

        // given

        // when
        cache.putValue(1, "String1");
        cache.putValue(2, "String2");
        cache.putValue(3, "String3");
        cache.putValue(4, "String4");

        // then
        assertThat(cache.getValue(1)).isNull();
    }

    @Test
    void whenItemAccessed_thenItShouldMoveToTop() {

        // given

        // when
        cache.putValue(1, "String1");
        cache.putValue(2, "String2");
        cache.putValue(3, "String3");
        cache.getValue(1);
        cache.putValue(4, "String4");

        // then
        assertThat(cache.getValue(1)).isEqualTo("String1");
        assertThat(cache.getValue(2)).isNull();
    }

    @Test
    void whenItemUpdated_thenGetShouldReturnNewValue() {

        // given

        // when
        cache.putValue(1, "AAA");
        cache.putValue(1, "BBB");

        // then
        assertThat(cache.getValue(1)).isEqualTo("BBB");
    }
}
