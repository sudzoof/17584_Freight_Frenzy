package org.firstinspires.ftc.teamcode.autonomousevent;

public interface Event {

    /*
    What the event does on start
     */
    void start();
    /*
    What the event does on update
     */
    void update();
    /*
    What the event does on end
     */
    void end();
    /*
    Flag to end event
     */
    boolean willEnd();

}
