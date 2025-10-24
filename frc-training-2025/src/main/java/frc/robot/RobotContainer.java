// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.ArcadeDriveTrain;
import frc.robot.commands.ArcadeMotor;
import frc.robot.commands.MoveForDistance;
import frc.robot.commands.MoveForTime;
import frc.robot.constants.DriveConstants;
import frc.robot.constants.IOConstants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Motor;

public class RobotContainer {

  private Joystick m_motorJoystick = new Joystick(IOConstants.kJoystickPort);
  private Motor m_motor = new Motor();
  private ArcadeMotor m_arcadeMotor = new ArcadeMotor(m_motor, m_motorJoystick);

  private Joystick m_driveTrainJoystick = new Joystick(IOConstants.kDriveTrainJoystickPort);
  private DriveTrain m_driveTrain = new DriveTrain();
  private ArcadeDriveTrain m_arcadeDriveTrain = new ArcadeDriveTrain(m_driveTrainJoystick, m_driveTrain);
  private MoveForTime m_moveForTime = new MoveForTime(m_driveTrain, DriveConstants.kSpeed, DriveConstants.kTimeInSeconds);
  private MoveForDistance m_moveForDistance = new MoveForDistance(m_driveTrain, DriveConstants.kSpeed, DriveConstants.kDistanceInFeet);


  

  public RobotContainer() {
    configureBindings();
    
    m_motor.setDefaultCommand(m_arcadeMotor);
    m_driveTrain.setDefaultCommand(m_arcadeDriveTrain);

    SendableRegistry.add(m_moveForDistance.getSendable(), "MoveForDistance");
    Shuffleboard.getTab("SmartDashboard").add(m_moveForDistance.getSendable());
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return m_moveForDistance;
  }
}
