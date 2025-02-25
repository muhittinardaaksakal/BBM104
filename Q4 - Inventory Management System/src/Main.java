import java.io.File;

public class Main {
    public static void main(String[] args) {

        String input = args[0];
        File inputFile = new File(input);
        String outputFilePath = args[1];
        String[] inputlines = FileInput.readFile(input, true, false);
        ProcessCommands processCommands = new ProcessCommands(outputFilePath);
        processCommands.executeCommands(inputlines);
    }
}
