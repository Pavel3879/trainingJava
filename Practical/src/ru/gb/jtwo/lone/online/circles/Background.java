package ru.gb.jtwo.lone.online.circles;

import java.awt.*;

public class Background {
    private float r;
    private float g;
    private float b;
    private float vr = 10;
    private float vg = 20;
    private float vb = 30;

    public Background() {
        r = (float) (Math.random() * 255);
        g = (float) (Math.random() * 255);
        b = (float) (Math.random() * 255);
    }

    void update(GameCanvas canvas, float deltaTime) {
        r += vr * deltaTime;
        g += vg * deltaTime;
        b += vb * deltaTime;

        if (r > 255 || r < 0) {
            vr = -vr;
        }
        if (g > 255 || g < 0) {
            vg = -vg;
        }
        if (b > 255 || b < 0) {
            vb = -vb;
        }
    }

    void render(GameCanvas canvas, Graphics g) {
        canvas.setBackground(new Color((int) this.r, (int) this.g , (int) this.b));
    }


}
