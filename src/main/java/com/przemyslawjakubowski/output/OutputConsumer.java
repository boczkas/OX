package com.przemyslawjakubowski.output;

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
        String outputText = languageStrings.getLangueString(outputOption);
        output.accept(outputText);
    }

    public void accept(OutputOption exceptionFieldNotEmpty, ReplacePattern... replacePatterns) {
        String outputText = languageStrings.getLangueString(exceptionFieldNotEmpty);

        for(ReplacePattern replacePattern : replacePatterns){
            outputText = outputText.replace(replacePattern.getPattern(), replacePattern.getReplaceWith());
        }

        output.accept(outputText);
    }
}
