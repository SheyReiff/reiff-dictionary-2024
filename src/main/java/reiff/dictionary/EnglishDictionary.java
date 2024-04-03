package reiff.dictionary;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Reads the englishDictionary file ONCE.
 */
public class EnglishDictionary {
    private final Map<String, List<String>> dictionary;

    public EnglishDictionary() throws CsvValidationException, IOException {

        InputStream in = EnglishDictionary.class.getResourceAsStream("/englishDictionary.csv");
        assert in != null;
        CSVReader reader = new CSVReader(new InputStreamReader(in));
        this.dictionary = new HashMap<>();

        String[] record;
        while ((record = reader.readNext()) != null) {
            String word = record[0].toLowerCase();
            String definition = record[1] + record[2];

            if (dictionary.containsKey(word)) {
                dictionary.get(word).add(definition);
            } else {
                List<String> definitions = new ArrayList<>();
                definitions.add(definition);
                dictionary.put(word, definitions);
            }

        }

        reader.close();
    }

    /**
     * @param word to look up.
     * @return a List of definitions for the word, or an empty list if not found.
     */
    public List<String> getDefinition(String word) {
        return dictionary.getOrDefault(word.toLowerCase(), new ArrayList<>());
    }

}
