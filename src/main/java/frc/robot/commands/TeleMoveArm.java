/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.TelescopingArm;

public class TeleMoveArm extends Command {
  boolean setForward;
  public TeleMoveArm(boolean setForward) {
    // Use requires() here to declare subsystem dependencies
    this.setForward = setForward;
    requires(Robot.m_teleArm);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if(setForward){
      Robot.m_teleArm.setForward();
    }else{
      Robot.m_teleArm.setBackwards();
    }
    Robot.m_teleArm.moveArm();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
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
