// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;
import swervelib.math.Matter;

import static edu.wpi.first.math.util.Units.inchesToMeters;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class OiConstants {

        public static final int DRIVER_CONTROLLER_PORT   = 0;
        public static final int OPERATOR_CONTROLLER_PORT = 1;
    }

    public static final class SwerveDriveConstants {
        /**
         * 32lbs * kg per pound
         */
        public static final double ROBOT_MASS                              = (148 - 20.3) * 0.453592;
        public static final Matter CHASSIS                                 = new Matter(
            new Translation3d(0, 0, Units.inchesToMeters(8)), ROBOT_MASS);
        /**
         * s, 20ms + 110ms sprk max velocity lag
         */
        public static final double LOOP_TIME                               = 0.13;
        /**
         * Maximum speed of the robot in meters per second, used for both angular
         * acceleration used in
         * swervelib.SwerveController and drive feedforward in
         * SwerveMath#createDriveFeedforward(double, double, double).
         */
        public static final double MAX_SPEED_MPS                           = 1;
        public static final double MAX_ROTATION_RADIANS_PER_SEC            = 6;

        public static final double ROTATION_TOLERANCE_DEGREES              = 2.0;
        public static final double ROTATION_ANGULAR_VELOCITY_TOLERANCE_PCT = 0.08;

        // Speed Factors - NOT TESTED
        public static final double GENERAL_SPEED_FACTOR                    = .5;
        public static final double MAX_SPEED_FACTOR                        = 1;
        public static final double SLOW_SPEED_FACTOR                       = .1;
    }

    public static final class Swerve {

        public static final class Chassis {

            public static final double SDS_MK4I_WHEEL_RADIUS_METRES        = 0.0051;
            /**
             * Specify the maximum speed a module can reach in m/s. This should be measured
             * so that we don't ask the module to do something it's not capable of doing. This
             * is NOT a value we should use to cap how fast the robot drives on the field. For
             * that, use {@link #MAX_TRANSLATION_SPEED_MPS}.
             */
            public static final double MAX_MODULE_SPEED_MPS                = 5;
            /**
             * Set how fast you want the robot to actually translate across the field.
             * This is the "speed limit" of the robot.
             *
             * Practically speaking 4.42 m/s is a good max, but
             * consider 1-2 for development and 2-3 for competitions.
             */
            public static final double MAX_TRANSLATION_SPEED_MPS           = 4.42;
            public static final double MAX_ROTATIONAL_VELOCITY_RAD_PER_SEC = Rotation2d.fromRotations(0.75).getRadians();
            public static final double ROTATION_TOLERANCE_RADIANS          = Rotation2d.fromDegrees(2.0).getRadians();

            public static final class HeadingPIDConfig {
                public static final double P = 0.036;
                public static final double I = 0;
                public static final double D = 0;
            }
        }

        public static final class Motor {
            public boolean            inverted;
            public int                currentLimitAmps;
            public double             nominalVoltage;
            public double             rampRate;
            public double             gearRatio;
            public double             p;
            public double             i;
            public double             d;
            public double             ff;
            public double             iz;
            public static final Motor DRIVE = new Motor();

            static {
                DRIVE.inverted         = true;
                DRIVE.currentLimitAmps = 40;
                DRIVE.nominalVoltage   = 12;
                DRIVE.rampRate         = 0.15;
                DRIVE.gearRatio        = 6.75;     // SDS MK4i L2 --> 6.75:1
                DRIVE.p                = 0.0020645;
                DRIVE.i                = 0;
                DRIVE.d                = 0;
                DRIVE.ff               = 0;
                DRIVE.iz               = 0;
            }

            public static final Motor ANGLE = new Motor();

            static {
                ANGLE.inverted         = true;
                ANGLE.currentLimitAmps = 20;            // must not exceed 30 (fuse)
                ANGLE.nominalVoltage   = 12;
                ANGLE.rampRate         = 0.05;
                ANGLE.gearRatio        = 21.4285714286; // SDS MK4i 150/7:1
                ANGLE.p                = 0.01;
                ANGLE.i                = 0;
                ANGLE.d                = 0;
                ANGLE.ff               = 0;
                ANGLE.iz               = 0;
            }
        }

        public static final class Module {
            public double              wheelRadiusMetres;
            public Translation2d       locationMetres;
            public int                 driveCANID;
            public int                 angleCANID;
            public int                 encoderCANID;
            public double              encoderAbsoluteOffsetDegrees;
            public static final Module BACK_LEFT = new Module();

            static {
                BACK_LEFT.wheelRadiusMetres            = Chassis.SDS_MK4I_WHEEL_RADIUS_METRES;
                BACK_LEFT.locationMetres               = new Translation2d(inchesToMeters(-11.375), inchesToMeters(10.875));
                BACK_LEFT.driveCANID                   = 35;
                BACK_LEFT.angleCANID                   = 36;
                BACK_LEFT.encoderCANID                 = 37;
                BACK_LEFT.encoderAbsoluteOffsetDegrees = Rotation2d.fromRotations(0.504883).getDegrees();
            }

            public static final Module BACK_RIGHT = new Module();

            static {
                BACK_RIGHT.wheelRadiusMetres            = Chassis.SDS_MK4I_WHEEL_RADIUS_METRES;
                BACK_RIGHT.locationMetres               = new Translation2d(inchesToMeters(-11.375), inchesToMeters(-10.875));
                BACK_RIGHT.driveCANID                   = 30;
                BACK_RIGHT.angleCANID                   = 31;
                BACK_RIGHT.encoderCANID                 = 32;
                BACK_RIGHT.encoderAbsoluteOffsetDegrees = Rotation2d.fromRotations(0.359131).getDegrees();
            }

            public static final Module FRONT_LEFT = new Module();

            static {
                FRONT_LEFT.wheelRadiusMetres            = Chassis.SDS_MK4I_WHEEL_RADIUS_METRES;
                FRONT_LEFT.locationMetres               = new Translation2d(inchesToMeters(11.375), inchesToMeters(10.875));
                FRONT_LEFT.driveCANID                   = 10;
                FRONT_LEFT.angleCANID                   = 11;
                FRONT_LEFT.encoderCANID                 = 12;
                FRONT_LEFT.encoderAbsoluteOffsetDegrees = Rotation2d.fromRotations(0.154297).getDegrees();
            }

            public static final Module FRONT_RIGHT = new Module();

            static {
                FRONT_RIGHT.wheelRadiusMetres            = Chassis.SDS_MK4I_WHEEL_RADIUS_METRES;
                FRONT_RIGHT.locationMetres               = new Translation2d(inchesToMeters(11.375), inchesToMeters(-10.875));
                FRONT_RIGHT.driveCANID                   = 20;
                FRONT_RIGHT.angleCANID                   = 21;
                FRONT_RIGHT.encoderCANID                 = 22;
                FRONT_RIGHT.encoderAbsoluteOffsetDegrees = Rotation2d.fromRotations(0.414795).getDegrees();
            }
        }
    }
}
