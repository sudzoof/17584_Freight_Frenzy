package org.firstinspires.ftc.teamcode.autonomousevent;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * A custom builder for Events
 */
public class EventBuilder {
    private Event toBuild;

    /**
     * Initializer
     */
    public EventBuilder(){

    }

    /**
     * Initializer for chaining
     * @param eb The EventBuilder to be chained
     */
    private EventBuilder(EventBuilder eb){
        toBuild = eb.toBuild;
    }

    /**
     * Builds the Event
     * @return The Event that is built
     */
    public Event build(){
        return toBuild;
    }

    /**
     * Powers a motor
     * @param motor Motor to be powered
     * @param power Amount to power by
     * @return EventBuilder for chaining
     */
    public EventBuilder powerMotor(DcMotorSimple motor, double power) {
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

    /**
     * Powers a drivetrain to move straight
     * @param leftDrive Left drive motor
     * @param rightDrive Right drive motor
     * @param power Amount to power by
     * @return EventBuilder for chaining
     */
    public EventBuilder driveStraight(DcMotorSimple leftDrive, DcMotorSimple rightDrive, double power){
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

    /**
     * Powers a drivetrain to turn left
     * @param leftDrive Left drive motor
     * @param rightDrive Right drive motor
     * @param power Amount to power by
     * @return EventBuilder for chaining
     */
    public EventBuilder turnLeft(DcMotorSimple leftDrive, DcMotorSimple rightDrive, double power){
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

    /**
     * Powers a drivetrain to turn right
     * @param leftDrive Left drive motor
     * @param rightDrive Right drive motor
     * @param power Amount to power by
     * @return EventBuilder for chaining
     */
    public EventBuilder turnRight(DcMotorSimple leftDrive, DcMotorSimple rightDrive, double power){
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

    /**
     * Stops events until this event ends
     * @param eventHandler The EventHandler to stop
     * @return EventBuilder for chaining
     */
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

    /**
     * Ends the event after a set time in milliseconds
     * @param timeMilli Time in milliseconds
     * @return EventBuilder for chaining
     */
    public EventBuilder timerMilli(int timeMilli){
        toBuild = new Event() {
            ElapsedTime timer = new ElapsedTime();
            @Override
            public void start() {
                timer.reset();
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

    /**
     * Ends the event with another event
     * @param event Event to end with
     * @return EventBuilder for chaining
     */
    public EventBuilder timeWith(Event event){
        toBuild = new Event() {
            @Override
            public void start() { }

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

    /**
     * Never ends the Event
     * @return EventBuilder for chaining
     */
    public EventBuilder forever(){
        toBuild = new Event() {

            @Override
            public void start() { }

            @Override
            public void update() {
                toBuild.update();
            }

            @Override
            public void end() {
                toBuild.end();
            }

            @Override
            public boolean willEnd() {
                return false;
            }
        };
        return this;
    }

}
