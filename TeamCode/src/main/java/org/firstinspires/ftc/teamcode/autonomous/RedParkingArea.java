package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomousevent.EventBuilder;
import org.firstinspires.ftc.teamcode.autonomousevent.EventHandler;
import org.firstinspires.ftc.teamcode.autonomousevent.TimeConstants;
import org.firstinspires.ftc.teamcode.gamecode.MyOpMode;

@Autonomous(name="Red Parking Area", group="Pushbot")
public class RedParkingArea extends MyOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        autonomousSetup();

        EventHandler h = new EventHandler();
        h.add(new EventBuilder()
                .driveStraight(getLeftDrive(), getRightDrive(), 1)
                .timerMilli(TimeConstants.distanceTime(28.75))
                .wait(h)
                .build());
        h.add(new EventBuilder()
                .turnLeft(getLeftDrive(), getRightDrive(), 1)
                .timerMilli(TimeConstants.degreeTime(90))
                .wait(h)
                .build());
        h.add(new EventBuilder()
                .driveStraight(getLeftDrive(), getRightDrive(), 1)
                .timerMilli(TimeConstants.distanceTime(29.75))
                .wait(h)
                .build());
        h.add(new EventBuilder().powerMotor(getGrabber(), -1).timerMilli(400).wait(h).build());
        waitForStart();
        while(opModeIsActive()){
            h.update();
        }
    }

}
