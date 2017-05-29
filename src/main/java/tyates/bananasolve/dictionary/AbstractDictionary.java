package tyates.bananasolve.dictionary;

import com.google.common.collect.ImmutableSet;

import java.util.Collection;
import java.util.Set;

import static tyates.bananasolve.util.StringStandardizer.standardize;

/**
 * Abstract class that uses a {@link Collection} for storing valid words.
 */
public abstract class AbstractDictionary implements Dictionary {
    private final Collection<String> validWords;

    AbstractDictionary(final Collection<String> validWords) {
        this.validWords = validWords;
    }

    @Override
    public final boolean isValidWord(String word) {
        return validWords.contains(standardize(word));
    }

    @Override
    public final Set<String> validWords() {
        return ImmutableSet.copyOf(validWords);
    }
}
