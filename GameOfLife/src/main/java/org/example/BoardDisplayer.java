package org.example;

public class BoardDisplayer {

    public static void display(Board board){
        String aliveMark = "■";
        String deadMark = "□";
        Cell[][] cells = board.getCells();
        int row = cells.length;
        int column = cells[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if(cells[i][j].getState()){
                    System.out.print(aliveMark);
                }
                else {
                    System.out.print(deadMark);
                }
                System.out.print("  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
