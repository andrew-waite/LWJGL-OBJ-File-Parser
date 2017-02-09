package objloader;

import utilities.Vec3;

public class MTLParser
{
    private Vec3 ambient;
    private Vec3 diffuse;
    private Vec3 specular;
    
    private String filePathAbsolute;
    
    public MTLParser(String fileName)
    {
        this.filePathAbsolute = "./OBJ_FILES/" + fileName;
    }
    
    public Vec3 getAmbient()
    {
        return this.ambient;
    }
    
    
    
}
