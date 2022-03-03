package org.firstinspires.ftc.teamcode.autonomousevent;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class FourWheelDrive implements DriveTrain {

    private DcMotorSimple[] leftDrive;
    private DcMotorSimple[] rightDrive;

    public FourWheelDrive(DcMotorSimple leftDrive, DcMotorSimple rightDrive) {
        this.leftDrive = new DcMotorSimple[] {leftDrive};
        this.rightDrive = new DcMotorSimple[] {rightDrive};
    }

    public FourWheelDrive(DcMotorSimple leftDrive1, DcMotorSimple leftDrive2, DcMotorSimple rightDrive1, DcMotorSimple rightDrive2) {
        leftDrive = new DcMotorSimple[] {leftDrive1, leftDrive2};
        rightDrive = new DcMotorSimple[] {rightDrive1, rightDrive2};
    }

    public void driveStraight(double power){
        for(DcMotorSimple m:leftDrive) {
            m.setPower(power);
        }
        for(DcMotorSimple m:rightDrive) {
            m.setPower(power);
        }
    }

    public void turnLeft(double power) {
        for(DcMotorSimple m:leftDrive){
            m.setPower(-power);
        }
        for(DcMotorSimple m: leftDrive){
            m.setPower(power);
        }
    }

    public void turnRight(double power){
        for(DcMotorSimple m:leftDrive){
            m.setPower(power);
        }
        for(DcMotorSimple m:rightDrive){
            m.setPower(-power);
        }
    }

    public void stop(){
        driveStraight(0);
    }
}
