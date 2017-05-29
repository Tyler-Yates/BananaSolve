package tyates.bananasolve.dictionary;

import com.google.common.collect.ImmutableSet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import static tyates.bananasolve.util.StringStandardizer.standardize;

public abstract class AbstractTextFileDictionary implements Dictionary {
    private static final Pattern ALPHABET_CHARS_ONLY = Pattern.compile("[a-z]+");

    private final Set<String> validWords;

    AbstractTextFileDictionary(final String resourceFileName) throws FileNotFoundException {
        final File dictFile = new File(getClass().getClassLoader().getResource(resourceFileName).getFile());
        final Scanner scanner = new Scanner(dictFile);

        final ImmutableSet.Builder<String> wordBuilder = ImmutableSet.builder();
        while (scanner.hasNext()) {
            final String word = standardize(scanner.nextLine());
            if (ALPHABET_CHARS_ONLY.matcher(word).matches()) {
                wordBuilder.add(word);
            }
        }
        validWords = wordBuilder.build();
    }

    @Override
    public final boolean isValidWord(final String word) {
        return validWords.contains(standardize(word));
    }

    @Override
    public final Set<String> validWords() {
        return validWords;
    }
}
