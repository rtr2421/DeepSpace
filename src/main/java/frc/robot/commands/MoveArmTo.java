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
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Wrist;

public class MoveArmTo extends Command {
  int position = 0;
  int target;
  boolean isDown;
  boolean finished;
  int currentPos;
  double WRIST_ANGLE_TOP = 15;
  double WRIST_ANGLE_BOTTOM = 1;

  public MoveArmTo(int target) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.arm);
    requires(Robot.m_wrist);
    this.target = target;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    position = Robot.arm.readPos();
    if(Robot.arm.readPos() < target){
      isDown = false;
    }else if(Robot.arm.readPos() > target){
      isDown = true;
    }
    finished = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.arm.readPos() == 1 && target == 1){
      finished = true;
    }else if(Robot.arm.readPos() == 2 && target == 2){
      finished = true;
    }else if(Robot.arm.readPos() == 3 && target == 3){
      finished = true;
    }else{
      finished = false;
    }
      if(target == 7){
        Robot.m_wrist.setTarget(WRIST_ANGLE_TOP);
      }
      if(target != 7){
        Robot.m_wrist.setTarget(WRIST_ANGLE_BOTTOM);
      }
      if(!isDown){
        Robot.m_wrist.move();
      }else{
        Robot.arm.moveDown();
      }
    
    SmartDashboard.putBoolean("Arm Done", finished);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return finished && Robot.m_wrist.getFinished();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.arm.stop();
    Robot.m_wrist.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }

  public void setTarget(int target) {
    this.target = target;
  }
}
