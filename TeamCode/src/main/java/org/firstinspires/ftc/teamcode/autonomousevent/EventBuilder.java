package org.firstinspires.ftc.teamcode.autonomousevent;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.Stack;

/**
 * A custom builder for Events
 */
public class EventBuilder {

    final private Stack<Event> storage;


    /**
     * Initializer
     */
    public EventBuilder(){
        storage = new Stack<>();
        storage.push( new Event() {
            @Override
            public void start() { }
            @Override
            public void update() { }
            @Override
            public void end() { }
            @Override
            public boolean willEnd() { return false; }
        });
    }

    /**
     * Builds the Event
     * @return The Event that is built
     */
    public Event build(){
        return storage.peek();
    }

    /**
     * Powers a motor
     * @param motor Motor to be powered
     * @param power Amount to power by
     * @return EventBuilder for chaining
     */
    public EventBuilder powerMotor(DcMotorSimple motor, double power) {
        storage.push( new Event() {
            final Event prevEvent = storage.peek();

            @Override
            public void start() {
                prevEvent.start();
                motor.setPower(power);
            }

            @Override
            public void update() {
                prevEvent.update();
            }

            @Override
            public void end() {
                prevEvent.end();
                motor.setPower(0);
            }

            @Override
            public boolean willEnd() {
                return prevEvent.willEnd();
            }
        });
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
        storage.push( new Event() {
            final Event prevEvent = storage.peek();

            @Override
            public void start() {
                prevEvent.start();
                leftDrive.setPower(power);
                rightDrive.setPower(power);
            }

            @Override
            public void update() {
                prevEvent.update();
            }

            @Override
            public void end() {
                prevEvent.end();
                leftDrive.setPower(0);
                rightDrive.setPower(0);
            }

            @Override
            public boolean willEnd() {
                return prevEvent.willEnd();
            }
        });
        return this;
    }

    public EventBuilder driveStraight(DriveTrain drive, double power){
        storage.push( new Event() {
            final Event prevEvent = storage.peek();

            @Override
            public void start() {
                prevEvent.start();
                drive.driveStraight(power);
            }

            @Override
            public void update() {
                prevEvent.update();
            }

            @Override
            public void end() {
                prevEvent.end();
                drive.stop();
            }

            @Override
            public boolean willEnd() {
                return prevEvent.willEnd();
            }
        });
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
        storage.push( new Event() {
            final Event prevEvent = storage.peek();

            @Override
            public void start() {
                prevEvent.update();
                leftDrive.setPower(-power);
                rightDrive.setPower(power);
            }

            @Override
            public void update() {
                prevEvent.update();
            }

            @Override
            public void end() {
                prevEvent.end();
                leftDrive.setPower(0);
                rightDrive.setPower(0);
            }

            @Override
            public boolean willEnd() {
                return prevEvent.willEnd();
            }
        });
        return this;
    }

    public EventBuilder turnLeft(DriveTrain drive, double power){
        storage.push( new Event() {
            final Event prevEvent = storage.peek();

            @Override
            public void start() {
                prevEvent.start();
                drive.turnLeft(power);
            }

            @Override
            public void update() {
                prevEvent.update();
            }

            @Override
            public void end() {
                prevEvent.end();
                drive.stop();
            }

            @Override
            public boolean willEnd() {
                return prevEvent.willEnd();
            }
        });
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
        storage.push( new Event() {
            final Event prevEvent = storage.peek();

            @Override
            public void start() {
                prevEvent.update();
                leftDrive.setPower(power);
                rightDrive.setPower(-power);
            }

            @Override
            public void update() {
                prevEvent.update();
            }

            @Override
            public void end() {
                prevEvent.end();
                leftDrive.setPower(0);
                rightDrive.setPower(0);
            }

            @Override
            public boolean willEnd() {
                return prevEvent.willEnd();
            }
        });
        return this;
    }

    public EventBuilder turnRight(DriveTrain drive, double power){
        storage.push( new Event() {
            final Event prevEvent = storage.peek();

            @Override
            public void start() {
                prevEvent.start();
                drive.turnRight(power);
            }

            @Override
            public void update() {
                prevEvent.update();
            }

            @Override
            public void end() {
                prevEvent.end();
                drive.stop();
            }

            @Override
            public boolean willEnd() {
                return prevEvent.willEnd();
            }
        });
        return this;
    }

    /**
     * Stops events until this event ends
     * @param eventHandler The EventHandler to stop
     * @return EventBuilder for chaining
     */
    public EventBuilder wait(EventHandler eventHandler){
        storage.push( new Event() {
            final Event prevEvent = storage.peek();

            @Override
            public void start() {
                prevEvent.start();
                eventHandler.setWait(true);
            }

            @Override
            public void update() {
                prevEvent.update();
            }

            @Override
            public void end() {
                prevEvent.end();
                eventHandler.setWait(false);
            }

            @Override
            public boolean willEnd() {
                return prevEvent.willEnd();
            }
        });
        return this;
    }

    /**
     * Ends the event after a set time in milliseconds
     * @param timeMilli Time in milliseconds
     * @return EventBuilder for chaining
     */
    public EventBuilder timerMilli(double timeMilli){
        storage.push( new Event() {
            final Event prevEvent = storage.peek();

            final ElapsedTime timer = new ElapsedTime();
            double currentTime = 0;

            @Override
            public void start() {
                prevEvent.start();
                timer.reset();
            }

            @Override
            public void update() {
                prevEvent.update();
                currentTime = timer.milliseconds();
            }

            @Override
            public void end() {
                prevEvent.end();
            }

            @Override
            public boolean willEnd() {
                return currentTime >= timeMilli;
            }
        });
        return this;
    }

    /**
     * Ends the event with another event
     * @param event Event to end with
     * @return EventBuilder for chaining
     */
    public EventBuilder timeWith(Event event){
        storage.push( new Event() {
            final Event prevEvent = storage.peek();

            @Override
            public void start() {
                prevEvent.start();
            }

            @Override
            public void update() {
                prevEvent.update();
            }

            @Override
            public void end() {
                prevEvent.end();
            }

            @Override
            public boolean willEnd() {
                return event.willEnd();
            }
        });
        return this;
    }

    public EventBuilder timeWithMotorEncoder(DcMotor motor, double degree) {
        storage.push( new Event() {
            final Event prevEvent = storage.peek();

            double startPosition = 0;
            double currentPosition = 0;

            @Override
            public void start() {
                prevEvent.start();
                startPosition = motor.getCurrentPosition();
            }

            @Override
            public void update() {
                prevEvent.update();
                currentPosition = motor.getCurrentPosition() - startPosition;
            }

            @Override
            public void end() {
                prevEvent.end();
            }

            @Override
            public boolean willEnd() {
                return (degree >= 0 && currentPosition >= degree)||(degree < 0 && currentPosition <= degree);
            }
        });
        return this;
    }


    /**
     * Never ends the Event
     * @return EventBuilder for chaining
     */
    public EventBuilder forever(){
        storage.push( new Event() {
            final Event prevEvent = storage.peek();

            @Override
            public void start() {
                prevEvent.start();
            }

            @Override
            public void update() {
                prevEvent.update();
            }

            @Override
            public void end() {
                prevEvent.end();
            }

            @Override
            public boolean willEnd() {
                return false;
            }
        });
        return this;
    }

}
