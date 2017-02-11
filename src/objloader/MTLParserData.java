package objloader;

import utilities.Vec3;

public class MTLParserData
{
    private Vec3 ambient;
    private Vec3 diffuse;
    private Vec3 specular;
    
    private int id;
    
    public MTLParserData(int id, Vec3 ambient, Vec3 diffuse, Vec3 specular)
    {
        this.id = id;
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
    }
    
    public Vec3 getAmbient()
    {
        return this.ambient;
    }
    
    public Vec3 getDiffuse()
    {
        return this.diffuse;
    }
    
    public Vec3 getSpecular()
    {
        return this.specular;
    }
}
