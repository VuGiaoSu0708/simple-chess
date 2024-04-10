package LogicMove;

import java.lang.Math;

public class Pawn extends ChessPieces {
    private int x;
    private int y;
    private String color;
    private String name;
    private boolean isAlive;

    public Pawn(int x, int y, String color, String name) {
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
        if (this.getColor().equals("White")) {
            if (currentPosition[0] == 6) {
                if (newPosition[0] == currentPosition[0] - 1 && newPosition[1] == currentPosition[1] && board[newPosition[0]][newPosition[1]] == null) {
                    return true;
                }
                else if (newPosition[0] == currentPosition[0] - 2 && newPosition[1] == currentPosition[1] && board[newPosition[0]][newPosition[1]] == null && board[newPosition[0] + 1][newPosition[1]] == null) {
                    return true;
                }
                else if (newPosition[0] == currentPosition[0] - 1 && Math.abs(newPosition[1] - currentPosition[1]) == 1 && board[newPosition[0]][newPosition[1]] != null) {
                    return canKill(newPosition, currentPosition, board);
                }
            } else {
                if (newPosition[0] == currentPosition[0] - 1 && newPosition[1] == currentPosition[1] && board[newPosition[0]][newPosition[1]] == null) {
                    return true;
                }
                if (newPosition[0] == currentPosition[0] - 1 && Math.abs(newPosition[1] - currentPosition[1]) == 1 && board[newPosition[0]][newPosition[1]] != null) {
                    return canKill(newPosition, currentPosition, board);
                }
            }
        } else {
            if (currentPosition[0] == 1) {
                if (newPosition[0] == currentPosition[0] + 1 && newPosition[1] == currentPosition[1] && board[newPosition[0]][newPosition[1]] == null) {
                    return true;
                }
                else if (newPosition[0] == currentPosition[0] + 2 && newPosition[1] == currentPosition[1] && board[newPosition[0]][newPosition[1]] == null && board[newPosition[0] - 1][newPosition[1]] == null) {
                    return true;
                }
                else if (newPosition[0] == currentPosition[0] + 1 && Math.abs(newPosition[1] - currentPosition[1]) == 1 && board[newPosition[0]][newPosition[1]] != null) {
                    return canKill(newPosition, currentPosition, board);
                }
            } else {
                if (newPosition[0] == currentPosition[0] + 1 && newPosition[1] == currentPosition[1] && board[newPosition[0]][newPosition[1]] == null) {
                    return true;
                }
                if (newPosition[0] == currentPosition[0] + 1 && Math.abs(newPosition[1] - currentPosition[1]) == 1 && board[newPosition[0]][newPosition[1]] != null) {
                    return canKill(newPosition, currentPosition, board);
                }
            }
        }
        return false;
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