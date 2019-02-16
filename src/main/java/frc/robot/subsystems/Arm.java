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
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.MoveArm;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Add your docs here.
 */
public class Arm extends Subsystem {
  //WPI_TalonSRX motor;
  private static final double leftMod = .95;
  private static final double rightMod = 1;
  Spark spark;
  Encoder armEncoder;
  DigitalInput enc;
  double armModifier = .1;
  static DigitalInput switchBottom;
  DigitalInput switchTop;
  Spark sparkR;
  
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Arm() {
    armEncoder = new Encoder(2,3);
    //motor = new WPI_TalonSRX(3);
    spark = new Spark(5);
    sparkR = new Spark(6);
    switchBottom = new DigitalInput(1);
  }
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new MoveArm());
  }
  public void move(){
    double speed = (OI.xBoxControl.getTriggerAxis(Hand.kRight) - OI.xBoxControl.getTriggerAxis(Hand.kLeft))*1;
    SmartDashboard.putNumber("Arm", speed);
    if(speed > 0){
      if(getSwitch()){
        spark.setSpeed(0);
        sparkR.setSpeed(0);
      }else{
        spark.setSpeed(speed * leftMod);
        sparkR.setSpeed(speed * rightMod);
      }
    }else if(speed < 0){
      spark.setSpeed(speed * leftMod);
      sparkR.setSpeed(speed * rightMod);
    }
    
    //sparkR.setSpeed(speed * rightMod);
    //motor.set(speed);
  }
  public double getRotations(){
    return armEncoder.getDistance();
  }
  public static boolean getSwitch(){
    return switchBottom.get();
  }
}