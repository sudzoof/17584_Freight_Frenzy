package org.firstinspires.ftc.teamcode.autonomousevent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
    
    public EventHandler(Event... events) {
    	this();
    	this.add(events);
    }

    /**
     * Adds Events to the EventHandler
     * @param events The event(s) to add
     */
    public void add(Event... events){
        readList.addAll(Arrays.asList(events));
    }

    /**
     * Updates the EventHandler
     */
    public void update(){
        while(!wait && !readList.isEmpty()){
            Event e = readList.pop();
            activeEvents.add(e);
            e.start();
        }

        Iterator<Event> iterator = activeEvents.iterator();
        while(iterator.hasNext()) {
            Event e = iterator.next();
            e.update();
            if(e.willEnd()){
                e.end();
                iterator.remove();
            }
        }
    }

    /**
     *
     * @param wait Whether to stop reading the Event stack
     */
    protected void setWait(boolean wait){
        this.wait = wait;
    }

    /**
     * Currently is not used
     * @return Whether to stop reading the Event stack
     */
    protected boolean getWait(){
        return wait;
     }

    /**
     *
     * @return Whether all the events have been read and run
     */
    public boolean isDone() {
        return readList.isEmpty() && activeEvents.isEmpty();
    }
}
