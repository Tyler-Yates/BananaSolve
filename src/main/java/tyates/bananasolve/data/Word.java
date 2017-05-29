package tyates.bananasolve.data;

import tyates.bananasolve.util.Direction;

import static tyates.bananasolve.util.StringStandardizer.standardize;

public class Word {
    private String word;
    private final Direction direction;

    public Word(final String word, final Direction direction) {
        this.word = standardize(word);
        this.direction = direction;
    }

    public String string() {
        return word;
    }

    public Direction getDirection() {
        return direction;
    }
}
