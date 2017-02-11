package objloader;

import java.util.ArrayList;
import java.util.List;

import utilities.Vec3;

public class MTLParser
{
    private Vec3 ambient;
    private Vec3 diffuse;
    private Vec3 specular;
    
    private String filePathRelative;
    
    private List<MTLParserData> materailData = new ArrayList<MTLParserData>();
    
    /**
     * Constructor for reading in MTL files
     * @param fileName
     */
    
    public MTLParser(String fileName)
    {
        this.filePathRelative = "./OBJ_FILES/" + fileName;
        this.readFile();
    }
    
    private void readFile()
    {
        
    }
    
}
