package com.przemyslawjakubowski.output;

import java.util.HashMap;
import java.util.Map;

public class LanguageStrings {
    private Map<String, String> languageStrings;

    LanguageStrings() {
        languageStrings = new HashMap<>();
    }

    void addLanguageString(String key, String value){
        languageStrings.put(key, value);
    }
}
