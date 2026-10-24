package mx.unison.svg.util;

import mx.unison.svg.data.Geometric;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

public class Polyline extends Geometric {
    private int[] puntosX;
    private int[] puntosY;
    private int cantidadPuntos;

    public Polyline(int[] puntosX, int[] puntosY, int cantidadPuntos) {
        this.puntosX = puntosX;
        this.puntosY = puntosY;
        this.cantidadPuntos = cantidadPuntos;
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (cantidadPuntos == 0) return;
        GeneralPath camino = new GeneralPath();
        camino.moveTo(puntosX[0], puntosY[0]);
        for (int i = 1; i < cantidadPuntos; i++) {
            camino.lineTo(puntosX[i], puntosY[i]);
        }
        if (fill != null && !fill.equals("none")) {
            g2d.setColor(getColorFromString(fill));
            g2d.fill(camino);
        }
        if (stroke != null && !stroke.equals("none")) {
            g2d.setColor(getColorFromString(stroke));
            float grosor = (strokeWidth != null) ? Float.parseFloat(strokeWidth) : 1.0f;
            g2d.setStroke(new BasicStroke(grosor));
            g2d.draw(camino);
        }
    }
}
