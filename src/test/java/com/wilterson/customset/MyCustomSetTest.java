package com.wilterson.customset;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MyCustomSetTest {

    @ParameterizedTest
    @MethodSource("bucketTypes")
    void givenEmptySet_whenSizeInvoked_thenSizeShouldBeZero(Class<Bucket> bucketClass) {

        // given
        CustomSet<String> customSet = new MyCustomSet<>(bucketClass);

        // when
        int size = customSet.size();

        // then
        assertThat(size).isZero();
    }

    @ParameterizedTest
    @MethodSource("bucketTypes")
    void givenNewSet_whenOneElementAdded_thenSizeShouldBeOne(Class<Bucket> bucketClass) {

        // given
        CustomSet<String> customSet = new MyCustomSet<>(bucketClass);
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
        CustomSet<String> customSet = new MyCustomSet<>(bucketClass);
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
        CustomSet<String> customSet = new MyCustomSet<>(bucketClass);
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
        CustomSet<String> customSet = new MyCustomSet<>(bucketClass);
        customSet.add("element");

        // when
        boolean contain = customSet.contain("element");

        // then
        assertThat(contain).isTrue();
    }

    @ParameterizedTest
    @MethodSource("bucketTypes")
    void givenNotEmptySet_whenNonExistingElementChecked_thenItShouldBeFalse(Class<Bucket> bucketClass) {

        // give
        CustomSet<String> customSet = new MyCustomSet<>(bucketClass);
        customSet.add("element");

        // when
        boolean contain = customSet.contain("non existing element");

        // then
        assertThat(contain).isFalse();
    }

    @ParameterizedTest
    @MethodSource("bucketTypes")
    void givenSetWithOneElement_whenElementRemoved_thenSizeShouldBeZero_thenContainShouldBeFalse(Class<Bucket> bucketClass) {

        // given
        CustomSet<String> customSet = new MyCustomSet<>(bucketClass);
        customSet.add("element");

        // when
        customSet.remove("element");

        // then
        assertThat(customSet.size()).isZero();
        assertThat(customSet.contain("element")).isFalse();
    }

    @ParameterizedTest
    @MethodSource("bucketTypes")
    void givenSetFullCapacity_whenLastElementRemoved_thenSizeShouldBeThree_thenContainShouldBeFalse(Class<Bucket> bucketClass) {

        // given
        CustomSet<String> customSet = new MyCustomSet<>(bucketClass);
        customSet.add("element1");
        customSet.add("element2");
        customSet.add("element3");
        customSet.add("element4");

        // when
        customSet.remove("element4");

        // then
        assertThat(customSet.size()).isEqualTo(3);
        assertThat(customSet.contain("element1")).isTrue();
        assertThat(customSet.contain("element2")).isTrue();
        assertThat(customSet.contain("element3")).isTrue();
        assertThat(customSet.contain("element4")).isFalse();
    }

    @ParameterizedTest
    @MethodSource("bucketTypes")
    void givenSetFullCapacity_whenFirstElementRemoved_thenSizeShouldBeThree_thenContainShouldBeFalse(Class<Bucket> bucketClass) {

        // given
        CustomSet<String> customSet = new MyCustomSet<>(bucketClass);
        customSet.add("element1");
        customSet.add("element2");
        customSet.add("element3");
        customSet.add("element4");

        // when
        customSet.remove("element1");

        // then
        assertThat(customSet.size()).isEqualTo(3);
        assertThat(customSet.contain("element1")).isFalse();
        assertThat(customSet.contain("element2")).isTrue();
        assertThat(customSet.contain("element3")).isTrue();
        assertThat(customSet.contain("element4")).isTrue();
    }

    @ParameterizedTest
    @MethodSource("bucketTypes")
    void givenSetFullCapacity_whenExistentElementAdded_thenSizeShouldNotChange(Class<Bucket> bucketClass) {

        // given
        CustomSet<String> customSet = new MyCustomSet<>(bucketClass);
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
                Arguments.of(ListBucket.class),
                Arguments.of(ArrayBucket.class),
                Arguments.of(LinkedListBuket.class)
        );
    }
}