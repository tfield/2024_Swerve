package frc.robot.commands.operator;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

/**
 * The DriverController exposes all driver functions
 */
public class OperatorInput {

    private final XboxController driverController;
    private final XboxController operatorController;

    public enum Stick {
        LEFT, RIGHT
    }

    public enum Axis {
        X, Y
    }

    /**
     * Construct an OperatorInput class that is fed by a DriverController and an
     * OperatorController.
     *
     * @param driverControllerPort   on the driver station which the driver joystick
     *                               is plugged into
     * @param operatorControllerPort on the driver station which the aux joystick is
     *                               plugged into
     */
    public OperatorInput(int driverControllerPort, int operatorControllerPort) {
        driverController = new RunnymedeGameController(driverControllerPort);
        operatorController = new RunnymedeGameController(operatorControllerPort);
    }

    public double getBoostMultiplier() {

        if (driverController.getLeftBumper()) {
            return Constants.Swerve.Chassis.SLOW_SPEED_FACTOR;
        } else if (driverController.getRightBumper()) {
            return Constants.Swerve.Chassis.MAX_SPEED_FACTOR;
        } else {
            return Constants.Swerve.Chassis.GENERAL_SPEED_FACTOR;
        }
    }

    // Testing purposes only
    public boolean isA() {
        return driverController.getAButton();
    }

    public boolean isZeroGyro() {
        return driverController.getBackButton();
    }

    public boolean isCancel() {
        return driverController.getStartButton();
    }

    public int getJumpAngle() {
        int rawPOV = driverController.getPOV();
        final int retVal;
        if (rawPOV == -1) {
            retVal = -1;
        } else {
            retVal = (rawPOV + 90) % 360;
        }
        return retVal;

    }

    public double getDriverControllerAxis(Stick stick, Axis axis) {

        switch (stick) {

            case LEFT:
                switch (axis) {
                    case X:
                        return driverController.getLeftX();
                    case Y:
                        return driverController.getLeftY();
                }
                break;

            case RIGHT:
                switch (axis) {
                    case X:
                        return driverController.getRightX();
                }
                break;
        }

        return 0;
    }

}
