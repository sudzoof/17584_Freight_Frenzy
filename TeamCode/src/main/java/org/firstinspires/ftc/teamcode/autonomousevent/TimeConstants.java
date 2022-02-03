package org.firstinspires.ftc.teamcode.autonomousevent;

public class TimeConstants {
    // 700 was pretty clean but had a very slight increase in degree
    public static final double DEGREE_90 = 700;

    public static double degreeTime(int degree){
        switch(degree){
            case 90:
                return DEGREE_90;
            default:
                return degreeTime((double)degree);
        }
    }

    public static double degreeTime(double degree){
        return DEGREE_90 * degree / 90;
    }


    public static double distanceTime(double distance){
        return 1000.0 * distance / 22 ;
    }

}
