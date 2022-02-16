package ConsoleWriter;

public class ConsoleThread extends Thread {
    private final char symbol;
    private final IConsoleWriter counter;
    private final int lines;
    private final int rows;

    ConsoleThread(char symbol, IConsoleWriter counter, int lines, int rows) {
        this.symbol = symbol;
        this.counter = counter;
        this.lines = lines;
        this.rows = rows;
    }

    @Override
    public void run() {
        for (int i = 0; i < lines; ++i) {
            for (int j = 0; j < rows; ++j) {
                counter.printSymbol(symbol);
            }
        }
    }
}
