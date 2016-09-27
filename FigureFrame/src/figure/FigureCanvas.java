package figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;


public class FigureCanvas extends JPanel {

    private ArrayList<Figure> figures = new ArrayList<>();
    private boolean isSelected;

    private int xM;
    private int yM;
    private Rectangle boarder;
   public enum BoarderResizeMode{
       LEFT_RESIZE, RIGHT_RESIZE, TOP_RESIZE, BOTTOM_RESIZE, LEFT_BOTTOM_RESIZE,
       LEFT_TOP_RESIZE, RIGHT_TOP_RESIZE, RIGHT_BOTTOM_RESIZE, NO_RESIZE
   }

    public void addFigure(Figure figure) {
        figures.add(figure);
        repaint();
    }

    public void removeAllFigure() {
        figures.removeAll(figures);
    }

    public void removeSelected() {
        figures.remove(getSelected());
        if (figures.isEmpty()) {
            isSelected = false;
        }
    }

    public Figure getSelected() {
        return isSelected ? figures.get(figures.size() - 1) : null;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void start() {
        if (isSelected) {
            try {
                getSelected().start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        if (isSelected) {
            getSelected().stop();
        }
    }

    public void pause() {
        if (isSelected()) {
            getSelected().pause();
        }

    }

    public void resume() {
        if (isSelected()) {
            getSelected().resume();
        }
    }

    public FigureCanvas() {

        boarder = new Rectangle(30, 30, getWidth() - 60, getHeight() - 60, this, new Color(200, 196, 62));
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                performMousePressed(e);
                performMousePressesOnBoarderRight(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                performMouseDragged(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
    }

    private void select(int x, int y) {
        isSelected = false;
        for (int i = figures.size() - 1; i >= 0; i--) {
            Figure f = figures.get(i);
            if (f.isBelong(x, y)) {
                figures.add(figures.remove(i));
                isSelected = true;
                return;
            }
        }
    }

    private void performMouseDragged(MouseEvent e) {
        if (isSelected) {
            Figure fg = figures.get(figures.size() - 1);
            fg.move(e.getX() - xM, e.getY() - yM);

            this.xM = e.getX();
            this.yM = e.getY();
            repaint();
        }
    }

    private void performMousePressed(MouseEvent e) {
        this.xM = e.getX();
        this.yM = e.getY();
        select(xM, yM);
        repaint();
    }

    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {

        g.clearRect(0, 0, 1000, 1000);
        boarder.setWidth(getWidth() - 60);
        boarder.setHeight(getHeight() - 60);
        boarder.draw(g);

        for (Figure figure : figures) {
            figure.draw(g);
        }
    }

    public Rectangle getBoarder(){
        return boarder;
    }

    public int getBoarderLeft(){
        return boarder.getX();
    }

    public int getBoarderRight(){
        return boarder.getX() + boarder.getWidth();
    }
    public int getBoarderTop(){
        return boarder.getY();
    }

    public int getBoarderBottom(){
        return boarder.getY() + boarder.getHeight();
    }

    public void performMousePressesOnBoarderRight(MouseEvent e){
        if(isSelected){
            if(this.xM == getBoarderRight()){
                boarder.setX(xM);
            }
        }

    }
}
