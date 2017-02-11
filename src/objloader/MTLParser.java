package objloader;

import java.util.ArrayList;
import java.util.List;

public class MTLParser
{
    private String filePathRelative;
    
    private List<MTLParserData> materialData = new ArrayList<MTLParserData>();
    
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
