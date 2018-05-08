package com.przemyslawjakubowski;

import com.przemyslawjakubowski.textOutput.LanguageFileReader;
import com.przemyslawjakubowski.textOutput.LanguageStrings;
import com.przemyslawjakubowski.textOutput.OutputConsumer;
import com.przemyslawjakubowski.userInput.UserInputProvider;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        UserInputProvider userInputProviderProvider = new UserInputProvider(new Scanner(System.in)::nextLine);
        OutputConsumer outputConsumer = new OutputConsumer(System.out::println);
        LanguageStrings languageStrings;

        if(args.length > 0){
            languageStrings = LanguageFileReader.getLanguageStringsFromFile(args[0], outputConsumer);
        }
        else {
            languageStrings = LanguageFileReader.getDefaultLanguageStrings(outputConsumer);
        }

        outputConsumer = new OutputConsumer(System.out::println, languageStrings);
        new XOGame(userInputProviderProvider, outputConsumer).start();
    }
}
