package com.przemyslawjakubowski;

import com.przemyslawjakubowski.textOutput.LanguageFileReader;
import com.przemyslawjakubowski.textOutput.LanguageStrings;
import com.przemyslawjakubowski.textOutput.OutputConsumer;

import java.util.Scanner;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Supplier<String> userInputProvider = new Scanner(System.in)::nextLine;
        OutputConsumer outputConsumer = new OutputConsumer(System.out::println);
        LanguageStrings languageStrings;

        if(args.length > 0){
            languageStrings = LanguageFileReader.getLanguageStringsFromFile(args[0], outputConsumer);
        }
        else {
            languageStrings = LanguageFileReader.getDefaultLanguageStrings(outputConsumer);
        }

        outputConsumer = new OutputConsumer(System.out::println, languageStrings);
        new XOGame(userInputProvider, outputConsumer).start();
    }
}
