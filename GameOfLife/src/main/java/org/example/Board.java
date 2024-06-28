package org.example;

public class Board {
    private final Cell[][] cells;
    private final int rowSize;
    private final int colSize;

    public Board(Cell[][] cells) throws IllegalArgumentException {
        if (cells == null) {
            throw new IllegalArgumentException();
        }
        this.cells = cells;
        rowSize = cells.length;
        colSize = cells[0].length;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void nextGeneration() {
        Cell[][] newCells = new Cell[rowSize][colSize];

        deepCopyCellMatrix(cells,newCells);
        calculateNextStateMatrix(newCells);
        deepCopyCellMatrix(newCells,cells);
    }

    private void calculateNextStateMatrix(Cell[][] matrix){
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                matrix[i][j].setState(calculateCellNextState(i,j));
            }
        }
    }

    private boolean calculateCellNextState(int row,int col) {

        int numberOfNeighbor = countAliveNeighbor(row,col);
        Cell cell = cells[row][col];
        if(cell.getState()){
            //underpopulation and overpopulation
            return numberOfNeighbor >= 2 && numberOfNeighbor <= 3;
        }
        else {
            // reproduction
            return numberOfNeighbor == 3;
        }
    }
    private int countAliveNeighbor(int row,int column) {
        int count = 0;

        for(Direction dir : Direction.values()) {
            int dirRow = row+dir.getVector()[0];
            int dirCol = column+dir.getVector()[1];
            if(!isPositionValid(dirRow,dirCol)){
                continue;
            }
            Cell cell = cells[dirRow][dirCol];
            if(cell.getState()){count++;}
        }

        return count;
    }

    private void deepCopyCellMatrix(Cell[][] copyFrom, Cell[][] copyTo) {
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                try {
                    copyTo[i][j] = (Cell) copyFrom[i][j].clone();
                }
                catch (CloneNotSupportedException e){
                    System.err.println(e);
                }

            }
        }
    }

    private boolean isPositionValid(int row, int col) {
        return row >= 0 && row < rowSize - 1 &&
                col >= 0 && col < colSize - 1;
    }
}
