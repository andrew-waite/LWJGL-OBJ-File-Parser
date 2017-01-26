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
    
    
}
