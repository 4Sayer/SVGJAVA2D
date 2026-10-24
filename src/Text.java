package mx.unison.svg.util;
import mx.unison.svg.data.Geometric;
import java.awt.Font;
import java.awt.Graphics2D;

public class Text extends Geometric {
    private float x, y;
    private String fontFamily;
    private float fontSize;
    private String contenido;

    public Text(float x, float y, String fontFamily, float fontSize, String contenido) {
        this.x = x;
        this.y = y;
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.contenido = contenido;
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (contenido == null || contenido.isEmpty() || fontSize <= 0) return;

        String familia = (fontFamily != null) ? fontFamily : "Arial";

        Font fuente = new Font(familia, Font.PLAIN, (int) fontSize);

        g2d.setFont(fuente);

        if (fill != null && !fill.equals("none")) {
            g2d.setColor(getColorFromString(fill));
        } else {
            g2d.setColor(java.awt.Color.BLACK);
        }

        g2d.drawString(contenido, x, y);
    }
}