package scheme;
/**
 * Created with IntelliJ IDEA.
 * User: Johan Isaksson
 * Date: 2015-06-25
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 */

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;

public class GUI extends JFrame {
    JMenuBar menuBar;
    JPanel drawArea, left;

    public GUI(){
        super("Cal-Print");

        menuBar = new JMenuBar();
        menuBar.add(new FileMenu());
        menuBar.add(new EditMenu());
        menuBar.add(new ToolMenu());
        menuBar.add(new ExportMenu());


        left = new JPanel();
        left.setLocation(0,0);
        left.setSize(100,600);
        left.setVisible(true);
        left.setBorder(new BasicBorders.SplitPaneBorder(Color.white, Color.BLACK));

        drawArea = new JPanel();
        setLocation(200,0);
        drawArea.setSize(600,600);
        drawArea.setVisible(true);



        this.setSize(800, 600);
        this.setLayout(new GridLayout());


        this.setJMenuBar(menuBar);
        this.getContentPane().add(left);
        this.getContentPane().add(drawArea);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);



    }
}
