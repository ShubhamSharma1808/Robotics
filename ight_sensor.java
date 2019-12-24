package using_light_Sensor;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
public class ight_sensor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LCD.drawString("Program Started ", 0, 0);
        Button.waitForAnyPress();
        LCD.drawString("Started Sensing", 0, 0);
        LCD.clear();
        LightSensor light = new LightSensor(SensorPort.S3);
        while(true){
        	
        	LCD.drawInt(light.getNormalizedLightValue(), 4, 0, 1);
        	
        	
        	
        }
        
        
	}

}
