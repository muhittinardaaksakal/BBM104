import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class for handling BNF (Backus-Naur Form) grammars.
 */
public class BNF {
    private Map<String, List<String>> grammarMap;

    /**
     * Constructor for BNF class.
     * Initializes the grammar map.
     */
    public BNF() {
        grammarMap = new HashMap<>();
    }

    /**
     * Method to add a production rule to the grammar.
     *
     * @param nonTerminal The non-terminal symbol.
     * @param item        The expansion for the non-terminal.
     */
    public void addProductionRule(String nonTerminal, String item) {
        List<String> productRules = grammarMap.get(nonTerminal);
        if (productRules == null) {
            productRules = new ArrayList<>();
            grammarMap.put(nonTerminal, productRules);
        }
        productRules.add(item);
    }

    /**
     * Method to generate all possible strings based on the BNF grammar.
     *
     * @param startLetter The starting symbol (usually 'S').
     * @return A list of all possible strings.
     */
    public List<String> convertStrings(String startLetter) {
        List<String> finalResult = new ArrayList<>();
        convertOfString(startLetter, finalResult);
        return finalResult;
    }

    /**
     * Helper method to recursively convert BNF strings.
     *
     * @param letter      The current symbol being processed.
     * @param finalResult The list to store the final converted strings.
     */
    private void convertOfString(String letter, List<String> finalResult) {
        // If the symbol is a terminal, add it to the result list
        if (!isNonTerminal(letter)) {
            finalResult.add(letter);
            return;
        }

        // If the symbol is a non-terminal, recursively expand it
        List<String> items = grammarMap.getOrDefault(letter, Collections.emptyList());
        StringBuilder current = new StringBuilder();
        boolean isFirst = true;
        for (String item : items) {
            List<String> nestedResults = new ArrayList<>();
            for (char c : item.toCharArray()) {
                List<String> nestedExpansions = new ArrayList<>();
                convertOfString(String.valueOf(c), nestedExpansions);
                nestedResults.addAll(nestedExpansions);
            }
            if (!isFirst) {
                current.append("|");
            }
            current.append(String.join("", nestedResults));
            isFirst = false;
        }
        finalResult.add("(" + current.toString() + ")");
    }

    /**
     * Checks if a given symbol is a non-terminal.
     *
     * @param symbol The symbol to check.
     * @return True if the symbol is a non-terminal, false otherwise.
     */
    private static boolean isNonTerminal(String symbol) {
        return Character.isUpperCase(symbol.charAt(0));
    }

    /**
     * Main method to execute BNF conversion.
     *
     * @param args Command line arguments (input and output file paths).
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java BNF <input_file> <output_file>");
            return;
        }

        String input = args[0];
        String outputFile = args[1];

        BNF generator = new BNF();

        // Read the BNF grammar from input file
        String[] lines = FileInput.readFile(input, true, true);
        if (lines != null) {
            for (String line : lines) {
                String[] parts = line.split("->");
                String nonTerminal = parts[0];
                String item = parts[1];
                generator.addProductionRule(nonTerminal, item);
            }
        } else {
            System.out.println("Error reading input file.");
            return;
        }

        // Generate strings based on the BNF grammar
        List<String> strings = generator.convertStrings("S");

        // Write the generated strings to the output file
        StringBuilder output = new StringBuilder();
        for (String string : strings) {
            output.append(string);
        }
        FileOutput.writeToFile(outputFile, output.toString(), false, false);

    }
}
