package org.firstinspires.ftc.teamcode.gamecode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.autonomousevent.Event;
import org.firstinspires.ftc.teamcode.autonomousevent.EventBuilder;
import org.firstinspires.ftc.teamcode.autonomousevent.EventHandler;
import org.firstinspires.ftc.teamcode.autonomousevent.TimeConstants;

@Autonomous(name="TimeTest", group="Pushbot")
public class TimeTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
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

        EventHandler h = new EventHandler();
        Event e = new EventBuilder()
                .turnRight(leftDrive, rightDrive, 1)
                .timerMilli(TimeConstants.DEGREE_90)
//                .forever()
                .build();
        h.add(e);
        h.add(new EventBuilder()
                .wait(h)
                .timeWith(e)
                .build());
        waitForStart();
        while(opModeIsActive()){
            h.update();
        }
    }

}
