/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.Rumbler;

/**
 * Add your docs here.
 */
public class UltraSonic extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  AnalogInput sonicSensor = new AnalogInput(RobotMap.ULTRASONIC);
  private double distance = 0.0;
  private double volt = 0.0;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new Rumbler());
  }
  public Double distance(){
    distance = voltage()*1024;
    return distance; //distance(mm) = volt*1024
  }

  public Double voltage()
  {
      volt = sonicSensor.getVoltage();
      return volt;
  }
}
