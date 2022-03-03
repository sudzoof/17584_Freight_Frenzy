package org.firstinspires.ftc.teamcode.autonomousevent;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class MecanumDrive implements DriveTrain{

    private DcMotorSimple frontLeft;
    private DcMotorSimple backLeft;
    private DcMotorSimple frontRight;
    private DcMotorSimple backRight;

    public MecanumDrive(DcMotorSimple frontLeft, DcMotorSimple backLeft, DcMotorSimple frontRight, DcMotorSimple backRight) {
        this.frontLeft = frontLeft;
        this.backLeft = backLeft;
        this.frontRight = frontRight;
        this.backRight = backRight;
    }

    @Override
    public void driveStraight(double power) {
        frontLeft.setPower(power);
        backLeft.setPower(power);
        frontRight.setPower(power);
        backRight.setPower(power);
    }

    public void turnLeft(double power) {
        frontLeft.setPower(-power);
        backLeft.setPower(-power);
        frontRight.setPower(power);
        backRight.setPower(power);
    }

    @Override
    public void turnRight(double power) {
        frontLeft.setPower(power);
        backLeft.setPower(power);
        frontRight.setPower(-power);
        backRight.setPower(-power);
    }

    public void moveLeft(double power) {
        frontLeft.setPower(-power);
        backLeft.setPower(power);
        frontRight.setPower(power);
        backRight.setPower(-power);
    }

    public void moveRight(double power) {
        frontLeft.setPower(power);
        backLeft.setPower(-power);
        frontRight.setPower(-power);
        backRight.setPower(power);
    }

    @Override
    public void stop() {
        driveStraight(0);
    }
}
