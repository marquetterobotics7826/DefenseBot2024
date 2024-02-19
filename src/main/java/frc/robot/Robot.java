// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;


/** This is a demo program showing how to use Mecanum control with the MecanumDrive class. */
public class Robot extends TimedRobot {
  private static final int kFrontLeftID = 2;
  private static final int kRearLeftID = 3;
  private static final int kFrontRightID = 1;
  private static final int kRearRightID = 0;

  private static final int kJoystickID = 0;

  private VictorMecanumDrive m_robotDrive;
  private XboxController m_driveController;

  private double deadbandRange = 0.1;


  @Override
  public void robotInit() {
    VictorSPX frontLeft = new VictorSPX(kFrontLeftID);
    VictorSPX rearLeft = new VictorSPX(kRearLeftID);
    VictorSPX frontRight = new VictorSPX(kFrontRightID);
    VictorSPX rearRight = new VictorSPX(kRearRightID);

    // Invert the right side motors.
    // You may need to change or remove this to match your robot.
    frontRight.setInverted(true);
    rearRight.setInverted(true);

    m_robotDrive = new VictorMecanumDrive(frontLeft, rearLeft, frontRight, rearRight);

    m_driveController = new XboxController(kJoystickID);
  }

  public double applyDeadband(double stickValue) {
    if (Math.abs(stickValue) < deadbandRange) {
      return 0;
    }
    return deadbandRange;
  }

  @Override
  public void teleopPeriodic() {
    // Use the joystick Y axis for forward movement, X axis for lateral
    // movement, and Z axis for rotation.
    m_robotDrive.drive(-applyDeadband(m_driveController.getLeftX()), -applyDeadband(m_driveController.getLeftY()), -applyDeadband(m_driveController.getRightX()), -applyDeadband(m_driveController.getRightY()));
  }
}
