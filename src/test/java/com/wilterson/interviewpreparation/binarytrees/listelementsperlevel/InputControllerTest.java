package com.wilterson.interviewpreparation.binarytrees.listelementsperlevel;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import org.junit.jupiter.api.Test;

class InputControllerTest {

    private InputController inputController;

    @Test
    void givenNumbers_whenCapturingUserInput_thenShouldBuildList() {

        // given
        InputStream inputStream = new ByteArrayInputStream("10\n2\n7\n36\n0\n3\nq".getBytes());
         inputController = new InputController(inputStream);

        // when
        List<Integer> input = inputController.captureInput();

        // then
        assertThat(input)
                .isNotNull()
                .isNotEmpty()
                .hasSize(6)
                .containsAll(input);
    }
}