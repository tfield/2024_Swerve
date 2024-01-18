package frc.robot.commands.operator;

import edu.wpi.first.wpilibj.XboxController;

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
     * Construct an OperatorInput class that is fed by a DriverController and an OperatorController.
     *
     * @param driverControllerPort on the driver station which the driver joystick is plugged into
     * @param operatorControllerPort on the driver station which the aux joystick is plugged into
     */
    public OperatorInput(int driverControllerPort, int operatorControllerPort) {
        driverController   = new XboxController(driverControllerPort); // drive base has built-in deadband and axis flipping
        operatorController = new RunnymedeGameController(operatorControllerPort); // deadband support & axis flipping
    }

    public boolean isZeroGyro() {
        return driverController.getStartButton();
    }

    public int getJumpAngle() {
        return driverController.getPOV();
    }

    public double getDriverControllerAxis(Stick stick, Axis axis) {

        switch (stick) {

        case LEFT:
            switch (axis) {
            case X:
                return driverController.getLeftX();
            case Y:
                return -driverController.getLeftY();
            }
            break;

        case RIGHT:
            switch (axis) {
            case X:
                return -driverController.getRightX();
            case Y:
                return driverController.getRightY();
            }
            break;
        }

        return 0;
    }

}
