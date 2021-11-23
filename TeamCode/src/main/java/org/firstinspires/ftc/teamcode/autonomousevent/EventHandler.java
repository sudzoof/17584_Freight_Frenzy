package org.firstinspires.ftc.teamcode.autonomousevent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * A handler for Events
 */
public class EventHandler {

    private LinkedList<Event> readList;
    private ArrayList<Event> activeEvents;

    private boolean wait;

    /**
     * Initializer
     */
    public EventHandler(){
        readList = new LinkedList<>();
        activeEvents = new ArrayList<>();
        wait = false;
    }

    /**
     * Adds Events to the EventBuilder
     * @param events The event(s) to add
     */
    public void add(Event... events){
        readList.addAll(Arrays.asList(events));
    }

    /**
     * Updates the EventBuilder
     */
    public void update(){
        while(!wait){
            Event e = readList.pop();
            activeEvents.add(e);
            e.start();
        }
        for(Event e:activeEvents){
            if(!e.willEnd()){
                e.update();
            }
            else{
                e.end();
                activeEvents.remove(e);
            }
        }
    }

    protected void setWait(boolean wait){
        this.wait = wait;
    }

    protected boolean getWait(){
        return wait;
     }
}
