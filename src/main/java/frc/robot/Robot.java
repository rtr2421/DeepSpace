/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.Teleop;
import frc.robot.commands.TurnDegrees;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.CameraI2c;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.LaserFinder;
import frc.robot.subsystems.Photoresistor;
import frc.robot.subsystems.pneumatics;
import frc.robot.subsystems.UltraSonic;
import frc.robot.subsystems.Wrist;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static pneumatics m_pneumatics;
  public static OI m_oi;
  public static DriveTrain m_driveTrain;
  //public static final ADIS16448_IMU imu = new ADIS16448_IMU();
  public static Teleop m_teleop;
  Command m_autonomousCommand;
  Compressor com = new Compressor(0);
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  public static UltraSonic m_ultraSonic;
  public static Claw claw;
  public static CameraI2c camera;
  public static Arm arm;
  public static Photoresistor resistor;
  public static Timer m_timer;
  public static LaserFinder m_laser;
  public static Wrist m_wrist;
  public static Climb m_climb;
  //public static Ramps m_ramps;
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   * 
   */
  @Override
  public void robotInit() {
    SmartDashboard.putBoolean("Arm moving",false);
    com.setClosedLoopControl(true);
    com.start();
    m_climb = new Climb();
    claw = new Claw();
    arm = new Arm();
    camera = new CameraI2c();
    m_driveTrain = new DriveTrain();
    m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    m_pneumatics = new pneumatics();
    SmartDashboard.putData("Auto mode", m_chooser);
    CameraServer.getInstance().startAutomaticCapture(0);
    CameraServer.getInstance().startAutomaticCapture(1);
    Scheduler.getInstance().add(new Teleop());
    resistor = new Photoresistor();
    m_timer = new Timer();
    m_laser = new LaserFinder();
    SmartDashboard.putBoolean("TankDrive", false);
    m_wrist = new Wrist();

    m_ultraSonic = new UltraSonic();
    
    //Scheduler.getInstance().add(new GetDistance());
    //OI must be init last
    SmartDashboard.putNumber("Claw Speed", claw.speed);
    SmartDashboard.putNumber("Offset", TurnDegrees.offset); 
    m_oi = new OI();
    m_timer.start();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Wrist Encoder", m_wrist.getAngle());
    SmartDashboard.putNumber("Reed switch", arm.readPos());
    SmartDashboard.putNumber("String pot", arm.readPos());
    //--------------------------Do 10 times per Second --------------------------------------------------
    if(m_timer.hasPeriodPassed(.1)){
      CameraI2c.read();
      m_laser.read();
      m_timer.reset();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //m_serialPort.getString();  //REMOVE LATER LSKJDFLKSDFLJAFHSDGJOEWJ9248&(*#@!^%(*!@&$(*@&$37)))
    //CameraI2c.read();
    /*
    SmartDashboard.putNumber("Gyro-X", m_driveTrain.getGyroX());
    SmartDashboard.putNumber("Gyro-Y", m_driveTrain.getGyroY());
    SmartDashboard.putN
    umber("Gyro-Z", m_driveTrain.getGyroZ());
    */
    /*
    TurnDegrees.offset = SmartDashboard.getNumber("Offset",  TurnDegrees.offset);
    SmartDashboard.putNumber("Offset", TurnDegrees.offset);
    /*
    SmartDashboard.putNumber("Gyro-X", m_driveTrain.getGyroX());
    SmartDashboard.putNumber("Gyro-Y", imu.getAngleY());
    SmartDashboard.putNumber("Gyro-Z", imu.getAngleZ());
    
    SmartDashboard.putNumber("Accel-X", imu.getAccelX());
    SmartDashboard.putNumber("Accel-Y", imu.getAccelY());
    SmartDashboard.putNumber("Accel-Z", imu.getAccelZ());
    
    SmartDashboard.putNumber("Pitch", imu.getPitch());
    SmartDashboard.putNumber("Roll", imu.getRoll());
    SmartDashboard.putNumber("Yaw", imu.getYaw());
    
    SmartDashboard.putNumber("Pressure: ", imu.getBarometricPressure());
    SmartDashboard.putNumber("Temperature: ", imu.getTemperature()); 
    */
  }
  @Override
  public void disabledInit() {
    
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    Scheduler.getInstance().run();
    
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    //CameraI2c.read();
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
