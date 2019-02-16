/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;             //TESTING)@9815831597438*@^$*&^@$*&@
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.SerialDistance;

public class GetDistance extends Command {

  public GetDistance() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_serialPort);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //SmartDashboard.putString("asldjfdsSerialDistance String", Robot.m_serialPort.getString());
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putString("SerialDistance String", Robot.m_serialPort.getString());//.replace("R", "") );
    //distance = Robot.m_serialPort.getString();
    //SmartDashboard.putString("distance", distance.replace("R",""));
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
