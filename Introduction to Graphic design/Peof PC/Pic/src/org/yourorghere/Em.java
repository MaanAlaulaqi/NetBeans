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

import javax.media.opengl.GLAutoDrawable;

import javax.media.opengl.GLCanvas;

import javax.media.opengl.GLEventListener;

import javax.media.opengl.glu.GLU;

import java.util.Random;

 

 

/**

 * Stars.java <BR>

 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>

 *

 * This version is equal to Brian Paul's version 1.2 1999/10/21

 */

public class Em implements GLEventListener {

    private int tex;

    private int tex2;

    private int numStars=1000;

    Random rand=new Random();

    private float zoom=-10f;

    private float direction=1f;

    private float starx[]=new float[numStars];

    private float stary[]=new float[numStars];

    private float starz[]=new float[numStars];

    

    public void starCor()

    {

        for(int i=0;i<numStars;i++)

        {

            starx[i]=rand.nextFloat()*10-2;

            stary[i]=rand.nextFloat()*10-2;

            starz[i]=(float)(rand.nextInt(10)*-1);

        }

    }

    public static void main(String[] args) {

        Frame frame = new Frame("Simple JOGL Application");

        GLCanvas canvas = new GLCanvas();

 

        canvas.addGLEventListener(new Em());

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

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        starCor();

        

        

         try{

            File im=new File("C:/Users/Jester/Downloads/Photo.jpg");

            Texture t=TextureIO.newTexture(im, true);

            tex=t.getTextureObject();

        

        }

        catch(IOException e)

        {

            e.printStackTrace();

            System.out.println("Error in File");

        }

        

        

        

        try{

            File im=new File("C:/Users/Jester/Downloads/Photo.jpg");

            Texture t=TextureIO.newTexture(im, true);

            tex2 =t.getTextureObject();

        

        }

        catch(IOException e)

        {

            e.printStackTrace();

            System.out.println("Error in File");

        }

        

        

        

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

 

    

    

    public void Cir(GL gl,float xc, float yc,double r){

         gl.glBegin(GL.GL_POLYGON);    

        for(int i=1;i<=360;i++)

        {

           float x=(float)(xc+r*Math.cos((double)i));

           float y=(float)(yc+r*Math.sin((double)i));

           gl.glVertex3f(x, y, 0.0f);

        }

         gl.glEnd();

    }

    

    

    

    

    

    

    

    

    public void display(GLAutoDrawable drawable) {

        GL gl = drawable.getGL();

 

        // Clear the drawing area

        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        // Reset the current matrix to the "identity"

        gl.glLoadIdentity();

 gl.glPushMatrix();// earth moon

        {

       gl.glBindTexture(GL.GL_TEXTURE_2D, tex);

       gl.glEnable(GL.GL_TEXTURE_2D);

        gl.glTranslatef(0f, 0f, -15f);

       gl.glBegin(GL.GL_QUADS);//earth

            //gl.glColor3f(0.5f, 0.5f, 1.0f);    // Set the current drawing color to light blue

            gl.glTexCoord2f(1f, 0f);

            gl.glVertex3f(-1.0f, 1.0f, 0.0f);  // Top Left

            gl.glTexCoord2f(1f, 1f);

            gl.glVertex3f(1.0f, 1.0f, 0.0f);   // Top Right

            gl.glTexCoord2f(0f, 1f);

            gl.glVertex3f(1.0f, -1.0f, 0.0f);  // Bottom Right

            gl.glTexCoord2f(0f, 0f);

            gl.glVertex3f(-1.0f, -1.0f, 0.0f); // Bottom Left

        // Done Drawing The Quad

        gl.glEnd();

        gl.glDisable(GL.GL_TEXTURE_2D);

         }

        gl.glPopMatrix();
        
        
        
        gl.glPushMatrix();{
        // Move the "drawing cursor" around

        gl.glTranslatef(0.0f, 0.0f, -6.0f);

        gl.glPointSize(1f);

        gl.glColor3f(1.0f, 1.0f, 1.0f);

        // Drawing Using Triangles

        gl.glBegin(GL.GL_POINTS);

                // Set the current drawing color to red

            for(int i=0;i<numStars;i++)

            {

                  gl.glVertex3f(starx[i], stary[i], starz[i]);   

            }

             

        // Finished Drawing The Triangle

        gl.glEnd();

    }
        gl.glPopMatrix();

        

         gl.glPushMatrix();// rocket window

        {
            gl.glTranslatef(0f, 0f, -15f);

         gl.glBegin(GL.GL_TRIANGLES);//head rockt

            gl.glColor3f(1.0f, 0.0f, 1.0f);    // Set the current drawing color to light blue

            gl.glVertex3f(0.0f, 3.0f, -0.0f);  // Top Left

            gl.glVertex3f(0.0f, 2.0f, -0.0f);  // Bottom Right

            gl.glVertex3f(1.0f, 2.5f, -0.0f); 

        gl.glEnd();

        

        gl.glBegin(GL.GL_QUADS);//roct base

            gl.glColor3f(0.5f, 0.5f, 1.0f);    // Set the current drawing color to light blue

            gl.glVertex3f(-2.0f, 2.0f, -0.0f);  // Top Left

            gl.glVertex3f(0.0f, 2.0f, -0.0f);   // Top Right

            gl.glVertex3f(0.0f, 3.0f, -0.0f);  // Bottom Right

            gl.glVertex3f(-2.0f, 3.0f, -0.0f); // Bottom Left

        // Done Drawing The Quad

        gl.glEnd();

        

         gl.glBegin(GL.GL_QUADS);//rockat back

            gl.glColor3f(0.5f, 0.0f, 0.0f);    // Set the current drawing color to light blue

           gl.glVertex3f(-2.0f, 2.0f, -0.0f);  // Top Left

            gl.glVertex3f(-2.0f, 3.0f, -0.0f);   // Top Right

            gl.glVertex3f(-3.0f, 3.5f, -0.0f);  // Bottom Right

            gl.glVertex3f(-3.0f, 1.5f, -0.0f); // Bottom Left

        // Done Drawing The Quad

        gl.glEnd();

        

        gl.glBegin(GL.GL_TRIANGLES);//rockat fire

            gl.glColor3f(0.0f, 1.0f, 0.0f);    // Set the current drawing color to light blue

           gl.glVertex3f(-3.0f, 2.0f, -0.0f);  // Top Left

            gl.glVertex3f(-3.0f, 3.0f, -0.0f);   // Top Right

            gl.glVertex3f(-4.0f, 2.5f, -0.0f);  // Bottom Right

            gl.glVertex3f(-4.0f, 1.5f, -0.0f); // Bottom Left

        // Done Drawing The Quad

        gl.glEnd();

        

        gl.glBegin(GL.GL_TRIANGLES);//rockat fire

            gl.glColor3f(0.0f, 1.0f, 0.0f);    // Set the current drawing color to light blue

           gl.glVertex3f(-3.0f, 2.0f, -0.0f);  // Top Left

            gl.glVertex3f(-3.0f, 3.0f, -0.0f);   // Top Right

            gl.glVertex3f(-4.0f, 4.0f, -0.0f);  // Bottom Right

            gl.glVertex3f(-4.0f, 1.5f, -0.0f); // Bottom Left

        // Done Drawing The Quad

        gl.glEnd();

        gl.glBegin(GL.GL_TRIANGLES);//rockat fire

            gl.glColor3f(0.0f, 1.0f, 0.0f);    // Set the current drawing color to light blue

           gl.glVertex3f(-3.0f, 2.0f, -0.0f);  // Top Left

            gl.glVertex3f(-3.0f, 3.0f, -0.0f);   // Top Right

            gl.glVertex3f(-4.0f, 1.0f, -0.0f);  // Bottom Right

            gl.glVertex3f(-4.0f, 4.5f, -0.0f); // Bottom Left

        // Done Drawing The Quad

        gl.glEnd();

        

         

        

        

        

            gl.glColor3f(2.0f, 5.5f, 1.0f);

           gl.glTranslatef(0.0f, 0.0f, -0.0f);

           Cir( gl , -0.5f , 2.5f , 0.3 );

        }

        gl.glPopMatrix();

        

        

        

        

        

        

        

         gl.glPushMatrix();// satalight

        {

        
gl.glTranslatef(0.0f, 0.0f, -6.0f);
        gl.glLineWidth(5f);

        gl.glBegin(GL.GL_LINES);//satalight wings connecter

            gl.glColor3f(0.0f, 0.0f, 1.0f);    // Set the current drawing color to light blue

            gl.glVertex3f(2.6f, 3.0f, 0.0f);  // Top Left

            gl.glVertex3f(2.6f, 1.5f, 0.0f);   // Top Right

       

        // Done Drawing The Quad

        gl.glEnd();

        

        

        

        gl.glColor3f(2.0f, 5.5f, 1.0f);// satalight center circl

           //gl.glTranslatef(0.0f, 0.0f, -6.0f);

           Cir( gl , 2.0f , 2.3f , 0.3 );

           

           

           gl.glBegin(GL.GL_QUADS);//satalight wings

            gl.glColor3f(0.5f, 0.5f, 1.0f);    // Set the current drawing color to light blue

            gl.glVertex3f(2.0f, 4.0f, -6.0f);  // Top Left

            gl.glVertex3f(0.0f, 4.0f, -6.0f);   // Top Right

            gl.glVertex3f(0.0f, 6.0f, -6.0f);  // Bottom Right

            gl.glVertex3f(2.0f, 6.0f, -6.0f); // Bottom Left

        // Done Drawing The Quad

        gl.glEnd();

        

        

         gl.glBegin(GL.GL_QUADS);//satalight wings

            gl.glColor3f(0.5f, 0.5f, 1.0f);    // Set the current drawing color to light blue

            gl.glVertex3f(2.0f, 1.0f, -6.0f);  // Top Left

            gl.glVertex3f(0.0f, 1.0f, -6.0f);   // Top Right

            gl.glVertex3f(0.0f, -1.0f, -6.0f);  // Bottom Right

            gl.glVertex3f(2.0f, -1.0f, -6.0f); // Bottom Left

        // Done Drawing The Quad

        gl.glEnd();

        

           }

        gl.glPopMatrix();

        

        

        

         gl.glPushMatrix();// rocket window

        {

        gl.glTranslatef(0.0f, 0.0f, -6.0f);

         gl.glBegin(GL.GL_TRIANGLES);//satalight wings

            gl.glColor3f(0.5f, 0.5f, 1.0f);    // Set the current drawing color to light blue

            gl.glVertex3f(4.5f, 4.5f, -6.0f);

            gl.glVertex3f(5.0f, 5.0f, -6.0f);

            gl.glVertex3f(4.0f, 5.0f, -6.0f);

            

            gl.glVertex3f(4.0f, 5.0f, -6.0f);

            gl.glVertex3f(5.0f, 5.0f, -6.0f);

            gl.glVertex3f(4.5f, 5.5f, -6.0f);

        // Done Drawing The Quad

        gl.glEnd();

        

        gl.glLineWidth(1f);

         gl.glBegin(GL.GL_LINES);//satalight wings

            gl.glColor3f(0.0f, 1.0f, 0.0f);    // Set the current drawing color to light blue

            gl.glVertex3f(4.5f, 4.5f, -6.0f);

            gl.glVertex3f(3.0f, 4.0f, -6.0f);

            gl.glVertex3f(4.5f, 5.5f, -6.0f);

            gl.glVertex3f(3.0f, 5.0f, -6.0f);

            gl.glVertex3f(4.1f, 5.0f, -6.0f);

            gl.glVertex3f(3.5f, 4.8f, -6.0f);

        // Done Drawing The Quad

        gl.glEnd();

        

        

        

          }

        gl.glPopMatrix();

        

        

 

        

       

        

        

        

        

        // Flush all drawing operations to the graphics card

        gl.glFlush();

        if(zoom>0f)

            direction=-1f;

        else if(zoom<-10f)

            direction=1f;

            

        zoom+=direction* .02f;

    }

 

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {

    }

}

 

