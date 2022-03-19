package Dots;

import java.io.Serializable;

public class AccelVector implements Serializable{
  public double xAcc;
  public double yAcc;
  
  public AccelVector(double xAcc, double yAcc){
    this.xAcc = xAcc;
    this.yAcc = yAcc;
  }
}