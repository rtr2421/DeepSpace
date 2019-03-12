/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveArmTo extends Command {
  int target;
  boolean isDown;
  boolean finished;
  int currentPos;

  public MoveArmTo(int target) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.arm);
    this.target = target;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    currentPos = 1024-Robot.arm.readPos();
    isDown = currentPos > target;
    finished = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    finished = false;
    currentPos = 1024-Robot.arm.readPos();
    if(isDown) {

      if(currentPos <= target) {
        Robot.arm.stop();
        finished = true;
      }
      else {
        Robot.arm.moveDown();
        finished = false;
      }

    }
    else {

      if(currentPos >= target) {
        Robot.arm.stop();
        finished = true;
      }
      else {
        Robot.arm.move();
        finished = false;
      }

    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return finished;
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

  public void setTarget(int target) {
    this.target = target;
  }
}
