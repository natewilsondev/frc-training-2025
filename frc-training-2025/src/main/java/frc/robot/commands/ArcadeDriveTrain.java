// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.IOConstants;
import frc.robot.subsystems.DriveTrain;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class ArcadeDriveTrain extends Command {

private Joystick m_joystick;
private DriveTrain m_driveTrain;


  /** Creates a new ArcadeDrive. */
  public ArcadeDriveTrain(Joystick joystick, DriveTrain driveTrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_driveTrain);
    this.m_joystick = joystick;
    this.m_driveTrain = driveTrain;


  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Set the variables in terms of the inputs received from joystick every 20ms
    double speed = m_joystick.getRawAxis(1);
    double turn = m_joystick.getRawAxis(4);
    double left = speed + turn;
    double right = speed - turn;
    //Set the speed based on the turn --- the left and right wheels turn at different speeds so that the robot can turn properly
    //Specifically, the rotational value
    m_driveTrain.setLeftSpeed(left);
    m_driveTrain.setRightSpeed(right);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
