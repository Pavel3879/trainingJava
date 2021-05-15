package ru.gb.jtwo.lone.online.circles;

import java.awt.*;

public class Sprite {
    protected float x;
    protected float y;
    protected float halfWidth;
    protected float halfHeight;
    protected boolean enable = false;

    protected float getLeft() {
        return x - halfWidth;
    }

    protected void setLeft(float left) {
        x = left + halfWidth;
    }

    protected float getRight() {
        return x + halfWidth;
    }

    protected void setRight(float right) {
        x = right - halfWidth;
    }

    protected float getTop() {
        return y - halfHeight;
    }

    protected void setTop(float top) {
        y = top + halfHeight;
    }

    protected float getBottom() {
        return y + halfHeight;
    }

    protected void setBottom(float bottom) {
        y = bottom - halfHeight;
    }

    protected float getWidth() {
        return 2f * halfWidth;
    }

    protected float getHeight() {
        return 2f * halfHeight;
    }

    protected boolean isEnable() {
        return enable;
    }

    protected void setEnable(boolean enable) {
        this.enable = enable;
    }

    void update(GameCanvas canvas, float deltaTime) {
    }

    void render(GameCanvas canvas, Graphics g) {
    }

}
