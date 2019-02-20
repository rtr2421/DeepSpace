/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class LaserFinder extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static I2C Wire;
  public static double distance;
  public LaserFinder(){
    Wire = new I2C(Port.kOnboard, 0x35);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  
    public static double read(){
      String received = "";
      char[] ch = new char[20];
      byte[] toSend = new byte[1];
      byte[] fromArduino = new byte[20];
      Wire.transaction(toSend, 1, fromArduino, 16);
      for(int i = 0; i < fromArduino.length; i++){
        ch[i] = (char) fromArduino[i];
        if(ch[i] == '-' || ch[i] == '0' || ch[i] == '1' || ch[i] == '2' || ch[i] == '3'|| ch[i] == '4' || ch[i] == '5' || ch[i] == '6' || ch[i] == '7'|| ch[i] == '8' || ch[i] == '9'){
          received += ch[i];
        }
      }
      if(!received.isBlank()){
        distance = Double.parseDouble(received);
      }
      SmartDashboard.putNumber("Distance from laser", distance);
      return distance;
    }
  
}
