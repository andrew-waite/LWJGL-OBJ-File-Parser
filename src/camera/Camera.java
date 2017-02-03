package camera;

import org.lwjgl.util.vector.Vector3f;

public class Camera
{
    private double speed = 0.5f;
    private double angle = 0.0f;
    
    private double deltaAngle = 0.0f;
    private double deltaMove = 0;
    private int xOrigin = -1;
    private double mouseDirection = 0.0;

    private Vector3f position;
    private Vector3f rotation;
    
    public Camera()
    {
        position = new Vector3f(0,0,0);
        rotation = new Vector3f(0,0,0);
    }
    
    public void computePos(double d) 
    {

        position.x += d * rotation.getX() * 1.0f;
        position.z += d * rotation.getZ() * 1.0f;
        position.y += d * rotation.getY() * 1.0f;
    }

    public void crossProduct(boolean positive, float ax, float ay, float az, float bx, float by, float bz)
    {
        float strafeSpeed = 0.5f;
        
        float cx = 0.0f, cy, cz;
        cx = ay * bz - az * by;
        cy = az * bx - ax * bz;
        cz = ax * by - ay * bx;

        cx *= strafeSpeed;
        cy *= strafeSpeed;
        cz *= strafeSpeed;
        
        if(positive)
        {
            position.x += cx;
            position.y += cy;
            position.z += cz;
        }
        else
        {
            position.x -= cx;
            position.y -= cy;
            position.z -= cz;
        }
    } 
    
    public double getAngle()
    {
        return this.angle;
    }

    public Vector3f getRotation()
    {
        return this.rotation;
    }
    
    public Vector3f getPosition()
    {
        return this.position;
    }

    public double getMouseDirection()
    {
        return this.mouseDirection;
    }

    public double getDeltaAngle()
    {
        return this.deltaAngle;
    }

    public double getDeltaMove()
    {
        return this.deltaMove;
    }

    public int getXOrigin()
    {
        return this.xOrigin;
    }
    
    public double getSpeed()
    {
        return this.speed;
    }

    public void setAngle(float angle)
    {
        this.angle = angle;
    }

   public void setRotation(Vector3f vector)
   {
       this.rotation = vector;
   }

    public void setPosition(Vector3f position)
    {
        this.position = position;
    }

    public void setMouseDirection(float mouseDirection)
    {
        this.mouseDirection = mouseDirection;
    }

    public void setDeltaAngle(float deltaAngle)
    {
        this.deltaAngle = deltaAngle;
    }

    public void setDeltaMove(float deltaMove)
    {
        this.deltaMove = deltaMove;
    }

    public void setXOrigin(int xOrigin)
    {
        this.xOrigin = xOrigin;
    }
    
    
}
