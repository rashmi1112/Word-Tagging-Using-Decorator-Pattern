package textdecorators.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class to parse the input file and send the parsed input to InputDetails.
 *
 * @author Rashmi Badadale
 */

public class FileProcessor {
    private final String inputFileName;
    private final MyLogger myLogger = MyLogger.getInstance();
    private BufferedReader bufferedReader;

    /**
     * Constructor for FileProcessor
     * @param inputFileNameIn input filename
     */
    public FileProcessor(String inputFileNameIn) {
        inputFileName = inputFileNameIn;
        try {
            if (!Files.exists(Paths.get(inputFileName))) {
                throw new FileNotFoundException("Invalid input file or input file in incorrect location" + " " + inputFileName);
            }
            bufferedReader = new BufferedReader(new FileReader(inputFileName));
            myLogger.writeMessage("Constructor of FileProcessor was called for reading " + inputFileNameIn, MyLogger.DebugLevel.FILE_PROCESSOR);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Function to read the line from BufferedReader
     * @return String Line read
     * @throws IOException On any I/O errors while reading from input file
     */
    public String poll() throws IOException {
        return bufferedReader.readLine();
    }

    /**
     * Function to close the BufferedReader
     */

    public void close() {
        try {
            bufferedReader.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.exit(0);
        }
        myLogger.writeMessage("FileProcessor for " + inputFileName + " closed!", MyLogger.DebugLevel.FILE_PROCESSOR);
    }

}
