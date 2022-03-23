package Tester;
import java.awt.MouseInfo;
import java.awt.Point;

public class Main {
    public static void main(String[] args){
        Point p = MouseInfo.getPointerInfo().getLocation();
    }
}
