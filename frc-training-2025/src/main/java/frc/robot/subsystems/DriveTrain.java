// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.DriveConstants;
import frc.robot.constants.IOConstants;

public class DriveTrain extends SubsystemBase {
  /** Creates a new Drive. */

  private WPI_TalonSRX m_talonLeft = new WPI_TalonSRX(DriveConstants.kTalonLeftChannel);
  private WPI_TalonSRX m_talonRight = new WPI_TalonSRX(DriveConstants.kTalonRightChannel);
  private WPI_VictorSPX m_victorLeft = new WPI_VictorSPX(DriveConstants.kVictorLeftChannel);
  private WPI_VictorSPX m_victorRight = new WPI_VictorSPX(DriveConstants.kVictorRightChannel);
  
  

  public DriveTrain() {
    //Brake mode means the motors do not move unless acted upon by a force and are still when no force is being applied
    m_talonLeft.setNeutralMode(NeutralMode.Brake); 
    m_talonRight.setNeutralMode(NeutralMode.Brake);

    m_talonLeft.follow(m_victorRight);
    m_talonRight.follow(m_victorLeft);

    m_talonRight.setInverted(true);
    m_talonRight.setInverted(true);
  
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Left Speed", getLeft());
    SmartDashboard.putNumber("Right Speed", getRight());
  }


  public void setLeftSpeed(double speed) {
    //Percent Output Mode takes in speed as a percentage from -1 to 1 according to the power level -- extremely simple
    m_talonLeft.set(ControlMode.PercentOutput ,speed);
    m_victorLeft.set(ControlMode.PercentOutput, speed);

  }

  public void setRightSpeed(double speed) {
    //Percent Output Mode takes in speed as a percentage from -1 to 1 according to the power level -- extrmemely simple
    m_talonRight.set(ControlMode.PercentOutput ,speed);
    m_victorRight.set(ControlMode.PercentOutput, speed);
  }

  public double getLeft() {
    return m_talonLeft.get();
  }
  public double getRight() {
    return m_talonRight.get();
  }
}