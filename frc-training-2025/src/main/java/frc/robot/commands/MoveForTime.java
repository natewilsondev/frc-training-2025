// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class MoveForTime extends Command {
  private double m_time;
  private double m_speed;
  private DriveTrain m_driveTrain;
  Timer timer = new Timer();
  /** Creates a new MoveForTime. */
  public MoveForTime(double time, double speed, DriveTrain driveTrain) {
    m_time = time;
    m_speed = speed;
    m_driveTrain = driveTrain;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.start();
    m_driveTrain.setLeftSpeed(m_speed);
    m_driveTrain.setRightSpeed(m_speed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.setLeftSpeed(0);
    m_driveTrain.setRightSpeed(0);
    timer.stop();
    timer.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.hasElapsed(m_time);
  }
}
