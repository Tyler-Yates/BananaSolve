package tyates.bananasolve.dictionary;

import com.google.common.collect.ImmutableSet;
import com.google.common.primitives.Chars;
import tyates.bananasolve.data.TileGroup;
import tyates.bananasolve.util.CountingMap;
import tyates.bananasolve.util.HashCountingMap;

import java.util.Collection;
import java.util.HashSet;
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

    @Override
    public Set<String> validWordsPossible(final TileGroup tileGroup) {
        final Set<String> validWordsPossible = new HashSet<>();
        final CountingMap<Character> tileCounts = tileGroup.getTileCounts();
        for (final String word : validWords()) {
            final HashCountingMap<Character> wordCountingMap = new HashCountingMap<>(Chars.asList(word.toCharArray()));
            if (tileCounts.subsumes(wordCountingMap)) {
                validWordsPossible.add(word);
            }
        }
        return validWordsPossible;
    }

    @Override
    public Set<String> validWordsPossible(final TileGroup tileGroup, final Restriction restriction) {
        final Set<String> validWordsPossible = new HashSet<>();
        final CountingMap<Character> tileCounts = tileGroup.getTileCounts();
        for (final String word : validWords()) {
            final HashCountingMap<Character> wordCountingMap = new HashCountingMap<>(Chars.asList(word.toCharArray()));
            if (restriction.permissible(word) && tileCounts.subsumes(wordCountingMap)) {
                validWordsPossible.add(word);
            }
        }
        return validWordsPossible;
    }
}
