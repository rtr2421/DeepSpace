/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.awt.event.KeyListener;
import java.util.ResourceBundle.Control;

import edu.wpi.first.wpilibj.Controller;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.CameraI2c;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  public static XboxController xBoxControl = new XboxController(0);
  public static Joystick xBoxControlArm = new Joystick(1);
  Button pistonExtend = new JoystickButton(xBoxControl, 8);
  Button pistonRetract = new JoystickButton(xBoxControl, 7);
  Button lowRocket_c = new JoystickButton(xBoxControlArm, 1);
  Button midRocket_c = new JoystickButton(xBoxControlArm, 2);
  Button button = new JoystickButton(xBoxControl, 2);
  Button climb = new JoystickButton(xBoxControl, 1);
  Button highRocket_c = new JoystickButton(xBoxControlArm, 3);
  Button lowRocket_h = new JoystickButton(xBoxControlArm, 4);
  Button cargoShip_h = new JoystickButton(xBoxControlArm, 6);
  Button midRocket_h = new JoystickButton(xBoxControlArm, 5);
  public OI() {
    //Button rTrig = new JoystickButton(xBoxControl, buttonNumber)
    
    pistonExtend.whenPressed(new ExtendPiston());
    
    pistonRetract.whenPressed(new RetractPiston());
    climb.whenPressed(new ClimbStair());
    button.whenPressed(new TurnDegrees());

    
    lowRocket_c.whenPressed(new MoveArmTo(1));
    
    midRocket_c.whenActive(new MoveArmTo(2));

    highRocket_c.whenActive(new MoveArmTo(3));


    lowRocket_h.whenPressed(new MoveArmTo(4));

    midRocket_h.whenPressed(new MoveArmTo(5));
    //not possible to do high rocket hatch due to no teleArm


    cargoShip_h.whenPressed(new MoveArmTo(6));

    /*Button aButton = new JoystickButton(xBoxControl, 1);
    aButton.whenPressed(new ExtendPiston());
    aButton.whenReleased(new StopTeleArm());*/
  }
}
