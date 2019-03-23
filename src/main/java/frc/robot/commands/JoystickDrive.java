/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

public class JoystickDrive extends Command {
  boolean tankDrive = false;
  public JoystickDrive() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double rightSpeed = 0.0;
    double leftSpeed = 0.0;
    

    /*if(tankDrive){
      leftSpeed = OI.xBoxControl.getY(Hand.kLeft);
      rightSpeed = OI.xBoxControl.getY(Hand.kRight);
      Robot.m_driveTrain.tankDrive(leftSpeed, rightSpeed);
    }else{*/
      leftSpeed = OI.xBoxControl.getX(Hand.kRight);
      rightSpeed = -OI.xBoxControl.getY(Hand.kRight);
      Robot.m_driveTrain.drive(rightSpeed, leftSpeed);
      SmartDashboard.putNumber("Drive train value", rightSpeed);
    //}
    SmartDashboard.putNumber("GyroAngle", Robot.m_driveTrain.getGyroZ());
    //SmartDaShboard.putNumber("Left Speed: ", leftSpeed);
    //SmartDashboard.putNumber("Right Speed: ", rightSpeed);
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
