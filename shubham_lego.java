package com.lego;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
public class shubham_lego {
	   public static void main(String[] args)
	   {
	              LCD.drawString("Program 1", 0, 0);
	              Button.waitForAnyPress();
	              LCD.clear();
	              Motor.A.forward();
	              Motor.C.forward();
	              LCD.drawString("FORWARD",0,0);
	              Button.waitForAnyPress(4000);
	              LCD.drawString("ROTATION",0,0);
	              Motor.A.setSpeed(50);
	              Motor.C.setSpeed(360);
	              Button.waitForAnyPress(2000);
	              Motor.A.setSpeed(360);  
	              Button.waitForAnyPress(2000);
	              Motor.A.stop();
	              Motor.C.stop();
	   }
}
