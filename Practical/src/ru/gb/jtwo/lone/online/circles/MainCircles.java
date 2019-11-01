package ru.gb.jtwo.lone.online.circles;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MainCircles extends JFrame {

    /*
     * Полностью разобраться с кодом
     * Прочитать методичку к следующему уроку
     * Написать класс Бэкграунд, изменяющий цвет канвы в зависимости от времени
     *  * Реализовать добавление новых кружков по клику используя ТОЛЬКО массивы
     *  ** Реализовать по клику другой кнопки удаление кружков (никаких эррейЛист)
     * */

    private static final int POS_X = 600;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    Sprite[] sprites = new Sprite[20];
    Background background = new Background();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainCircles();
            }
        });
    }

    private MainCircles() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Circles");

        GameCanvas canvas = new GameCanvas(this);
        add(canvas, BorderLayout.CENTER);
        initApplication();
        setVisible(true);
    }

    private void initApplication() {
        for (int i = 0; i < sprites.length; i++) {
            //sprites[i] = new Ball();
        }
    }

    public void mouseEventHandle(MouseEvent e) {
        if (e.getButton() == 1) {
            addSprites();
        } else {
            hideSprites(e.getPoint());
        }
    }

    public void addSprites() {
        for (int i = 0; i < sprites.length; i++) {
            /*if (!sprites[i].isEnable()) {
                sprites[i].setEnable(true);
                break;
            }*/
            if (sprites[i] instanceof Sprite) {
                //*
            } else {
                sprites[i] = new Ball();
                break;
            }
        }
    }

    public void hideSprites(Point point) {
        float distance;
        for (int i = 0; i < sprites.length; i++) {
            /*if (!sprites[i].isEnable()) continue;
            distance = (float) Math.sqrt((Math.pow(point.x - sprites[i].x, 2) + Math.pow(point.y - sprites[i].y, 2)));
            if (distance > sprites[i].halfWidth) continue;
            sprites[i].setEnable(false);
            break;*/
            if (sprites[i] instanceof Sprite) {
                distance = (float) Math.sqrt((Math.pow(point.x - sprites[i].x, 2) + Math.pow(point.y - sprites[i].y, 2)));
                if (distance > sprites[i].halfWidth) continue;
                sprites[i] = null;
                break;
            } else {
                //*
            }
        }
    }

    public void onDrawFrame(GameCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(GameCanvas canvas, float deltaTime) {
        background.update(canvas, deltaTime);
        for (int i = 0; i < sprites.length; i++) {
            /*if (sprites[i].isEnable()) {
                sprites[i].update(canvas, deltaTime);
            }*/
            if (sprites[i] instanceof Sprite) {
                sprites[i].update(canvas, deltaTime);
            }
        }
    }

    private void render(GameCanvas canvas, Graphics g) {
        background.render(canvas, g);
        for (int i = 0; i < sprites.length; i++) {
            /*if (sprites[i].isEnable()) {
                sprites[i].render(canvas, g);
            }*/
            if (sprites[i] instanceof Sprite) {
                sprites[i].render(canvas, g);
            }
        }
    }
}
