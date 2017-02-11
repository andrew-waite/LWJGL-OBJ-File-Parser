package objloader;

import utilities.Vec3;

public class MTLParser
{
    private Vec3 ambient;
    private Vec3 diffuse;
    private Vec3 specular;
    
    private String filePathRelative;
    
    /**
     * Constructor for reading in MTL files
     * @param fileName
     */
    
    public MTLParser(String fileName)
    {
        this.filePathRelative = "./OBJ_FILES/" + fileName;
    }
    
    public Vec3 getAmbient()
    {
        return this.ambient;
    }
    
    
    
}
