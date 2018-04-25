package com.przemyslawjakubowski;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Supplier<String> userInputProvider = new Scanner(System.in)::nextLine;
        Consumer<String> output = System.out::println;

        new XOGame(userInputProvider, output).start();
    }
}
