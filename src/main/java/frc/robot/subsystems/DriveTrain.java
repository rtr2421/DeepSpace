/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.JoystickDrive;
import com.analog.adis16448.frc.ADIS16448_IMU;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static final ADIS16448_IMU imu = new ADIS16448_IMU();

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

  public void drive(double leftSpeed, double rightSpeed) {
    diffDrive.arcadeDrive(leftSpeed, rightSpeed);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new JoystickDrive());
  }

  public double getGyroX() {
    return imu.getAngleX();
  }
  public double getGyroY() {
    return imu.getAngleY();
  }
  public double getGyroZ() {
    return imu.getAngleZ();
  }
}
