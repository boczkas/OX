package com.przemyslawjakubowski;

import com.przemyslawjakubowski.output.LanguageFileReader;
import com.przemyslawjakubowski.output.OutputConsumer;

import java.util.Scanner;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Supplier<String> userInputProvider = new Scanner(System.in)::nextLine;
        OutputConsumer outputConsumer = new OutputConsumer(System.out::println);
        LanguageFileReader languageFileReader = new LanguageFileReader("ENG.lang", outputConsumer);
        outputConsumer = new OutputConsumer(System.out::println, languageFileReader.getLanguageStrings());
        new XOGame(userInputProvider, outputConsumer).start();
    }
}
