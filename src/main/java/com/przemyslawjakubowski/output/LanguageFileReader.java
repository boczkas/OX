package com.przemyslawjakubowski.output;

import java.io.*;

public class LanguageFileReader {
    OutputConsumer outputConsumer;
    File languageFile;
    LanguageStrings languageStrings;

    public LanguageFileReader(String path, OutputConsumer outputConsumer) {
        this.outputConsumer = outputConsumer;
        this.languageFile = new File(path);
        this.languageStrings = new LanguageStrings();
        parseFile();
    }

    private void parseFile() {
        try(BufferedReader reader = new BufferedReader(new FileReader(languageFile))){
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
    }

    public LanguageStrings getLanguageStrings() {
        return languageStrings;
    }
}

