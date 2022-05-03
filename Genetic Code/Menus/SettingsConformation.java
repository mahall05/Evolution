package Menus;

import Core.Constants;
import java.awt.*;

public class SettingsConformation {
    public Button keep;
    public Button use;

    public SettingsConformation(){
        /* Keep Button */
        keep = new Button(40, Constants.HEIGHT-100, 75, 25);
        keep.setButtonColor(Color.white);
        keep.setLabel("Keep", Color.red, 20, true);
        keep.offsetLabel(11, -45);

        /* Use Button */
        use = new Button(40, Constants.HEIGHT-100, 75, 25);
        use.setButtonColor(Color.white);
        use.setLabel("Load", Color.red, 20, true);
        use.offsetLabel(11, -45);
    }

    public void tick(){

    }

    public void render(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 60));
        g.setColor(Color.white);
        g.drawString("Settings Conflict", (Constants.WIDTH/2)-110, (Constants.HEIGHT/2)-300);

        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 30));
        g.drawString("The settings in the save may differ from your current settings", Constants.WIDTH/2-100, Constants.HEIGHT/2);
        g.drawString("Do you want to keep your current settings or load the saved ones?", Constants.WIDTH/2-100, Constants.HEIGHT/2+50);

        keep.render(g);
        use.render(g);
    }
}
