//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import javax.swing.JPanel;

public class NonogramPanel extends JPanel implements MouseListener {
    Nonogram puzzle;

    public NonogramPanel(Nonogram puzzle) {
        this.puzzle = puzzle;
        this.addMouseListener(this);
    }

    public Dimension getPreferredSize() {
        return new Dimension(250, 200);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int totalRows = 2 + this.puzzle.height + this.puzzle.maxColGroups;
        int totalCols = 2 + this.puzzle.width + this.puzzle.maxRowGroups;
        double width = (double)this.getWidth();
        double height = (double)this.getHeight();
        double xDivider = width * (double)(1 + this.puzzle.maxRowGroups) / (double)totalCols;
        double yDivider = height * (double)(1 + this.puzzle.maxColGroups) / (double)totalRows;
        double cellWidth = width / (double)totalCols;
        double cellHeight = height / (double)totalRows;
        Graphics2D gg = (Graphics2D)g;
        Stroke s = gg.getStroke();
        gg.setStroke(new BasicStroke(3.0F));
        Rectangle2D r = new Double(xDivider, yDivider, cellWidth * (double)this.puzzle.width, cellHeight * (double)this.puzzle.height);
        gg.draw(r);
        gg.setStroke(s);

        int j;
        int k;
        for(j = 0; j < this.puzzle.height; ++j) {
            for(k = 0; k < this.puzzle.width; ++k) {
                Rectangle2D cell = new Double(xDivider + (double)k * cellWidth, yDivider + (double)j * cellHeight, cellWidth, cellHeight);
                gg.draw(cell);
                if (this.puzzle.guess[j][k]) {
                    gg.fill(cell);
                }
            }
        }

        Double cell;
        int val;
        for(j = 0; j < this.puzzle.height; ++j) {
            for(k = 0; k < this.puzzle.rowGroupLength[j].length; ++k) {
                val = this.puzzle.rowGroupLength[j][k];
                cell = new Double(xDivider - (double)(this.puzzle.rowGroupLength[j].length - k) * cellWidth, yDivider + (double)j * cellHeight, cellWidth, cellHeight);
                gg.draw(cell);
                gg.drawString("" + val, (float)(xDivider - cellWidth * ((double)(this.puzzle.rowGroupLength[j].length - k) - 0.5D)), (float)(yDivider + ((double)j + 0.5D) * cellHeight));
            }
        }

        for(j = 0; j < this.puzzle.width; ++j) {
            for(k = 0; k < this.puzzle.colGroupLength[j].length; ++k) {
                val = this.puzzle.colGroupLength[j][k];
                cell = new Double(xDivider + (double)j * cellWidth, yDivider - (double)(this.puzzle.colGroupLength[j].length - k) * cellHeight, cellWidth, cellHeight);
                gg.draw(cell);
                gg.drawString("" + val, (float)(xDivider + cellWidth * ((double)j + 0.5D)), (float)(yDivider - cellHeight * ((double)(this.puzzle.colGroupLength[j].length - k) - 0.5D)));
            }
        }

    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int totalRows = 2 + this.puzzle.height + this.puzzle.maxColGroups;
        int totalCols = 2 + this.puzzle.width + this.puzzle.maxRowGroups;
        double width = (double)this.getWidth();
        double height = (double)this.getHeight();
        int xCount = (int)((double)(x * totalCols) / width);
        int yCount = (int)((double)(y * totalRows) / height);
        xCount = xCount - 1 - this.puzzle.maxRowGroups;
        yCount = yCount - 1 - this.puzzle.maxColGroups;
        System.out.println("click: " + xCount + "," + yCount);
        this.puzzle.handleMouseClickAt(yCount, xCount);
        this.repaint();
    }

    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int totalRows = 2 + this.puzzle.height + this.puzzle.maxColGroups;
        int totalCols = 2 + this.puzzle.width + this.puzzle.maxRowGroups;
        double width = (double)this.getWidth();
        double height = (double)this.getHeight();
        int xCount = (int)((double)(x * totalCols) / width);
        int yCount = (int)((double)(y * totalRows) / height);
        xCount = xCount - 1 - this.puzzle.maxRowGroups;
        yCount = yCount - 1 - this.puzzle.maxColGroups;
        System.out.println("mousePress: " + xCount + "," + yCount);
        this.puzzle.handleMousePressAt(yCount, xCount);
        this.repaint();
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int totalRows = 2 + this.puzzle.height + this.puzzle.maxColGroups;
        int totalCols = 2 + this.puzzle.width + this.puzzle.maxRowGroups;
        double width = (double)this.getWidth();
        double height = (double)this.getHeight();
        int xCount = (int)((double)(x * totalCols) / width);
        int yCount = (int)((double)(y * totalRows) / height);
        xCount = xCount - 1 - this.puzzle.maxRowGroups;
        yCount = yCount - 1 - this.puzzle.maxColGroups;
        System.out.println("mouseUp: " + xCount + "," + yCount);
        this.puzzle.handleMouseReleaseAt(yCount, xCount);
        this.repaint();
    }
}
