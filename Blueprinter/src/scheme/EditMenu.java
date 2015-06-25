package scheme;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Johan Isaksson
 * Date: 2015-06-25
 * Time: 17:26
 * To change this template use File | Settings | File Templates.
 */
public class EditMenu extends JMenu {

    JRadioButtonMenuItem rbMenuItem;
    JMenu submenu;
    JCheckBoxMenuItem cbMenuItem;
    JMenuItem menuItem;
    JMenu me;

    public EditMenu(){
        super("Edit");
        me = this;

        //undo
        menuItem = new JMenuItem("Undo");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));

        ActionListener undoL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //undo
            }
        };

        menuItem.addActionListener(undoL);
        this.add(menuItem);

        //redo
        menuItem = new JMenuItem("Redo");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));

        ActionListener redoL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //redo
            }
        };

        menuItem.addActionListener(redoL);
        this.add(menuItem);

        //copy
        menuItem = new JMenuItem("Copy");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));

        ActionListener copyL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //copy
            }
        };

        menuItem.addActionListener(copyL);
        this.add(menuItem);

        //paste
        menuItem = new JMenuItem("Paste");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));

        ActionListener pasteL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //save blueprint
                final JFileChooser fc = new JFileChooser();
                int returnVal = fc.showSaveDialog(me);
            }
        };

        menuItem.addActionListener(pasteL);
        this.add(menuItem);

        //cut
        menuItem = new JMenuItem("Cut");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        ActionListener cutL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //cut
            }
        };

        menuItem.addActionListener(cutL);
        this.add(menuItem);





        /*

        //a group of radio button menu items
        this.addSeparator();
        ButtonGroup group = new ButtonGroup();
        rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
        rbMenuItem.setSelected(true);
        rbMenuItem.setMnemonic(KeyEvent.VK_R);
        group.add(rbMenuItem);
        this.add(rbMenuItem);

        rbMenuItem = new JRadioButtonMenuItem("Another one");
        rbMenuItem.setMnemonic(KeyEvent.VK_O);
        group.add(rbMenuItem);
        this.add(rbMenuItem);

        //a group of check box menu items
        this.addSeparator();
        cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
        cbMenuItem.setMnemonic(KeyEvent.VK_C);
        this.add(cbMenuItem);

        cbMenuItem = new JCheckBoxMenuItem("Another one");
        cbMenuItem.setMnemonic(KeyEvent.VK_H);
        this.add(cbMenuItem);

        /*
        //a submenu
        this.addSeparator();
        submenu = new JMenu("A submenu");
        submenu.setMnemonic(KeyEvent.VK_S);

        menuItem = new JMenuItem("An item in the submenu");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        submenu.add(menuItem);

        menuItem = new JMenuItem("Another item");
        submenu.add(menuItem);
        this.add(submenu);*/

    }
}
