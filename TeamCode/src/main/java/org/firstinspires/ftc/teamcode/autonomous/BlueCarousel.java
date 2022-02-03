package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomousevent.EventBuilder;
import org.firstinspires.ftc.teamcode.autonomousevent.EventHandler;
import org.firstinspires.ftc.teamcode.autonomousevent.TimeConstants;
import org.firstinspires.ftc.teamcode.gamecode.MyOpMode;

@Autonomous(name="Blue Carousel", group="Pushbot")
public class BlueCarousel extends MyOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        autonomousSetup();

        EventHandler h = new EventHandler();
        h.add(new EventBuilder()
                .driveStraight(getLeftDrive(), getRightDrive(), 1)
                .timerMilli(TimeConstants.distanceTime(7))
                .wait(h)
                .build());
        h.add(new EventBuilder()
                .turnRight(getLeftDrive(), getRightDrive(), 1)
                .timerMilli(TimeConstants.degreeTime(90))
                .wait(h)
                .build());
        h.add(new EventBuilder()
                .driveStraight(getLeftDrive(), getRightDrive(), 1)
                .timerMilli(TimeConstants.distanceTime(25.75))
                .wait(h)
                .build());
        h.add(new EventBuilder()
                .powerMotor(getServo(), 1)
                .timerMilli(6400)
                .wait(h)
                .build());
        waitForStart();
        while(opModeIsActive()){
            h.update();
        }
    }

}
