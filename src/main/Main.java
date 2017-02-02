package main;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector3f;

import camera.Camera;
import objloader.OBJLoader;

public class Main
{
    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;
    
    private OBJLoader water;
    private Camera camera = new Camera();
    
    public void pre_init()
    {
        water = new OBJLoader("./OBJ_FILES/water.obj");
    }
    
    public void init()
    {
        pre_init();
        
        try 
        {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.create();
             
            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();
             
            GLU.gluPerspective(60.0f, (WIDTH / HEIGHT), 0.4f, 1000f);
            
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
             
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glDepthFunc(GL11.GL_NEAREST);
            
            Mouse.setGrabbed(true);
        } 
        catch (LWJGLException e) 
        {
            e.printStackTrace();
        }
    }
    
    public void run()
    {
        init();
        
        while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
        {
            camera.computePos(camera.getDeltaMove());
            
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
             
            GL11.glLoadIdentity();
            
           updateMouse();
           updateKeyboard(); 

           draw();
            
            Display.update();
        }
        
        Display.destroy();
        System.exit(-1);
    }
    
    public void updateMouse()
    {
        camera.setXOrigin(Mouse.getDX());
        
        camera.setMouseDirection((Mouse.getY() - HEIGHT / 2) * 0.002f);

        // update deltaAngle
        camera.setDeltaAngle((Mouse.getX() - camera.getXOrigin()) * 0.002f);

        // update camera's direction        
        Vector3f rotation = new Vector3f((float)Math.cos(camera.getDeltaAngle()), 
                                         (float) (0.1 + camera.getMouseDirection()), 
                                         (float)Math.sin((camera.getDeltaAngle())));
        
        camera.setRotation(rotation);
        
        GLU.gluLookAt(camera.getPosition().getX(), 
                      camera.getPosition().getY(),
                      camera.getPosition().getZ(), 
                      camera.getPosition().getX() + camera.getRotation().getX(), 
                      camera.getPosition().getY() + camera.getRotation().getY(), 
                      camera.getPosition().getZ() + camera.getRotation().getZ(), 
                      0.0f, 
                      1.0f, 
                      0.0f);
        
        
       // Mouse.setCursorPosition(Mouse.getX() / 2, Mouse.getY() / 2);
        
        camera.setAngle((float) (camera.getAngle() + camera.getDeltaAngle()));
    }
    
    public void updateKeyboard()
    {
        while (Keyboard.next())
        {
            if (Keyboard.getEventKey() == Keyboard.KEY_A)
            {
                if (Keyboard.getEventKeyState())
                {
                    camera.crossProduct(true, 0.0f, 15.0f, 0.0f,
                            camera.getRotation().getX(), camera.getRotation().getY(),
                            camera.getRotation().getZ());
                }
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_D)
            {
                if (Keyboard.getEventKeyState())
                {
                    camera.crossProduct(false, 0.0f, 15.0f, 0.0f,
                            camera.getRotation().getX(), camera.getRotation().getY(),
                            camera.getRotation().getZ());
                }
            }

            if (Keyboard.getEventKey() == Keyboard.KEY_W)
            {
                if (Keyboard.getEventKeyState())
                {
                    camera.setDeltaMove(0.04f);
                } 
                else
                    camera.setDeltaMove(0.0f);
            }

            if (Keyboard.getEventKey() == Keyboard.KEY_S)
            {
                if (Keyboard.getEventKeyState())
                {
                    camera.setDeltaMove(-0.04f);
                } 
                else
                    camera.setDeltaMove(0.0f);
            }
        }
    }
        
    public void draw()
    {   
       GL11.glColor3f(1.0f,0.0f,0.0f);
       water.setColor(0, 0, 255).renderObject();
    }
    
    public static void main(String[] args) throws LWJGLException
    {
        new Main().run();
    }
}