/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class PneumaticsDrive extends Command {
  public PneumaticsDrive() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_pneumatics);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //System.out.println("init");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    /*if(OI.xBoxControl.getBumperPressed(Hand.kLeft)) {
      pneumatics.retract();
    }
    if(OI.xBoxControl.getBumperPressed(Hand.kRight)) {
      pneumatics.extend();
    }
    */
    if(OI.xBoxControl.getYButtonPressed()){
      Robot.m_pneumatics.toggleOut();
      if(Robot.m_pneumatics.getOut()){
        Robot.m_pneumatics.retract();
      }else{
        Robot.m_pneumatics.extend();
      }
    }

    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
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
