/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.MoveArm;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Add your docs here.
 */
public class Arm extends Subsystem {
  SpeedController motor;
  Encoder armEncoder;
  DigitalInput enc;
  
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Arm() {
    armEncoder = new Encoder(2,3);
    motor = new WPI_TalonSRX(1);
  }
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new MoveArm());
  }
  public void move(Double speed){
    motor.set(speed);
  }
  public double getRotations(){
    return armEncoder.getDistance();
  }
}
