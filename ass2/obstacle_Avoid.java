import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;


public class obstacle_Avoid {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws InterruptedException  {
		// TODO Auto-generated method stub
		int count=0,sharp_left=0,sharp_right=0,distance=0;
		double intensity,intensity2,change;
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
        Motor.C.setSpeed(275);
        Motor.A.setSpeed(275); 
        Motor.A.forward();
        Motor.C.forward();
        
        
	       UltrasonicSensor us = new UltrasonicSensor(SensorPort.S4);       
	       LCD.drawString("Ultrasonic ", 0, 0);
	       LCD.drawString("Distance(cm) ", 0, 2);
	       LCD.clear();
		   int angle,limit,limit2,prev_cnt=0,cnt=0;
	       while (! Button.ESCAPE.isDown())
	       {
	    	   prev_cnt=0;
	    	   cnt=0;
	    	   distance=us.getDistance();
	    	   LCD.drawInt(distance,3,13,2);     
	           count++;   
	           if(count==1)
	        	intensity=light.getNormalizedLightValue();	//greater the value greater the reflection and greater reflection 
	           intensity2=light.getNormalizedLightValue();	//occurs when surface is of light(white) colour. 
	           change=intensity2-intensity;
	           if(sharp_left>0){
	        	   Motor.A.forward();
	        	   Motor.C.setSpeed(275);
	        	   sharp_left=0;
	           }
	          // LCD.drawString("Change is", 5, 3);
	           if(distance<30){
	        	   limit=0;
	        	   limit2=0;
	        	   cnt=1;
	    		   LCD.drawString("Obstacle Detected", 0, 4);
	    		   Motor.A.setSpeed(10);	//A is left side
	    		   Motor.C.setSpeed(200);	//C is right side	    		   
	    		   intensity2=light.getNormalizedLightValue();
	    		   change=intensity2-intensity;		//change positive means darker(black) surface se lighter(white) surface pe gye hai.
	        	   while(!(change<0 && change/intensity>0.009)){		//% change used is .9%
	        		   angle=0;	        		   	        		        		   
	        		   if(limit2==0){
	        			   cnt=0;
		        		   for(int i=1;i<5;i++){
		        			   Motor.B.rotate(angle);
		        			   distance=us.getDistance();
		        			   if(distance<25)
		        				   cnt++;
		        			   angle=angle+10*i;	        			   
		        		   }
		        		   Motor.B.rotate(-angle);
		        		   LCD.drawString("done with loop",0,0);
		        		   limit2=0;
	        		   }
	        		   limit2=(limit2+1)%100;
	        		   //Button.waitForAnyEvent(200);	        		   
	        		   if(cnt!=0 && prev_cnt==0){
	        			   LCD.drawString("in 1st condn", 0, 2);
	        			   Motor.A.setSpeed(10);
	        			   Motor.C.setSpeed(200);
	        			   limit=0;
	        		   }
	        		   else if(cnt==0 && prev_cnt==0 ){
	        			   LCD.drawString("in 2nd condn", 0, 2);
	        			   if(limit<100){
		        			   Motor.A.setSpeed(20);
		        			   Motor.C.setSpeed(200);
	        			   }
	        			   else if(limit>500){
	        				   Motor.A.setSpeed(200);
		        			   Motor.C.setSpeed(2);
		        			   //Button.waitForAnyEvent(1000);
		        			   limit=101;
	        			   }   			   
	        			   else{
	        				   Motor.A.setSpeed(200);
		        			   Motor.C.setSpeed(200);
	        			   }
	        			   limit++;
	        		   }
	        		   else if(cnt==0 && prev_cnt!=0){
	        			   LCD.drawString("in 3rd condn", 0, 2);
	        			   Motor.A.setSpeed(200);
	        			   Motor.C.setSpeed(10);
	        			    limit=0;
	        		   }
	        		   else{
	        			   LCD.drawString("in 4th condn", 0, 2);
	        			   Motor.A.setSpeed(200);
	        			   Motor.C.setSpeed(200);
	        			   limit=0;
	        		   }
	        		   //Button.waitForAnyEvent(1000);
	        		   LCD.clear();
	        		   prev_cnt=cnt;
	        		   intensity2=light.getNormalizedLightValue();
		    		   change=intensity2-intensity;
		    		   cnt=0;
	        	   }
	        	   sharp_left++;
	        	   Motor.A.backward();
	        	   Motor.A.setSpeed(275);
	        	   Motor.C.setSpeed(300);
	        	   
	           }
	          
	           //_________________________________________________if no obstacle then simple line follower____________________________________
	           	else{        	   
					           
					           if(sharp_right>0){
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
					           }
					           //for sharp left turn 
					           else if(change<0 && Math.abs(change/intensity)>0.12){
					        	   sharp_left++;
					        	   Motor.A.setSpeed(275);
					        	   Motor.C.setSpeed(300);
					        	   Motor.A.backward();
					           }       		
					           //for right turn
					           else if( change>0&& (change/intensity)>0.08){  
					        	   Motor.C.setSpeed(50);
					        	   Motor.A.setSpeed(275);     	   
					           }
					           //for left turn
					           else if(change<0 && Math.abs(change/intensity)>.05){
					        	   Motor.A.setSpeed(25);
					        	   Motor.C.setSpeed(275);	        	  
					           }
					           //for straight
					           else{
					        	   Motor.A.setSpeed(275);
					        	   Motor.C.setSpeed(275);
					           }
	           	}
	           	LCD.clear();
	       }	       
	       LCD.drawString("Program stopped", 0, 0);
	       Thread.sleep(2000);
	}  
	

}
