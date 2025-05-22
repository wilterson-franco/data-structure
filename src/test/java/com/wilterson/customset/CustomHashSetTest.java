package com.wilterson.customset;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CustomHashSetTest {

    @ParameterizedTest
    @MethodSource("bucketTypes")
    void givenEmptySet_whenSizeInvoked_thenSizeShouldBeZero(Class<Bucket> bucketClass) {

        // given
        CustomSet<String> customSet = new CustomHashSet<>(bucketClass);

        // when
        int size = customSet.size();

        // then
        assertThat(size).isZero();
    }

    @ParameterizedTest
    @MethodSource("bucketTypes")
    void givenNewSet_whenOneElementAdded_thenSizeShouldBeOne(Class<Bucket> bucketClass) {

        // given
        CustomSet<String> customSet = new CustomHashSet<>(bucketClass);
        customSet.add("element");

        // when
        int size = customSet.size();

        // then
        assertThat(size).isOne();
    }

    @ParameterizedTest
    @MethodSource("bucketTypes")
    void givenNewSet_whenTwoElementsAdded_thenSizeShouldBeTwo(Class<Bucket> bucketClass) {

        // given
        CustomSet<String> customSet = new CustomHashSet<>(bucketClass);
        customSet.add("element1");
        customSet.add("element2");

        // when
        int size = customSet.size();

        // then
        assertThat(size).isEqualTo(2);
    }

    @ParameterizedTest
    @MethodSource("bucketTypes")
    void givenNewSet_whenSameElementAddedTwice_thenSizeShouldBeOne(Class<Bucket> bucketClass) {

        // given
        CustomSet<String> customSet = new CustomHashSet<>(bucketClass);
        customSet.add("element");

        // when
        customSet.add("element");

        // then
        assertThat(customSet.size()).isOne();
    }

    @ParameterizedTest
    @MethodSource("bucketTypes")
    void givenNotEmptySet_whenExistingElementChecked_thenItShouldBeTrue(Class<Bucket> bucketClass) {

        // give
        CustomSet<String> customSet = new CustomHashSet<>(bucketClass);
        customSet.add("element");

        // when
        boolean contain = customSet.contains("element");

        // then
        assertThat(contain).isTrue();
    }

    @ParameterizedTest
    @MethodSource("bucketTypes")
    void givenNotEmptySet_whenNonExistingElementChecked_thenItShouldBeFalse(Class<Bucket> bucketClass) {

        // give
        CustomSet<String> customSet = new CustomHashSet<>(bucketClass);
        customSet.add("element");

        // when
        boolean contain = customSet.contains("non existing element");

        // then
        assertThat(contain).isFalse();
    }

    @ParameterizedTest
    @MethodSource("bucketTypes")
    void givenSetWithOneElement_whenElementRemoved_thenSizeShouldBeZero_thenContainShouldBeFalse(Class<Bucket> bucketClass) {

        // given
        CustomSet<String> customSet = new CustomHashSet<>(bucketClass);
        customSet.add("element");

        // when
        customSet.remove("element");

        // then
        assertThat(customSet.size()).isZero();
        assertThat(customSet.contains("element")).isFalse();
    }

    @ParameterizedTest
    @MethodSource("bucketTypes")
    void givenSetFullCapacity_whenElementRemoved_thenSizeShouldBeThree_thenContainShouldBeFalse(Class<Bucket> bucketClass) {

        // given
        CustomSet<String> customSet = new CustomHashSet<>(bucketClass);
        customSet.add("element1");
        customSet.add("element2");
        customSet.add("element3");
        customSet.add("element4");
        customSet.add("element5"); // chosen on purpose because it falls in the same bucket as "element1"

        // when
        customSet.remove("element1");

        // then
        assertThat(customSet.size()).isEqualTo(4);
        assertThat(customSet.contains("element1")).isFalse();
        assertThat(customSet.contains("element2")).isTrue();
        assertThat(customSet.contains("element3")).isTrue();
        assertThat(customSet.contains("element4")).isTrue();
        assertThat(customSet.contains("element5")).isTrue();
    }

    @ParameterizedTest
    @MethodSource("bucketTypes")
    void givenSetWithOneElement_whenNonexistentElementRemoved_thenSizeShouldBeOne(Class<Bucket> bucketClass) {

        // given
        CustomSet<Integer> customSet = new CustomHashSet<>(bucketClass);
        customSet.add(1);

        // when
        customSet.remove(2);

        // then
        assertThat(customSet.size()).isOne();
    }

    @ParameterizedTest
    @MethodSource("bucketTypes")
    void givenSetFullCapacity_whenFirstElementRemoved_thenSizeShouldBeThree_thenContainShouldBeFalse(Class<Bucket> bucketClass) {

        // given
        CustomSet<String> customSet = new CustomHashSet<>(bucketClass);
        customSet.add("element1");
        customSet.add("element2");
        customSet.add("element3");
        customSet.add("element4");

        // when
        customSet.remove("element1");

        // then
        assertThat(customSet.size()).isEqualTo(3);
        assertThat(customSet.contains("element1")).isFalse();
        assertThat(customSet.contains("element2")).isTrue();
        assertThat(customSet.contains("element3")).isTrue();
        assertThat(customSet.contains("element4")).isTrue();
    }

    @ParameterizedTest
    @MethodSource("bucketTypes")
    void givenSetFullCapacity_whenExistentElementAdded_thenSizeShouldNotChange(Class<Bucket> bucketClass) {

        // given
        CustomSet<String> customSet = new CustomHashSet<>(bucketClass);
        customSet.add("element1");
        customSet.add("element2");
        customSet.add("element3");
        customSet.add("element4");

        // when
        customSet.add("element3");

        // then
        assertThat(customSet.size()).isEqualTo(4);
    }

    private static Stream<Arguments> bucketTypes() {
        return Stream.of(
                Arguments.of(BucketList.class),
                Arguments.of(BucketArray.class),
                Arguments.of(BuketLinkedList.class)
        );
    }
}