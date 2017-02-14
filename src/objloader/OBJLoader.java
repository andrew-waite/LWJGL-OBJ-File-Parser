package objloader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import utilities.Vec3;

public class OBJLoader
{
    List<Vec3> texCoords = new ArrayList<Vec3>();
    List<Vec3> vertCoords = new ArrayList<Vec3>();
    List<Vec3> normalCoords = new ArrayList<Vec3>();
    
    List<Integer> normalIndex = new ArrayList<Integer>();
    List<Integer> vertIndex = new ArrayList<Integer>();
    List<Integer> texIndex = new ArrayList<Integer>();
    
    private float tx = 0, ty = 0, tz = 0; //Translate axis
    private float ra = 0, rz = 0, ry = 0, rx = 0; //Rotation angle, and axis
    private float sx = 1, sy = 1, sz = 1; //Scale axis and amount
    
    public OBJLoader(String fileName)
    {
            BufferedReader fileReader = null;
            
            try
            {
                fileReader = new BufferedReader(new FileReader(fileName));
            } 
            catch (FileNotFoundException e)
            {
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
                        
                        normalCoords.add(new Vec3(Float.valueOf(currentLineSplit[1]), Float.valueOf(currentLineSplit[2]), Float.valueOf(currentLineSplit[3])));
                    }
                    else
                    if(currentLine.charAt(0) == 'v' && currentLine.charAt(1) == 't')
                    {
                        String[] currentLineSplit = currentLine.split(" ");
                        
                        texCoords.add(new Vec3(Float.valueOf(currentLineSplit[1]), Float.valueOf(currentLineSplit[2]), Float.valueOf(currentLineSplit[3])));
                    }
                    else
                    if(currentLine.charAt(0) == 'v')
                    {
                        String[] currentLineSplit = currentLine.split(" ");
                        
                        vertCoords.add(new Vec3(Float.valueOf(currentLineSplit[2]), Float.valueOf(currentLineSplit[3]), Float.valueOf(currentLineSplit[4])));
                    }
                    else
                    if(currentLine.charAt(0) == 'f')
                    {
                        String[] currentLineSplit = currentLine.split("[/ ]");
                        texIndex.add(Integer.valueOf(currentLineSplit[2]));
                        texIndex.add(Integer.valueOf(currentLineSplit[5]));
                        texIndex.add(Integer.valueOf(currentLineSplit[8]));
                        
                        vertIndex.add(Integer.valueOf(currentLineSplit[1]));
                        vertIndex.add(Integer.valueOf(currentLineSplit[4]));
                        vertIndex.add(Integer.valueOf(currentLineSplit[7]));
                        
                        normalIndex.add(Integer.valueOf(currentLineSplit[3]));
                        normalIndex.add(Integer.valueOf(currentLineSplit[6]));
                        normalIndex.add(Integer.valueOf(currentLineSplit[9]));
                    }
                    else
                    if(currentLine.charAt(0) == 'm')
                    {
                        //TODO: Add mtl reader
                    }
                    
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
            
            //TODO: Parse mtl data into lighting here
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
        GL11.glColor3ub((byte)r, (byte)g, (byte)b);
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
        
        for(int i = 0; i < vertIndex.size(); i++)
        {
            GL11.glNormal3f(normalCoords.get(normalIndex.get(i) - 1).getX(), normalCoords.get(normalIndex.get(i) - 1).getY(), normalCoords.get(normalIndex.get(i) - 1).getZ());
            GL11.glTexCoord3f(texCoords.get(texIndex.get(i) - 1).getX(), texCoords.get(texIndex.get(i) - 1).getY(), texCoords.get(texIndex.get(i) - 1).getZ());
            GL11.glVertex3f(vertCoords.get(vertIndex.get(i) - 1).getX(), vertCoords.get(vertIndex.get(i) - 1).getY(), vertCoords.get(vertIndex.get(i) - 1).getZ());
        }
        
        GL11.glEnd();
        GL11.glPopMatrix();  
    }
}
