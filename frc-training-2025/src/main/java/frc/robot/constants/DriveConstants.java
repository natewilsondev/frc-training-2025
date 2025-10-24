// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

/** Add your docs here. */
public class DriveConstants {
    //Primary
    public final static int kTalonLeftChannel = 6;
    public final static int kTalonRightChannel = 3;
    //Secondary
    public final static int kVictorLeftChannel = 8;
    public final static int kVictorRightChannel = 7;

    //Speed:for MoveForTime and MoveForDistance
    //Time: for MoveForTime
    //Distance: for MoveForDistance
    public static double kSpeed = 0.5;
    public static double kTimeInSeconds = 15;
    public static double kDistanceInFeet;

    public static double kWheelDiamterInFeet = 6;
    public static double kWheelCircumfrence = Math.PI * kWheelDiamterInFeet;
    public static double kTicksPerWheelRotation;

    //Mult: for ArcadeDriveTrain
    public static double kspeedMultiplier = 0.5;
    


    
    
}
