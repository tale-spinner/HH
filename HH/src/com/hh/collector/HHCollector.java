package com.hh.collector;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.MouseEvent;


public class HHCollector {
    // Constants
    private static final boolean  DEBUG      =  false;   // Debug mode
    private static final int      CURR_PAGE  =  2;       // Current page Adventures start on
    private static final int      NUM_GIRLS  =  53;      // Tells us how many girls are in the harem
    
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
        
        // Initialize date/time vars
        Date curDTTM = new Date();
        Date clearHaremDTTM = curDTTM;
        Date battleVillDTTM = DateAddMins(30,curDTTM);
        Date clearArenaDTTM = DateAddMins(30,curDTTM);
        
        
        // Main cycle
        while(true) {
            // Reset current date/time
            curDTTM = new Date();
            
            // Clear harem when current date/time is after clearHaremDTTM
            if ( curDTTM.after(clearHaremDTTM) ) {
                ClearHarem();
                clearHaremDTTM = DateAddMins(3,curDTTM);
            }

            // Battle the villain when current date/time is after battleVillDTTM
            if ( curDTTM.after(battleVillDTTM) ) {
                FightSilvanus();
                battleVillDTTM = DateAddMins(30,curDTTM);
            }

            // Clear the arena when current date/time is after clearArenaDTTM
            if ( curDTTM.after(clearArenaDTTM) ) {
                ClearArena();
                clearArenaDTTM = DateAddMins(30);
            }
            
            delay(1);  // Wait a second so we're not just cycling a thousand times a second or something.
        }
    }
    
    public static void GoToTown() {
        // Clear occasional goof up screen
        klick( 1950, 450 );
        try { Thread.sleep(150); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        klick( 1950, 451 );
        try { Thread.sleep(150); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        klick( 1950, 452 );
        try { Thread.sleep(150); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        
        // Go home screen / town
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
        
        msg( "Clearing Harem", true );
        
        // Reset our location
        GoToTown();

        // Go to Harem from Town
        klick( 3575, 650 );
        try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        klick( 3575, 650 );
        try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        
        // Scroll to the top
        klick( topPosX, topPosY );
        scrollUp(150);
        
        // Scroll through the girls and collect money
        for( int o=1; o<=Math.ceil(NUM_GIRLS/4); o++ ) {
            for( int i=0; i<=20; i++ ) {
                klick( topPosX, topPosY + (i * 25) );
                try { Thread.sleep(100); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
            scrollDown(3);
            try { Thread.sleep(300); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
    }

    
    public static void FightDarkLord() {
        msg("Fighting Dark Lord!",true);
        BattleVillain( 2400, 650, 1 );
    }

    
    public static void FightNinjaSpy() {
        msg("Fighting Ninja Spy!",true);
        BattleVillain( 2830, 680, 1 );
    }
    
    public static void FightGruntt() {
        msg("Fighting Gruntt!",true);
        BattleVillain( 2920, 220, 1 );
    }
    
    public static void FightEdwarda() {
        msg("Fighting Edwarda!",true);
        BattleVillain( 3435, 380, 1 );
    }

    
    public static void FightDonatien() {
        msg("Fighting Donatien!",true);
        BattleVillain( 3250, 700, 1 );
    }
    
    public static void FightSilvanus() {
        msg("Fight Silvanus!",true);
        BattleVillain( 2480, 685, 2 );
    }
    
    public static void FightBremen() {
        msg("Fight Bremen!",true);
        BattleVillain( 2530, 320, 2 );
    }
    
    public static void FightFinalmecia() {
        msg("Fight Finalmecia!",true);
        BattleVillain( 3100, 230, 2 );
    }
    
    // Overload Battle for backwards compatibility
    public static void BattleVillain( int a, int b ) {
        BattleVillain(a,b,CURR_PAGE);
    }
    
    
    public static void BattleVillain( int locX, int locY, int page ) {
        
        GoToTown();
        
        // Adventure!
        klick( 2800, 400 );
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        
        if ( page > CURR_PAGE ) { 
            msg("WAIT!!!!  I can't do that!!!!" );
            return;
        }
        
        // If we need to go to a previous page of the map
        if( page < CURR_PAGE ) {
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
        klick( 2760, 850 );
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        
        // Skip
        klick( 2875, 830 );
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        
        // Ok
        klick( 2875, 620 );
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        
        // Click button for any girls we won
        klick( 3017, 725 );
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        
    }
    
    public static void ClearArena() {
        msg("Clearing Arena!",true);
        
        // Reset our location to town
        GoToTown();
        
        // Go to Arena
        klick( 2390, 670 );
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        
        // Battle each of our opponents
        BattleArena(1);
        BattleArena(2);
        BattleArena(3);
    }
    
    
    public static void BattleArena( int a ) {
        
        // Choose our opponent
        if( a == 1 ) {
            // Opponent 1
            klick( 2825, 550 );
            try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
        else if ( a == 2 ) {
            // Opponent 2
            klick( 3150, 550 );
            try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
        else if ( a == 3 ) {
            // Opponent 3
            klick( 3475, 550 );
            try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
        

        // Battle!
        klick( 2760, 850 );
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        
        // Skip
        klick( 2875, 830 );
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        
        // In case we lose
        klick( 2880, 670 );
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        
        // In case we win
        klick( 2875, 830 );
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        
        // In case we got a prize
        klick( 2880, 620 );
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
    
    // Return a date/time <min> minutes after aDTTM
    public static Date DateAddMins( int min, Date aDTTM ) {
        final long ONE_MINUTE_IN_MILLIS = 60000;
        return new Date( aDTTM.getTime() + (min * ONE_MINUTE_IN_MILLIS));
    }

    // Return a date/time <min> minutes after current time
    public static Date DateAddMins( int min ) {
        return DateAddMins( min, new Date() );
    }
    
    // Wait for <seconds> seconds
    public static void delay( int seconds ) {
        try { Thread.sleep(seconds*1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
    
    // Point mouse at x,y coordinates
    public static void pointAt( int x, int y ) {
        robot.mouseMove(x, y);
        robot.delay(100);
    }
    
    // Click mouse at x,y coordinates
    public static void klick ( int x, int y ) {
        robot.mouseMove(x, y);
        robot.delay(100);
        
        if(!DEBUG) {
            robot.mousePress(MouseEvent.BUTTON1_MASK);
            robot.mouseRelease(MouseEvent.BUTTON1_MASK);
        }
    }
    
    public static void drag ( int startX, int startY, int endX, int endY ) {
        robot.mouseMove( startX, startY );
        robot.delay(100);
        
        if(!DEBUG) {
            robot.mousePress(MouseEvent.BUTTON1_MASK);
            robot.delay(100);
            
            robot.mouseMove( endX, endY );
            robot.delay(100);
            
            robot.mouseRelease(MouseEvent.BUTTON1_MASK);
        }
    }
    
    public static void scrollDown ( int amt ) {
        for( int i=0; i<amt; i++ ) {
            robot.mouseWheel( 1 );
            robot.delay(50);
        }
    }
    
    public static void scrollUp ( int amt ) {
        for( int i=0; i<amt; i++ ) {
            robot.mouseWheel( -1 );
            robot.delay(50);
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
