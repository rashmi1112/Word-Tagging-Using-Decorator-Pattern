package textdecorators.driver;

import textdecorators.AbstractTextDecorator;
import textdecorators.KeywordDecorator;
import textdecorators.MostFrequentWordDecorator;
import textdecorators.SentenceDecorator;
import textdecorators.SpellCheckDecorator;
import textdecorators.util.FileDisplayInterface;
import textdecorators.util.InputDetails;
import textdecorators.util.MyLogger;

public class Driver {
    private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 5;

    public static void main(String[] args) {
        try {
            if ((args.length != REQUIRED_NUMBER_OF_CMDLINE_ARGS) ||
                    (args[0].equals("${input}")) || (args[1].equals("${output}")) || (args[2].equals("${misspelledWords}")) ||
                    (args[3].equals("${keywords}")) || (args[4].equals("${debug}"))) {
                throw new IllegalArgumentException("Error: Incorrect number of arguments. Program accepts " + REQUIRED_NUMBER_OF_CMDLINE_ARGS + " arguments.");
            }
            if (args[0].isEmpty() || args[1].isEmpty() || args[2].isEmpty() ||
                    args[3].isEmpty() || args[4].isEmpty()) {
                throw new IllegalArgumentException(" Provided Invalid arguments: Empty arguments");
            }
            if (Integer.parseInt(args[4]) < 0 || Integer.parseInt(args[4]) > 7) {
                throw new IllegalArgumentException("Debug value should be between 1 and 6.");
            }
            if (args[0].equals(args[2]) || args[0].equals(args[3]) || args[2].equals(args[3])) {
                throw new IllegalArgumentException("Input files/Keywords file/Misspelled words file have the same path and name!");
            }
            MyLogger myLogger = MyLogger.getInstance();
            myLogger.setDebugValue(Integer.parseInt(args[4]));
            myLogger.openBufferedWriter();
            InputDetails inputDetails = new InputDetails(args[0], args[1], args[2], args[3]);
            AbstractTextDecorator sentenceDecorator = new SentenceDecorator(null, inputDetails);
            AbstractTextDecorator spellCheckDecorator = new SpellCheckDecorator(sentenceDecorator, inputDetails);
            AbstractTextDecorator keywordDecorator = new KeywordDecorator(spellCheckDecorator, inputDetails);
            AbstractTextDecorator mostFrequentWordDecorator = new MostFrequentWordDecorator(keywordDecorator, inputDetails);
            mostFrequentWordDecorator.processInputDetails();
            ((FileDisplayInterface) inputDetails).writeToFile();
            myLogger.closeBufferedWriter();
        } catch (IllegalArgumentException illegalArgumentException) {
            illegalArgumentException.printStackTrace();
            System.exit(0);
        }
    }
}
