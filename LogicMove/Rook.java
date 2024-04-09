package LogicMove;
import java.lang.Math;
import javax.swing.*;
import java.awt.event.*;

public class Rook extends ChessPieces{
    private int x;
    private int y;
    private String color;
    private String name;
    private boolean isAlive;

    public Rook(int x, int y, String color, String name) {
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

    public String toString(){
        return this.color + "_" + this.name;
    }

    public boolean canMove(int[] newPosition, int[] currentPosition, ChessPieces[][] board){
        int dx = (newPosition[0] - currentPosition[0])/ Math.abs(newPosition[0] - currentPosition[0]);
        int dy = (newPosition[1] - currentPosition[1])/ Math.abs(newPosition[1] - currentPosition[1]);
        while (currentPosition[0] != newPosition[0] && currentPosition[1] != newPosition[1]){
            currentPosition[0] += dx;
            currentPosition[1] += dy;
            if (board[currentPosition[0]][currentPosition[1]] == null){
                return false;
            }
        }
        return true;
    }

    public boolean canKill(int[] newPosition, int[] currentPosition, ChessPieces[][] board) {
        int dx = (newPosition[0] - currentPosition[0])/ Math.abs(newPosition[0] - currentPosition[0]);
        int dy = (newPosition[1] - currentPosition[1])/ Math.abs(newPosition[1] - currentPosition[1]);
        currentPosition[0] -= dx;
        currentPosition[1] -= dy;
        boolean validKill = true;
        while (currentPosition[0] != newPosition[0] && currentPosition[1] != newPosition[1]){
            currentPosition[0] += dx;
            currentPosition[1] += dy;
            if (board[currentPosition[0]][currentPosition[1]] == null){
                validKill = false;
            }
        }
        if (validKill){
            if (board[newPosition[0]][newPosition[1]] != null){
                if (board[newPosition[0]][newPosition[1]].getColor() != this.getColor()){
                    return true;
                }
            }
        }
        return false;
    }

}
