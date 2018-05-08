package com.przemyslawjakubowski.output;

import java.util.HashMap;
import java.util.Map;

public class LanguageStrings {
    private Map<OutputOption, String> languageStrings;

    LanguageStrings() {
        languageStrings = new HashMap<>();
    }

    void addLanguageString(OutputOption key, String value){
        languageStrings.put(key, value);
    }

    String getLanguageString(OutputOption key){
        return languageStrings.get(key);
    }
}
