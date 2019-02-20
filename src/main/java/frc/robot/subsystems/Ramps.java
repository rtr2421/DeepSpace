/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Ramps extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final double speed = .5;
  Spark motor;
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public Ramps(){
    motor = new Spark(RobotMap.RAMPS);
  }
  public void lower(){
    motor.set(speed);
  }
  public void raise(){
    motor.setSpeed(speed);
  }
}
