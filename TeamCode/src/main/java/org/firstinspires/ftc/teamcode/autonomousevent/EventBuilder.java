package org.firstinspires.ftc.teamcode.autonomousevent;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * A custom builder for Events
 */
public class EventBuilder {

    private Event toBuild;
    private Event toChain;

    /**
     * Initializer
     */
    public EventBuilder(){
        toBuild = new Event() {
            @Override
            public void start() { }
            @Override
            public void update() { }
            @Override
            public void end() { }
            @Override
            public boolean willEnd() { return false; }
        };
    }

    /**
     * Initializer for chaining
     * @param eb The EventBuilder to be chained
     */
    private EventBuilder(EventBuilder eb){
        toBuild = eb.toChain;
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
        toChain = new Event() {

            @Override
            public void start() {
                toBuild.start();
                motor.setPower(power);
            }

            @Override
            public void update() {
                toBuild.update();
            }

            @Override
            public void end() {
                toBuild.end();
                motor.setPower(0);
            }

            @Override
            public boolean willEnd() {
                return toBuild.willEnd();
            }
        };
        return new EventBuilder(this);
    }

    /**
     * Powers a drivetrain to move straight
     * @param leftDrive Left drive motor
     * @param rightDrive Right drive motor
     * @param power Amount to power by
     * @return EventBuilder for chaining
     */
    public EventBuilder driveStraight(DcMotorSimple leftDrive, DcMotorSimple rightDrive, double power){
        toChain = new Event() {

            @Override
            public void start() {
                toBuild.start();
                leftDrive.setPower(power);
                rightDrive.setPower(power);
            }

            @Override
            public void update() {
                toBuild.update();
            }

            @Override
            public void end() {
                toBuild.end();
                leftDrive.setPower(0);
                rightDrive.setPower(0);
            }

            @Override
            public boolean willEnd() {
                return toBuild.willEnd();
            }
        };
        return new EventBuilder(this);
    }

    /**
     * Powers a drivetrain to turn left
     * @param leftDrive Left drive motor
     * @param rightDrive Right drive motor
     * @param power Amount to power by
     * @return EventBuilder for chaining
     */
    public EventBuilder turnLeft(DcMotorSimple leftDrive, DcMotorSimple rightDrive, double power){
        toChain = new Event() {

            @Override
            public void start() {
                toBuild.update();
                leftDrive.setPower(-power);
                rightDrive.setPower(power);
            }

            @Override
            public void update() {
                toBuild.update();
            }

            @Override
            public void end() {
                toBuild.end();
                leftDrive.setPower(0);
                rightDrive.setPower(0);
            }

            @Override
            public boolean willEnd() {
                return toBuild.willEnd();
            }
        };
        return new EventBuilder(this);
    }

    /**
     * Powers a drivetrain to turn right
     * @param leftDrive Left drive motor
     * @param rightDrive Right drive motor
     * @param power Amount to power by
     * @return EventBuilder for chaining
     */
    public EventBuilder turnRight(DcMotorSimple leftDrive, DcMotorSimple rightDrive, double power){
        toChain = new Event() {

            @Override
            public void start() {
                toBuild.update();
                leftDrive.setPower(power);
                rightDrive.setPower(-power);
            }

            @Override
            public void update() {
                toBuild.update();
            }

            @Override
            public void end() {
                toBuild.end();
                leftDrive.setPower(0);
                rightDrive.setPower(0);
            }

            @Override
            public boolean willEnd() {
                return toBuild.willEnd();
            }
        };
        return new EventBuilder(this);
    }

    /**
     * Stops events until this event ends
     * @param eventHandler The EventHandler to stop
     * @return EventBuilder for chaining
     */
    public EventBuilder wait(EventHandler eventHandler){
        toChain = new Event() {
            @Override
            public void start() {
                toBuild.start();
                eventHandler.setWait(true);
            }

            @Override
            public void update() {
                toBuild.update();
            }

            @Override
            public void end() {
                toBuild.end();
                eventHandler.setWait(false);
            }

            @Override
            public boolean willEnd() {
                return toBuild.willEnd();
            }
        };
        return new EventBuilder(this);
    }

    /**
     * Ends the event after a set time in milliseconds
     * @param timeMilli Time in milliseconds
     * @return EventBuilder for chaining
     */
    public EventBuilder timerMilli(int timeMilli){
        toChain = new Event() {
            ElapsedTime timer = new ElapsedTime();
            @Override
            public void start() {
                toBuild.start();
                timer.reset();
            }

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
                return timer.milliseconds() >= timeMilli;
            }
        };
        return new EventBuilder(this);
    }

    /**
     * Ends the event with another event
     * @param event Event to end with
     * @return EventBuilder for chaining
     */
    public EventBuilder timeWith(Event event){
        toChain = new Event() {
            @Override
            public void start() {
                toBuild.start();
            }

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
                return event.willEnd();
            }
        };
        return new EventBuilder(this);
    }

    /**
     * Never ends the Event
     * @return EventBuilder for chaining
     */
    public EventBuilder forever(){
        toChain = new Event() {

            @Override
            public void start() {
                toBuild.start();
            }

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
        return new EventBuilder(this);
    }

}
