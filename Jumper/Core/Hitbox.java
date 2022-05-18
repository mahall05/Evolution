package Core;

import java.awt.*;
import Maps.*;

public class Hitbox extends Box{
    //public int x, y, width, height;
    private final int BOX_SIZE = 2;

    public Box bottom, top, right, left;
    public Box[] boxes = {top, bottom, left, right};

    public Hitbox(int x, int y, int width, int height){
        super(x, y, width, height);

        bottom = new Box(this.x, this.y+(height-(height/BOX_SIZE)), this.width, this.height/BOX_SIZE);
        top = new Box(this.x, this.y, this.width, this.height/BOX_SIZE);
        right = new Box(this.x+(width-(width/BOX_SIZE)), this.y, this.width/BOX_SIZE, this.height);
        left = new Box(this.x, this.y, this.width/BOX_SIZE, this.height);
    }

    public int checkCollision(){
        int sum = 0;
        
        for(int i = 0; i < Maps.testing.obstacles.length; i++){
            if(bottom.checkWithin(Maps.testing.obstacles[i].hitbox.top)){
                sum += 1;
            }
            if(top.checkWithin(Maps.testing.obstacles[i].hitbox.bottom)){
                sum += 2;
            }
            if(right.checkWithin(Maps.testing.obstacles[i].hitbox.left)){
                sum += 4;
            }
            if(left.checkWithin(Maps.testing.obstacles[i].hitbox.right)){
                sum += 8;
            }
        }

        if(checkWithin(Maps.testing.borders[0].hitbox)){
            sum += 16;
        }
        if(checkWithin(Maps.testing.borders[1].hitbox)){
            sum += 32;
        }
        if(checkWithin(Maps.testing.borders[2].hitbox)){
            sum += 64;
        }

        /*
        for(int i = 0; i < Maps.testing.borders.length; i++){
            if(bottom.checkWithin(Maps.testing.borders[i].hitbox.top)){
                sum += 1;
            }
            if(top.checkWithin(Maps.testing.borders[i].hitbox.bottom)){
                sum += 2;
            }
            if(right.checkWithin(Maps.testing.borders[i].hitbox.left)){
                sum += 4;
            }
            if(left.checkWithin(Maps.testing.borders[i].hitbox.right)){
                sum += 16;
            }
        }
        */

        if(bottom.checkWithin(Maps.testing.ground.hitbox.top)){
            sum += 1;
        }
        if(top.checkWithin(Maps.testing.ground.hitbox.bottom)){
            sum += 2;
        }
        /*
        if(right.checkWithin(Maps.testing.ground.hitbox.left)){
            sum += 4;
        }
        if(left.checkWithin(Maps.testing.ground.hitbox.right)){
            sum += 8;
        }
        */

        return sum;
    }

    public int checkCollision(Hitbox object){
        // 1, 2, 4, 16, 32, 64
        int sum = 0;

        if(bottom.checkWithin(object.top)){
            sum += 1;
        }

        if(top.checkWithin(object.bottom)){
            sum += 2;
        }

        if(right.checkWithin(object.left)){
            sum += 4;
        }

        if(left.checkWithin(object.right)){
            sum += 16;
        }

        return sum;
    }

    @Override
    public void render(Graphics g){
        bottom.render(g);
        top.render(g);
        right.render(g);
        left.render(g);
    }

    @Override
    public void update(int x, int y){
        this.x = x;
        this.y = y;

        bottom.update(this.x, this.y+(height-(height/BOX_SIZE)));
        top.update(this.x, this.y);
        right.update(this.x+(width-(width/BOX_SIZE)), this.y);
        left.update(this.x, this.y);
    }
}
