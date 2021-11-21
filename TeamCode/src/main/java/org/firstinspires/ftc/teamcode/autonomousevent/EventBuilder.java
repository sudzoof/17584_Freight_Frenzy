package org.firstinspires.ftc.teamcode.autonomousevent;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class EventBuilder {
    private Event toBuild;
    public EventBuilder(){

    }
    private EventBuilder(EventBuilder eb){
        toBuild = eb.toBuild;
    }

    public Event build(){
        return toBuild;
    }

    public EventBuilder powerMotor(DcMotor motor, double power) {
        toBuild = new Event() {

            @Override
            public void start() {
                toBuild.start();
            }

            @Override
            public void update() {
                motor.setPower(power);
            }

            @Override
            public void end() {
                motor.setPower(0);
            }

            @Override
            public boolean willEnd() {
                return toBuild.willEnd();
            }
        };
        return this;
    }
    public EventBuilder driveStraight(DcMotor leftDrive, DcMotor rightDrive, double power){
        toBuild = new Event() {

            @Override
            public void start() {
                toBuild.start();
            }

            @Override
            public void update() {
                leftDrive.setPower(power);
                rightDrive.setPower(power);
            }

            @Override
            public void end() {
                leftDrive.setPower(0);
                rightDrive.setPower(0);
            }

            @Override
            public boolean willEnd() {
                return toBuild.willEnd();
            }
        };
        return this;
    }
    public EventBuilder turnLeft(DcMotor leftDrive, DcMotor rightDrive, double power){
        toBuild = new Event() {

            @Override
            public void start() {
                toBuild.start();
            }

            @Override
            public void update() {
                leftDrive.setPower(-power);
                rightDrive.setPower(power);
            }

            @Override
            public void end() {
                leftDrive.setPower(0);
                rightDrive.setPower(0);
            }

            @Override
            public boolean willEnd() {
                return toBuild.willEnd();
            }
        };
        return this;
    }
    public EventBuilder turnRight(DcMotor leftDrive, DcMotor rightDrive, double power){
        toBuild = new Event() {

            @Override
            public void start() {
                toBuild.start();
            }

            @Override
            public void update() {
                leftDrive.setPower(power);
                rightDrive.setPower(-power);
            }

            @Override
            public void end() {
                leftDrive.setPower(0);
                rightDrive.setPower(0);
            }

            @Override
            public boolean willEnd() {
                return toBuild.willEnd();
            }
        };
        return this;
    }
    public EventBuilder timerMilli(int timeMilli){
        toBuild = new Event() {
            ElapsedTime timer = new ElapsedTime();
            @Override
            public void start() {
                timer.startTime();
            }

            @Override
            public void update() {
                toBuild.update();
            }

            @Override
            public void end() {
                toBuild.update();
            }

            @Override
            public boolean willEnd() {
                return timer.milliseconds() >= timeMilli;
            }
        };
        return this;
    }
    public EventBuilder timeWith(Event event){
        toBuild = new Event() {
            @Override
            public void start() {

            }

            @Override
            public void update() {
                toBuild.update();
            }

            @Override
            public void end() {
                toBuild.update();
            }

            @Override
            public boolean willEnd() {
                return event.willEnd();
            }
        };
        return this;
    }
    public EventBuilder wait(EventHandler eventHandler){
        toBuild = new Event() {
            @Override
            public void start() {
                toBuild.start();
            }

            @Override
            public void update() {
                eventHandler.setWait(true);
            }

            @Override
            public void end() {
                eventHandler.setWait(false);
            }

            @Override
            public boolean willEnd() {
                return toBuild.willEnd();
            }
        };
        return this;
    }
}
