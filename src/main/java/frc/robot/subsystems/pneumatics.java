/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Pneumatics extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static Compressor compressor = new Compressor(0);
  public static DoubleSolenoid doubleSol = new DoubleSolenoid(0, 1);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public static void extend() {
    doubleSol.set(DoubleSolenoid.Value.kForward);
  }
  public static void retract() {
    doubleSol.set(DoubleSolenoid.Value.kReverse);
  }
  public static void stop() {
    doubleSol.set(DoubleSolenoid.Value.kOff);
  }
}
