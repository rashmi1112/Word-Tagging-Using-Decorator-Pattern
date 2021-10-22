package textdecorators.util;

import textdecorators.Enums.Decorators;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Logger to set the debug level and print the debug messages.
 *
 * @author Rashmi Badadale
 */
public class MyLogger {

    public static MyLogger myLogger;
    private DebugLevel debugLevel;
    private BufferedWriter bufferedWriter;

    public void openBufferedWriter() {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter("log.txt"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void closeBufferedWriter() {
        try {
            bufferedWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private MyLogger() {

    }

    public static MyLogger getInstance() {
        if (myLogger == null) {
            myLogger = new MyLogger();
        }
        return myLogger;
    }

    /**
     * Method to set the debug level
     *
     * @param levelIn int debug level value from the command line
     */
    public void setDebugValue(int levelIn) {
        switch (levelIn) {
            case 6:
                debugLevel = DebugLevel.SENTENCE_DECORATOR;
                break;
            case 5:
                debugLevel = DebugLevel.SPELLCHECKER_DECORATOR;
                break;
            case 4:
                debugLevel = DebugLevel.KEYWORD_DECORATOR;
                break;
            case 3:
                debugLevel = DebugLevel.MOST_FREQUENT_DECORATOR;
                break;
            case 2:
                debugLevel = DebugLevel.INPUT_DETAILS;
                break;
            case 1:
                debugLevel = DebugLevel.FILE_PROCESSOR;
                break;
            default:
                debugLevel = DebugLevel.NONE;
                break;
        }
    }

    /**
     * Method to print the debug message on the std output.
     *
     * @param message String debug message
     * @param levelIn int debug level
     */
    public void writeMessage(String message,
                             DebugLevel levelIn) {
        if (levelIn == debugLevel) {
            try {
                switch (levelIn) {
                    case SENTENCE_DECORATOR:
                        bufferedWriter.write(Decorators.SENTENCE_DECORATOR.getDecorator() + message + Decorators.SENTENCE_DECORATOR.getDecorator() + "\n");
                        break;
                    case SPELLCHECKER_DECORATOR:
                        bufferedWriter.write(Decorators.SPELLCHECK_DECORATOR.getDecorator() + message + Decorators.SPELLCHECK_DECORATOR.getDecorator() + "\n");
                        break;
                    case KEYWORD_DECORATOR:
                        bufferedWriter.write(Decorators.KEYWORD_DECORATOR.getDecorator() + message + Decorators.KEYWORD_DECORATOR.getDecorator() + "\n");
                        break;
                    case MOST_FREQUENT_DECORATOR:
                        bufferedWriter.write(Decorators.MOST_FREQUENT_WORD_DECORATOR.getDecorator() + message + Decorators.MOST_FREQUENT_WORD_DECORATOR.getDecorator() + "\n");
                        break;
                    case INPUT_DETAILS:
                        bufferedWriter.write("INPUT_DETAILS " + message + " INPUT_DETAILS" + "\n");
                        break;
                    case FILE_PROCESSOR:
                        bufferedWriter.write("FILE_PROCESSOR " + message + " FILE_PROCESSOR" + "\n");
                        break;
                    default:
                        debugLevel = DebugLevel.NONE;
                        break;
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }


    /**
     * Overriding toString method
     *
     * @return String
     */

    public String toString() {
        return "The debug level has been set to the following " + debugLevel;
    }

    /**
     * Enum to store various level for debugging.
     */
    public enum DebugLevel {
        FILE_PROCESSOR,
        KEYWORD_DECORATOR,
        SENTENCE_DECORATOR,
        SPELLCHECKER_DECORATOR,
        MOST_FREQUENT_DECORATOR,
        INPUT_DETAILS,
        NONE
    }

}
