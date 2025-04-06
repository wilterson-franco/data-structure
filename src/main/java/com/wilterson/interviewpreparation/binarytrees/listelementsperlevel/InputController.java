package com.wilterson.interviewpreparation.binarytrees.listelementsperlevel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputController {

    private final InputStream source;

    public InputController(InputStream source) {
        this.source = source;
    }

    public List<Integer> captureInput() {
        boolean done = false;
        Scanner myScanner = new Scanner(source);
        List<Integer> input = new ArrayList<>();

        System.out.println("Enter a number for the tree and any non-number character for when you are done.");

        while (!done) {
            System.out.print("> ");
            String userInput = myScanner.next();
            try {
                input.add(Integer.parseInt(userInput));
            } catch (NumberFormatException exception) {
                done = true;
            }
        }

        return input;
    }
}
