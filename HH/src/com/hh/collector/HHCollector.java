package com.hh.collector;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.MouseEvent;


//Girl 4, 0 scrolls, y=659, s=n/a
//Girl 5, 1 scroll , y=700, s=87
//Girl 6, 2 scrolls, y=732, s=96
//Girl 6, 3 scrolls, y=632, s=100
//Girl 7, 4 scrolls, y=653, s=107
//Girl 8, 5 scrolls, y=669, s=112
//Girl 9, 6 scrolls, y=680, s=117
//Girl 10, 7 scrolls, y=687, s=121
//Girl 11, 8 scrolls, y=690, s=125
//girl 12, 9 scrolls, y=690, s=128
//girl 13, 10 scrolls, y=687, 


public class HHCollector {
    // Constants
    private static final boolean debug   = false;   // Debug mode
    private static final int curPage     = 2;       // Current page Adventures start on
    private static final int numGirls    = 48;      // Tells us how many girls are in the harem
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
                
                ClearHarem2();
                
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
    
    public static void ClearHarem2() {
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
    
    public static void ClearHarem() {
        // Set position of top button. This is our reference
        final int topBtnX    = 2500;
        final int topBtnY    = 275;
        
        // Distance a single click on the scroll wheel takes us
        int scrollDist = 122;  // Originally 115
        
        // Our current Y position
        int curY = topBtnY;
        
        
        // Reset our location
        GoToTown();

        // Go to Harem from Town
        klick( 3575, 650 );
        try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        klick( 3575, 650 );
        try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        
      // Scroll through the girls and collect money
      for( int i=1; i <= 20; i++ ){
          // First button doesn't need to change positions
          if( i > 1 ){ curY += 128; }
          
          if( i==5  ) { scroll(1); curY-=87; }
          if( i==6  ) { scroll(1); curY-=96; scroll(1); curY-=100; }
          if( i==7  ) { scroll(1); curY-=107; }
          if( i==8  ) { scroll(1); curY-=112; }
          if( i==9  ) { scroll(1); curY-=117; }
          if( i==10 ) { scroll(1); curY-=122; }
//          if( i==11 ) { scroll(1); curY-=125; }
//          if( i==12 ) { scroll(1); curY-=128; }
//          if( i==13 ) { scroll(1); curY-=131; }
//          if( i==14 ) { scroll(1); curY-=134; }
//          if( i==15 ) { scroll(1); curY-=137; }
//          if( i==16 ) { scroll(1); curY-=140; }
//          if( i==17 ) { scroll(1); curY-=143; }
//          if( i==18 ) { scroll(1); curY-=146; }
          
          if( curY > 710 ) { scroll(1); curY-=scrollDist; scrollDist+=3; }
          msg("i=" + i + "  scrollDist=" + scrollDist );
        
        
//        // Scroll through the girls and collect money
//        for( int i=1; i <= numGirls; i++ ){
//            // First button doesn't need to change positions
//            if( i > 1 ){ curY += 128; }
//            
//            // Y=755 is the bottom edge. If we're beyond that, scroll until we're above that and then subtract 115 pixels for every time we scrolled
//            int safety = 0;
//            while( curY > 710 ) {
//                scroll( 1 );
//                curY -= scrollDist;  // Compensate for scrolling
//                
//                if(safety++>3){msg("Shit went sideways!");System.exit(1);}
//            }
//            
//            // There seems to be some slippage over time. Attempt to compensate.
//            if( i == 25 ) { curY -= 5; }
//            
//            // Collect money
//            if( i == numGirls ) {
//                klick( topBtnX, 700 );         // Last button is at Y position 700
//            }
//            else {
                klick( topBtnX, curY );
//            }
            try { Thread.sleep(800); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
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
