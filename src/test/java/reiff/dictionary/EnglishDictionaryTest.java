package reiff.dictionary;

import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EnglishDictionaryTest {

    @Test
    public void getDefinition() throws CsvValidationException, IOException {
        // given
        EnglishDictionary dictionary = new EnglishDictionary();

        // when
        List<String> definitions = dictionary.getDefinition("Mispaint");

        // then
        assertEquals("v. t.To paint ill, or wrongly.", definitions.get(0));
    }

    @Test
    public void getManyDefinitions() throws CsvValidationException, IOException {
        // given
        EnglishDictionary dictionary = new EnglishDictionary();

        // when
        List<String> definitions = dictionary.getDefinition("wiry");

        // then
        assertEquals("a.Made of wire; like wire; drawn out like wire.", definitions.get(0));
        assertEquals("a.Capable of endurance; tough; sinewy; as, a wiry frame or constitution.", definitions.get(1));

    }

    @Test
    public void wordDoesNotExist() throws CsvValidationException, IOException {
        // given
        EnglishDictionary dictionary = new EnglishDictionary();

        // when
        List<String> definitions = dictionary.getDefinition("zip");

        // then
        assertArrayEquals(new String[]{}, definitions.toArray(new String[0]));
    }

}
