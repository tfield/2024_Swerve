package frc.robot.commands.auto;


import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.auto.stubs.FakeScoreAmpCommand;
import frc.robot.commands.swervedrive.DriveToPositionCommand;
import frc.robot.commands.swervedrive.ResetOdometryCommand;
import frc.robot.subsystems.swerve.SwerveSubsystem;
import frc.robot.subsystems.vision.HughVisionSubsystem;

import static frc.robot.Constants.UsefulPoses.SCORE_BLUE_AMP;
import static frc.robot.Constants.UsefulPoses.SCORE_RED_AMP;


public class Score1AmpAutoCommand extends SequentialCommandGroup {

    public Score1AmpAutoCommand(SwerveSubsystem swerve, HughVisionSubsystem hugh) {

        Pose2d blueStartingPose = new Pose2d(1.5, 7.8, Rotation2d.fromDegrees(90));
        Pose2d redStartingPose  = new Pose2d(15.91, 7.8, Rotation2d.fromDegrees(90));

        Pose2d blueFinishPose   = new Pose2d(4, 7.7, new Rotation2d(90));
        Pose2d redFinishPose    = new Pose2d(12.54, 7.4, new Rotation2d());


        // TODO: implement Auto Selector
        // Configure
        addCommands(new LogMessageCommand("Starting Auto"));
        addCommands(new ResetOdometryCommand(swerve, blueStartingPose, redStartingPose));


        addCommands(new DriveToPositionCommand(swerve, SCORE_BLUE_AMP, SCORE_RED_AMP));
        addCommands(new FakeScoreAmpCommand());
        addCommands(new DriveToPositionCommand(swerve, blueFinishPose, redFinishPose));

        addCommands(new LogMessageCommand("Auto Complete"));

    }
}
