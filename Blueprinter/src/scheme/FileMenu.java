package scheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created with IntelliJ IDEA.
 * User: Johan Isaksson
 * Date: 2015-06-25
 * Time: 14:59
 * To change this template use File | Settings | File Templates.
 */
public class FileMenu extends JMenu {

    JMenuItem menuItem;
    JMenu me;

    public FileMenu() {
        //a group of JMenuItems
        super("File");
        me = this;

        //new
        menuItem = new JMenuItem("New...");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));

        ActionListener newL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new blueprint
            }
        };

        menuItem.addActionListener(newL);
        this.add(menuItem);

        //open
        menuItem = new JMenuItem("Open...");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));

        ActionListener openL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //open blueprint
                final JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(me);
            }
        };

        menuItem.addActionListener(openL);
        this.add(menuItem);

        //save
        menuItem = new JMenuItem("Save");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

        ActionListener saveL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //save blueprint
                //final JFileChooser fc = new JFileChooser();
                //int returnVal = fc.showSaveDialog(me);
            }
        };

        menuItem.addActionListener(saveL);
        this.add(menuItem);

        //save as
        menuItem = new JMenuItem("Save As...");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));

        ActionListener saveAsL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //save blueprint
                final JFileChooser fc = new JFileChooser();
                int returnVal = fc.showSaveDialog(me);
            }
        };

        menuItem.addActionListener(saveAsL);
        this.add(menuItem);





    }

}
