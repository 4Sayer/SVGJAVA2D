package mx.unison.svg.display;

import mx.unison.svg.data.Geometric;
import mx.unison.svg.util.Circle;
import mx.unison.svg.util.Ellipse;
import mx.unison.svg.util.Line;
import mx.unison.svg.util.Polyline;
import mx.unison.svg.util.Rect;
import mx.unison.svg.util.Text;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import java.util.List;

public class TagsHandler extends DefaultHandler {
    private List<Geometric> elementos = new ArrayList<>();
    private String currentTag = "";
    private StringBuilder textContent = new StringBuilder();
    private Attributes currentAttributes;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentTag = qName;
        currentAttributes = attributes;
        textContent.setLength(0);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if ("text".equals(currentTag)) {
            textContent.append(ch, start, length);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        try {
            if ("line".equals(qName)) {
                float x1 = Float.parseFloat(currentAttributes.getValue("x1"));
                float y1 = Float.parseFloat(currentAttributes.getValue("y1"));
                float x2 = Float.parseFloat(currentAttributes.getValue("x2"));
                float y2 = Float.parseFloat(currentAttributes.getValue("y2"));
                Line line = new Line(x1, y1, x2, y2);
                line.setStroke(currentAttributes.getValue("stroke"));
                line.setStrokeWidth(currentAttributes.getValue("stroke-width"));
                elementos.add(line);
            } else if ("circle".equals(qName)) {
                float cx = Float.parseFloat(currentAttributes.getValue("cx"));
                float cy = Float.parseFloat(currentAttributes.getValue("cy"));
                float r = Float.parseFloat(currentAttributes.getValue("r"));
                Circle circle = new Circle(cx, cy, r);
                circle.setFill(currentAttributes.getValue("fill"));
                circle.setStroke(currentAttributes.getValue("stroke"));
                circle.setStrokeWidth(currentAttributes.getValue("stroke-width"));
                elementos.add(circle);
            } else if ("polyline".equals(qName)) {
                String pointsStr = currentAttributes.getValue("points");
                String[] pairs = pointsStr.trim().split("\\s+");
                int count = pairs.length;
                int[] px = new int[count];
                int[] py = new int[count];
                for (int i = 0; i < count; i++) {
                    String[] coords = pairs[i].split(",");
                    px[i] = (int) Float.parseFloat(coords[0]);
                    py[i] = (int) Float.parseFloat(coords[1]);
                }
                Polyline polyline = new Polyline(px, py, count);
                polyline.setFill(currentAttributes.getValue("fill"));
                polyline.setStroke(currentAttributes.getValue("stroke"));
                polyline.setStrokeWidth(currentAttributes.getValue("stroke-width"));
                elementos.add(polyline);
            } else if ("rect".equals(qName)) {
                float x = Float.parseFloat(currentAttributes.getValue("x"));
                float y = Float.parseFloat(currentAttributes.getValue("y"));
                float w = Float.parseFloat(currentAttributes.getValue("width"));
                float h = Float.parseFloat(currentAttributes.getValue("height"));
                String rxStr = currentAttributes.getValue("rx");
                String ryStr = currentAttributes.getValue("ry");
                float rx = rxStr != null ? Float.parseFloat(rxStr) : 0;
                float ry = ryStr != null ? Float.parseFloat(ryStr) : 0;
                Rect rect = new Rect(x, y, w, h, rx, ry);
                rect.setFill(currentAttributes.getValue("fill"));
                rect.setStroke(currentAttributes.getValue("stroke"));
                rect.setStrokeWidth(currentAttributes.getValue("stroke-width"));
                elementos.add(rect);
            } else if ("ellipse".equals(qName)) {
                float cx = Float.parseFloat(currentAttributes.getValue("cx"));
                float cy = Float.parseFloat(currentAttributes.getValue("cy"));
                float rx = Float.parseFloat(currentAttributes.getValue("rx"));
                float ry = Float.parseFloat(currentAttributes.getValue("ry"));
                Ellipse ellipse = new Ellipse(cx, cy, rx, ry);
                ellipse.setFill(currentAttributes.getValue("fill"));
                ellipse.setStroke(currentAttributes.getValue("stroke"));
                ellipse.setStrokeWidth(currentAttributes.getValue("stroke-width"));
                elementos.add(ellipse);
            } else if ("text".equals(qName)) {
                float x = Float.parseFloat(currentAttributes.getValue("x"));
                float y = Float.parseFloat(currentAttributes.getValue("y"));
                String fontFamily = currentAttributes.getValue("font-family");
                String fontSizeStr = currentAttributes.getValue("font-size");
                float fontSize = fontSizeStr != null ? Float.parseFloat(fontSizeStr) : 12;
                Text text = new Text(x, y, fontFamily, fontSize, textContent.toString().trim());
                text.setFill(currentAttributes.getValue("fill"));
                elementos.add(text);
            }
        } catch (Exception e) {
        }
        currentTag = "";
    }

    public List<Geometric> getElementos() {
        return elementos;
    }
}
