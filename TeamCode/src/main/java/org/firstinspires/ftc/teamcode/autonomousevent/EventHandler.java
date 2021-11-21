package org.firstinspires.ftc.teamcode.autonomousevent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class EventHandler {

    private LinkedList<Event> readList;
    private ArrayList<Event> activeEvents;

    private boolean wait;

    public EventHandler(){
        readList = new LinkedList<>();
        activeEvents = new ArrayList<>();
        wait = false;
    }

    public void add(Event... events){
        readList.addAll(Arrays.asList(events));
    }

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

    public void setWait(boolean wait){
        this.wait = wait;
    }

    public boolean getWait(){
        return wait;
     }
}
