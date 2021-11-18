package org.firstinspires.ftc.teamcode.gamecode;

public interface State<T>{
    void enter(T entity);
    void update(T entity);
    void exit(T entity);
}