package camera;

import org.lwjgl.util.vector.Vector3f;

public class Camera
{
    private double speed = 0.5f;

    private Vector3f position;
    private Vector3f rotation;
    
    public Camera()
    {
        position = new Vector3f(0,0,0);
        rotation = new Vector3f(0,0,0);
    }
    
    public void computePos(float deltaMove) 
    {

        position.x += deltaMove * rotation.x * 1.0f;
        position.z += deltaMove * rotation.z * 1.0f;
        position.y += deltaMove * rotation.y * 1.0f;
    }

    public void crossProduct(boolean positive, float ax, float ay, float az, float bx, float by, float bz)
    {
        float strafeSpeed = 0.2f;
        
        float cx = 0.0f, cy, cz;
        cx = ay * bz - az * by;
        cy = az * bx - ax * bz;
        cz = ax * by - ay * bx;

        cx *= strafeSpeed;
        cy *= strafeSpeed;
        cz *= strafeSpeed;
        
        position.x += positive ? cx : -cx;
        position.y += positive ? cy : -cy;
        position.z += positive ? cz : -cz;
    } 
}
