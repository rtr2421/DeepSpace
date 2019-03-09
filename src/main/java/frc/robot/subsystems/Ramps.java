/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.LowerRamps;

/**
 * Add your docs here.
 */
public class Ramps extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final double speed = .5;
  Spark motor;
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
     setDefaultCommand(new LowerRamps());
  }
  public Ramps(){
    motor = new Spark(RobotMap.RAMPS);
  }
  public void lower(){
    if(OI.xBoxControl.getYButton()){
      motor.set(speed);
    }else if(OI.xBoxControl.getXButton()){
      motor.set(-speed);
    }else{
      motor.set(0);
    }
  }

  public void stop(){
    motor.set(0);
  }
}
