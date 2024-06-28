package org.example;

public class Cell implements Cloneable {
    private boolean state;

    public Cell() {
        state = false;
    }
    public Cell(boolean state) {
        this.state = state;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state){
        this.state = state;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        super.clone();
        Cell cell = new Cell();
        cell.state = this.state;

        return cell;
    }
}
