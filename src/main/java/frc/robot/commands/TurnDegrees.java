/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

public class TurnDegrees extends Command {
  public int target;
  public int turnAngle;
  public boolean isLeft;

  public TurnDegrees(int target) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_driveTrain);
    this.target = target;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    turnAngle = (int) Robot.m_driveTrain.getGyroZ() + target;
    isLeft = target<0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    int leftSpeed = 0;
    int rightSpeed = 0;
    if(isLeft) {
      leftSpeed = -1;
      rightSpeed = 1;
    }
    else {
      leftSpeed = 1;
      rightSpeed = -1;
    }
      
    DriveTrain.drive(leftSpeed, rightSpeed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(isLeft) {
      if(Robot.m_driveTrain.getGyroZ() <= turnAngle)
        return true;
    }
    else {
      if(Robot.m_driveTrain.getGyroZ() >= turnAngle)
        return true;
    }
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
