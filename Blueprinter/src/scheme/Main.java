package scheme;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Johan Isaksson
 * Date: 2015-06-25
 * Time: 11:28
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI();
            }
        });

    }
}

