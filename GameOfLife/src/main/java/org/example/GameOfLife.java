package org.example;

public class GameOfLife {

    public static void startGameOfLife() {
        Board board = BoardFactory.createInitialBoard2();
        BoardDisplayer.display(board);
        for (int i = 0; i < 250; i++) {
            clearConsole();
            board.nextGeneration();
            BoardDisplayer.display(board);
            waitMilliseconds(100);
        }
    }

    private static void waitMilliseconds(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        }
        catch (InterruptedException e) {
            System.err.println(e);
        }

    }

    private static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                // For Windows, run the 'cls' command
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // For Unix-like systems, use ANSI escape codes
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
