package mx.unison.svg.util;

import mx.unison.svg.data.Geometric;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Geometric {
    private float cx, cy, rx, ry;

    public Ellipse(float cx, float cy, float rx, float ry) {
        this.cx = cx;
        this.cy = cy;
        this.rx = rx;
        this.ry = ry;
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (rx <= 0 || ry <= 0) return;
        float x = cx - rx;
        float y = cy - ry;
        float width = rx * 2;
        float height = ry * 2;
        Ellipse2D elipse = new Ellipse2D.Float(x, y, width, height);
        if (fill != null && !fill.equals("none")) {
            g2d.setColor(getColorFromString(fill));
            g2d.fill(elipse);
        }
        if (stroke != null && !stroke.equals("none")) {
            g2d.setColor(getColorFromString(stroke));
            float grosor = (strokeWidth != null) ? Float.parseFloat(strokeWidth) : 1.0f;
            g2d.setStroke(new BasicStroke(grosor));
            g2d.draw(elipse);
        }
    }
}
