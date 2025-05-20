package com.wilterson.customset;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Node {

    @Getter
    private Object data;

    @Setter
    private Node next;
}
