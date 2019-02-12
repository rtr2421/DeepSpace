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
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.GuideToTarget;
import frc.robot.commands.JoystickDrive;
import com.analog.adis16448.frc.*;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static final ADIS16448_IMU imu = new ADIS16448_IMU();
  public static boolean fast;
  private static Double speedModifier = 1.0;
  public Spark sparkL1 = new Spark(0);
  public Spark sparkL2 = new Spark(1);
  public Spark sparkR1 = new Spark(2);
  public Spark sparkR2 = new Spark(3);

  SpeedControllerGroup leftGroup;
  SpeedControllerGroup rightGroup;

  public DifferentialDrive diffDrive;

  public DriveTrain() {
    leftGroup = new SpeedControllerGroup(sparkL1, sparkL2);
    rightGroup = new SpeedControllerGroup(sparkR1, sparkR2);

    diffDrive = new DifferentialDrive(leftGroup, rightGroup);
    imu.reset();
    imu.calibrate();
  }

  //maybe change back to static (broken code?)
  public void drive(double leftSpeed, double rightSpeed) {
    diffDrive.arcadeDrive(leftSpeed * speedModifier, rightSpeed * speedModifier);
    SmartDashboard.putNumber("Gyro-Z drive", imu.getAngleZ());
    Shuffleboard.selectTab("Live Window");
    Shuffleboard.update();
    SmartDashboard.putNumber("SpeedModifier", speedModifier);

    
  }
  public void tankDrive(double leftSpeed, double rightSpeed){
    diffDrive.tankDrive(leftSpeed, rightSpeed);
    SmartDashboard.putNumber("Gyro-Z drive", imu.getAngleZ());
  }
  

  

  @Override
  public void initDefaultCommand() { 
    // Set the default command for a subsystem here.
    setDefaultCommand(new JoystickDrive());
  }
  public void setFast(){
    speedModifier = 1.0;
  }
  public void setSlow(){
    speedModifier = 0.65;
  }
  public double getGyroX(){
    //SmartDashboard.putNumber("Imu Z axis", imu.getAngleZ());
    return imu.getAngleX();
  }
  public double getGyroY(){
    return imu.getAngleY();
  }
  public double getGyroZ(){
    return imu.getAngleZ();
  }

  
}
