/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.PneumaticsDrive;

/**
 * Add your docs here.
 */
public class pneumatics extends Subsystem {
  boolean out = false;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static Compressor compressor;
  //public static Solenoid forward = new Solenoid(RobotMap.DOUBLESOL_FORWARD);
  ///public static Solenoid backwards = new Solenoid(RobotMap.DOUBLESOL_REVERSE);
  
  public static DoubleSolenoid doubleSol = new DoubleSolenoid(RobotMap.DOUBLESOL_FORWARD, RobotMap.DOUBLESOL_REVERSE);
  public static DoubleSolenoid doubleSol2 = new DoubleSolenoid(RobotMap.DOUBLESOL_FORWARD1, RobotMap.DOUBLESOL_REVERSE1);
  public pneumatics() {
    compressor = new Compressor(RobotMap.PNEUMATIC_COMPRESSOR);
    compressor.setClosedLoopControl(true);
    compressor.start();
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new PneumaticsDrive());
  }

  public void extend() {
    //backwards.set(false);
    //forward.set(true);
    doubleSol.set(DoubleSolenoid.Value.kForward);
    doubleSol2.set(DoubleSolenoid.Value.kForward);
  }
  public void retract() {
    //forward.set(false);
    //backwards.set(true);
    doubleSol.set(DoubleSolenoid.Value.kReverse);
    doubleSol2.set(DoubleSolenoid.Value.kReverse);
  }
  public void stop() {
    doubleSol.set(DoubleSolenoid.Value.kOff);
    doubleSol2.set(DoubleSolenoid.Value.kOff);
  }
  public boolean getOut(){
    return out;
 }
 public void toggleOut(){
    out = !out;   
 }
}
