/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
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
import frc.robot.commands.ArmDrive;

/**
 * Add your docs here.
 */
public class Arm extends Subsystem {
  //REMEMBER TO REPLACE WITH ACTUAL MEASUREMENTS))(*@&*&%(*($*!@&$@!*&#^*&@!$
  public static final int POSITION[] = {0,1,2,3,4,5,6,7};

  //WPI_TalonSRX motor;
  private static final double leftMod = .95;
  private static final double rightMod = 1;
  
  AnalogInput sensor = new AnalogInput(RobotMap.STRING_POT);
  Encoder armEncoder;
  DigitalInput enc;
  double armModifier = .1;
  static DigitalInput switchBottom;
  DigitalInput switchTop;
  Spark sparkL;
  Spark sparkR;
  double speed = 1;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Arm() {
    
    //0 = lorwRockCarg  1 = midRockCarg 2 = highRockCarg  3 = lowRockHatch  4 = midRockHatch  5 = cargoShipHatch
    armEncoder = new Encoder(RobotMap.ARM_ENCODER_A, RobotMap.ARM_ENCODER_B);
    //motor = new WPI_TalonSRX(3);
    sparkL = new Spark(RobotMap.ARM_L);
    sparkR = new Spark(RobotMap.ARM_R);
    switchBottom = new DigitalInput(RobotMap.ARM_SWITCHBOTTOM);
  }
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ArmDrive());
  }

  public void move(){
    //SmartDashboard.putNumber("Arm", speed);
    
      if(getSwitch()){
        sparkL.setSpeed(0);
        sparkR.setSpeed(0);
      } else {
        sparkL.setSpeed(leftMod);
        sparkR.setSpeed(rightMod);
      }
    
    //sparkR.setSpeed(speed * rightMod);
    //motor.set(speed);
  }
  
  public int readPos(){
    return sensor.getValue();
  }
  public double getRotations(){
    return armEncoder.getDistance();
  }
  public boolean getSwitch(){
    return switchBottom.get();
  }
  public void moveDown(){
    if(getSwitch()) {
      stop();
    }
    else {
      sparkL.setSpeed(-leftMod);
      sparkR.setSpeed(-rightMod);
    }
  }
  public void stop(){
    sparkL.setSpeed(0);
    sparkR.setSpeed(0);
  }
}