package org.yourorghere;

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javafx.scene.effect.Light;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;



/**
 * DrawPolygon.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class DrawPolygon implements GLEventListener {
    private float rtri;  //for angle of rotation
    private float tr=-1.5f;
    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new DrawPolygon());
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
        gl.glTranslatef(tr, 0.0f, -6.0f);
        //triangle rotation      
      //gl.glRotatef( rtri, 0.5f, 0.5f, 0.0f );  
              
      // Drawing Using Triangles 
      gl.glBegin( GL.GL_TRIANGLES );          
      
      gl.glColor3f( 1.0f, 0.0f, 0.0f );   // Red 
      gl.glVertex3f( 0.5f,0.7f,0.0f );    // Top 
      gl.glColor3f( 0.0f,1.0f,0.0f );     // blue 
      gl.glVertex3f( -0.2f,-0.50f,0.0f ); // Bottom Left 
      gl.glColor3f( 0.0f,0.0f,1.0f );     // green 
      gl.glVertex3f( 0.5f,-0.5f,0.0f );   // Bottom Right 
      
      gl.glEnd();    
      gl.glFlush(); 
      if(tr>1)
          tr=-1.5f;
      else
        tr+=0.02f;
      rtri +=0.8f;  //assigning the angle 
      /*gl.glEnable( GL.GL_LIGHTING );  
      gl.glEnable( GL.GL_LIGHT0 );  
      gl.glEnable( GL.GL_NORMALIZE );  

      // weak RED ambient 
      float[] ambientLight = { 0.1f, 0.f, 1.f,.1f };  
      gl.glLightfv(GL.GL_LIGHT0, GL.GL_SPECULAR, ambientLight, 0);  
        */
      // multicolor diffuse 
      //float[] diffuseLight = { 1f,2f,1f,0f };  
      //gl.glLightfv( GL.GL_LIGHT0, GL.GL_DIFFUSE, diffuseLight, 0 ); 
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

