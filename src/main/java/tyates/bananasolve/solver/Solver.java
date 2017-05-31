package tyates.bananasolve.solver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static tyates.bananasolve.util.StringStandardizer.standardize;

public class Solver {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter tiles separated by commas: ");
        final String input = reader.readLine();
        final String[] parts = input.split(",");
        final List<Character> tiles = new ArrayList<>();
        for (final String part : parts) {
            final String tile = standardize(part);
            if (Pattern.matches("[a-z]", tile)) {
                tiles.add(tile.charAt(0));
            } else {
                System.err.printf("Invalid tile! '%s' is not a recognized tile\n", tile);
                return;
            }
        }

        System.out.println("Tiles: " + tiles);
    }
}
