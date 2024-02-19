package frc.robot.commands.auto.stubs;

import edu.wpi.first.math.geometry.Translation2d;
import frc.robot.Constants;
import frc.robot.commands.swervedrive.DriveToPositionFacingCommand;
import frc.robot.subsystems.swerve.SwerveSubsystem;

public class FakeVisionNotePickupCommand extends DriveToPositionFacingCommand {
    public FakeVisionNotePickupCommand(SwerveSubsystem swerve, Constants.BotTarget prePositionedNote) {
        super(swerve, prePositionedNote.getLocation().toTranslation2d(), prePositionedNote.getLocation().toTranslation2d());
    }
}
