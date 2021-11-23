package org.firstinspires.ftc.teamcode.gamecode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="Driver Control", group="Linear Opmode")

public class DriverControl extends LinearOpMode{

    @Override
    public void runOpMode(){

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        DcMotor leftDrive;
        DcMotor rightDrive;
        DcMotor lift;
        DcMotor grabber;

        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        lift = hardwareMap.get(DcMotor.class, "lift");
        grabber = hardwareMap.get(DcMotor.class, "grabber");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        lift.setDirection(DcMotor.Direction.FORWARD);
        grabber.setDirection(DcMotorSimple.Direction.FORWARD);

        grabber.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        while (opModeIsActive()){

            leftDrive.setPower(leftPower());
            rightDrive.setPower(rightPower());

            double liftPower = .55;
            if(liftDownValue()) liftPower = -liftPower;
            if(liftUpValue() == liftDownValue()) liftPower = 0;
            lift.setPower(liftPower);

            double grabberPower = .70;
            if(grabberOpen()) grabberPower = -grabberPower;
            if(grabberOpen() == grabberClose()) grabberPower = 0;
            grabber.setPower(grabberPower);
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

    private boolean grabberOpen(){
        return gamepad1.right_bumper;
    }

    private boolean grabberClose(){
        return gamepad1.left_bumper;
    }
}