package com.przemyslawjakubowski.output;

import java.io.*;

public class LanguageFileReader {

    public static LanguageStrings getLanguageStringsFromFile(String path, OutputConsumer outputConsumer) {
        LanguageStrings languageStrings = new LanguageStrings();
        try(BufferedReader reader = new BufferedReader(new FileReader(new File(path)))){
            String line;
            String textName;
            String textContent;
            while ((line = reader.readLine()) != null && !line.equals("")){
                textName = line.substring(0, line.indexOf(":")).trim();
                textContent = line.substring(line.indexOf(":") + 1).trim();
                textContent = textContent.replaceAll("\\\\n", System.lineSeparator());
                try{
                    languageStrings.addLanguageString(OutputOption.valueOf(textName.toUpperCase()), textContent);
                } catch (IllegalArgumentException e){
                    outputConsumer.accept("Unrecognized game string: " + textName);
                }
            }
        } catch (FileNotFoundException e) {
            outputConsumer.accept("File not found, setting default language - english");
        } catch (IOException e) {
            outputConsumer.accept("Problem with file, setting default language - english");
        }
        return languageStrings;
    }

    public static LanguageStrings getDefaultLanguageStrings(OutputConsumer outputConsumer) {
        outputConsumer.accept("Language file not specified. Starting with default language - english\n");
        return new LanguageStrings();
    }
}

