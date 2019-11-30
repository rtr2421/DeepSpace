/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Ultrasonic.Unit;
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
  AnalogInput in = new AnalogInput(RobotMap.ULTRASONIC);
  //Ultrasonic us = new Ultrasonic (RobotMap.ULTRASONIC, RobotMap.ULTRASONIC_ECHO, Unit.kInches);

  public double getDistance(){
    return in.getVoltage() * 1024 / 25.4;
    //return us.getRangeInches();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new Rumbler());
  }
}
