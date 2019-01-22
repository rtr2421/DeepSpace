/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.JoystickDrive;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static final Double speedModifier = 0.4;
  public Spark sparkL1 = new Spark(0);
  public Spark sparkL2 = new Spark(1);
  public Spark sparkR1 = new Spark(2);
  public Spark sparkR2 = new Spark(3);

  SpeedControllerGroup leftGroup;
  SpeedControllerGroup rightGroup;

  public static DifferentialDrive diffDrive;

  public DriveTrain() {
    leftGroup = new SpeedControllerGroup(sparkL1, sparkL2);
    rightGroup = new SpeedControllerGroup(sparkR1, sparkR2);

    diffDrive = new DifferentialDrive(leftGroup, rightGroup);
  }

  public static void drive(double leftSpeed, double rightSpeed) {
    diffDrive.arcadeDrive(leftSpeed * speedModifier, rightSpeed * speedModifier);
  }
  public static void hyperDrive(Double leftSpeed,Double rightSpeed){
    diffDrive.arcadeDrive(leftSpeed, rightSpeed);
  }

  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new JoystickDrive());
  }
}
