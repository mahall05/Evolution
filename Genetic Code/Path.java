public class Path {
    int x = 0;
    int y = 0;
    double xVel = 0;
    double yVel = 0;
    double xAcc = 0;
    double yAcc = 0;
    int velCap = 1000;

    public Path(){

    }

    public Path(double xAcc, double yAcc){
        this.xAcc = xAcc;
        this.yAcc = yAcc;
    }

    public void move(){
        x +=  xVel;
        y += yVel;
    }

    public void addAcceleration(double xAcc, double yAcc){
        this.xAcc += xAcc;
        this.yAcc += yAcc;
    }

    public void accelerate(){
        xVel += xAcc;
        yVel += yAcc;
        if(calcAcc() > velCap){
            while(calcAcc() > velCap){
                xVel -= 0.1;
                yVel -= 0.1;
            }
        }else if(calcAcc() < -velCap){
            while(calcAcc() < -velCap){
                xVel += 0.1;
                yVel -= 0.1;
            }
        }
    }

    private double calcAcc(){
        return Math.sqrt(((xVel - 0) * (xVel - 0)) + ((yVel - 0) * (yVel - 0)));
    }

    public void setVelCap(int cap){
        velCap = cap;
    }
}
