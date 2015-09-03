package scheme;

import com.fazecast.jSerialComm.SerialPort;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Scanner;



/**
 * Created with IntelliJ IDEA. User: JOHANOVIC Date: 22/08/15 Time: 19:33 To change this template use File | Settings | File
 * Templates.
 */
public class Graph extends ApplicationFrame {

    int buffer[] = new int[1000];
    private ActionListener action;
    private Timer loop;

    //serial port stuff
    private SerialPort serialPort;
    //private SerialPortDataListener dataListener;

    private JTextArea log;
    private Graph me;

    public Graph(){
	super("Data");
	me = this;

	this.setSize(800,600);
	//this.setBorder(new LineBorder(, 2, false));
	this.setBackground(new Color(220,220,220));
	this.setVisible(true);

	/*log = new JTextArea(30,20);
	this.add(log);*/






	SerialPort[] ports = SerialPort.getCommPorts();
	System.out.println("Select a port:");
	int i = 1;
	for(SerialPort port : ports)
		System.out.println(i++ +  ": " + port.getSystemPortName());
	Scanner s = new Scanner(System.in);
	int chosenPort = s.nextInt();

	serialPort = ports[chosenPort - 1];
	serialPort.setBaudRate(38400);
	//serialPort.addDataListener(dataListener);

	if(serialPort.openPort())
		System.out.println("Port opened successfully.");
	else {
		System.out.println("Unable to open the port.");
		return;
	}
	//serialPort.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);
	serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 0, 0);


	final XYSeries series = new XYSeries("Random Data");
	series.add(1.0, 500.2);
	series.add(5.0, 694.1);
	series.add(4.0, 100.0);
	series.add(12.5, 734.4);
	series.add(17.3, 453.2);
	series.add(21.2, 500.2);
	series.add(21.9, null);
	series.add(25.6, 734.4);
	series.add(30.0, 453.2);
	final XYSeriesCollection data = new XYSeriesCollection(series);
	final JFreeChart chart = ChartFactory.createXYLineChart(
	    "XY Series Demo",
	    "X",
	    "Y",
	    data,
	    PlotOrientation.VERTICAL,
	    true,
	    true,
	    false
	);

	final ChartPanel chartPanel = new ChartPanel(chart);
	chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
	setContentPane(chartPanel);





    }


    /** Invoked when an action occurs. */
    /*
    @Override public void actionPerformed(final ActionEvent e) {

	//update shit

	Scanner data = new Scanner(serialPort.getInputStream());
	    int value = 0;
	    while(data.hasNextLine()){
		log.setText(data.nextLine());
		me.repaint();
		System.out.println(data.nextLine());

	    }
	    //System.out.println("Done.");
	repaint();
    }*/
}
