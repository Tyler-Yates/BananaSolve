package tyates.bananasolve.dictionary;

import java.io.FileNotFoundException;

public class TenThousandCommonEnglishDictionary extends AbstractTextFileDictionary {

    public TenThousandCommonEnglishDictionary() throws FileNotFoundException {
        super("ten_thousand_english_dictionary.txt");
    }
}
