// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.File;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OiConstants;
import frc.robot.commands.CancelCommand;
import frc.robot.commands.operator.OperatorInput;
import frc.robot.commands.swervedrive.*;
import frc.robot.subsystems.swerve.SwerveSubsystem;
import frc.robot.subsystems.swerve.yagsl.YagslSubsystem;
import frc.robot.subsystems.vision.VisionSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final File            yagslConfig          = new File(Filesystem.getDeployDirectory(), "swerve/neo");

    private final VisionSubsystem visionSubsystem      = new VisionSubsystem();

    private final SwerveSubsystem swerveDriveSubsystem = new YagslSubsystem(yagslConfig, visionSubsystem);
    // private final SwerveSubsystem swerveDriveSubsystem = new
    // RunnymedeSwerveSubsystem(visionSubsystem);

    private final OperatorInput   operatorInput        = new OperatorInput(
        OiConstants.DRIVER_CONTROLLER_PORT, OiConstants.OPERATOR_CONTROLLER_PORT);

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {

        // Initialize all Subsystem default commands
        swerveDriveSubsystem.setDefaultCommand(new TeleopDriveCommand(swerveDriveSubsystem, operatorInput));
        // Configure the trigger bindings
        configureBindings();
    }

    /**
     * Use this method to define your trigger->command mappings. Triggers can be
     * created via the
     * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
     * an arbitrary
     * predicate, or via the named factories in {@link
     * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
     * {@link
     * CommandXboxController
     * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
     * PS4} controllers or
     * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
     * joysticks}.
     */
    private void configureBindings() {
        new Trigger(operatorInput::isZeroGyro).onTrue(new ZeroGyroCommand(swerveDriveSubsystem));
        new Trigger(operatorInput::isCancel).whileTrue(new CancelCommand(swerveDriveSubsystem));
        new Trigger(operatorInput::isX)
            .whileTrue(new ResetOdometryCommand(swerveDriveSubsystem, new Pose2d(1.83, 0.40, Rotation2d.fromDegrees(0))));

        // drive forward
        Translation2d        fwd        = new Translation2d(0, .25);
        Rotation2d           fwdHeading = Rotation2d.fromDegrees(0);
        DriveDistanceCommand ddc        = new DriveDistanceCommand(swerveDriveSubsystem, fwd, fwdHeading, 3);
        new Trigger(operatorInput::isA).onTrue(ddc);

        // drive to position test
        Translation2d          location    = new Translation2d(2, 2);
        Rotation2d             heading     = Rotation2d.fromDegrees(-20);
        Pose2d                 desiredPose = new Pose2d(location, heading);
        DriveToPositionCommand dtpc        = new DriveToPositionCommand(swerveDriveSubsystem, desiredPose);
        new Trigger(operatorInput::isY).onTrue(dtpc);
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return null; // todo: implement
    }
}
