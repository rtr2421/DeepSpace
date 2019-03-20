/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Wrist extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final static double SPEED = .1;
  //num of angles = 1 rotation   is it 360? no one knows
  private final static double ANGLE_TO_ROTATION = 360;
  public static final double TOP = 100;
  public static final double BOTTOM = 0;
  private boolean finished = false;
  private double targetAngle;

  Encoder wristEncoder;
  DigitalInput switchBottom;
  DigitalInput switchTop;

  WPI_TalonSRX wristTalon;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public Wrist() {
    wristEncoder = new Encoder(RobotMap.WRIST_ENCODER_A, RobotMap.WRIST_ENCODER_B);
    wristTalon = new WPI_TalonSRX(RobotMap.WRISTMOTOR);
  }

  public void move() {
    finished = false;
    boolean down = getAngle()>targetAngle;
    if(getBottom()) {
      wristEncoder.reset();
      wristTalon.set(0);
    }
    else if(getTop()) {
      wristTalon.set(0);
    }
    //forward = down  backwards = up
    if(down){
      if(getAngle() < targetAngle && getAngle() >= BOTTOM){
        finished = true;
        wristTalon.set(0);
      }else{
        finished = false;
        wristTalon.set(SPEED);
      }
    }else{
      if(getAngle() < targetAngle && getAngle() <= TOP) {
        wristTalon.set(-SPEED);
      }
      else {
        finished = true;
        wristTalon.set(0);
      }
      
    }
    
  }

  public boolean atTarget() {
    return getAngle() == targetAngle;
  }
  public void setTarget(double target) {
    targetAngle = target;
  }

  public boolean getTop() {
    return switchTop.get();
  }
  public boolean getBottom() {
    return switchBottom.get();
  }
  public boolean getFinished(){
    return finished;
  }
  public double getAngle(){
    return wristEncoder.getDistance()*ANGLE_TO_ROTATION;
  }
  public void stop(){
    wristTalon.set(0);
  }
}
