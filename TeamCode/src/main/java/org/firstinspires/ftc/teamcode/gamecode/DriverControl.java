package org.firstinspires.ftc.teamcode.gamecode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Driver Control", group="Linear Opmode")

public class DriverControl extends MyOpMode{

    @Override
    public void runOpMode(){

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        controlSetup();

        waitForStart();

        while (opModeIsActive()){

            getLeftDrive().setPower(leftPower());
            getRightDrive().setPower(rightPower());

            double liftPower = .55;
            if(liftDownValue()) liftPower = -liftPower;
            if(liftUpValue() == liftDownValue()) liftPower = 0;
            getLift().setPower(liftPower);

            double grabberPower = .70;
            if(grabberClose()) grabberPower = -grabberPower;
            if(grabberOpen() == grabberClose()) grabberPower = 0;
            getGrabber().setPower(grabberPower);

            double servoPower = 1;
            if(servoLeft()) servoPower = -servoPower;
            if(servoLeft() == servoRight()) servoPower = 0;
            getServo().setPower(servoPower);
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
        return gamepad1.left_bumper;
    }

    private boolean grabberClose(){
        return gamepad1.right_bumper;
    }

    private boolean servoLeft(){
        return gamepad1.left_trigger > 0;
    }

    private boolean servoRight(){
        return gamepad1.right_trigger > 0;
    }
}