package tyates.bananasolve.dictionary;

import com.google.common.collect.ImmutableSet;

import java.util.Collection;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * A {@link Dictionary} implementation that is backed by an immutable Set.
 */
class ImmutableSetDictionary extends AbstractDictionary {
    private static final Pattern ALPHABET_CHARS_ONLY = Pattern.compile("[a-z]+");

    ImmutableSetDictionary(final Collection<String> validWords) {
        super(buildImmutableSet(validWords));
    }

    private static Set<String> buildImmutableSet(final Collection<String> validWords) {
        final ImmutableSet.Builder<String> wordBuilder = ImmutableSet.builder();
        for (final String word : validWords) {
            // Only add words that only contain characters 'a' - 'z'
            if (ALPHABET_CHARS_ONLY.matcher(word).matches()) {
                wordBuilder.add(word);
            }
        }
        return wordBuilder.build();
    }
}
