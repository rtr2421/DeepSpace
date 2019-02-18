/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.CameraI2c;
import frc.robot.subsystems.DriveTrain;

public class TurnDegrees extends Command {
  boolean finished = false;
  public static double offset = 6;
  public double target;
  public double turnAngle;
  public boolean isLeft;
  double leftSpeed = 0;
  double rightSpeed = 0;
  public TurnDegrees() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_driveTrain);
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.target = CameraI2c.read();
    //SmartDashboard.putNumber("Target", target);
    isLeft = target<0;
    //SmartDashboard.putBoolean("IsLeft", isLeft);
    if(isLeft){
      turnAngle = Robot.m_driveTrain.getGyroZ() - Math.abs(target);
    }else{
      turnAngle = Robot.m_driveTrain.getGyroZ() + Math.abs(target);
    }
    //SmartDashboard.putNumber("Gyro angle of target", turnAngle);
    //SmartDashboard.putNumber("Angle to target", target);
    
    if(isLeft) {
      leftSpeed = -.5;
      rightSpeed = .5;
    }
    else {
      leftSpeed = .5;
      rightSpeed = -.5;
    }
      
   
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_driveTrain.tankDrive(leftSpeed, rightSpeed);
    SmartDashboard.putNumber("Offset", offset);
    SmartDashboard.getNumber("Offset", offset);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    Double gyroZ = Robot.m_driveTrain.getGyroZ();
    if(target == 0)
      finished = true;
    else {
      if(isLeft) {
        if(gyroZ <= turnAngle + offset){
          finished = true;  
        }else{
          finished = false;
        }
      }
      else {
        if(gyroZ >= turnAngle - offset){
          
          finished = true;
        }else{
          finished = false;
        }
      }
    }
    //SmartDashboard.putNumber("GyroZ turnangle", gyroZ);
    //SmartDashboard.putNumber("TurnAngle",turnAngle);
    //SmartDashboard.putBoolean("Stopped", finished);
    
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
