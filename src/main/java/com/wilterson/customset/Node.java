package com.wilterson.customset;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class Node {

    @Getter
    private Object data;

    @Setter
    private Node next;

    @Setter
    private Node previous;
}
