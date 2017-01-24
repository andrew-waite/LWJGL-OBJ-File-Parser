package main;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import objloader.OBJLoader;

public class Main
{
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;
    
    private OBJLoader water;
    
 // actual vector representing the camera's direction
    float lx = 0.0f, ly = 0.0f, lz = -1.0f;
    // XZ position of the camera
    float x = 0.0f, z = 5.0f;
    //The camera height
    float y = 0.0f;
    
    public void pre_init()
    {
        //water = new OBJLoader("./OBJ_FILES/water.obj");
    }
    
    public void init()
    {
        //GL11.glMatrixMode(GL11.GL_PROJECTION);
        //GL11.glLoadIdentity();
        changeSize(1200,800);
        //GL11.glOrtho(-3.2, 3.2, -2.4, 2.4, -1, 1);
        //GL11.glMatrixMode(GL11.GL_MODELVIEW);    
        GLU.gluLookAt(20, 0, 5.0f, 0, 0, 0, 0, 1, 0);
    }
    
    public void run() throws LWJGLException
    {
        pre_init();
        
        try
        {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.create();
        } 
        catch (LWJGLException e)
        {
            e.printStackTrace();
        }
        
        init();
        
        while(!(Display.isCloseRequested()))
        {
            loop();
        }
        
        Display.destroy();
    }
    
    public void loop() throws LWJGLException
    {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);  
        //GL11.glClearColor(0f, 129.0f, 255.0f, 1.0f);
        //GL11.glMatrixMode(GL11.GL_MODELVIEW);
        // Reset transformations
        //GL11.glLoadIdentity();   
        draw();
        //Display.swapBuffers();
        Display.update();  
    }
    
    void changeSize(int w, int h) 
    {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GLU.gluPerspective(75, WIDTH/HEIGHT, 0.3f, 100);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }
    
    public void draw()
    {
        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
        
        GL11.glPushMatrix();
        GL11.glLoadIdentity();  
        //GL11.glTranslatef(0f,0.0f,-7f);  
        //GL11.glScalef(2.0f, 2.0f, 2.0f);
        GL11.glRotatef(45f,0.0f,1.0f,0.0f);               
        GL11.glColor3f(0.5f,0.5f,1.0f);  
             
        GL11.glBegin(GL11.GL_QUADS);    
           GL11.glColor3f(1.0f,1.0f,0.0f);           
           GL11.glVertex3f( 1.0f, 1.0f,-1.0f);        
           GL11.glVertex3f(-1.0f, 1.0f,-1.0f);        
           GL11.glVertex3f(-1.0f, 1.0f, 1.0f);
           GL11.glVertex3f( 1.0f, 1.0f, 1.0f);  
           GL11.glColor3f(1.0f,0.5f,0.0f);            
           GL11.glVertex3f( 1.0f,-1.0f, 1.0f);
           GL11.glVertex3f(-1.0f,-1.0f, 1.0f);
           GL11.glVertex3f(-1.0f,-1.0f,-1.0f);
           GL11.glVertex3f( 1.0f,-1.0f,-1.0f);
           GL11.glColor3f(1.0f,0.0f,0.0f);
           GL11.glVertex3f( 1.0f, 1.0f, 1.0f);
           GL11.glVertex3f(-1.0f, 1.0f, 1.0f);
           GL11.glVertex3f(-1.0f,-1.0f, 1.0f);
           GL11.glVertex3f( 1.0f,-1.0f, 1.0f);
           GL11.glColor3f(1.0f,1.0f,0.0f);
           GL11.glVertex3f( 1.0f,-1.0f,-1.0f);
           GL11.glVertex3f(-1.0f,-1.0f,-1.0f);
           GL11.glVertex3f(-1.0f, 1.0f,-1.0f);
           GL11.glVertex3f( 1.0f, 1.0f,-1.0f);
           GL11.glColor3f(0.0f,0.0f,1.0f);
           GL11.glVertex3f(-1.0f, 1.0f, 1.0f);
           GL11.glVertex3f(-1.0f, 1.0f,-1.0f);
           GL11.glVertex3f(-1.0f,-1.0f,-1.0f);
           GL11.glVertex3f(-1.0f,-1.0f, 1.0f);
           GL11.glColor3f(1.0f,0.0f,1.0f);
           GL11.glVertex3f( 1.0f, 1.0f,-1.0f);
           GL11.glVertex3f( 1.0f, 1.0f, 1.0f);
           GL11.glVertex3f( 1.0f,-1.0f, 1.0f);
           GL11.glVertex3f( 1.0f,-1.0f,-1.0f);
       GL11.glEnd();  
       GL11.glPopMatrix();
    }
    
    public static void main(String[] args) throws LWJGLException
    {
        new Main().run();
    }

}
