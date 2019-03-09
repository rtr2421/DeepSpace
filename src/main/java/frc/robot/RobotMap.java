/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  //Analog
  public final static int PHOTORESISTOR = 0;  
  //public final static int ULTRASONIC = 3;
  public static final int STRING_POT = 2;
  //public final static int ARMLIMITSWITCH = 2;

  //DIO
  public final static int ARM_ENCODER_A = 2;  
  public final static int ARM_ENCODER_B = 3;
  public final static int ARM_SWITCHBOTTOM = 0;
  public final static int HALLEFFECT = 1; //UNUSED(?)
  public final static int ULTRASONIC = 4;
  public final static int ULTRASONIC_ECHO = 5;

  //PWM
  public final static int ARM_L = 2;//
  public final static int ARM_R = 0;//
  public final static int TELE_ARM = 5;//
  public final static int CLAW_1 = 3;//
  public final static int CLAW_2 = 1;//
  public static final int RAMPS = 4;//
  
  //CAN
  public final static int LEFTMOTOR_1 = 3;  
  public final static int LEFTMOTOR_2 = 1;
  public final static int RIGHTMOTOR_1 = 2;
  public final static int RIGHTMOTOR_2 = 0; 

   //Pneumatics
  public final static int PNEUMATIC_COMPRESSOR = 0;
  public final static int DOUBLESOL_FORWARD = 0;
  public final static int DOUBLESOL_REVERSE = 1;
  public final static int DOUBLESOL_FORWARD1 = 2;
  public static final int DOUBLESOL_REVERSE1 = 3;
}
