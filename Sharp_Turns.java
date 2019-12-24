package Third_day;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;

public class Sharp_Turns {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int count=0,sharp_left=0,sharp_right=0;
		double intensity,intensity1,intensity2,change;
		//double low,high;
		
		LCD.drawString("Program Started ", 0, 0);
		LCD.drawString("Press Button to Continue ", 0, 1);
        Button.waitForAnyPress();
        LCD.drawString("Started Sensing", 0, 2);
        LCD.drawString("fix position of robot", 0, 3);
        Button.waitForAnyPress();
        LCD.clear();
        LightSensor light = new LightSensor(SensorPort.S3);
        intensity=light.getNormalizedLightValue();
        LCD.clear();
        LCD.drawString("intensity is",0,0 );
        System.out.println(intensity);
        LCD.drawString("press button to start", 0, 1);
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
        		change=intensity2-intensity;
        		LCD.drawString("Change is", 5, 3);
        		System.out.println(change);
        		
        		if(sharp_left>0){
        			Motor.A.forward();
        			Motor.C.setSpeed(300);
        			sharp_left=0;
        		}
        		else if(sharp_right>0){
        			Motor.C.forward();
        			Motor.A.setSpeed(275);
        			sharp_right=0;
        		}        		
        		//for Sharp right turn
        		if(change>0&& (change/intensity)>0.20){
        			sharp_right++;
        			Motor.C.setSpeed(275);
  	              	Motor.A.setSpeed(300);
  	              	Motor.C.backward();
  	              	LCD.drawString("Sharp Right Turn", 5, 1);
        		}
        		//for sharp left turn 
        		else if(change<0 && Math.abs(change/intensity)>0.12){
        			sharp_left++;
        			Motor.A.setSpeed(275);
  	              	Motor.C.setSpeed(300);
  	              	Motor.A.backward();
  	              	LCD.drawString("Sharp Left Turn", 5, 3);
        		}       		
        		//for right turn
        		else if( change>0&& (change/intensity)>0.08){  
        			Motor.C.setSpeed(25);
  	              	Motor.A.setSpeed(275);
  	              	LCD.drawString("Right Turn", 5, 5);
  	             // Button.waitForAnyPress(250);
        		}
        		//for left turn
        		else if(change<0 && Math.abs(change/intensity)>.05){
        			Motor.A.setSpeed(5);
  	              	Motor.C.setSpeed(275);
  	              LCD.drawString("Left Turn", 5, 7);
  	             //	Button.waitForAnyPress(250);
        		}
        		//for straight
        		else{
        			Motor.A.setSpeed(275);
  	              	Motor.C.setSpeed(275);
  	              	//Button.waitForAnyPress(250);
  	              LCD.drawString("Straight", 5,9);
        		}        		        		
        		LCD.clear();
        	}      	
	}

}
