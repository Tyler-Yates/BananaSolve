package tyates.bananasolve.solver;

import tyates.bananasolve.data.Board;
import tyates.bananasolve.data.HashTileGroup;
import tyates.bananasolve.dictionary.Dictionaries;
import tyates.bananasolve.dictionary.Dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static tyates.bananasolve.util.StringStandardizer.standardize;

public class SolverRunner {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter tiles: ");
        final String input = reader.readLine().toLowerCase().replaceAll("[^a-z]", "");
        final String[] parts = input.split("");
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

        final Dictionary dictionary = Dictionaries.fiveThousandEnglishWordsDictionary();
        final Solver solver = new BruteForceSolver(dictionary);
        final Board solved = solver.solve(new HashTileGroup(tiles));
        System.out.println(solved);
    }
}
