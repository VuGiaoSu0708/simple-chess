package Graphics;
import LogicMove.*;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.lang.reflect.Array;


public class GraphicsDealing extends JPanel{

    private final AssetsLoading assets;
    private final Board board;
    private int dragX = -1;
    private int dragY = -1;
    private int oldX = -1;
    private int oldY = -1;
    private ChessPieces draggedPiece = null;
    private String turn = "White";
    private ArrayList<ArrayList<Integer>> prediction = new ArrayList<ArrayList<Integer>>();
    private ArrayList<ArrayList<Integer>> choosing = new ArrayList<ArrayList<Integer>>();
    private ArrayList<ArrayList<Integer>> killable = new ArrayList<ArrayList<Integer>>();


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
                if (piece != null && piece.getColor().equals(turn)) {
                    getAllPredictions(oldY, oldX);
                    removePieceAt(oldY, oldX);
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
                    if ((y-25/100) == (oldY-25/100) && (x-25/100) == (oldX-25/100)){
                        setPiece(draggedPiece.toString(), (oldY-25)/100, (oldX-25)/100);
                    } else {
                        if  (draggedPiece.canMove(new int[]{(y-25)/100, (x-25)/100}, new int[]{(oldY-25)/100, (oldX-25)/100}, board.getPieces())){
                            prediction.clear();
                            choosing.clear();
                            killable.clear();
                            setPiece(draggedPiece.toString(), (y-25)/100, (x-25)/100);
                            if (turn.equals("White")){
                                turn = "Black";
                            }else if (turn.equals("Black")){
                                turn = "White";
                            }
                        } else {
                            setPiece(draggedPiece.toString(), (oldY-25)/100, (oldX-25)/100);
                            prediction.clear();
                            choosing.clear();
                            killable.clear();
                        }
                    }
                    if (wasKingDead(turn)){
                        System.out.println("Game Over");
                        turn = "End";
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
        drawPrediction(g);
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

    public void drawPrediction(Graphics g){
        for (ArrayList<Integer> integers : prediction) {
            g.setColor(Color.cyan);
            g.fillRect(integers.get(1)*100, integers.get(0)*100, 100, 100);
        }
        for (ArrayList<Integer> integers : choosing) {
            g.setColor(Color.yellow);
            g.fillRect(integers.get(1)*100, integers.get(0)*100, 100, 100);
        }
        for (ArrayList<Integer> integers : killable) {
            g.setColor(Color.red);
            g.fillRect(integers.get(1)*100, integers.get(0)*100, 100, 100);
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
                        ChessPieces piece = new Knight(x, y, "Black", "Knight");
                        board.setPiece(piece, i, j);
                    }else if (j == 2 || j == 5){
                        ChessPieces piece = new Bishop(x, y, "Black", "Bishop");
                        board.setPiece(piece, i, j);
                    }else if (j == 3){
                        ChessPieces piece = new Queen(x, y, "Black", "Queen");
                        board.setPiece(piece, i, j);
                    }else if (j == 4){
                        ChessPieces piece = new King(x, y, "Black", "King");
                        board.setPiece(piece, i, j);
                    }
                } else if (i == 1){
                    ChessPieces piece = new Pawn(x, y, "Black", "Pawn");
                    board.setPiece(piece, i, j);
                } else if (i == 6){
                    ChessPieces piece = new Pawn(x, y, "White", "Pawn");
                    board.setPiece(piece, i, j);
                } else if (i == 7){
                    if (j == 0 || j == 7){
                        ChessPieces piece = new Rook(x, y, "White", "Rook");
                        board.setPiece(piece, i, j);
                    }else if (j == 1 || j == 6){
                        ChessPieces piece = new Knight(x, y, "White", "Knight");
                        board.setPiece(piece, i, j);
                    }else if (j == 2 || j == 5){
                        ChessPieces piece = new Bishop(x, y, "White", "Bishop");
                        board.setPiece(piece, i, j);
                    }else if (j == 3){
                        ChessPieces piece = new Queen(x, y, "White", "Queen");
                        board.setPiece(piece, i, j);
                    }else if (j == 4){
                        ChessPieces piece = new King(x, y, "White", "King");
                        board.setPiece(piece, i, j);
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
        } else if (pieceInfo[1].equals("Bishop")){
            board.setPiece(new Bishop(x, y, pieceInfo[0], pieceInfo[1]), x, y);
        } else if (pieceInfo[1].equals("Queen")){
            board.setPiece(new Queen(x, y, pieceInfo[0], pieceInfo[1]), x, y);
        } else if (pieceInfo[1].equals("Pawn")){
            board.setPiece(new Pawn(x, y, pieceInfo[0], pieceInfo[1]), x, y);
        } else if (pieceInfo[1].equals("King")){
            board.setPiece(new King(x, y, pieceInfo[0], pieceInfo[1]), x, y);
        } else if (pieceInfo[1].equals("Knight")){
            board.setPiece(new Knight(x, y, pieceInfo[0], pieceInfo[1]), x, y);
        }
    }

    public void getAllPredictions(int x, int y){
        ChessPieces piece = getPieceAt(x, y);

        if (piece != null && piece.getColor().equals(turn)){
            final int[] currentPosition = {(x-25)/100, (y-25)/100};
            choosing.add(new ArrayList<Integer>(Arrays.asList(currentPosition[0], currentPosition[1])));
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    int[] current = Arrays.copyOf(currentPosition, currentPosition.length);
                    if (piece.canMove(new int[]{i, j}, current, board.getPieces())){
                        prediction.add(new ArrayList<Integer>(Arrays.asList(i, j)));
                        if (board.getPiece(i, j) != null){
                            killable.add(new ArrayList<Integer>(Arrays.asList(i, j)));
                        }
                    }
                }
            }
        }
    }

    public boolean wasKingDead(String turn){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPieces piece = board.getPiece(i, j);
                if (piece != null && piece.getName().equals("King") && piece.getColor().equals(turn) && piece.isAlive()){
                    return false;
                }
            }
        }
        return true;
    }
}
