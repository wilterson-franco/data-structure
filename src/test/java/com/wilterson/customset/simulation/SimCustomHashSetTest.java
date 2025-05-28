package com.wilterson.customset.simulation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SimCustomHashSetTest {

    @Test
    void givenEmptySet_whenSizeInvoked_thenSizeShouldBeZero() {

        // given
        CustomSet<String> customSet = new SimCustomHashSet<>();

        // when
        int size = customSet.size();

        // then
        assertThat(size).isZero();
    }

    @Test
    void givenNewSet_whenOneElementAdded_thenSizeShouldBeOne() {

        // given
        CustomSet<String> customSet = new SimCustomHashSet<>();
        customSet.add("element");

        // when
        int size = customSet.size();

        // then
        assertThat(size).isOne();
    }

    @Test
    void givenNewSet_whenTwoElementsAdded_thenSizeShouldBeTwo() {

        // given
        CustomSet<String> customSet = new SimCustomHashSet<>();
        customSet.add("element1");
        customSet.add("element2");

        // when
        int size = customSet.size();

        // then
        assertThat(size).isEqualTo(2);
    }

    @Test
    void givenNewSet_whenSameElementAddedTwice_thenSizeShouldBeOne() {

        // given
        CustomSet<String> customSet = new SimCustomHashSet<>();
        customSet.add("element");

        // when
        customSet.add("element");

        // then
        assertThat(customSet.size()).isOne();
    }

    @Test
    void givenNotEmptySet_whenExistingElementChecked_thenItShouldBeTrue() {

        // give
        CustomSet<String> customSet = new SimCustomHashSet<>();
        customSet.add("element");

        // when
        boolean contain = customSet.contains("element");

        // then
        assertThat(contain).isTrue();
    }

    @Test
    void givenNotEmptySet_whenNonExistingElementChecked_thenItShouldBeFalse() {

        // give
        CustomSet<String> customSet = new SimCustomHashSet<>();
        customSet.add("element");

        // when
        boolean contain = customSet.contains("non existing element");

        // then
        assertThat(contain).isFalse();
    }

    @Test
    void givenSetWithOneElement_whenElementRemoved_thenSizeShouldBeZero_thenContainShouldBeFalse() {

        // given
        CustomSet<String> customSet = new SimCustomHashSet<>();
        customSet.add("element");

        // when
        customSet.remove("element");

        // then
        assertThat(customSet.size()).isZero();
        assertThat(customSet.contains("element")).isFalse();
    }

    @Test
    void givenSetFullCapacity_whenElementRemoved_thenSizeShouldBeThree_thenContainShouldBeFalse() {

        // given
        CustomSet<String> customSet = new SimCustomHashSet<>();
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

    @Test
    void givenSetWithOneElement_whenNonexistentElementRemoved_thenSizeShouldBeOne() {

        // given
        CustomSet<Integer> customSet = new SimCustomHashSet<>();
        customSet.add(1);

        // when
        customSet.remove(2);

        // then
        assertThat(customSet.size()).isOne();
    }

    @Test
    void givenSetFullCapacity_whenFirstElementRemoved_thenSizeShouldBeThree_thenContainShouldBeFalse() {

        // given
        CustomSet<String> customSet = new SimCustomHashSet<>();
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

    @Test
    void givenSetFullCapacity_whenExistentElementAdded_thenSizeShouldNotChange() {

        // given
        CustomSet<String> customSet = new SimCustomHashSet<>();
        customSet.add("element1");
        customSet.add("element2");
        customSet.add("element3");
        customSet.add("element4");

        // when
        customSet.add("element3");

        // then
        assertThat(customSet.size()).isEqualTo(4);
    }
}