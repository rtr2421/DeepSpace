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
public class TelescopingArm extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  Spark spark = new Spark(RobotMap.TELE_ARM);
  double speed = 1;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void moveArm()
  {
    spark.set(speed);
  }
  public void setForward(){
    speed = Math.abs(speed);
  }
  public void setBackwards(){
    speed = -Math.abs(speed);
  }
  public void stop(){
    spark.set(0);
  }
}

