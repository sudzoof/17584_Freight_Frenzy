package org.firstinspires.ftc.teamcode.gamecode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name="Driver Control", group="Linear Opmode")

public class DriverControl extends LinearOpMode{

    @Override
    public void runOpMode(){

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        DcMotor leftDrive;
        DcMotor rightDrive;
        DcMotor lift;

        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        lift = hardwareMap.get(DcMotor.class, "lift");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);

        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        while (opModeIsActive()){

            leftDrive.setPower(leftPower());
            rightDrive.setPower(rightPower());

            double liftPower = .55;
            if(liftDownValue()) liftPower = -liftPower;
            if(liftUpValue() == liftDownValue()) liftPower = 0;
            lift.setPower(liftPower);
        }
    }

    private double leftPower(){
        return -gamepad1.left_stick_y;
    }

    private double rightPower(){
        return -gamepad1.right_stick_y;
    }

    private boolean liftUpValue(){
        return gamepad1.dpad_up;
    }

    private boolean liftDownValue(){
        return gamepad1.dpad_down;
    }
}