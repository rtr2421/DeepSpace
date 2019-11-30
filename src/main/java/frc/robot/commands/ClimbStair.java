/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class ClimbStair extends Command {
  boolean finished = false;
  int step = 0;
  // Step is 49 in deep
  final static double firstDistance = 55;
  final static double secondDistance = 29;
  public ClimbStair() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_climb);
    requires(Robot.m_driveTrain);
    requires(Robot.m_ultraSonic);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    SmartDashboard.putBoolean("Climb run", true);
    Robot.m_climb.liftFront();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(step == 0){
      if(Robot.m_ultraSonic.getDistance() < firstDistance){
        step++;
      }else{
        Robot.m_driveTrain.drive(.75, 0);
      }
    }else if(step == 1){
      Robot.m_climb.dropFront();
      step++;
    }else if(step == 2){
      Robot.m_climb.liftBack();
      step++;
    }else if(step == 3){
      if(Robot.m_ultraSonic.getDistance() > secondDistance){
        Robot.m_driveTrain.drive(.75, 0);
      }
    }else if(step == 4){
      Robot.m_climb.dropBack();
    }else if(step == 5){
      if(Robot.m_ultraSonic.getDistance() > 10){
        Robot.m_driveTrain.drive(.5, 0);
      }else{
        step++;
      }
    }else if(step == 6){
      finished = true;
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
}
