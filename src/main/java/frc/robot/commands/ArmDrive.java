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

public class ArmDrive extends Command {
  public static final double WRIST_POSISTION_STRAIGHT = 15;
  int count = 1;
  public ArmDrive() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.arm);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putBoolean("Swtich one", Robot.arm.switchOne());
    SmartDashboard.putBoolean("Switch two", Robot.arm.switchTwo());
    SmartDashboard.putBoolean("Switch three", Robot.arm.switchThree());
    SmartDashboard.putNumber("Arm position", Robot.arm.readPos());
    if(OI.xBoxControl.getBumper(Hand.kLeft)){
      Robot.arm.move();
      SmartDashboard.putBoolean("Arm moving", true);
    }else if(OI.xBoxControl.getBumper(Hand.kRight)){
      Robot.arm.moveDown();
      SmartDashboard.putBoolean("Arm moving", true);
    }else{
      SmartDashboard.putBoolean("Arm moving", false);
      Robot.arm.stop();
    }
    if(OI.xBoxControl.getY(Hand.kLeft) > 0 && !OI.xBoxControl.getStickButton(Hand.kLeft)){
      Robot.m_wrist.raise();
      Robot.m_wrist.move();
    }else if(OI.xBoxControl.getY(Hand.kLeft) < 0 && !OI.xBoxControl.getStickButton(Hand.kLeft)){
      Robot.m_wrist.lower();
      Robot.m_wrist.move();
    }else if(OI.xBoxControl.getStickButton(Hand.kLeft)){
      Robot.m_wrist.setTarget(WRIST_POSISTION_STRAIGHT);
      Robot.m_wrist.move();
    }else {
      Robot.m_wrist.stop();
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
