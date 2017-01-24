
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author macdt8987
 */


public class FinalProjectGame extends JComponent implements KeyListener{

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000)/desiredFPS;
    
    //game variables
    Rectangle player = new Rectangle(375,500,50,50);
    Color spacecolour = new Color (121,131,147);
    // load in the image files
    BufferedImage background = loadImage("images333.jpg");
    BufferedImage spaceship = loadImage ("spaceship2.png");
    BufferedImage rockpic = loadImage("rockpicture.png");
    // Movement
    // dash key variable
    boolean dash = false;
    // move right or left
    boolean jump = false;
    // tell the player to start the game
   
    // Speed of the game
    int speed = 20;
    int score = 0;
    // wait to start
    boolean start = false;
    boolean dead = false;
    //
   
    // rocks
    Rectangle[] toprocks = new Rectangle [5];
    Rectangle[] bottomrocks = new Rectangle[5];
   
    // the height of a rock
    int rockheight = HEIGHT - 25;
    // the width of a single rock
    int rockwidth = 50;
    // distance between rocks
    int rockspacing = 75;
    // min distance from edge
    int minDistance = 100;
   // gap of rocks
    int rockgap = 100;
    // background
    int bgY1 = 0;
    int bgY2 = -HEIGHT;
   
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g)
    {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);
        
        // GAME DRAWING GOES HERE 
        
        //draw the sky background
        //Draw the sky
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(background, 0, bgY1, WIDTH, HEIGHT, null);
        g.drawImage(background, 0, bgY2, WIDTH, HEIGHT, null);
        // Draw the spaceship
        g.drawImage(spaceship, player.x, player.y, player.width, player.height, null);
        
      
        }
        // GAME DRAWING ENDS HERE
    
    // get something to load the images
    public BufferedImage loadImage(String filename){
        BufferedImage img = null;
        try{
         File file = new File(filename);   
        img = ImageIO.read(file);
        }catch(Exception e){
            // if there's an error print it
            e.printStackTrace();
        }
    return img;
    }
    
    public void reset(){
        // reset the player
        score = 0;
        start = false;
        dead = false;
        
    }
  
    
    // The main game loop
    // In here is where all the logic for my game will go
    public void run()
    {
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;
       
        // the main game loop section
        // game will end if you set done = false;
        boolean done = false; 
        while(!done)
        {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();
            
            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
           
            
            
            
            //player.y = player.y - 1;
            if (start){
                
            
            bgY1 = bgY1 + 8;
            bgY2 = bgY2 + 8;
            if(bgY1 >= HEIGHT){
                bgY1 = -HEIGHT;
            }
            if(bgY2 >= HEIGHT){
                bgY2 = -HEIGHT;
            }
            }
           // make it so the player cannot go out of bounds
            
            // left x coordinate
            if (player.x < - 25){
                player.x = 25;
            }
             // right x coordinate
            if (player.x > + 775){
                    player.x = 700;
                }   
            // top y x coordinate
            if (player.y < - 25){
                player.y = 30;  
            }
          // kill the player if they hit the bottom of the screen
            if(player.y == 600){
                player.x = 375;
                
                dead = true;
                reset();
            }
            // GAME LOGIC ENDS HERE 
            
            // update the drawing (calls paintComponent)
            repaint();
            
            
            
            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            try
            {
               if(deltaTime > desiredTime)
               {
                   //took too much time, don't wait
                   Thread.sleep(1);
               }else{
                  // sleep to make up the extra time
                 Thread.sleep(desiredTime - deltaTime);
               }
            }catch(Exception e){};
        }
            
            }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates a windows to show my game
        JFrame frame = new JFrame("My Game");
       
        // creates an instance of my game
        FinalProjectGame game = new FinalProjectGame();
        // sets the size of my game
        game.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        // adds the game to the window
        
        frame.add(game);
        // add the key listener
        frame.addKeyListener(game);
        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);
 
        // starts my game loop
        game.run();
    }
      // Controls     
    @Override
    public void keyPressed(KeyEvent e){
               int key = e.getKeyCode();
               if (key == KeyEvent.VK_SPACE)
                   dash = true;
                   start = true;
               // move right
               int key2 = e.getKeyCode();
               if (key2 == KeyEvent.VK_RIGHT){
                   // move it right
                   
                   player.x = (player.x + 100);
                   
               }
               // move left
               int key3 = e.getKeyCode();
               if (key3 == KeyEvent.VK_LEFT){
                   // move it left
                   player.x = (player.x - 100);
               }
               // make the player be able to move down
               int key4 = e.getKeyCode();
               if (key4 == KeyEvent.VK_DOWN){
                   player.y = (player.y + 50);
               }
               // make the player be able to move up 
               int key5 = e.getKeyCode();
               if (key5 == KeyEvent.VK_UP){
                   player.y = (player.y - 50);
               }
    }
    
    @Override
           public void keyTyped(KeyEvent e){
               
           }
    @Override
           public void keyReleased(KeyEvent e){
               int key = e.getKeyCode();
               if (key == KeyEvent.VK_UP)
                   dash = false;
               int key4 = e.getKeyCode();
               if (key4 == KeyEvent.VK_DOWN){
                   
               }
               
           }
}
        //
 
    
    
