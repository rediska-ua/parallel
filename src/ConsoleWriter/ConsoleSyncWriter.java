package ConsoleWriter;

public class ConsoleSyncWriter implements IConsoleWriter {

    int currentCharIndex;
    int rows;
    int lines;
    char symbolToPrint = '-';

    ConsoleSyncWriter(int lines, int rows) {
        currentCharIndex = 0;
        this.lines = lines;
        this.rows = rows;
    }

    @Override
    public synchronized void printSymbol(char symbol) {

            while (symbolToPrint == symbol) {
                try {
                    wait();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            System.out.print(symbol);
            currentCharIndex++;
            if (symbolToPrint == '-') {
                symbolToPrint = '|';
            } else {
                symbolToPrint = '-';
            }

            if (currentCharIndex % rows == 0) System.out.println();

            notify();

    }


}
