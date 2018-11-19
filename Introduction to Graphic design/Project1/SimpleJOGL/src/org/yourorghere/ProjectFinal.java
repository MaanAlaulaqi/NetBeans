package org.yourorghere;

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

/**
 * Train.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel)
 * <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class ProjectFinal implements GLEventListener {
    private float transT,transC, transU1, transU2, transU3, transU4, zoomS, direction ,transU5 = 1f;
    
    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new ProjectFinal());
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
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
    }

    public void ENGINE(float x1, float y1, GL gl) {

        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(0.2f, 0.522f, 1.0f);//ENGINE Bogy 
        gl.glVertex2f(x1 + 1, y1); 
        gl.glColor3f(0.0f, 0.0f, 1.0f);
        gl.glVertex2f(x1 + 6, y1);
        gl.glColor3f(0.502f, 0.502f, 0.502f);
        gl.glVertex2f(x1 + 6, y1 - 1);
        gl.glColor3f(0.502f, 0.502f, 0.502f);
        gl.glVertex2f(x1, y1 - 1);
        gl.glEnd();
    }
    
    public void circle(float Xc, float Yc,float r, GL gl)
     {
         gl.glBegin(GL.GL_POLYGON);
         for(int i=1;i<=360;i++)
         {
             float X=(float)((Xc+2*r*Math.cos((i))));
             float Y=(float)((Yc+2*r*Math.sin((i))));
             gl.glVertex3f(X, Y, 0.0f);
         }
         gl.glEnd();
     }

    public void BOGIES(float a, float b, GL gl) {
        int i = 0;
        while (i < 3) {
            gl.glBegin(GL.GL_QUADS); //BOGIES 
            gl.glColor3f(0.2f, 0.522f, 1.0f); //For right train a=795,b=510 
            gl.glVertex2f(a, b);
            gl.glColor3f(0.0f, 0.0f, 1.0f);
            gl.glVertex2f(a + 5, b);
            gl.glColor3f(0.502f, 0.502f, 0.502f);
            gl.glVertex2f(a + 5, b - 1);
            gl.glColor3f(0.502f, 0.502f, 0.502f);
            gl.glVertex2f(a, b - 1);
            gl.glEnd();
            a += 65;
            i++;
        }
    }

    public void WINDOWS(float w1, float w2, GL gl) {
        int i = 0;
        while (i < 3) {
        gl.glBegin(GL.GL_QUADS); //BOGIES 
        gl.glColor3f(0.102f, 0.549f, 1.0f);//For right train a=795,b=510 
        gl.glVertex2f(w1, w2);
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glVertex2f(w1 + 0.5f, w2);
        gl.glColor3f(0.502f, 0.8f, 1.0f);
        gl.glVertex2f(w1 + 0.5f, w2 - 0.5f);
        gl.glColor3f(0.502f, 0.8f, 1.0f);
        gl.glVertex2f(w1, w2 - 0.5f);
        gl.glEnd();
        w1 += 65;
        i++;
        }
    }

    public void DOOR(float d1, float d2, GL gl) {
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.102f, 0.549f, 1.0f);
        gl.glVertex2f(d1, d2);
        gl.glColor3f(0.902f, 0.961f, 1.0f);
        gl.glVertex2f(d1 + 0.55f, d2);
        gl.glColor3f(0.6f, 0.8f, 1.0f);
        //gl.glColor3f(0.0f, 0.0f, 0.0f);
        gl.glVertex2f(d1 + 0.55f, d2 - 0.85f);
        gl.glColor3f(0.502f, 0.8f, 1.0f);
        gl.glColor3f(0.349f, 0.349f, 0.349f);
        //gl.glColor3f(0.0f, 0.0f, 0.0f);
        gl.glVertex2f(d1, d2 - 0.85f);
        gl.glEnd();
    }

    public void DOORL(float l1, float l2, GL gl) {
        gl.glBegin(GL.GL_LINES); //BOGIES 
        gl.glColor3f(0.0f, 0.0f, 0.0f);//For right train a=795,b=510 
        gl.glVertex2f(l1 + 0.28f, l2 - 0.05f);
        gl.glVertex2f(l1 + 0.28f, l2 - 0.85f);
        gl.glEnd();
    }
    
    public void LINES(float l1, float l2, GL gl) {
        gl.glBegin(GL.GL_LINES); //BOGIES 
        gl.glColor3f(1.0f, 0.0f, 0.0f);//For right train a=795,b=510 
        gl.glVertex2f(l1, l2 - 0.5f);
        gl.glVertex2f(l1 + 1f, l2 - 0.5f);
        gl.glEnd();
    }
    
    public void SKY(GL gl)
    {
       gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.0f, 1.0f, 1.0f);
        gl.glVertex3f(-10f,0f, -6f);
        gl.glColor3f(0.2f, 0.522f, 1.0f);
        gl.glVertex3f(10f,0f, -6f);
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glVertex3f(10f,20f, -6f);
        gl.glColor3f(0.8f, 0.902f, 1.0f);
        gl.glVertex3f(-10f,20f, -6f);
        gl.glEnd();
    }
    
    public void EARTH(GL gl)
    {
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.0f, 0.6f, 0.2f);
        gl.glVertex3f(-10f,-0.09f, -5f);
        gl.glVertex3f(10f,-0.09f, -5f);
        gl.glVertex3f(10f,-20f, -5f);
        gl.glVertex3f(-10f,-20f, -5f);
        gl.glEnd();
    }
    public void CLOUDS1(GL gl)
    {   
        circle(-0.2f , 0f , 0.3f, gl);
        circle(-1.0f , 0f , 0.3f, gl);
        circle(-0.5f , 0.8f , 0.3f, gl);
        circle(-1f , 0.9f , 0.3f, gl);
        circle(-1.5f , 0.2f , 0.3f, gl);
    }
    public void CLOUDS2(GL gl)
    {
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        circle(0.2f , 0f , 0.3f  , gl );
        circle(1.0f , 0f , 0.3f ,  gl);
        circle(0.5f , 0.8f , 0.3f, gl);
        circle(1f , 0.9f , 0.3f ,gl);
        circle(1.5f , 0.2f , 0.3f ,gl);
    }
    public void CLOUDS3(GL gl)
    {   
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        circle(1.7f , 0f , 0.3f,gl );
        circle(2.6f , 0f , 0.3f,gl );
        circle(2.1f , 0.8f , 0.3f,gl);
        circle(2.6f , 0.9f , 0.3f,gl);
        circle(3.1f , 0.2f , 0.3f,gl);
    }
    
    public void CLOUDS4(GL gl)
    {   
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        circle(  1.3f , 0f , 0.3f,gl  );
        circle(2.3f , 0f , 0.3f,gl);
        circle(1.9f , 0.8f , 0.3f,gl);
        circle(2.3f , 0.9f , 0.3f,gl);
        circle(2.9f , 0.2f , 0.3f,gl);
    }
    
    public void SUN(GL gl)
    {
        gl.glColor3f(1.0f, 1.0f, 0.0f);
        gl.glBegin(GL.GL_POLYGON);
         for(int i=1;i<=20;i++)
        {
            float X=(float)(((-2)+2*0.5*Math.cos((i))));
            float Y=(float)(((0)+2*0.5*Math.sin((i))));
            gl.glVertex3f(X, Y, 0.0f);
        }
        gl.glEnd();
        gl.glColor3f(1.0f, 0.678f, 0.2f);
        circle(-2f , 0f , 0.35f ,gl);
    }
    
    public void RAILWAY1(GL gl)
    {
         gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.0f, 0.0f, 0.0f);
        gl.glVertex3f(-30f,-0.03f, 0.0f);
        gl.glVertex3f(30f,-0.03f, 0.0f);
        gl.glVertex3f(30f,0.01f, 0.0f);
        gl.glVertex3f(-30f,0.01f, 0.0f);
        gl.glEnd();
    }
    
    public void RAILWAY2(GL gl)
    {
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.0f, 0.0f, 0.0f);
        gl.glVertex3f(-30f,-0.04f, 0.0f);
        gl.glVertex3f(30f,-0.04f, 0.0f);
        gl.glVertex3f(30f,0.03f, 0.0f);
        gl.glVertex3f(-30f,0.03f, 0.0f);
        gl.glEnd(); 
    }
    
    public void RAILWAYL(float y,GL gl)
    {
        for(int i=-30;i<=30;i++)
        {
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.0f, 0.0f, 0.0f);
        gl.glVertex2f(i,y);
        gl.glVertex2f(i+0.1f,y);
        gl.glVertex2f(i+0.1f,y-1f);
        gl.glVertex2f(i,y-1f);
        gl.glEnd();
        }
    }
    
    public void STREET(GL gl)
    {
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.0f, 0.0f, 0.0f);
        gl.glVertex3f(-30f,3f, 0.0f);
        gl.glVertex3f(30f,3f, 0.0f);
        gl.glVertex3f(30f,2f, 0.0f);
        gl.glVertex3f(-30f,2f, 0.0f);
        gl.glEnd();
    }
    
    public void STREETL(float y,GL gl)
    {
        for(int i=-30;i<=30;i++)
        {
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glVertex2f(i,y);
        gl.glVertex2f(i+0.8f,y);
        gl.glVertex2f(i+0.8f,y+0.2f);
        gl.glVertex2f(i,y+0.2f);
        gl.glEnd();
        }
    }
    
    public void CAR(float x, float y, GL gl)
    {
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.8f, 0.0f, 0.0f);
        gl.glVertex2f(x, y); 
        gl.glVertex2f(x+ 1, y);
        gl.glVertex2f(x + 1, y +0.5f);
        gl.glVertex2f(x, y +0.5f);
        gl.glEnd();
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
        
        gl.glTranslatef(0.0f,0.0f,0.0f);//Sky an Earth
         gl.glPushMatrix();
        {
            gl.glTranslatef(0.0f,1.0f,-6.0f);
            SKY(gl);
            EARTH(gl);
        }
        gl.glPopMatrix();
        
        gl.glPushMatrix();//Street
        {
            gl.glTranslatef(-4.0f,-4f,-6.0f);
            STREET(gl);
        }
        gl.glPopMatrix();
        
        gl.glPushMatrix();//Street Lines
        {
            gl.glTranslatef(-2.0f,-4.5f,-15.0f);
            STREETL(0.75f,gl);
        }
        
        gl.glPushMatrix();//Car
        {
         gl.glTranslatef(transC,3.15f,7.0f);
            gl.glColor3f(0.651f, 0.651f, 0.651f);
            circle(-2,-1,0.05f,gl);
            circle(-2.5f,-1,0.05f,gl);
            gl.glColor3f(1.0f, 0.8f, 0.0f);
            circle(-1.75f,-0.85f,0.05f,gl);
            gl.glColor3f(0.8f, 0.0f, 0.0f);
            circle(-2.25f,-0.5f,0.15f,gl);
            CAR(-2.75f, -1,gl);
            gl.glColor3f(0.0f, 0.8f, 1.0f);
            circle(-2.125f,-0.45f,0.05f,gl);
            circle(-2.375f,-0.45f,0.05f,gl);
        }
        gl.glPopMatrix();
                
         gl.glPushMatrix();//Sun
        {   
            gl.glTranslatef(2f, 4f, -14f);
            gl.glTranslatef(-8,transU5, 0f);
        
            SUN(gl);
        }
        gl.glPopMatrix();
        
        gl.glPushMatrix();//Clouds
         {
             gl.glTranslatef(transU1, 4f, -14f);//Cloud 2
             CLOUDS2(gl);
             gl.glTranslatef(transU1, 0f, 0f);//Cloud 1
             CLOUDS1(gl);
             gl.glTranslatef(transU1, -2f, 0f);//Cloud 3
             CLOUDS3(gl);
             gl.glTranslatef(transU1, 0f, 0f);//Cloud 4
             CLOUDS4(gl);
        }
        gl.glPopMatrix();
        
        gl.glPushMatrix();//Railway
        {   
            gl.glTranslatef(4.0f,0.0f,-20.0f);//Railway 1
            RAILWAY1(gl);
            gl.glTranslatef(-4.0f,-1f,0.0f);//Railway 2
            RAILWAY2(gl);
        }
        gl.glPopMatrix();
        
        gl.glPushMatrix();
        {  
            gl.glTranslatef(4.0f,0.0f,-20.0f);//Railway Lines 
            RAILWAYL(0.03f,gl);
        }
        gl.glPopMatrix();
        
        gl.glPushMatrix();//Train
        {
            gl.glTranslatef(transT,-1.5f,-20.0f);
            gl.glTranslatef(transT,0.0f,0.0f);
            //gl.glTranslatef(transT,-1.5f,-20.0f);
            //gl.glTranslatef(transT,0.0f,0.0f);
            gl.glColor3f(0.0f,0.0f,0.0f);//Wheels Color
            circle(7.1f,1.19f,0.18f,gl);//Engine Wheels
            circle(11.1f,1.19f,0.18f,gl);
            //gl.glTranslatef(0.0f,-1.5f,-20.0f);
            //gl.glTranslatef(transT,0.0f,0.0f);
            ENGINE(6, 2, gl);//Engine Bogy
            LINES(12, 2.2f, gl);//Lines Between Engine Bogy and First Bogy
            //LINES(12, 2f, gl);
            LINES(12, 1.8f, gl);
            WINDOWS(7.1f, 1.85f, gl);//First Window
            WINDOWS(7.9f, 1.85f, gl);//Second Window
            WINDOWS(8.7f, 1.85f, gl);//Third Window
            DOOR(9.5f, 1.95f, gl);//First Door
            DOORL(9.5f, 1.96f, gl);//First Door Line 
            WINDOWS(10.3f, 1.85f, gl);//Forth Window
            WINDOWS(11.1f, 1.85f, gl);//Fifth Window
            gl.glColor3f(0.0f,0.0f,0.0f);//Wheels Color
            circle(14.1f,1.19f,0.18f,gl);//First Bogy Wheels
            circle(17.1f,1.19f,0.18f,gl);
            BOGIES(13, 2, gl);//First Bogy
            LINES(18, 2.2f, gl);//Lines Between First Bogy and Second Bogy
            //LINES(18.02f, 2f, gl);
            LINES(18, 1.8f, gl);
            //The Windows and the Doors as the First Ones with Diffirences in X and Y Axis
            WINDOWS(13.3f, 1.85f, gl);
            WINDOWS(14.1f, 1.85f, gl);
            WINDOWS(14.9f, 1.85f, gl);
            DOOR(15.7f, 1.95f, gl);
            DOORL(15.7f, 1.96f, gl);
            WINDOWS(16.5f, 1.85f, gl);
            WINDOWS(17.3f, 1.85f, gl);
            gl.glColor3f(0.0f,0.0f,0.0f);//Wheels Color
            circle(20.1f,1.19f,0.18f,gl);//Second Bogy Wheels
            circle(23.1f,1.19f,0.18f,gl);
            BOGIES(19, 2, gl);//Second Bogy
            LINES(24.02f, 2.2f, gl);//Lines Between Second Bogy and Third Bogy
            //LINES(24.02f, 2f, gl);
            LINES(24, 1.8f, gl);
            //The Windows and the Doors as the First Ones with Diffirences in X and Y Axis
            WINDOWS(19.3f, 1.85f, gl);
            WINDOWS(20.1f, 1.85f, gl);
            WINDOWS(20.9f, 1.85f, gl);
            DOOR(21.7f, 1.95f, gl);
            DOORL(21.7f, 1.96f, gl);
            WINDOWS(22.5f, 1.85f, gl);
            WINDOWS(23.3f, 1.85f, gl);
            gl.glColor3f(0.0f,0.0f,0.0f);//Wheels Color
            circle(26.1f,1.19f,0.18f,gl);//Third Bogy Wheels
            circle(29.1f,1.19f,0.18f,gl);
            BOGIES(25, 2, gl);//Tird Bogy
            //The Windows and the Doors as the First Ones with Diffirences in X and Y Axis
            WINDOWS(25.3f, 1.85f, gl);
            WINDOWS(26.1f, 1.85f, gl);
            WINDOWS(26.9f, 1.85f, gl);
            DOOR(27.7f, 1.95f, gl);
            DOORL(27.7f, 1.96f, gl);
            WINDOWS(28.5f, 1.85f, gl);
            WINDOWS(29.3f, 1.85f, gl);
        }
        gl.glPopMatrix();

        
        //gl.glTranslatef(0.0f,0.0f,0.0f);
        
        // Flush all drawing operations to the graphics card
        gl.glFlush();
        
        //transT+=0.03;
        if(transU5>1.00)//Sun Movement
            transU5--;
        else
            transU5+=0.001f;
        
        if(transU1>10.0)//Clouds Movement
            transU1+=-19.0f;
        else
            transU1+=0.009f;
        
        if(transC>10.0)//Car Movement
            transC+=-20.0f;
        else
            transC+=0.02f;
        //Train Movement
        if(transT>30.0)
            transT+=-60.0f;
        else
            transT+=-0.096f;
       
        /*gl.glEnable(GL.GL_LIGHTING);//Lightning(?TRY ME?)
        gl.glEnable(GL.GL_LIGHT0);
        gl.glEnable( GL.GL_NORMALIZE ); 
        float[] lightPos = {-1.5f, 1.0f, -4.0f, 1.0f };//Light Position
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION,lightPos, 0);
        float[] ambientLight= { 0.0f, 0.0f, 0.0f, 0.0f };//Ambient Light
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, ambientLight, 0);
        float[] diffuseLight = { 0.8f, 0.8f, 0.8f, 1.0f  };//Diffuse Colour
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, diffuseLight, 0);*/
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}