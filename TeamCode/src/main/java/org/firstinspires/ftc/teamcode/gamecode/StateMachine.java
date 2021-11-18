package org.firstinspires.ftc.teamcode.gamecode;

public class StateMachine<E, S extends State<E>>{
    private E entity;

    private S currentState;

    public StateMachine(E entity, S initialState) {
        this.entity = entity;
        currentState = initialState;
    }

    public void update(){
        currentState.update(entity);
    }

    public void changeState(S newState){
        currentState.exit(entity);
        currentState = newState;
        newState.enter(entity);
    }
}
