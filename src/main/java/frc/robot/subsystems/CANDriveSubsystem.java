package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CANDriveSubsystem extends SubsystemBase {
  private final SparkMax m_leftLeader;
  private final SparkMax m_leftFollower;
  private final SparkMax m_rightLeader;
  private final SparkMax m_rightFollower;

  private final DifferentialDrive m_robotDrive;

  public CANDriveSubsystem() {
    // spark max ID number
    m_leftLeader = new SparkMax(1, MotorType.kBrushed);
    m_leftFollower = new SparkMax(2, MotorType.kBrushed);
    m_rightLeader = new SparkMax(3, MotorType.kBrushed);
    m_rightFollower = new SparkMax(4, MotorType.kBrushed);

    // Arcade drive config
    m_robotDrive = new DifferentialDrive(m_leftLeader, m_rightLeader);

    m_leftLeader.setCANTimeout(250);
    m_rightLeader.setCANTimeout(250);
    m_leftFollower.setCANTimeout(250);
    m_rightFollower.setCANTimeout(250);

    // spark max config list
    SparkMaxConfig globalConfig = new SparkMaxConfig();
    SparkMaxConfig leftFollowerConfig = new SparkMaxConfig();
    SparkMaxConfig rightLeaderConfig = new SparkMaxConfig();
    SparkMaxConfig rightFollowerConfig = new SparkMaxConfig();

    globalConfig
        .smartCurrentLimit(15)
        .idleMode(IdleMode.kBrake)
        .voltageCompensation(12);

    leftFollowerConfig.apply(globalConfig).follow(m_leftLeader);
    rightLeaderConfig.apply(globalConfig).inverted(true);
    rightFollowerConfig.apply(globalConfig).follow(m_rightLeader);
    // apply all of the configs
    m_leftLeader.configure(globalConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    m_leftFollower.configure(leftFollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    m_rightLeader.configure(rightLeaderConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    m_rightFollower.configure(rightFollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  // sets the speed of the drive motors
  public void driveArcade(double xSpeed, double zRotation) {
    m_robotDrive.arcadeDrive(xSpeed, zRotation);
  }
}