package ConsoleWriter;

public class ConsoleNonSyncWriter implements IConsoleWriter {

    int lines;
    int rows;
    int currentCharIndex = 0;
    char symbolToPrint = '-';

    public ConsoleNonSyncWriter(int lines, int rows) {
        this.lines = lines;
        this.rows = rows;
    }

    @Override
    public synchronized void printSymbol(char symbol) {

        System.out.print( symbol);
        currentCharIndex++;
        if (symbolToPrint == '-') {
            symbolToPrint = '|';
        } else {
            symbolToPrint = '-';
        }
        if (currentCharIndex % rows == 0) System.out.println();
    }

}
