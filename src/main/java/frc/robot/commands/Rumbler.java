/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.UltraSonic;

public class Rumbler extends Command {
public static double zone = 18;
public static boolean warn = false;
int count = 0;
  public Rumbler() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_ultraSonic);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double distance = Robot.m_ultraSonic.getDistance();
    if(count >= 5){
    if(distance < zone){
      OI.xBoxControl.setRumble(RumbleType.kLeftRumble, 1 - (distance/zone));
      OI.xBoxControl.setRumble(RumbleType.kRightRumble, 1 - (distance/zone));
    }else{
      OI.xBoxControl.setRumble(RumbleType.kLeftRumble, 0);
      OI.xBoxControl.setRumble(RumbleType.kRightRumble, 0);
    }
    if(distance < zone) {
      warn = true;
    }
    else {
      warn = false;
    }
    
    SmartDashboard.putNumber("Ultrasonic Distance", distance);
    SmartDashboard.putBoolean("WARNING", warn);
    count = 0;
  }
  count++;

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
