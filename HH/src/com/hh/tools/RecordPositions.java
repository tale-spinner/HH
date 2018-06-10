package com.hh.tools;

import java.awt.AWTEvent;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;
import java.awt.*;

import javax.swing.*;

public class RecordPositions {
    
    public static JFrame frame = new JFrame();

    public static void main(String[] args) {
        int whatever = MouseEvent.MOUSE_CLICKED;
        msg("Button1_Down_Mask: " + whatever);
        
        Toolkit.getDefaultToolkit().addAWTEventListener(
          new Listener(), AWTEvent.MOUSE_EVENT_MASK | AWTEvent.FOCUS_EVENT_MASK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setLocation(2400,100);
        frame.setSize( 50, 800 );
        frame.getContentPane().setBackground(new Color(1,1,1,20));
        frame.setBackground(new Color(1,1,1,20));
        frame.setVisible(true);
    }

    private static class Listener implements AWTEventListener {
        public void eventDispatched(AWTEvent event) {

            // We do not want the event to show twice,
            // as it shows for focusing and unfocusing
            
//            msg( "Event: " + event.getID() );

            if( event.getID() == 501 ) {
                Point p = MouseInfo.getPointerInfo().getLocation();
                System.out.println("Mouse down at " + p.x + ", " + p.y);
            }
//            if( event.getID() == 502 ) {
//                Point p = MouseInfo.getPointerInfo().getLocation();
//                System.out.println("Mouse up at " + p.x + ", " + p.y);
//            }

            // The frame was just unfocused! To make
            // sure we get the next mouse click, we
            // need to focus it again!

            frame.setVisible(true);

        }
    }

    // I just hate typing out that whole line to print to the screen
    public static void msg ( String s ) { System.out.println(s); }
}
