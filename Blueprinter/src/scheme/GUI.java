package scheme;
/**
 * Created with IntelliJ IDEA.
 * User: Johan Isaksson
 * Date: 2015-06-25
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 */

import com.fazecast.jSerialComm.SerialPort;
import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;

public class GUI extends JFrame {
    private JMenuBar menuBar;
    private JPanel drawArea, left;
    private JTextField log;
    private JFrame me;




    public GUI(){
        super("Cal-Print");
	me = this;


        menuBar = new JMenuBar();
        menuBar.add(new FileMenu());
        menuBar.add(new EditMenu());
        menuBar.add(new ToolMenu());
        menuBar.add(new ExportMenu());






	/*
	log = new JTextField(20);
	log.addActionListener(left);
	log.setSize(90,100);
	log.setLocation(5,5);
	log.setVisible(true);

	*/

	Graph graph1 = new Graph();
	//Graph graph2 = new Graph();







        this.setSize(800, 600);
        this.setLayout(new FlowLayout());

	this.add(graph1);
	//this.add(graph2);

        this.setJMenuBar(menuBar);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);









    }
}
