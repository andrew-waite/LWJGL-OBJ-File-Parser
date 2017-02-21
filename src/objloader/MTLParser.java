package objloader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utilities.Vec3;

public class MTLParser
{
    private String filePathRelative;
    private String fileName;
    
    private List<MTLParserData> materialData = new ArrayList<MTLParserData>();
    
    /**
     * Constructor for reading in MTL files
     * @param fileName
     */
    
    public MTLParser(String fileName)
    {
        this.filePathRelative = "./OBJ_FILES/" + fileName;
        this.fileName = fileName;
        this.readFile();
    }
    
    private void readFile()
    {
        BufferedReader fileReader = null;
        
        try
        {
            fileReader = new BufferedReader(new FileReader(this.filePathRelative));
        } 
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        
        String currentLine;
        
        try
        {
            //TODO: Overhaul algorithm, change MTLParserData class as well to accept single inputs
            while((currentLine = fileReader.readLine()) != null)
            {
                int textureID;
                Vec3 ambient;
                Vec3 diffuse;
                Vec3 specular;
                
                if(currentLine.length() == 0) continue;
                else
                if(currentLine.charAt(0) == 'K' && currentLine.charAt(1) == 'a')
                {
                    String[] currentLineSplit = currentLine.split(" ");
                    
                    ambient = new Vec3(Float.valueOf(currentLineSplit[1]), Float.valueOf(currentLineSplit[2]), Float.valueOf(currentLineSplit[3]));
                }
                else
                if(currentLine.charAt(0) == 'K' && currentLine.charAt(1) == 'd')
                {
                    String[] currentLineSplit = currentLine.split(" ");
                
                    diffuse = new Vec3(Float.valueOf(currentLineSplit[1]), Float.valueOf(currentLineSplit[2]), Float.valueOf(currentLineSplit[3]));
                }
                else
                if(currentLine.charAt(0) == 'K' && currentLine.charAt(1) == 's')
                {
                    String[] currentLineSplit = currentLine.split(" ");
                    
                    specular = new Vec3(Float.valueOf(currentLineSplit[1]), Float.valueOf(currentLineSplit[2]), Float.valueOf(currentLineSplit[3]));
                }
                else
                if(currentLine.charAt(0) == 't' && currentLine.charAt(1) == 'e')
                {
                    String[] currentLineSplit = currentLine.split(" ");
                    
                    textureID = Integer.valueOf(currentLineSplit[1]);
					
					//End current file here and build
                }
				
				//TODO get texture filename
                else continue;
            }
        } 
        catch (NumberFormatException e)
        {
            e.printStackTrace();
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        try
        {
            fileReader.close();
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
}
