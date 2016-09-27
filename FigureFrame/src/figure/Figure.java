package figure;


import java.awt.*;

public abstract class Figure implements Runnable {

    public static final Color DEFAULT_COLOR = new Color(20, 178, 200);

    private int width;
    private int height;
    private int x;
    private int y;
    private FigureCanvas canvas;

    private Color color;

    private Thread t;
    private boolean isRunning;
    private boolean isPaused;

    private int dx;
    private int dy;


    public Figure(int x, int y, int width, int height, FigureCanvas canvas, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.canvas = canvas;
        this.color = color;
    }

    public Figure(int x, int y, int width, int height, FigureCanvas canvas) {
        this(x, y, width, height, canvas, DEFAULT_COLOR);
    }


    public int getX() {

        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    public abstract void draw(Graphics g);

    public abstract boolean isBelong(int x, int y);

    @Override
    public void run() {
        while (isRunning) {
            if (isPaused) {
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            insureDirection();
            this.move(dx, dy);
            canvas.repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    void checkLeft() {
        if (x <= canvas.getBoarderLeft()) {
            if (dx < 0) {
                dx = -dx;
            }
        }
    }

    void checkRight() {
        if (x + getWidth() >= canvas.getBoarderRight()) {
            dx = dx > 0 ? -dx : dx;
        }
    }

    void checkTop() {
        if (y <= canvas.getBoarderTop()) {
            if (dy < 0) {
                dy = -dy;
            }
        }
    }

    void checkBottom() {
        if (y + getHeight() >= canvas.getBoarderBottom()) {
            if (dy > 0) {
                dy = -dy;
            }
        }
    }

    public void insureDirection() {
        checkBottom();
        checkTop();
        checkLeft();
        checkRight();
    }

    public void move(int dx, int dy) {

        this.setX(x + dx);
        this.setY(y + dy);
    }

    public void start() throws InterruptedException {
        if (t != null && isRunning == true) {
            stop();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = new Thread(this);
        dx = 3;
        dy = 3;
        isRunning = true;
        t.start();
    }

    public void stop() {
        dx = 0;
        dy = 0;
        resume();
        isRunning = false;
    }

    public void pause() {
        isPaused = true;
    }

    public synchronized void resume() {
        if (isPaused) {
            isPaused = false;
            notify();
        }
    }

}
