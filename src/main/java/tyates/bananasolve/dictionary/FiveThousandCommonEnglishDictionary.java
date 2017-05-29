package tyates.bananasolve.dictionary;

import java.io.FileNotFoundException;

public class FiveThousandCommonEnglishDictionary extends AbstractTextFileDictionary {

    public FiveThousandCommonEnglishDictionary() throws FileNotFoundException {
        super("five_thousand_english_dictionary.txt");
    }
}
