package com.hh.tools;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.MouseEvent;

public class ScratchPad {
    private static Robot robot = null;
    private static boolean debug = false;
    private static SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss.SSS");

    public static void main(String[] args) {
        // Buid our robot to control the mouse
        try { robot = new Robot(); } catch (AWTException e) { e.printStackTrace(); }
        
        klick( 2000, 275 );
//        pointAt( 2400, 275 );
//        pointAt( 2400, 403 );
//        pointAt( 2400, 531 );
//        pointAt( 2400, 659 );
        
        int startX = 2400;
        int startY = 275;
        int curY   = startY;
        int girls  = 10;
        
        for( int i=0; i<girls; i++ ){
            
            if( i > 0 ){ curY += 128; }

            if( curY > 755 ) {
                scroll( 1 );
                curY -= 115;
            }
            
            msg( "Y: " + curY );
            pointAt( startX, curY );
            
        }
        
        
//        scroll( 1 );

        
        // 659 - 115 = 544
    }

    public static void pointAt( int x, int y ) {
        robot.mouseMove(x, y);
        robot.delay(500);
    }
    
    public static void klick ( int x, int y ) {
        robot.mouseMove(x, y);
        robot.delay(100);
        
        if(!debug) {
            robot.mousePress(MouseEvent.BUTTON1_MASK);
            robot.mouseRelease(MouseEvent.BUTTON1_MASK);
        }
    }
    
    public static void drag ( int startX, int startY, int endX, int endY ) {
        robot.mouseMove( startX, startY );
        robot.delay(100);
        
        if(!debug) {
            robot.mousePress(MouseEvent.BUTTON1_MASK);
            robot.delay(100);
            
            robot.mouseMove( endX, endY );
            robot.delay(100);
            
            robot.mouseRelease(MouseEvent.BUTTON1_MASK);
        }
    }
    
    public static void scroll ( int amt ) {
        robot.mouseWheel( amt );
    }
    
    public static void displayCurTime() {
        Date date = new Date();
        System.out.println(date.toString());
    }
    

    // I hate typing out that whole println thing
    public static void msg ( String s, boolean showTime) {
        String outmsg = s;
        if(showTime){
            outmsg = outmsg + " @ " + df.format(new Date());
        }
        System.out.println(outmsg);
    }
    public static void msg ( String s ) { msg( s, false ); }
}
