package Testing;

import java.io.Serializable;

public class Brain implements Serializable {
    public AccelVector[] paths = new AccelVector[10];

    public Brain(){
        for(int i = 0; i < paths.length; i++){
            paths[i] = new AccelVector(i*i, i*i);
        }
    }

    public Brain(AccelVector[] paths){
        this.paths = paths;
    }
}
