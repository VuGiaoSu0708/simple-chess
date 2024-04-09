package LogicMove;

public abstract class ChessPieces {
    private int x;
    private int y;
    private String color;
    private String name;
    private boolean isAlive;

    public ChessPieces(int x, int y, String color, String name) {
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

    public abstract boolean canMove(int[] newPosition, int[] currentPosition, ChessPieces[][] board);

    public abstract boolean canKill(int[] newPosition, int[] currentPosition, ChessPieces[][] board);

}
