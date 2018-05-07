package com.przemyslawjakubowski.output;

import java.util.function.Consumer;

public class OutputConsumer {
    Consumer<String> output;

    public OutputConsumer(Consumer<String> output) {
        this.output = output;
    }

    public void accept(String stringToPrint) {
        output.accept(stringToPrint);
    }
}
