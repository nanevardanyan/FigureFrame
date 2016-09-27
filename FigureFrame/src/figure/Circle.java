package figure;


import java.awt.*;

public class Circle extends Figure {

    public Circle(int x, int y, int diameter, FigureCanvas canvas, Color color) {
        super(x, y, diameter, diameter, canvas, color);
    }

    public Circle(int x, int y, int diameter, FigureCanvas canvas) {
        super(x, y, diameter, diameter, canvas);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillOval(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public boolean isBelong(int x, int y) {
        int dX  = x - (getX() + getWidth()/2);
        int dY  = y - (getY() + getWidth()/2);
        return dX*dX + dY*dY <= getHeight()/2 * getHeight()/2;
    }
}
