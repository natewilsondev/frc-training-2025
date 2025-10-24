// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.DriveConstants;
import frc.robot.subsystems.DriveTrain;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class MoveForDistance extends Command {
  private double m_speed;
  private DriveTrain m_driveTrain;
  private double m_distanceInTicks;

  private double m_rightWheelTicks;
  private double m_targetTicks;
  private double m_distanceTraveledInFeet;
  /** Creates a new MoveForDistance. */
  public MoveForDistance(DriveTrain driveTrain, double speed, double distanceInFeet) {
    //Converting feet to ticks
    m_distanceInTicks = (distanceInFeet / DriveConstants.kWheelCircumfrence);
    m_distanceInTicks *= DriveConstants.kTicksPerWheelRotation;

    m_speed = speed;
    m_driveTrain = driveTrain;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_rightWheelTicks = m_driveTrain.getEncoderTicksRight();
    m_targetTicks = m_rightWheelTicks + m_distanceInTicks;
    m_driveTrain.setRightSpeed(m_speed);
    m_driveTrain.setLeftSpeed(m_speed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_rightWheelTicks = m_driveTrain.getEncoderTicksRight();
    //Converting back to feet for SmartDashboard
    m_distanceTraveledInFeet = (m_targetTicks - m_rightWheelTicks) / DriveConstants.kTicksPerWheelRotation * DriveConstants.kWheelCircumfrence;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.setRightSpeed(0);
    m_driveTrain.setLeftSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_targetTicks >= m_driveTrain.getEncoderTicksRight();
  }

  private MoveForDistanceSendable m_moveForDistanceSendable = new MoveForDistanceSendable();
  
  public MoveForDistanceSendable getSendable() {
    return m_moveForDistanceSendable;
  }

  private class MoveForDistanceSendable implements Sendable {

    
    @Override
    public void initSendable(SendableBuilder builder) {
     builder.setSmartDashboardType("MoveForDistance");
     builder.addDoubleProperty("Speed", () -> m_speed, (double speed) -> {m_speed = speed;});
     builder.addDoubleProperty("Dist. Traveled (ft)", () -> m_distanceTraveledInFeet, null);
    }
    
  }

}

