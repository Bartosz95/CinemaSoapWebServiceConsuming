package main.java.myclass.loggers;

public class Logger {

    private static String info = "Application : ";

    public static void Log(String message){
        System.out.println(info + message);
    }

    public static void Log(int message){
        System.out.println(info + message);
    }

}
