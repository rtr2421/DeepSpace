/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.ArmLimitSwitch;
import frc.robot.subsystems.BaseLimitSwitch;

public class ArmToNextPosition extends Command {
  boolean startRaised = false;
  static final double	speed = 1;
  public ArmToNextPosition() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.arm);
    requires(Robot.m_baseSwitch);
    requires(Robot.m_limitSwitch);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    startRaised = ArmLimitSwitch.getState();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(startRaised){
      Arm.raise(speed);
    }else if(!startRaised){
        Arm.raise(-speed);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(startRaised){
      if(BaseLimitSwitch.getState()){
        return true;
      }
    }else{
      if(ArmLimitSwitch.getState()){
        return true;
      }
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
