package LogicMove;

public class Board {
    private ChessPieces[][] board;

    public Board() {
        board = new ChessPieces[8][8];
    }

    public void setPiece(ChessPieces piece, int x, int y) {
        board[x][y] = piece;
    }

    public ChessPieces getPiece(int x, int y) {
        if (board[x][y] == null) {
            
            return null;
        }
        return board[x][y];
    }

    public ChessPieces[][] getPieces() {
        return board;
    }
}
