// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public class CANRollerSubsystem extends SubsystemBase {
  private final SparkMax m_rollerMotor;

  public CANRollerSubsystem() {
    // spark max ID number
    m_rollerMotor = new SparkMax(5, MotorType.kBrushed);

    m_rollerMotor.setCANTimeout(250);
    // spark max config
    SparkMaxConfig globalConfig = new SparkMaxConfig();
    globalConfig
        .smartCurrentLimit(15)
        .idleMode(IdleMode.kBrake)
        .voltageCompensation(12);
    // apply config
    m_rollerMotor.configure(globalConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  // roller movement
  public void runRoller(double forward, double reverse) {
    m_rollerMotor.set(forward - reverse);
  }
}