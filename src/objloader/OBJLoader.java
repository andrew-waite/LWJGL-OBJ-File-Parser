package objloader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

public class OBJLoader
{
    List<Float> texCoords = new ArrayList<Float>();
    List<Float> vertCoords = new ArrayList<Float>();
    List<Float> normalCoords = new ArrayList<Float>();
    
    List<Integer> normalIndex = new ArrayList<Integer>();
    List<Integer> vertIndex = new ArrayList<Integer>();
    List<Integer> texIndex = new ArrayList<Integer>();
    
    private float tx, ty, tz; //Translate axis
    private float ra, rz, ry, rx; //Rotation angle, and axis
    private float sx, sy, sz; //Scale axis and amount
    
    public OBJLoader(String fileName)
    {
            BufferedReader fileReader = null;
            
            try
            {
                fileReader = new BufferedReader(new FileReader(fileName));
            } 
            catch (FileNotFoundException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            String currentLine;
            
            try
            {
                while((currentLine = fileReader.readLine()) != null)
                {
                    if(currentLine.length() == 0) continue;
                    else
                    if(currentLine.charAt(0) == 'v' && currentLine.charAt(1) == 'n')
                    {
                        String[] currentLineSplit = currentLine.split(" ");
                        
                        normalCoords.add(Float.valueOf(currentLineSplit[1]));
                        normalCoords.add(Float.valueOf(currentLineSplit[2]));
                        normalCoords.add(Float.valueOf(currentLineSplit[3]));
                    }
                    else
                    if(currentLine.charAt(0) == 'v' && currentLine.charAt(1) == 't')
                    {
                        String[] currentLineSplit = currentLine.split(" ");
                        
                        texCoords.add(Float.valueOf(currentLineSplit[1]));
                        texCoords.add(Float.valueOf(currentLineSplit[2]));
                        texCoords.add(Float.valueOf(currentLineSplit[3]));
                    }
                    else
                    if(currentLine.charAt(0) == 'v')
                    {
                        String[] currentLineSplit = currentLine.split(" ");
                            //System.out.println(currentLineSplit[2] + " " + currentLineSplit[3] + " " + currentLineSplit[4]);
                        vertCoords.add(Float.valueOf(currentLineSplit[2]));
                        vertCoords.add(Float.valueOf(currentLineSplit[3]));
                        vertCoords.add(Float.valueOf(currentLineSplit[4]));
                    }
                    else
                    if(currentLine.charAt(0) == 'f')
                    {
                        String[] currentLineSplit = currentLine.split("[/ ]");
                        texIndex.add(Integer.valueOf(currentLineSplit[1]));
                        texIndex.add(Integer.valueOf(currentLineSplit[4]));
                        texIndex.add(Integer.valueOf(currentLineSplit[7]));
                        
                        vertIndex.add(Integer.valueOf(currentLineSplit[2]));
                        vertIndex.add(Integer.valueOf(currentLineSplit[5]));
                        vertIndex.add(Integer.valueOf(currentLineSplit[8]));
                        
                        normalIndex.add(Integer.valueOf(currentLineSplit[3]));
                        normalIndex.add(Integer.valueOf(currentLineSplit[6]));
                        normalIndex.add(Integer.valueOf(currentLineSplit[9]));
                    }
                    
                    else continue;
                }
            } 
            catch (NumberFormatException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            try
            {
                fileReader.close();
            } 
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
    
    public OBJLoader rotate(float rotationAngle, float x, float y, float z)
    {
        this.ra = rotationAngle;
        this.rx = x;
        this.ry = y;
        this.rz = z;
        
        return this;
    }
    
    public OBJLoader setColor(int r, int g, int b)
    {
        GL11.glColor3f(r, g, b);
        return this;
    }
    
    public OBJLoader translate(float x, float y, float z)
    {
        this.tx = x;
        this.ty = y;
        this.tz = z;
        
        return this;
    }
    
    public OBJLoader scale(float x, float y, float z)
    {
        this.sx = x;
        this.sy = y;
        this.sz = z;
        
        return this;
    }
    
    public void renderObject()
    {
        GL11.glPushMatrix();
        
        GL11.glTranslatef(tx, ty, tz);
        GL11.glRotatef(ra, rx, ry, rz);
        GL11.glScalef(sx, sy, sz);
        
        GL11.glBegin(GL11.GL_TRIANGLES);
        
        for(int i = 0; i < vertIndex.size(); i += 3)
        {
            GL11.glNormal3f(normalCoords.get(normalIndex.get(i) - 1), normalCoords.get(normalIndex.get(i + 1) - 1), normalCoords.get(normalIndex.get(i + 2) - 1));
            GL11.glTexCoord3f(texCoords.get(texIndex.get(i) - 1), texCoords.get(texIndex.get(i + 1) - 1), texCoords.get(texIndex.get(i + 2) - 1));
            GL11.glVertex3f(vertCoords.get(vertIndex.get(i) - 1), vertCoords.get(vertIndex.get(i + 1) - 1), vertCoords.get(vertIndex.get(i + 2) - 1));
        }
        
        GL11.glEnd();
        GL11.glPopMatrix();  
    }
}
