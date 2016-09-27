package figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FigureFrame extends JFrame {

    JPanel controlPanel = new JPanel();
    FigureCanvas figureCanvas = new FigureCanvas();

    public FigureFrame() {

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addButtonActionPerformed(e);

            }
        });

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeButtonActionPerformed(e);
            }
        });

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButtonActionPerformed(e);

            }
        });

        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopActionPerformed(e);
            }
        });

        JButton pauseButton = new JButton("Pause");
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pauseActionPerformed(e);
            }
        });

        JButton resumeButton = new JButton("Resume");
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resumeActionPerformed(e);
            }
        });




        controlPanel.add(addButton);
        controlPanel.add(removeButton);
        controlPanel.add(startButton);
        controlPanel.add(stopButton);
        controlPanel.add(pauseButton);
        controlPanel.add(resumeButton);

        add(controlPanel, BorderLayout.NORTH);
        add(figureCanvas, BorderLayout.CENTER);

        setSize(500, 400);
        setLocation(100, 100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    }

    private void addButtonActionPerformed(ActionEvent e) {
        Figure f;
        double d = Math.random();
        if (d > 0.5) {
            f = new Rectangle(30, 30, 50, 30, figureCanvas, Color.green);
        } else {
            f = new Circle(30, 30, 40, figureCanvas, Color.yellow);
        }
        figureCanvas.addFigure(f);
    }

    private void removeButtonActionPerformed(ActionEvent e) {
        if (figureCanvas.isSelected()) {
            figureCanvas.removeSelected();
            repaint();
        } else {
            JOptionPane.showMessageDialog(null, "Please select a figure before remove!");
        }
    }

    private void startButtonActionPerformed(ActionEvent e) {
        figureCanvas.start();
    }

    private void stopActionPerformed(ActionEvent e) {
        figureCanvas.stop();
    }

    private void pauseActionPerformed(ActionEvent e) {
        figureCanvas.pause();
    }

    private void resumeActionPerformed(ActionEvent e) {
        figureCanvas.resume();
    }


    public static void main(String[] args) {
        new FigureFrame();
    }

}





