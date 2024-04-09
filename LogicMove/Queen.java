package LogicMove;

import java.lang.Math;

public class Queen extends ChessPieces {
    private int x;
    private int y;
    private String color;
    private String name;
    private boolean isAlive;

    public Queen(int x, int y, String color, String name) {
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
        int dx, dy;

        if (newPosition[0] != currentPosition[0] && newPosition[1] != currentPosition[1]) {
            if (Math.abs(newPosition[0] - currentPosition[0]) != Math.abs(newPosition[1] - currentPosition[1])) {
                return false;
            }
        }
        if (newPosition[0] - currentPosition[0] == 0) {
            dx = 0;
        } else {
            dx = (newPosition[0] - currentPosition[0]) / Math.abs(newPosition[0] - currentPosition[0]);
        }
        if (newPosition[1] - currentPosition[1] == 0) {
            dy = 0;
        } else {
            dy = (newPosition[1] - currentPosition[1]) / Math.abs(newPosition[1] - currentPosition[1]);
        }
        
        while (currentPosition[0] != newPosition[0] || currentPosition[1] != newPosition[1]) {
            currentPosition[0] += dx;
            currentPosition[1] += dy;
            if (currentPosition[0] == newPosition[0] && currentPosition[1] == newPosition[1] && board[newPosition[0]][newPosition[1]] != null) {
                return canKill(newPosition, currentPosition, board);
            }
            if (board[currentPosition[0]][currentPosition[1]] != null) {
                return false;
            }
        }
        return true;
    }

    public boolean canKill(int[] newPosition, int[] currentPosition, ChessPieces[][] board) {
        if (board[newPosition[0]][newPosition[1]] != null) {
            if (board[newPosition[0]][newPosition[1]].getColor() != this.getColor()) {
                return true;
            }
        }
        return false;
    }

}
