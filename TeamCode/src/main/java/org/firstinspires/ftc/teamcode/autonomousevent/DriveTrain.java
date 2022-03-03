package org.firstinspires.ftc.teamcode.autonomousevent;

public interface DriveTrain {
    void driveStraight(double power);
    void turnLeft(double power);
    void turnRight(double power);
    void stop();
}