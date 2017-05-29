package tyates.bananasolve.dictionary;

import java.io.FileNotFoundException;

public class ThousandCommonEnglishDictionary extends AbstractTextFileDictionary {

    public ThousandCommonEnglishDictionary() throws FileNotFoundException {
        super("thousand_english_dictionary.txt");
    }
}
