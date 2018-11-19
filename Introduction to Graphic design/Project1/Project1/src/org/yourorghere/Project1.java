package org.yourorghere;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.media.opengl.GL;
import static javax.media.opengl.GL.GL_FILL;
import static javax.media.opengl.GL.GL_FRONT_AND_BACK;
import static javax.media.opengl.GL.GL_LINE;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;




/**
 * Project1.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Project1 implements GLEventListener {
    private float x, strip1, strip2,timeR, timeG, timeB, timeRx = 0.0343f, timeGx = 0.0466f, timeBx = 0.0466f; //timeRx = 5% of 0.686, time Gx, Bx = 5% of 0.933
    private int texture;

    public static void main(String[] args) {
        Frame frame = new Frame("Welcome to My Daily Drive.");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Project1());
        frame.add(canvas);
        frame.setSize(640, 480);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        
        //--- Image stuff? 
       /* try{
           // File im=new File("/dash.png");
            Texture t=TextureIO.newTexture(im, true);
            texture =t.getTextureObject();
        
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Error in File");
        }*/
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
        
    
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!
        
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        // Clear the drawing area
        
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();
        
        gl.glTranslatef(0.0f, 0.0f, -6.0f);
        //timeR, timeG, timeB are elements of the sky light color. 
        //The idea here is to have it transition from day to night to day, etc. 
        // RGB values of day time:
        //0.686f, 0.933f, 0.933f
        //0.275f, 0.510f, 0.706f
       
        
        gl.glBegin(GL.GL_QUADS); //Skybox? Space? idk
            gl.glColor3f((0.686f+timeR), (0.933f+timeG), (0.933f+timeB));    // Set the current drawing color to light blue
            gl.glVertex3f(+10.0f, 10.0f, 0.0f);  // Top Left
            gl.glColor3f((0.686f+timeR), (0.933f+timeG), (0.933f+timeB));    // Set the current drawing color to light blue
            gl.glVertex3f(10.0f, 10.0f, 0.0f);   // Top Right
            gl.glColor3f(0.275f+timeR, 0.510f+timeG, 0.706f+timeB);    // Set the current drawing color to light blue
            gl.glVertex3f(10.0f, +10.0f, 0.0f);  // Bottom Right
            gl.glColor3f(0.275f+timeR, 0.510f+timeG, 0.706f+timeB);    // Set the current drawing color to light blue
            gl.glVertex3f(+10.0f, +10.0f, 0.0f); // Bottom Left
        // Done Drawing The Quad
        gl.glEnd();
        

        // Move the "drawing cursor" around
        
        gl.glPushMatrix();
        // Move the "drawing cursor" to another position
        //gl.glClear(GL.GL_TEXTURE_2D);
//        gl.glBindTexture(GL.GL_TEXTURE_2D, texture); //glFramebufferTexture2D //
//        gl.glEnable(GL.GL_TEXTURE_2D);
        
        
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        // Draw A Quad
        gl.glBegin(GL.GL_QUADS); //Green Area around the street?
        
            gl.glColor3f(0.133f, 0.545f, 0.133f);    // Set the current drawing color to light blue
////            gl.glTexCoord2f(-10.0f, 0.05f);
            gl.glVertex3f(-10.0f, 0.05f, 0.0f);  // Top Left
////            gl.glTexCoord2f(10.0f, 0.05f);
            gl.glVertex3f(10.0f, 0.05f, 0.0f);   // Top Right
////            gl.glTexCoord2f(10.0f, -10.0f);
            gl.glVertex3f(10.0f, -10.0f, 0.0f);  // Bottom Right
////            gl.glTexCoord2f(-10.0f, -10.0f);
            gl.glVertex3f(-10.0f, -10.0f, 0.0f); // Bottom Left
            

        // Done Drawing The Quad
        gl.glEnd();
//        gl.glDisable(GL.GL_TEXTURE_2D);
        gl.glPopMatrix();    

        
        
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        gl.glBegin(GL.GL_QUADS); //Street?
            gl.glColor3f(0.412f, 0.412f, 0.412f);    // Set the current drawing color to light blue
            gl.glVertex3f(-0.5f, 0.05f, 0.0f);  // Top Left
            gl.glVertex3f(0.5f, 0.05f, 0.0f);   // Top Right // Need to find slope to adjust stripes perhaps. It's m = 1.12222222222, and b = 0.61111 (future reference)
            gl.glVertex3f(5.0f, -5.0f, 0.0f);  // Bottom Right 
            gl.glVertex3f(-5.0f, -5.0f, 0.0f); // Bottom Left
        // Done Drawing The Quad
        gl.glEnd();
        
//        gl.glPushMatrix();{
//        
//        gl.glTranslatef(0.0f, x, 0.5f);
//        gl.glBegin(GL.GL_QUADS); //White lines on the street. Don't forget to make a "looping method" that replicates them every certain distance that 'x' has traveled. 
//            gl.glColor3f(1.0f,1.0f,1.0f); 
//            gl.glVertex3f(((strip1*0.04f)-0.008f), 0.05f, 0.0f);      //Top left 1
//            gl.glVertex3f(((strip2*0.04f)+0.008f), 0.05f, 0.0f);      //Top Right 2
//            gl.glVertex3f(((strip2*0.12f)+0.011f), -0.5f, 0.0f);      //Bottom right 3
//            gl.glVertex3f(((strip1*0.12f)-0.011f), -0.5f, 0.0f);    //bottom left 4
//            gl.glEnd();        //NOTE TO SELF; Add the strip1/strip2 incrementors to adjust strip sizes as they move
//    } gl.glPopMatrix();
           
        whiteStripes(gl, 0.0f);
  
        
        tree(-0.8f,strip1, gl);
        tree(1.8f,strip2, gl);
        tree(0.8f,strip2, gl);
                
        
        gl.glPopMatrix();

        gl.glPushMatrix(); //Dashboard 
        gl.glTranslatef(0.0f,0.0f,4.9f);
        gl.glLineWidth(15.0f);
            gl.glColor3f(0.35f, 0.35f, 0.35f);
            dashboard(gl);
        gl.glPopMatrix();
        
        gl.glPushMatrix(); //Steering wheel attempt
//        gl.glBindTexture(GL.GL_TEXTURE_2D, texture); //glFramebufferTexture2D //
//        gl.glEnable(GL.GL_TEXTURE_2D);
          gl.glPolygonMode (GL_FRONT_AND_BACK, GL_LINE);
          gl.glLineWidth(0.1f);//Is this a joke?
          gl.glBegin(GL.GL_POLYGON);
          gl.glColor3f(0.545f, 0.271f, 0.075f);
            circle(360,-1.5f,-1.65f,0.5f,gl);
          gl.glEnd();
          gl.glBegin(GL.GL_POLYGON);
            gl.glColor3f(0.627f, 0.322f, 0.176f);
            circle(360,-1.5f,-1.65f,0.45f,gl);
////        gl.glTexCoord2f(-1.4f, -1.6f);
          gl.glEnd();
          
//        gl.glDisable(GL.GL_TEXTURE_2D);
        gl.glPolygonMode (GL_FRONT_AND_BACK, GL_FILL);

        gl.glPopMatrix();




        // Flush all drawing operations to the graphics card
        gl.glFlush();
        //System.out.println(x);
        //Loop the stripe back up. 
       if (x <= -3.005f ) //idk why 3.005 was the sweet spot, but I'll take it.
           x =x+ 3.005f;
       else 
           x =x-0.007f;
       //System.out.println(x);
       //System.out.println(strip1 + " " + strip2);
       if(strip1 <= -3.005f)
           strip1 = strip1 + 3.005f;
       else
           strip1 =  (float) (( (0.05+x)-0.61111)/1.12222222222); // b intercept = 0.61111, m = 1.12222222222 (for the left side of the white stripe)
       if(strip2 <= -3.005f)
           strip2 = strip1 + 3.005f;
       else
           strip2 =  (float) (( (0.05+x)-0.61111)/-1.12222222222); // b intercept = 0.61111, m = 1.12222222222 (for the left side of the white stripe)
       //-- Day/Night cheange :
       System.out.println(timeR + " " + timeG + " " + timeB);
       timeB =+ timeBx;
       
       if (timeR <= 0.0f )
            timeR =+ timeRx;
       if (timeR >= 0.686) timeR =- timeRx;
       
       if (timeG <= 0.0f )
            timeG =+ timeGx;
       if (timeG >= 0.686) timeG =- timeGx;
       
       if (timeB <= 0.933f  )
            timeB =+ timeBx;
       if (timeB >= 0.933 ) timeB =- timeBx;
       
       
       
        
    }
    
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
    //To draw them big curves
    public void circle(int arc, float Xc, float Yc, float r, GL gl){
        float x, y;
       
        
        for(int i=1;i<=arc;i++){
            x =(float)((Xc+2*r*Math.cos((i))));
            y =(float)((Yc+2*r*Math.sin((i))));
            gl.glVertex3f(x, y, 0.0f);
        }
        //gl.glEnd();
    }
    
   //White stripes repetition attempt
    public void whiteStripes(GL gl, float d){
        for (int i = 0; i < 2; i++){
            gl.glPushMatrix();{

                gl.glTranslatef(0.0f, x, 0.5f);
                gl.glBegin(GL.GL_QUADS); //White lines on the street. Don't forget to make a "looping method" that replicates them every certain distance that 'x' has traveled. 
                    gl.glColor3f(1.0f,1.0f,1.0f); 
                    gl.glVertex3f(((strip1*0.04f)-0.008f), 0.05f-2*d, 0.0f);      //Top left 1
                    gl.glVertex3f(((strip2*0.04f)+0.008f), 0.05f-2*d, 0.0f);      //Top Right 2
                    gl.glVertex3f(((strip2*0.12f)+0.011f), -0.5f-2*d, 0.0f);      //Bottom right 3
                    gl.glVertex3f(((strip1*0.12f)-0.011f), -0.5f-2*d, 0.0f);    //bottom left 4
                    gl.glEnd();        //NOTE TO SELF; Add the strip1/strip2 incrementors to adjust strip sizes as they move
            } gl.glPopMatrix();
            d =+ 0.56f;
        }
    }
    
    public void tree(float y, float side, GL gl){
         gl.glPushMatrix(); //TREE
        gl.glTranslatef((side*1.0f)+(y), x, 0.0f);
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.333f, 0.420f, 0.184f);
            gl.glVertex3f(0.4f, 0.6f, 0.0f);            
            gl.glColor3f(0.502f, 0.502f, 0.000f);
            gl.glVertex3f(-0.3f, 0.6f, 0.0f);
            gl.glVertex3f(-0.3f, 1.0f, 0.0f);
            gl.glVertex3f(0.4f, 1.0f, 0.0f);
        gl.glEnd();
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.0f, 0.392f, 0.0f);
            gl.glVertex3f(0.2f, 0.04f, 0.0f);
            gl.glVertex3f(-0.1f, 0.04f, 0.0f);
            gl.glVertex3f(-0.1f, 0.9f, 0.0f);
            gl.glVertex3f(0.2f, 0.9f, 0.0f);
        gl.glEnd();
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.545f, 0.271f, 0.075f);
            gl.glVertex3f(0.2f, 0.04f, 0.0f);
            gl.glVertex3f(-0.1f, 0.04f, 0.0f);
            gl.glVertex3f(-0.1f, 0.6f, 0.0f);
            gl.glVertex3f(0.2f, 0.6f, 0.0f);
        gl.glEnd();
    
        
        gl.glPopMatrix();
    }
    
    //The motherload. The big guy. The Triangle strip to make the dashboard. 
    /*
    Triangle_Strip didn't work out
    */
    public void dashboard(GL gl){
        
        gl.glPolygonMode (GL_FRONT_AND_BACK, GL_LINE);
        gl.glBegin(GL.GL_LINE_LOOP); //Estimate 18 points. 
            
            gl.glVertex3f(-0.9f/2, 0.9f/2,0.0f);//Top left
            gl.glVertex3f(-1.1f/2, 0.8f/2,0.0f);
            gl.glVertex3f(-1.15f/2, 0.75f/2,0.0f);
            gl.glVertex3f(-1.2f/2, 0.7f/2,0.0f);
            gl.glVertex3f(-1.25f/2, 0.6f/2,0.0f);
            gl.glVertex3f(-1.3f/2, 0.4f/2,0.0f);
            gl.glVertex3f(-1.1f/2, -0.7f/2,0.0f);//bottom left
            gl.glVertex3f(1.1f/2, -0.7f/2 ,0.0f);//bottom right
            gl.glVertex3f(1.3f/2, 0.4f/2,0.0f);
            gl.glVertex3f(1.25f/2, 0.6f/2,0.0f);
            gl.glVertex3f(1.15f/2, 0.75f/2,0.0f);
            gl.glVertex3f(1.2f/2, 0.7f/2,0.0f);
            gl.glVertex3f(1.1f/2, 0.8f/2,0.0f);
            gl.glVertex3f(0.9f/2, 0.9f/2,0.0f);//top right
        gl.glEnd();
        gl.glPolygonMode (GL_FRONT_AND_BACK, GL_FILL);
        
        gl.glBegin(GL.GL_QUADS); //Left color fill
           gl.glVertex3f(-0.635f,3.0f,0.0f);
            gl.glVertex3f(-10.0f,3.0f,0.0f);
            gl.glVertex3f(-10.0f,-3.0f,0.0f);
            gl.glVertex3f(-0.635f,-3.0f,0.0f);
        gl.glEnd();
        
         gl.glBegin(GL.GL_QUADS); //Right color fill
           gl.glVertex3f(0.635f,3.0f,0.0f);
           gl.glVertex3f(10.0f,3.0f,0.0f);
           gl.glVertex3f(10.0f,-3.0f,0.0f);
           gl.glVertex3f(0.635f,-3.0f,0.0f);
        gl.glEnd();
        
        
        //gl.glTranslatef(0.0f, 0.0f,-4.9f);  //Testing dashboard color fill stuff
        gl.glBegin(GL.GL_QUADS); //Bottom color fill
            gl.glVertex3f(-5.0f,-0.35f,0.0f);
            gl.glVertex3f(5.0f,-0.35f,0.0f);
            gl.glVertex3f(5.0f,-10.0f,0.0f);
            gl.glVertex3f(-5.0f,-10.0f,0.0f);
        gl.glEnd();
        
        gl.glPushMatrix();
        //gl.glColor3f(1.0f, 0.0f,0.0f);
        gl.glBegin(GL.GL_TRIANGLES);//Left triangle bottom fill
            gl.glVertex3f(-0.55f,-0.35f,0.0f);
            gl.glVertex3f(-0.635f,-0.35f,0.0f);
            gl.glVertex3f(-0.635f,0.1f,0.0f);
        gl.glEnd();
        gl.glPopMatrix();
        
         gl.glPushMatrix();
        //gl.glColor3f(1.0f, 0.0f,0.0f);
        gl.glBegin(GL.GL_TRIANGLES);//Right triangle bottom fill
            gl.glVertex3f(0.55f,-0.35f,0.0f);
            gl.glVertex3f(0.635f,-0.35f,0.0f);
            gl.glVertex3f(0.635f,0.1f,0.0f);
        gl.glEnd();
        gl.glPopMatrix();
        
         gl.glPushMatrix(); //TOP RIGHT COLOR FILL
        gl.glBegin(GL.GL_POLYGON);//Top Right triangle fill ONE
            //gl.glColor3f(1.0f, 0.0f,0.0f);
            gl.glVertex3f(0.42f,0.46f,0.0f);
           // gl.glColor3f(0.0f, 1.0f,0.0f);
            gl.glVertex3f(0.65f,0.46f,0.0f);
            //gl.glColor3f(0.0f, 0.0f,1.0f);
            //gl.glVertex3f(0.635f,0.282f,0.0f);//0.65f,0.25f,0.0f
            //gl.glColor3f(0.0f, 0.0f,0.0f);
            gl.glVertex3f(0.61f,0.37f,0.0f);    
        gl.glEnd();
        gl.glBegin(GL.GL_POLYGON);//Top Right triangle fill TWO
           // gl.glColor3f(1.0f, 0.0f,0.0f);
            gl.glVertex3f(0.65f,0.25f,0.0f);
            //gl.glColor3f(0.0f, 1.0f,0.0f);
            gl.glVertex3f(0.65f,0.46f,0.0f);
            //gl.glColor3f(0.0f, 0.0f,0.0f);
            gl.glVertex3f(0.57f,0.39f,0.0f);
        gl.glEnd();
        gl.glEnd();
        gl.glPopMatrix();
        
        gl.glPushMatrix();//TOP LEFT COLOR FILL
        gl.glBegin(GL.GL_POLYGON);//Top LEFT triangle fill ONE
            //gl.glColor3f(1.0f, 0.0f,0.0f);
            gl.glVertex3f(-0.42f,0.46f,0.0f);
           // gl.glColor3f(0.0f, 1.0f,0.0f);
            gl.glVertex3f(-0.65f,0.46f,0.0f);
            //gl.glColor3f(0.0f, 0.0f,1.0f);
            //gl.glVertex3f(0.635f,0.282f,0.0f);//0.65f,0.25f,0.0f
            //gl.glColor3f(0.0f, 0.0f,0.0f);
            gl.glVertex3f(-0.61f,0.37f,0.0f);    
        gl.glEnd();
        gl.glBegin(GL.GL_POLYGON);//Top LEGFT triangle fill TWO
           // gl.glColor3f(1.0f, 0.0f,0.0f);
            gl.glVertex3f(-0.65f,0.25f,0.0f);
            //gl.glColor3f(0.0f, 1.0f,0.0f);
            gl.glVertex3f(-0.65f,0.46f,0.0f);
            //gl.glColor3f(0.0f, 0.0f,0.0f);
            gl.glVertex3f(-0.57f,0.39f,0.0f);
        gl.glEnd();
        gl.glEnd();
        gl.glPopMatrix();
        
        
        
       
        
    
        
        
        
    }
}


//Stuff that didn't work, keeping for future reference. 
/*
////
gl.glBegin(GL.GL_QUADS);
       testWhiteStripes();
       gl.glEnd();
/////

public void testWhiteStripes(GLAutoDrawable drawable){ //Playing with the concept of "GLAutoDrawable drawable"
                                                       //The idea here is to see if I can make a method that I can just call once to draw all the vertices I need.  
        GL gl = drawable.getGL();

       // gl.glBegin(GL.GL_QUADS); //White lines on the street. Don't forget to make a "looping method" that replicates them every certain distance that 'x' has traveled. 
            gl.glColor3f(1.0f,1.0f,1.0f); 
            gl.glVertex3f(-0.02f, 0.05f, 0.0f);      //Top left 1
            gl.glVertex3f(0.02f, 0.05f, 0.0f);      //Top Right 2
            gl.glVertex3f(0.02f, -0.5f, 0.0f);      //Bottom right 3
            gl.glVertex3f(-0.02f, -0.5f, 0.0f);    //bottom left 4
            gl.glEnd();        //NOTE TO SELF; Add the strip1/strip2 incrementors to adjust strip sizes as they move
        // Flush all drawing operations to the graphics card
        //gl.glFlush();
        
    }

///////
//Self note; these were for the dashboard coordinates.
 /*gl.glVertex3f(-0.9f, 1.0f,0.0f);//Top left
        gl.glVertex3f(-1.1f, 0.8f,0.0f);
        gl.glVertex3f(-1.15f, 0.75f,0.0f);
        gl.glVertex3f(-1.2f, 0.7f,0.0f);
        gl.glVertex3f(-1.25f, 0.6f,0.0f);
        gl.glVertex3f(-1.3f, 0.4f,0.0f);
        gl.glVertex3f(-1.1f, -1.0f,0.0f);//bottom left
        gl.glVertex3f(1.1f, -1.0f ,0.0f);//bottom right
        gl.glVertex3f(1.3f, 0.4f,0.0f);
        gl.glVertex3f(1.25f, 0.6f,0.0f);
        gl.glVertex3f(1.15f, 0.75f,0.0f);
        gl.glVertex3f(1.2f, 0.7f,0.0f);
        gl.glVertex3f(1.1f, 0.8f,0.0f);
        gl.glVertex3f(0.9f, 1.00f,0.0f);//top right*/
      
        
        
        /*gl.glVertex3f(-1.8f, 2.0f,0.0f);//
        gl.glVertex3f(-2.0f, 1.8f,0.0f);
        gl.glVertex3f(-2.1f, -1.0f ,0.0f);
        gl.glVertex3f(-2.0f, -1.2f,0.0f);//
        gl.glVertex3f(-1.5f, -2.0f,0.0f);
        gl.glVertex3f(1.8f, -2.0f ,0.0f);
        gl.glVertex3f(2.0f, -1.8f,0.0f);//
        gl.glVertex3f(2.0f, 1.6f,0.0f);
        gl.glVertex3f(1.9f, 1.8f ,0.0f);
        gl.glVertex3f(1.85f, 1.9f,0.0f);//
        gl.glVertex3f(1.8f, 1.95f,0.0f);
////

      /*gl.glPushMatrix(); //TREE
        gl.glTranslatef((strip2*1.0f)+1.8f, x, 0.0f);
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.333f, 0.420f, 0.184f);
            gl.glVertex3f(0.4f, 0.6f, 0.0f);            
            gl.glColor3f(0.502f, 0.502f, 0.000f);
            gl.glVertex3f(-0.3f, 0.6f, 0.0f);
            gl.glVertex3f(-0.3f, 1.0f, 0.0f);
            gl.glVertex3f(0.4f, 1.0f, 0.0f);
        gl.glEnd();
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.0f, 0.392f, 0.0f);
            gl.glVertex3f(0.2f, 0.04f, 0.0f);
            gl.glVertex3f(-0.1f, 0.04f, 0.0f);
            gl.glVertex3f(-0.1f, 0.9f, 0.0f);
            gl.glVertex3f(0.2f, 0.9f, 0.0f);
        gl.glEnd();
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.545f, 0.271f, 0.075f);
            gl.glVertex3f(0.2f, 0.04f, 0.0f);
            gl.glVertex3f(-0.1f, 0.04f, 0.0f);
            gl.glVertex3f(-0.1f, 0.6f, 0.0f);
            gl.glVertex3f(0.2f, 0.6f, 0.0f);
        gl.glEnd();*/




        


