package Graphics;
import LogicMove.Board;
import LogicMove.ChessPieces;
import LogicMove.Rook;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


public class GraphicsDealing extends JPanel{

    private final AssetsLoading assets;
    private final Board board;
    private int dragX = -1;
    private int dragY = -1;
    private int oldX = -1;
    private int oldY = -1;
    private ChessPieces draggedPiece = null;


    public GraphicsDealing(){
        assets = new AssetsLoading();
        board = new Board();
        Panel();
        getPieces();
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                ChessPieces piece = getPieceAt(y, x);
                oldX = x;
                oldY = y;
                if (piece != null) {
                    draggedPiece = piece;
                    dragX = x;
                    dragY = y;
                }
                repaint();
            }

            public void mouseReleased(MouseEvent e) {
                if (draggedPiece != null) {
                    int x = e.getX();
                    int y = e.getY();
                    if  (draggedPiece.canMove(new int[]{(y-25)/100, (x-25)/100}, new int[]{(oldY-25)/100, (oldX-25)/100}, board.getPieces())){
                        removePieceAt(oldY, oldX);
                        setPiece(draggedPiece.toString(), (y-25)/100, (x-25)/100);
                    }
                    draggedPiece = null;
                }
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (draggedPiece != null) {
                    dragX = e.getX()-25;
                    dragY = e.getY()-25;
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        drawBoard(g);
        drawPieces(g);

        if (draggedPiece != null) {
            g.drawImage(assets.getImage(draggedPiece.toString()).getImage(), dragX, dragY, 50, 50, null);
        }
    }

    public void drawBoard(Graphics g){
        int x = 0;
        int y = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if((i+j)%2 == 0){
                    g.drawImage(assets.getImage("Light_Tiles").getImage(), x, y, 100, 100, null);
                }else{
                    g.drawImage(assets.getImage("Dark_Tiles").getImage(), x, y, 100, 100, null);
                }
                x += 100;
            }
            x = 0;
            y += 100;
        }
    }

    public void drawPieces(Graphics g){
        int x = 25;
        int y = 25;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getPiece(i, j) != null){
                    g.drawImage(assets.getImage(board.getPiece(i, j).toString()).getImage(), x, y, 50, 50, null);
                }
                x += 100;
            }
            x = 25;
            y += 100;
        }
    }

    public void getPieces(){
        int x = 25;
        int y = 25;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 0){
                    if (j == 0 || j == 7){
                        ChessPieces piece = new Rook(x, y, "Black", "Rook");
                        board.setPiece(piece, i, j);
                    }else if (j == 1 || j == 6){
                    }else if (j == 2 || j == 5){
                    }else if (j == 3){
                    }else if (j == 4){
                    }
                } else if (i == 1){
                } else if (i == 6){
                } else if (i == 7){
                    if (j == 0 || j == 7){
                        ChessPieces piece = new Rook(x, y, "White", "Rook");
                        board.setPiece(piece, i, j);
                    }else if (j == 1 || j == 6){
                    }else if (j == 2 || j == 5){
                    }else if (j == 3){
                    }else if (j == 4){
                    }
                }
                x += 100;
            }
            x = 25;
            y += 100;
        }
    }

    public void Panel(){
        JFrame frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.add(this);
        frame.setVisible(true);
    }

    public ChessPieces getPieceAt(int x, int y) {
        return board.getPiece((x-25)/100, (y-25)/100);
    }

    public void removePieceAt(int x, int y) {
        board.setPiece(null, (x-25)/100, (y-25)/100);
    }

    public void setPiece(String piece, int x, int y) {
        String[] pieceInfo = piece.split("_");
        if (pieceInfo[1].equals("Rook")){
            board.setPiece(new Rook(x, y, pieceInfo[0], pieceInfo[1]), x, y);
        } 
    }
}
