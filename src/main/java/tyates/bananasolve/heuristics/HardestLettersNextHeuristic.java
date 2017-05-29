package tyates.bananasolve.heuristics;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

public class HardestLettersNextHeuristic implements NextWordHeuristic {
    private static final Set<Character> HARD_CHARACTERS = ImmutableSet.of('z', 'v', 'q', 'x', 'j');

    @Override
    public String getFirstWordToPlace(Set<String> possibleWords) {
        return "";
    }
}
