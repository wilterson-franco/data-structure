package com.wilterson.lru;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LRUCacheTest {

    @ParameterizedTest
    @MethodSource("buildConcreteCache")
    void whenEmptyCache_thenItemShouldBeAdded(LRUCache<Integer, String> cache) {

        // given

        // when
        cache.putValue(1, "My String");

        // then
        assertThat(cache.getValue(1)).isEqualTo("My String");
    }

    @ParameterizedTest
    @MethodSource("buildConcreteCache")
    void whenFullCache_thenOldestShouldBeEvicted(LRUCache<Integer, String> cache) {

        // given

        // when
        cache.putValue(1, "String1");
        cache.putValue(2, "String2");
        cache.putValue(3, "String3");
        cache.putValue(4, "String4");

        // then
        assertThat(cache.getValue(1)).isNull();
    }

    @ParameterizedTest
    @MethodSource("buildConcreteCache")
    void whenItemAccessed_thenItShouldMoveToTop(LRUCache<Integer, String> cache) {

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

    @ParameterizedTest
    @MethodSource("buildConcreteCache")
    void whenItemUpdated_thenGetShouldReturnNewValue(LRUCache<Integer, String> cache) {

        // given

        // when
        cache.putValue(1, "AAA");
        cache.putValue(1, "BBB");

        // then
        assertThat(cache.getValue(1)).isEqualTo("BBB");
    }

    @ParameterizedTest
    @MethodSource("buildConcreteCache")
    void whenDuplicateValueUnderDifferentKeys_thenCacheShouldHaveSizeTwo(LRUCache<Integer, String> cache) {

        // given

        // when
        cache.putValue(1, "MyString");
        cache.putValue(2, "MyString");

        // then
        assertThat(cache.getValue(1)).isEqualTo("MyString");
        assertThat(cache.getValue(2)).isEqualTo("MyString");
    }

    @ParameterizedTest
    @MethodSource("buildConcreteCache")
    void givenFullCacheWithTwoValuesUnderDifferentKeys_whenFirstItemRemoved_thenFirstItemShouldNotBeInTheCache(LRUCache<Integer, String> cache) {

        // given

        // when
        cache.putValue(2, "MyString");
        cache.putValue(1, "MyString");
        cache.putValue(3, "MyString");
        cache.putValue(4, "AnotherString");

        // then
        assertThat(cache.getValue(2)).isNull();
        assertThat(cache.getValue(1)).isEqualTo("MyString");
        assertThat(cache.getValue(3)).isEqualTo("MyString");
        assertThat(cache.getValue(4)).isEqualTo("AnotherString");
    }

    private static Stream<Arguments> buildConcreteCache() {
        return Stream.of(
                Arguments.of(new LinkedHashMapLRUCache<>(3)),
                Arguments.of(new CustomLRUCache<>(3)),
                Arguments.of(new Custom2LRUCache<>(3)),
                Arguments.of(new Custom3LRUCache<>(3)),
                Arguments.of(new Custom4LRUCache<>(3)));
    }
}
