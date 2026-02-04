package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.AutoCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.RollerCommand;
import frc.robot.subsystems.CANDriveSubsystem;
import frc.robot.subsystems.CANRollerSubsystem;

public class RobotContainer {
  // brings in the subsystems needed
  private final CANDriveSubsystem driveSubsystem = new CANDriveSubsystem();
  private final CANRollerSubsystem rollerSubsystem = new CANRollerSubsystem();
  // the driver controller
  private final CommandXboxController driverController = new CommandXboxController(0);
  // auto init
  private final SendableChooser<Command> autoChooser = new SendableChooser<>();

  public RobotContainer() {
    configureBindings();

    autoChooser.setDefaultOption("Autonomous", new AutoCommand(driveSubsystem));
  }

  // auto stuff
  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }

  // all button bindings
  private void configureBindings() {
    // the drive command
    driveSubsystem.setDefaultCommand(new DriveCommand(
        () -> -driverController.getLeftY(),
        () -> -driverController.getRightY(),
        driveSubsystem));
    // the roller command
    rollerSubsystem.setDefaultCommand(new RollerCommand(
        () -> driverController.getRightTriggerAxis(),
        () -> driverController.getLeftTriggerAxis(),
        rollerSubsystem));
  }
}
