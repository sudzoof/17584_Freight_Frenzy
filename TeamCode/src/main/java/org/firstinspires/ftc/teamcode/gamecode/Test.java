package org.firstinspires.ftc.teamcode.gamecode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp

public class Test extends LinearOpMode{

    @Override



    public void runOpMode(){

        telemetry.addData("Status", "Initialized");
        telemetry.update();


        DcMotor leftWheel;
        DcMotor rightWheel;
        DcMotor upDown;

        leftWheel = hardwareMap.get(DcMotor.class, "leftWheel");
        rightWheel = hardwareMap.get(DcMotor.class, "rightWheel");
        upDown = hardwareMap.get(DcMotor.class, "upDown");

        leftWheel.setDirection(DcMotor.Direction.FORWARD);
        rightWheel.setDirection(DcMotor.Direction.FORWARD);

        upDown.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        while (opModeIsActive()){

            telemetry.addData("Status", "Running");
            telemetry.update();

            if(leftPower() != 0){
                leftWheel.setPower(Math.abs(leftPower()/1.5));
            }
            else{
                leftWheel.setPower(0);
            }
            if(leftPower() < 0){
                leftWheel.setDirection(DcMotor.Direction.REVERSE);
            }
            else{
                leftWheel.setDirection(DcMotor.Direction.FORWARD);
            }

            if(rightPower() != 0){
                rightWheel.setPower(Math.abs(rightPower()/1.5));
            }
            else{
                rightWheel.setPower(0);
            }
            if(rightPower() < 0){
                rightWheel.setDirection(DcMotor.Direction.REVERSE);
            }
            else{
                rightWheel.setDirection(DcMotor.Direction.FORWARD);
            }

            if(liftUpValue()) upDown.setDirection(DcMotor.Direction.FORWARD);
            if(liftDownValue()) upDown.setDirection(DcMotor.Direction.REVERSE);
            if(liftUpValue() ^ liftDownValue()) upDown.setPower(.55);
            else upDown.setPower(0);
        }
    }

    private double leftPower(){
        return -gamepad1.left_stick_y;
    }

    private double rightPower(){
        return gamepad1.right_stick_y;
    }

    private boolean liftUpValue(){
        return gamepad1.dpad_up;
    }

    private boolean liftDownValue(){
        return gamepad1.dpad_down;
    }
}