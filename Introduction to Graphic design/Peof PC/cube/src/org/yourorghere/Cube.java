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
 * Cube.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Cube implements GLEventListener {
    private float L=1.0f;//cube length 
    private float x=0.0f;
    private float y=0.0f;
    private float z=0.0f;
    private float ra;

    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Cube());
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
        
        // Move the "drawing cursor" around
        gl.glTranslatef(0.0f, 0.0f, -6.0f);
        gl.glRotatef(ra, 0.0f, 1.0f, 0.0f);
               
        // Draw A Quad Face 2
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.f, 1.0f, 0.0f);    // Set the current drawing color to light blue
            gl.glVertex3f(x,y,z-L);  // Top Left
            gl.glVertex3f(x,y-L,z-L);   // Top Right
            gl.glVertex3f(x+L,y-L,z-L);  // Bottom Right
            gl.glVertex3f(x+L,y,z-L); // Bottom Left
        // Done Drawing The Quad
        gl.glEnd();
        
        // Draw A Quad Face 3
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.f, 0.0f, 1.0f);    // Set the current drawing color to light blue
            gl.glVertex3f(x+L,y,z); // Bottom Left
            gl.glVertex3f(x+L,y-L,z);  // Bottom Right
           gl.glVertex3f(x+L,y-L,z-L);  // Bottom Right
            gl.glVertex3f(x+L,y,z-L); // Bottom Left
        // Done Drawing The Quad
        gl.glEnd();
        // Draw A Quad Face 1
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(1.f, 0.0f, 0.0f);    // Set the current drawing color to light blue
            gl.glVertex3f(x,y,z);  // Top Left
            gl.glVertex3f(x,y-L,z);   // Top Right
            gl.glVertex3f(x+L,y-L,z);  // Bottom Right
            gl.glVertex3f(x+L,y,z); // Bottom Left
        // Done Drawing The Quad
        gl.glEnd();

        // Flush all drawing operations to the graphics card
        gl.glFlush();
        ra+=.2f;
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

