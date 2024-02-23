package frc.robot.commands.auto;


import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.BotTarget;
import frc.robot.commands.auto.stubs.FakeScoreAmpCommand;
import frc.robot.commands.auto.stubs.FakeScoreSpeakerCommand;
import frc.robot.commands.auto.stubs.FakeVisionNotePickupCommand;
import frc.robot.commands.swervedrive.*;
import frc.robot.subsystems.swerve.SwerveSubsystem;
import frc.robot.subsystems.vision.HughVisionSubsystem;

import static frc.robot.Constants.UsefulPoses.SCORE_BLUE_AMP;
import static frc.robot.Constants.UsefulPoses.SCORE_RED_AMP;
import static frc.robot.RunnymedeUtils.getRunnymedeAlliance;

public class Score2AmpAutoCommand extends SequentialCommandGroup {

    public Score2AmpAutoCommand(SwerveSubsystem swerve, HughVisionSubsystem hugh) {

        Pose2d blueStartPose  = new Pose2d(1.5, 7.8, Rotation2d.fromDegrees(90));
        Pose2d redStartPose   = new Pose2d(15.91, 7.8, Rotation2d.fromDegrees(90));

        Pose2d blueFinishPose = new Pose2d(4, 7.0, new Rotation2d(90));
        Pose2d redFinishPose  = new Pose2d(12.54, 7.0, new Rotation2d());

        // TODO: implement Auto Selector
        // Configure
        addCommands(new LogMessageCommand("Starting Auto"));
        addCommands(new ResetOdometryCommand(swerve, blueStartPose, redStartPose));

        /* Note 1 */
        addCommands(new DriveToPositionCommand(swerve, SCORE_BLUE_AMP, SCORE_RED_AMP));
        addCommands(new FakeScoreAmpCommand());

        /* Note 2 */
        addCommands(new RotateToPlacedNoteCommand(swerve, BotTarget.BLUE_NOTE_VALJEAN, BotTarget.RED_NOTE_VALJEAN));
        addCommands(new FakeVisionNotePickupCommand(swerve, BotTarget.BLUE_NOTE_VALJEAN, BotTarget.RED_NOTE_VALJEAN));
        addCommands(new DriveToPositionCommand(swerve, SCORE_BLUE_AMP, SCORE_RED_AMP));
        addCommands(new FakeScoreAmpCommand());

        /* Exit Zone */
        addCommands(new DriveToPositionCommand(swerve, blueFinishPose, redFinishPose));
        addCommands(new LogMessageCommand("Auto Complete"));
    }
}