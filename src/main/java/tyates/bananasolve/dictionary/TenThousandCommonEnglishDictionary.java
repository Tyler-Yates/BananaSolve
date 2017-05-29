package tyates.bananasolve.dictionary;

import com.google.common.collect.ImmutableSet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

import static tyates.bananasolve.util.StringStandardizer.standardize;

public class TenThousandCommonEnglishDictionary implements Dictionary {

    private final Set<String> validWords;

    public TenThousandCommonEnglishDictionary() throws FileNotFoundException {
        final File dictFile = new File(getClass().getClassLoader().getResource("dictionary.txt").getFile());
        final Scanner scanner = new Scanner(dictFile);

        final ImmutableSet.Builder<String> wordBuilder = ImmutableSet.builder();
        while (scanner.hasNext()) {
            wordBuilder.add(standardize(scanner.nextLine()));
        }
        validWords = wordBuilder.build();
    }

    @Override
    public boolean isValidWord(String word) {
        return validWords.contains(standardize(word));
    }

    @Override
    public Set<String> validWords() {
        return validWords;
    }
}
