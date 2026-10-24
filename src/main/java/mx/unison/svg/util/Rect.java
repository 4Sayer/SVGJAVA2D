package mx.unison.svg.util;

import mx.unison.svg.data.Geometric;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

public class Rect extends Geometric {
    private float x, y, width, height, rx, ry;

    public Rect(float x, float y, float width, float height, float rx, float ry) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rx = rx;
        this.ry = ry;
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (width <= 0 || height <= 0) return;
        RoundRectangle2D rectangulo = new RoundRectangle2D.Float(x, y, width, height, rx, ry);
        if (fill != null && !fill.equals("none")) {
            g2d.setColor(getColorFromString(fill));
            g2d.fill(rectangulo);
        }
        if (stroke != null && !stroke.equals("none")) {
            g2d.setColor(getColorFromString(stroke));
            float grosor = (strokeWidth != null) ? Float.parseFloat(strokeWidth) : 1.0f;
            g2d.setStroke(new BasicStroke(grosor));
            g2d.draw(rectangulo);
        }
    }
}
