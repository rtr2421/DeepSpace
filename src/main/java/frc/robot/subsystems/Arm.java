/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.ArmDrive;

/**
 * Add your docs here.
 */
public class Arm extends Subsystem {
  //REMEMBER TO REPLACE WITH ACTUAL MEASUREMENTS))(*@&*&%(*($*!@&$@!*&#^*&@!$
  public int position = 0;
  public static int MARGIN_OF_ERROR = 30;
  //WPI_TalonSRX motor;
  private static final double leftMod = .95;
  private static final double rightMod = 1;
  private static WPI_TalonSRX motorL = new WPI_TalonSRX(RobotMap.ARM_L);
  private static WPI_TalonSRX motorR = new WPI_TalonSRX(RobotMap.ARM_R);
  double speed = 1;
  DigitalInput switchBottom = new DigitalInput(RobotMap.ARM_SWITCHBOTTOM);
  DigitalInput reedSwitch_1 = new DigitalInput(RobotMap.ARM_REED_1);
  DigitalInput reedSwitch_2 = new DigitalInput(RobotMap.ARM_REED_2);
  DigitalInput reedSwitch_3 = new DigitalInput(RobotMap.ARM_REED_3);
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Arm() {
    //motor = new WPI_TalonSRX(3);
  }
  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new MoveArmTo(7));
    setDefaultCommand(new ArmDrive());
  }

  public void move(){
     if(getSwitch()){
       motorL.set(speed);
       motorR.set(speed);
      } else {
        stop();
      }
  }
  
  public int readPos(){
    if(!reedSwitch_1.get()){
      position = 1;
    }else if(!reedSwitch_2.get()){
      position = 2;
    }else if(!reedSwitch_3.get()){
      position = 3;
    }
    return position;
  }

  public boolean getSwitch(){
    return switchBottom.get();
  }
  public void moveDown(){
    if(getSwitch()) {
      stop();
    }
    else {
      motorL.set(speed);
      motorR.set(speed);
    }
  }
  public void stop(){
    motorL.set(0);
    motorR.set(0);
  }
  public boolean switchOne(){
    return reedSwitch_1.get();
  }
  public boolean switchTwo(){
    return reedSwitch_2.get();
  }
  public boolean switchThree(){
    return reedSwitch_3.get();
  }
}