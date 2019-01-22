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

/**
 * Add your docs here.
 */
public class UltraSonic extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  AnalogInput sonicSensor = new AnalogInput(0);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    
  }
  public Double distance(){
    return getVoltage()* 100/512;//("volt" * scale factor/sensitivity)
  }

  public Double getVoltage()
  {
      Double volt = sonicSensor.getVoltage();
      SmartDashboard.putNumber("AnalogInput Voltage", volt);
      return volt;
  }
}
