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
import frc.robot.RobotMap;
import frc.robot.commands.GuideToTarget;
import frc.robot.commands.JoystickDrive;
import com.analog.adis16448.frc.*;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public WPI_TalonSRX talonL1;
  public WPI_TalonSRX talonL2;
  public WPI_TalonSRX talonR1;
  public WPI_TalonSRX talonR2;
  
  public static final ADIS16448_IMU imu = new ADIS16448_IMU();
  public static Double speedModifier;
  private final double exponentialGrowth = .5;
  
  //public Spark sparkL1;
  //public Spark sparkL2;
  //public Spark sparkR1;
  //public Spark sparkR2;
  

  SpeedControllerGroup leftGroup;
  SpeedControllerGroup rightGroup;

  public DifferentialDrive diffDrive;

  public DriveTrain() {
    //sparkL1 = new Spark(RobotMap.LEFTMOTOR_1);
    //sparkL2 = new Spark(RobotMap.LEFTMOTOR_2);
    //sparkR1 = new Spark(RobotMap.RIGHTMOTOR_1);
    //sparkR2 = new Spark(RobotMap.RIGHTMOTOR_2);
    talonL1 = new WPI_TalonSRX(RobotMap.LEFTMOTOR_1);
    talonL2 = new WPI_TalonSRX(RobotMap.LEFTMOTOR_2);
    talonR1 = new WPI_TalonSRX(RobotMap.RIGHTMOTOR_1);
    talonR2 = new WPI_TalonSRX(RobotMap.RIGHTMOTOR_2);

    speedModifier = 1.0;
    
    leftGroup = new SpeedControllerGroup(talonL1, talonL2);
    rightGroup = new SpeedControllerGroup(talonR1, talonR2);

    diffDrive = new DifferentialDrive(leftGroup, rightGroup);
    imu.reset();
    imu.calibrate();
  }

  //maybe change back to static (broken code?)
  public void drive(double leftSpeed, double rightSpeed) {
    diffDrive.arcadeDrive(-leftSpeed * speedModifier, -rightSpeed * speedModifier);
  }
  public void tankDrive(double leftSpeed, double rightSpeed){
    diffDrive.tankDrive(-(Math.pow(rightSpeed - 1, 3) + .5*(rightSpeed-1) + 1.5) * .75, -(Math.pow(leftSpeed - 1, 3) + .5*(leftSpeed - 1) + 1.5) * .75);
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
  public double getGyroZ(){
    return imu.getAngleZ();
  }
  
}
