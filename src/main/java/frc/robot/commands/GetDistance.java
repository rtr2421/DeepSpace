/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.awt.Color;
import java.util.Map;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class GetDistance extends Command {
  double distance;
  boolean warning;
  public GetDistance() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_ultraSonic);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // Called repeatedly when this Command is scheduled to run
  }
  @Override
  protected void execute() {
    //Color distanceColor = new Color((int)(Robot.m_ultraSonic.voltage()*51), 50, 100);
    distance = Robot.m_ultraSonic.distance();
    if(distance < 360) {
      warning = true;
    }
    else {
      warning = false;
    }
    SmartDashboard.putBoolean("WARNING", warning);
    SmartDashboard.putNumber("Ultrasonic Distance", distance);
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
