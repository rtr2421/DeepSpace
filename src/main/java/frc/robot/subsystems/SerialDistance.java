/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.GetDistance;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SerialPort;

/**
 * Add your docs here.
 */
public class SerialDistance extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  SerialPort sensor = new SerialPort(9600, SerialPort.Port.kOnboard, 8, SerialPort.Parity.kNone, SerialPort.StopBits.kOne);
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new GetDistance());
  }

  public String getString()
  {
    return sensor.readString();
   //return "";
  }
}
