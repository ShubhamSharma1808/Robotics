package Line_follower;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import java.lang.Math;

public class first_attempt {

	public static void main(String[] args){
		
		int count=0;
		double intensity,intensity1,intensity2,change;
		//double low,high;
		
		LCD.drawString("Program Started,press button for starting the sensor ", 0, 0);
        Button.waitForAnyPress();
        LCD.drawString("Started Sensing", 0, 0);
        LCD.clear();
        LCD.drawString("if the position of robot is fixed press the button", 0, 0);
        Button.waitForAnyPress();
        LCD.clear();
        LightSensor light = new LightSensor(SensorPort.S3);
        intensity=light.getNormalizedLightValue();
        LCD.clear();
        LCD.drawString("intensity is",0,0 );
        System.out.println(intensity);
        LCD.drawString("press button to start", 0, 2);
        Button.waitForAnyPress();
        LCD.clear();
        intensity1=intensity;
        Motor.C.setSpeed(300);
        Motor.A.setSpeed(300); 
        Motor.A.forward();
        Motor.C.forward();
       // low=0.75*intensity1;
        //high=1.20*intensity1;
        
        while(true){
        	count++;   
        	if(count==1)
        		intensity=light.getNormalizedLightValue();
             intensity2=light.getNormalizedLightValue();
             LCD.drawString("Intensity2 is", 5, 1);
             System.out.println(intensity2);
        	if(true){        		
        		change=intensity2-intensity;
        		LCD.drawString("Change is", 5, 3);
        		System.out.println(change);
        		//for right turn
        		if( change>0&& (change/intensity)>0.08){  
        			Motor.C.setSpeed(25);
  	              	Motor.A.setSpeed(300);
  	              	LCD.drawString("Right Turn", 5, 5);
  	             // Button.waitForAnyPress(250);
        		}
        		//for left turn
        		else if(change<0 && Math.abs(change/intensity)>.08){
        			Motor.A.setSpeed(25);
  	              	Motor.C.setSpeed(300);
  	              LCD.drawString("Left Turn", 5, 7);
  	             //	Button.waitForAnyPress(250);
        		}
        		//for straight
        		else{
        			Motor.A.setSpeed(300);
  	              	Motor.C.setSpeed(300);
  	              	//Button.waitForAnyPress(250);
  	              LCD.drawString("Straight", 5,9);
        		}        		        		
        		LCD.clear();
        	}      	
        }
	}
}
