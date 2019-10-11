package xogame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Map extends JPanel {
    public static final int MODE_H_V_A = 0;
    public static final int MODE_H_V_H = 1;

    int mode;
    int fieldSizeX;
    int fieldSizeY;
    int winLength;

    int cellWidth;
    int cellHeight;

    boolean isInitialised = false;

    public Map() {
        setBackground(Color.ORANGE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                update(e);
            }
        });
    }

    void startNewGame(int mode, int fieldSizeX, int fieldSizeY, int winLength) {
        System.out.println(mode + " " + fieldSizeX + " " + fieldSizeY + " " + winLength);
        this.mode = mode;
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.winLength = winLength;

        isInitialised = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g) {
        if (!isInitialised) {
            return;
        }

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        cellHeight = panelHeight / fieldSizeY;
        cellWidth = panelWidth / fieldSizeX;

        for (int i = 0; i < fieldSizeY; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }

        for (int i = 0; i < fieldSizeX; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }

        for (int i = 0; i < Logic.SIZE_Y; i++) {
            for (int j = 0; j < Logic.SIZE_X; j++) {
                drawCell(g, j, i);
            }
        }
    }

    private void drawCell(Graphics g, int x, int y) {
        Color newColor;
        Color oldColor = g.getColor();
        newColor = new Color(27, 79, 21);
        g.setColor(newColor);
        if (Logic.map[y][x] > 0) {
            drawX(g, x, y);
        } else if (Logic.map[y][x] < 0) {
            drawO(g, x, y);
        }
        newColor = new Color(255, 47, 44);
        g.setColor(newColor);
        drawFinalLine(g);
        g.setColor(oldColor);
    }

    private void drawX(Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10.0f));  // толщина равна 10
        g2.drawLine(x * cellWidth + 10, y * cellHeight + 10,
                (x + 1) * cellWidth - 10, (y + 1) * cellHeight - 10);
        g2.drawLine((x + 1) * cellWidth - 10, y * cellHeight + 10,
                x * cellWidth + 10, (y + 1) * cellHeight - 10);
    }

    private void drawO(Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10.0f));  // толщина равна 10
        g2.drawOval(x * cellWidth + 10, y * cellHeight + 10,
                cellWidth - 20, cellHeight - 20);
    }

    private void drawFinalLine(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(20.0f));  // толщина равна 20
        g2.drawLine(Logic.lineWinX1 * cellWidth + cellWidth/2, Logic.lineWinY1 * cellHeight +  + cellWidth/2,
                Logic.lineWinX2 * cellWidth  + cellWidth/2, Logic.lineWinY2 * cellHeight  + cellWidth/2);
    }


    void update(MouseEvent e) {
//        System.out.println(e.getX() + " " + e.getY());
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;
        System.out.println(cellX + " " + cellY);

        Logic.humanTurn(cellX, cellY);
        repaint();
    }
}
