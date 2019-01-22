package frc.robot.subsystems;

public class PixyPacket {
	public int signature;
	public int x;
	public int y;
	public int width;
	public int height;
	public int angle;
	public int age;
	
	//public int checksumError;
	
	public String toString() {
		return "" + " S:" + signature + " X:" + x + " Y:" + 
		y + " W:" + width + " H:" + height + " A:" + angle + " Age:" + age;
	}
}