import java.awt.*;

public class Menu {
    public class Button{
        private String label;
        private int x;
        private int y;
        private int width;
        private int height;
        private int fontSize;
        private Color color;

        public Button(String label, int x, int y, int width, int height, int fontSize, Color color){
            this.label = label;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.color = color;
        }

        public Button(String label, int x, int y, int width, int height, int fontSize){
            this(label, x, y, width, height, fontSize, Color.white);
        }

        public void render(Graphics g){
            g.setColor(color);
            g.fillRect(x, y, width, height);

            g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, fontSize));
            g.setColor(Color.red);
            g.drawString(label, x+3, y+5 /*y+height/8*/);
        }
    }

    /*
    public Menu(Graphics g){
        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 12));
    }
    */

    public class Pause{
        private Button settingsButton = new Button("Settings", (Main.WIDTH/2)-100, Main.HEIGHT/2, 200, 100, 40);

        public void tick(){

        }

        public void render(Graphics g){
            g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 40));
            g.setColor(Color.white);
            g.drawString("Paused", (Main.WIDTH/2)-60, (Main.HEIGHT/2)-100);

            settingsButton.render(g);
        }
    }

    public class Settings{

    }

    public Pause pause = new Pause();
    public Settings settings = new Settings();
}
