//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class NonogramGUI implements ActionListener {
    Nonogram puzzle;
    NonogramPanel panel;

    public NonogramGUI(Nonogram puzzle) {
        this.puzzle = puzzle;
        this.panel = new NonogramPanel(puzzle);
        JFrame f = new JFrame("My Nonogram App");
        f.setDefaultCloseOperation(3);
        Container p = f.getContentPane();
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        p.add(resetButton, "West");
        p.add(this.panel, "Center");
        f.pack();
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Reset button pressed.");
        this.puzzle.handleResetButtonClick();
        this.panel.repaint();
    }

    public static void main(String[] args) {
        //Default String:
        String pic = "..XXX..\n.XX.XX.\nXX...XX\nX.....X\nXX...XX\n.XX.XX.\n..XXX..";
        //Example 1 on learn:
       /* String pic =
                "......X.XX\n" +
                "........XX\n" +
                ".......X..\n" +
                ".........X\n" +
                ".....XX...\n" +
                "XX..XXXX..\n" +
                "XX.XXXXXX.\n" +
                ".XXXXXXXX.\n" +
                "....X..X..\n" +
                "...XX.XX..";
*/
        /*String pic =
                "XX...X\n" +
                        ".X.XXX\n" +
                        ".X.XX.\n" +
                        ".XXX..\n" +
                        ".XXXX.\n" +
                        "...X..";

         */
        Nonogram nono = new Nonogram(pic);
        new NonogramGUI(nono);
    }
}
