/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.ClawDrive;
/**
 * Add your docs here.
 */
public class Claw extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public double speed = .5;
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
     setDefaultCommand(new ClawDrive()); 
  }
    static Spark claw1 = new Spark(RobotMap.CLAW_1);
    static Spark claw2 = new Spark(RobotMap.CLAW_2);
  public void moveForward(){
    speed = SmartDashboard.getNumber("Claw Speed", speed);
    claw2.set(speed);
    claw1.set(speed);
  }
  public void moveBackward(){
    speed = SmartDashboard.getNumber("Claw Speed", speed);
    claw2.set(-speed);
    claw1.set(-speed);
  }
  public void stop(){
    claw1.set(0);
    claw2.set(0);
  }
  public void move(){
    double speed = OI.xBoxControl.getTriggerAxis(Hand.kLeft);
    double backSpeed = OI.xBoxControl.getTriggerAxis(Hand.kRight);
    claw1.set(speed - backSpeed);
    claw2.set(speed - backSpeed);
  }
}
