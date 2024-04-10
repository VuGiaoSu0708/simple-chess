package LogicMove;

import java.lang.Math;

public class Knight extends ChessPieces {
    private int x;
    private int y;
    private String color;
    private String name;
    private boolean isAlive;

    public Knight(int x, int y, String color, String name) {
        super(x, y, color, name);
        this.x = x;
        this.y = y;
        this.color = color;
        this.name = name;
        this.isAlive = true;
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void kill() {
        this.isAlive = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public String toString() {
        return this.color + "_" + this.name;
    }

    public boolean canMove(int[] newPosition, int[] currentPosition, ChessPieces[][] board) {

        if (Math.abs(newPosition[0] - currentPosition[0]) == 2 && Math.abs(newPosition[1] - currentPosition[1]) == 1 || Math.abs(newPosition[0] - currentPosition[0]) == 1 && Math.abs(newPosition[1] - currentPosition[1]) == 2) {
            if (board[newPosition[0]][newPosition[1]] == null) {
                return true;
            } else {
                return canKill(newPosition, currentPosition, board);
            }
        } else {
            return false;
        }
    }

    public boolean canKill(int[] newPosition, int[] currentPosition, ChessPieces[][] board) {
        if (board[newPosition[0]][newPosition[1]] != null) {
            if (!board[newPosition[0]][newPosition[1]].getColor().equals(this.getColor())) {
                return true;
            }
        }
        return false;
    }

}
