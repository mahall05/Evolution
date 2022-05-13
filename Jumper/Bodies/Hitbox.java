package Bodies;

import Core.Box;

public class Hitbox extends Box{
    int x, y, width, height;

    Box bottom, top, right, left;

    public Hitbox(int x, int y, int width, int height){
        super(x, y, width, height);

        bottom = new Box(this.x, this.y+(height-(height/10)), this.width, this.height/10);
        top = new Box(this.x, this.y, this.width, this.height/10);
        right = new Box(this.x+(width-(width/10)), this.y, this.width/10, this.height);
        left = new Box(this.x, this.y, this.width/10, this.height);
    }

    public int checkCollision(Hitbox object){
        // 1, 2, 4, 16, 32, 64
        
    }
}
