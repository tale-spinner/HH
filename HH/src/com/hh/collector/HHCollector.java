package com.hh.collector;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.MouseEvent;


public class HHCollector {
    // Constants
    private static final boolean debug   = false;   // Debug mode
    private static final int curPage     = 2;       // Current page Adventures start on
    private static final int numGirls    = 49;      // Tells us how many girls are in the harem
    private static final int maxFights   = 500;     // In case we want to limit how many fights we fight
    private static final int cycleBuffer = 62200;   // Sleep time between cycles (in milliseconds)
    
    // Globals
    private static Robot robot = null;
    private static SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss.SSS");
    
    
    public static void main(String[] args) {
        
        // Buid our robot to control the mouse
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        
        
        // Main loop
        int fightCnt  = 0;
        for( int o=1; o<=500; o++){
        
            // Clear out harem every <cycleBuffer> seconds
            for(int i=1; i<=12; i++){
                msg("Cycle " + o + '-' + i, true );
                
                ClearHarem();
                
                try { Thread.sleep(cycleBuffer); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
            
            // Fight something occasionally
            if( ++fightCnt > maxFights ) { msg("Max fight count reached. Pacifism engaged."); continue; }
            FightGruntt();
        }
    }
    
    public static void GoToTown() {
        klick( 2220, 40 );
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        klick( 2220, 40 );
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        klick( 2220, 40 );
        try { Thread.sleep(4000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
    
    public static void ClearHarem() {
        // Set position of the top of the harem. This is our reference.
        final int topPosX    = 2500;
        final int topPosY    = 245;  // lowest Y of harem is 765
        
        // Reset our location
        GoToTown();

        // Go to Harem from Town
        klick( 3575, 650 );
        try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        klick( 3575, 650 );
        try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // Scroll through the girls and collect money
        for( int o=1; o<=Math.ceil(numGirls/3); o++ ) {
            for( int i=0; i<=20; i++ ) {
                klick( topPosX, topPosY + (i * 25) );
                try { Thread.sleep(100); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
            scroll(3);
            try { Thread.sleep(300); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
    }

    
    public static void FightDarkLord() {
        msg("Fighting Dark Lord!",true);
        Battle( 2400, 650, 1 );
    }

    
    public static void FightNinjaSpy() {
        msg("Fighting Ninja Spy!",true);
        Battle( 2830, 680, 1 );
    }
    
    public static void FightGruntt() {
        msg("Fighting Gruntt!",true);
        Battle( 2920, 220, 1 );
    }
    
    public static void FightEdwarda() {
        msg("Fighting Edwarda!",true);
        Battle( 3435, 380, 1 );
    }

    
    public static void FightDonatien() {
        msg("Fighting Donatien!",true);
        Battle( 3250, 700, 1 );
    }
    
    public static void FightSilvanus() {
        msg("Fight Silvanus!",true);
        Battle( 2950, 650, 2 );
    }
    
    public static void FightBremen() {
        msg("Fight Bremen!",true);
        Battle( 3280, 230, 2 );
    }
    
    public static void FightFinalmecia() {
        msg("Fight Finalmecia!",true);
        Battle( 3590, 230, 2 );
    }
    
    // Overload Battle for backwards compatibility
    public static void Battle( int a, int b ) {
        Battle(a,b,curPage);
    }
    
    
    public static void Battle( int locX, int locY, int page ) {
        
        GoToTown();
        
        // Adventure!
        klick( 2800, 400 );
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        
        if ( page > curPage ) { 
            msg("WAIT!!!!  I can't do that!!!!" );
            return;
        }
        
        // If we need to go to a previous page of the map
        if( page < curPage ) {
            klick( 2140, 475 );
            try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
        
        // Go to world....
        klick( locX, locY);
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        
        // The boss!
        klick( 3570, 735 );
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        
        // Battle!
        klick( 2850, 830 );
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        
        // Skip
        klick( 2875, 830 );
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        
        // Ok
        klick( 2850, 620 );
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
    
    
    public static void delay( int s ) {
        try { Thread.sleep(s); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
    
    public static void pointAt( int x, int y ) {
        robot.mouseMove(x, y);
        robot.delay(100);
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
        for( int i=0; i<amt; i++ ) {
            robot.mouseWheel( 1 );
            robot.delay(100);
        }
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
