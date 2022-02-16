package ConsoleWriter;

public class ConsoleWriter {
    public static void main(String[] args) {

        int rows = 100;
        int lines = 100;

        System.out.println("Non Sync");

        ConsoleNonSyncWriter nonSyncWriter = new ConsoleNonSyncWriter(lines, rows);
        var nonSyncDash = new ConsoleThread('-', nonSyncWriter, lines, rows);
        var nonSyncPipe = new ConsoleThread('|', nonSyncWriter, lines, rows);
        nonSyncDash.start();
        nonSyncPipe.start();


        try {
            nonSyncDash.join();
            nonSyncPipe.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        System.out.println("Sync");

        ConsoleSyncWriter syncWriter = new ConsoleSyncWriter(lines, rows);
        var syncDash = new ConsoleThread('-', syncWriter, lines, rows);
        var syncPipe = new ConsoleThread('|', syncWriter, lines, rows);
        syncDash.start();
        syncPipe.start();
    }
}
