package textdecorators.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class for storing,retrieving and updating the sentences.
 *
 * @author Rashmi Badadale
 */
public class InputDetails implements FileDisplayInterface {
    private final String inputFileName;
    private final String outputFileName;
    private final String misspelledWords;
    private final String keywords;
    private final StringBuilder stringBuilderInput = new StringBuilder();
    private final StringBuilder stringBuilderMisspelledWords = new StringBuilder();
    private final StringBuilder stringBuilderKeywords = new StringBuilder();
    private final MyLogger myLogger = MyLogger.getInstance();

    /**
     * Constructor for InputDetails
     *
     * @param inputFileNameIn   input file name
     * @param outputFileNameIn  output file name
     * @param misspelledWordsIn misspelled words file name
     * @param keywordsIn        keywords file name
     */
    public InputDetails(String inputFileNameIn, String outputFileNameIn, String misspelledWordsIn, String keywordsIn) {
        inputFileName = inputFileNameIn;
        outputFileName = outputFileNameIn;
        misspelledWords = misspelledWordsIn;
        keywords = keywordsIn;
        myLogger.writeMessage("Constructor of InputDetails was called.", MyLogger.DebugLevel.INPUT_DETAILS);
    }

    /**
     * Getter for the input string builder
     *
     * @return StringBuilder input file string parsed
     */

    public StringBuilder getStringBuilderInput() {
        return new StringBuilder(stringBuilderInput);
    }

    /**
     * Setter for the input string builder
     *
     * @param stringBuilderIn StringBuilder to be set
     */

    public void setStringBuilderInput(StringBuilder stringBuilderIn) {
        stringBuilderInput.replace(0, stringBuilderInput.length(), stringBuilderIn.toString());
    }

    /**
     * Function to read the input file line by line and store in a String Builder
     *
     * @return StringBuilder with parsed input file contents
     */
    public StringBuilder readInputFile() {
        FileProcessor fileProcessor = new FileProcessor(inputFileName);
        try {
            String currentWord;
            currentWord = fileProcessor.poll();
            if (null == currentWord) {
                throw new IllegalArgumentException("Empty Input File!");
            }
            while (currentWord != null) {
                if (currentWord.equals("")) {
                    stringBuilderInput.append("\n");
                } else {
                    if (!currentWord.matches("[a-zA-Z0-9\\.,\\s\\r\\n\\t\\f\\v]+|(^$)")) {
                        throw new IllegalArgumentException("Invalid input format in file " + inputFileName + "!");
                    }
                    stringBuilderInput.append(currentWord).append("\n");
                }
                currentWord = fileProcessor.poll();
            }
            myLogger.writeMessage("The contents of the Input file were read completely.", MyLogger.DebugLevel.INPUT_DETAILS);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.exit(0);
        } finally {
            fileProcessor.close();
        }
        return stringBuilderInput;
    }

    /**
     * Function to read the keyword file line by line and store in a String Builder
     *
     * @return StringBuilder with parsed keyword file contents
     */
    public StringBuilder readKeywords() {
        FileProcessor fileProcessor = new FileProcessor(keywords);
        String currentWord;
        try {
            currentWord = fileProcessor.poll();
            if (null == currentWord) {
                throw new IllegalArgumentException("Empty Keywords File!");
            }
            while (currentWord != null) {
                if (!currentWord.matches("[a-zA-Z0-9\\.,\\s\\r\\n\\t\\f\\v]+|(^$)")) {
                    throw new IllegalArgumentException("Invalid input format in file " + keywords + "!");
                }
                if (!currentWord.matches("(^$)"))
                    stringBuilderKeywords.append(currentWord.trim()).append(" ");
                currentWord = fileProcessor.poll();
            }
            myLogger.writeMessage("The contents of the Keywords file were read completely.", MyLogger.DebugLevel.INPUT_DETAILS);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.exit(0);
        } finally {
            fileProcessor.close();
        }
        return stringBuilderKeywords;
    }

    /**
     * Function to read the misspelledWords file line by line and store in a String Builder
     *
     * @return StringBuilder with parsed misspelled words file contents
     */
    public StringBuilder readMisspelledWords() {
        FileProcessor fileProcessor = new FileProcessor(misspelledWords);
        try {
            String currentWord = fileProcessor.poll();
            if (null == currentWord) {
                throw new IllegalArgumentException("Empty MisspelledWords File!");
            }
            while (currentWord != null) {
                if (!currentWord.matches("[a-zA-Z0-9\\.,\\s\\r\\n\\t\\f\\v]+|(^$)")) {
                    throw new IllegalArgumentException("Invalid input format in file " + misspelledWords + "!");
                }
                if (!currentWord.matches("(^$)"))
                    stringBuilderMisspelledWords.append(currentWord.trim()).append(" ");
                currentWord = fileProcessor.poll();
            }
            myLogger.writeMessage("The contents of the Misspelled file were read completely.", MyLogger.DebugLevel.INPUT_DETAILS);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.exit(0);
        } finally {
            fileProcessor.close();
        }
        return stringBuilderMisspelledWords;
    }

    /**
     * Function to store the set StringBuilder
     *
     * @param stringBuilderIn StringBuilder to be set
     */
    public void storeInput(StringBuilder stringBuilderIn) {
        setStringBuilderInput(stringBuilderIn);
    }

    /**
     * Method to create BufferedWriter for printing the stored output into the output file
     */
    @Override
    public void writeToFile() {

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFileName));
            myLogger.writeMessage("The BufferedWriter was opened.", MyLogger.DebugLevel.INPUT_DETAILS);
            bufferedWriter.write(stringBuilderInput.toString());
            myLogger.writeMessage("The processed input written into the output file.", MyLogger.DebugLevel.INPUT_DETAILS);
            bufferedWriter.close();
            myLogger.writeMessage("The BufferedWriter was closed!", MyLogger.DebugLevel.INPUT_DETAILS);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.exit(0);
        }
    }
}
