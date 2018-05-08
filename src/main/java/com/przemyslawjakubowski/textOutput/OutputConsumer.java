package com.przemyslawjakubowski.textOutput;

import java.util.function.Consumer;

public class OutputConsumer {
    Consumer<String> output;
    LanguageStrings languageStrings;

    public OutputConsumer(Consumer<String> output) {
        this.output = output;
    }

    public OutputConsumer(Consumer<String> output, LanguageStrings languageStrings) {
        this(output);
        this.languageStrings = languageStrings;
    }

    public void accept(String stringToPrint) {
        output.accept(stringToPrint);
    }

    public void accept(OutputOption outputOption) {
        String outputText = languageStrings.getLanguageString(outputOption);
        output.accept(outputText);
    }

    public void accept(OutputOption exceptionFieldNotEmpty, ReplacePattern... replacePatterns) {
        String outputText = languageStrings.getLanguageString(exceptionFieldNotEmpty);

        for(ReplacePattern replacePattern : replacePatterns){
            outputText = outputText.replace(replacePattern.getPattern(), replacePattern.getNewValue());
        }

        output.accept(outputText);
    }
}
