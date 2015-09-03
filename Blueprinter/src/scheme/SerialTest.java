package scheme;

/**
 * Created with IntelliJ IDEA. User: JOHANOVIC Date: 23/08/15 Time: 19:16 To change this template use File | Settings | File
 * Templates.
 */



import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.util.Enumeration;


public class SerialTest extends ApplicationFrame implements SerialPortEventListener {
    SerialPort serialPort;
    /** The port we're normally going to use. */
    private static final String PORT_NAMES[] = {                  "/dev/tty.usbserial-A9007UX1", // Mac OS X
	    "/dev/ttyUSB0", // Linux
	    "COM4" // Windows
    };
    private BufferedReader input;
    private OutputStream output;
    private static final int TIME_OUT = 8000;
    private static final int DATA_RATE = 38400;

    private int pos;

    private ApplicationFrame me;

    /* data series */
    private XYSeries pitch;
    private XYSeries pitchRate;
    private XYSeries roll;
    private XYSeries rollRate;
    private XYSeries yaw;
    private XYSeries yawRate;
    private XYSeries speedFL; //front left
    private XYSeries speedFR; //front right
    private XYSeries speedBL; //back left
    private XYSeries speedBR; //back right
    private XYSeries height;
    private XYSeries speedVert;


    private JTextArea log;


    public SerialTest(){
	super("Quadcopter Data");
	me = this;
    }

    public void createChart(XYSeriesCollection data, String Xaxis, String Yaxis, double r, double offset, ApplicationFrame frame, Dimension d){
	final JFreeChart chart = ChartFactory.createXYLineChart("", Xaxis, Yaxis, data, PlotOrientation.VERTICAL, true, true, false);
	final ChartPanel panel = new ChartPanel(chart);
	panel.setSize(d);
	panel.setBorder(new LineBorder(Color.GRAY));

	XYPlot plot = (XYPlot)chart.getPlot();
	plot.setDomainCrosshairVisible(true);
	plot.setRangeCrosshairVisible(true);
	NumberAxis range = (NumberAxis)plot.getRangeAxis();
	range.setRange(-r + offset, r + offset);
	range.setTickUnit(new NumberTickUnit((r*2.0)/10.0));

	frame.getContentPane().add(panel);
    }




    public void initialize() {
	pos = 0;

	/* connect to chosen COM port*/
	CommPortIdentifier portId = null;
	Enumeration portEnum =  CommPortIdentifier.getPortIdentifiers();

	//First, Find an instance of serial port as set in PORT_NAMES.
	while (portEnum.hasMoreElements()) {
	    CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
	    for (String portName : PORT_NAMES) {
		if (currPortId.getName().equals(portName)) {
		    portId = currPortId;
		    break;
		}
	    }
	}


	/* setup charts */

	//pitch
	pitch = new XYSeries("Pitch Angle");
	pitchRate = new XYSeries("Pitch Rate");
	XYSeriesCollection data = new XYSeriesCollection(pitch);
	data.addSeries(pitchRate);
	createChart(data, "time", "angle", 90.0, 0.0, this, new Dimension(400, 200));

	//roll
	roll = new XYSeries("Roll Angle");
	rollRate = new XYSeries("Roll Rate");
	data = new XYSeriesCollection(roll);
	data.addSeries(rollRate);
	createChart(data, "time", "angle", 90.0, 0.0, this, new Dimension(400, 200));

	//yaw
	yaw = new XYSeries("Yaw Angle");
	yawRate = new XYSeries("Yaw Rate");
	data = new XYSeriesCollection(yaw);
	data.addSeries(yawRate);
	createChart(data, "time", "angular rate", 90.0, 0.0, this, new Dimension(400, 200));

	//pid
	speedFL = new XYSeries("Front Left");
	speedFR = new XYSeries("Front Right");
	speedBL = new XYSeries("Back left");
	speedBR = new XYSeries("Back Right");
	data = new XYSeriesCollection(speedFL);
	data.addSeries(speedFR);
	data.addSeries(speedBL);
	data.addSeries(speedBR);
	createChart(data, "time", "throttle", 128.0, 128.0, this, new Dimension(400,200));

	//height
	height = new XYSeries("Height Estimate");
	speedVert = new XYSeries("Vertical Velocity");
	data = new XYSeriesCollection(height);
	data.addSeries(speedVert);
	createChart(data, "time", "height", 10000.0, 9500.0, this, new Dimension(400,200));

	//logs
	log = new JTextArea("Hejhej");
	log.setEditable(false);
	log.setVisible(true);
	JPanel logPanel = new JPanel();
	logPanel.setSize(new Dimension(400,200));
	logPanel.setBorder(new LineBorder(Color.GRAY));
	logPanel.setLayout(new GridLayout(1, 1, 5, 5));
	logPanel.setVisible(true);
	logPanel.add(log);
	this.getContentPane().add(logPanel);




    	this.setSize(1600,900);
	this.setLayout(new GridLayout(3, 3, 5, 5));
    	this.setVisible(true);



	if (portId == null) {
	    System.out.println("Could not find COM port.");
	    return;
	}

	try {
	    serialPort = (SerialPort) portId.open(this.getClass().getName(),
		    TIME_OUT);
	    serialPort.setSerialPortParams(DATA_RATE,
		    SerialPort.DATABITS_8,
		    SerialPort.STOPBITS_1,
		    SerialPort.PARITY_NONE);

	    // open the streams
	    input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
	    output = serialPort.getOutputStream();

	    serialPort.addEventListener(this);
	    serialPort.notifyOnDataAvailable(true);
	} catch (Exception e) {
	    System.err.println(e.toString());
	}


    }


    public synchronized void close() {
	if (serialPort != null) {
	    serialPort.removeEventListener();
	    serialPort.close();
	}
    }

    public synchronized void serialEvent(SerialPortEvent oEvent) {
	if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
	    try {
		String inputLine=null;
		if (input.ready()) {

		    inputLine = input.readLine();
		    String args[] = inputLine.split(",");
		    System.out.println(inputLine);

		    if (pos > 199){

			yaw.remove(0);
			yaw.add(pos, Double.parseDouble(args[0]));

			yawRate.remove(0);
			yawRate.add(pos, Double.parseDouble(args[1]));

			pitch.remove(0);
			pitch.add(pos, Double.parseDouble(args[2]));

			pitchRate.remove(0);
			pitchRate.add(pos, Double.parseDouble(args[3]));

			roll.remove(0);
			roll.add(pos, Double.parseDouble(args[4]));

			rollRate.remove(0);
			rollRate.add(pos, Double.parseDouble(args[5]));

			speedFL.remove(0);
			speedFL.add(pos, Double.parseDouble(args[6]));

			speedFR.remove(0);
			speedFR.add(pos, Double.parseDouble(args[7]));

			speedBL.remove(0);
			speedBL.add(pos, Double.parseDouble(args[8]));

			speedBR.remove(0);
			speedBR.add(pos, Double.parseDouble(args[9]));

			height.remove(0);
			height.add(pos, Double.parseDouble(args[10]));

			speedVert.remove(0);
			speedVert.add(pos, Double.parseDouble(args[11]));


		    }else{
			yaw.add(pos, Double.parseDouble(args[0]));
			yawRate.add(pos, Double.parseDouble(args[1]));
			pitch.add(pos, Double.parseDouble(args[2]));
			pitchRate.add(pos, Double.parseDouble(args[3]));
			roll.add(pos, Double.parseDouble(args[4]));
			rollRate.add(pos, Double.parseDouble(args[5]));

			speedFL.add(pos, Double.parseDouble(args[6]));
			speedFR.add(pos, Double.parseDouble(args[7]));
			speedBL.add(pos, Double.parseDouble(args[8]));
			speedBR.add(pos, Double.parseDouble(args[9]));

			height.add(pos, Double.parseDouble(args[10]));
			speedVert.add(pos, Double.parseDouble(args[11]));
		    }
		    pos++;

		    //print stuff in log
		    log.setText("Yaw Angle: \t\t" + args[0] + "\n" +
				"Yaw Rate: \t\t" + args[1] + "\n" +
				"Pitch Angle: \t\t" + args[2] + "\n" +
				"Pitch Rate: \t\t" + args[3] + "\n" +
				"Roll Angle: \t\t" + args[4] + "\n" +
				"Roll Rate: \t\t" + args[5] + "\n" +
				"Speed Front Left: \t" + args[6] + "\n" +
				"Speed Front Right: \t" + args[7] + "\n" +
				"Speed Back Left: \t" + args[8] + "\n" +
				"Speed Back Right: \t" + args[9] + "\n" +
				"Height: \t\t" + args[10] + "\n" +
				"Vertical Velocity: \t" + args[11] + "\n" +
				"Vertical Acceleration: \t" + args[12] + "\n" +
				"P: \t\t" + args[13] + "\n" +
				"I: \t\t" + args[14] + "\n" +
				"D: \t\t" + args[15] + "\n");/* +







		    );*/








		    me.repaint();
		}

	    } catch (Exception e) {
		System.err.println(e.toString());
	    }
	}
	// Ignore all the other eventTypes, but you should consider the other ones.
    }

    public static void main(String[] args) throws Exception {
	SerialTest main = new SerialTest();
	main.initialize();
	Thread t=new Thread() {
	    public void run() {
		//the following line will keep this app alive for 1000    seconds,
		//waiting for events to occur and responding to them    (printing incoming messages to console).
		try {Thread.sleep(1000000);} catch (InterruptedException    ie) {}
	    }
	};
	t.start();
	System.out.println("Started");
    }
}
