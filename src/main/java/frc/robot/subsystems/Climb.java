/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Climb extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  DoubleSolenoid frontPiston = new DoubleSolenoid(RobotMap.CLIMBSOL_FRONT_FORWARD, RobotMap.CLIMBSOL_FRONT_REVERSE);
  DoubleSolenoid backPiston = new DoubleSolenoid(RobotMap.CLIMBSOL_BACK_FORWARD, RobotMap.CLIMBSOL_BACK_REVERSE);
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public void liftFront(){
    frontPiston.set(DoubleSolenoid.Value.kForward);
    backPiston.set(DoubleSolenoid.Value.kReverse);
  }
  public void dropFront(){
    frontPiston.set(DoubleSolenoid.Value.kReverse);
  }
  public void liftBack(){
    backPiston.set(DoubleSolenoid.Value.kForward);
  }
  public void dropBack(){
    backPiston.set(DoubleSolenoid.Value.kReverse);
  }
}
