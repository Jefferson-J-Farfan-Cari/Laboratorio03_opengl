package com.idnp.openglsample;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;

public class GLClearRenderer implements Renderer {

    private Cube mCube = new Cube();
    private Piramide mPiramide = new Piramide();
    private Prisma mPrisma = new Prisma();
    private float mCubeRotation;
    private float mPiramideRotation;
    private float mPrisma2;

    public void onDrawFrame( GL10 gl ) {
        // This method is called per frame, as the name suggests.
        // For demonstration purposes, I simply clear the screen with a random translucent gray.
        //float c = 1.0f / 256 * ( System.currentTimeMillis() % 256 );
        //gl.glClearColor( c, c, c, 0.5f );
        //gl.glClear( GL10.GL_COLOR_BUFFER_BIT );
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        gl.glTranslatef(0.0f, 0.0f, -10.0f);
        gl.glRotatef(mCubeRotation, 1.0f, 1.0f, 1.0f);

        mCube.draw(gl);
        mCubeRotation -= 0.15f;

        gl.glLoadIdentity();

        gl.glTranslatef(3.0f, 0.0f, -10.0f);
        gl.glRotatef(mPiramideRotation, 2.0f, 2.0f, 2.0f);

        mPiramide.draw(gl);
        mPiramideRotation -= 0.15f ;


        gl.glLoadIdentity();

        gl.glTranslatef(-3.0f, 0.0f, -10.0f);
        gl.glRotatef(mPrisma2, 2.0f, 2.0f, 2.0f);

        mPrisma.draw(gl);
        mPrisma2 -= 0.15f ;
    }

    public void onSurfaceChanged( GL10 gl, int width, int height ) {
        // This is called whenever the dimensions of the surface have changed.
        // We need to adapt this change for the GL viewport.
        gl.glViewport( 0, 0, width, height );
        //gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 45.0f, (float)width / (float)height, 0.1f, 100.0f);
        gl.glViewport(0, 0, width, height);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void onSurfaceCreated( GL10 gl, EGLConfig config ) {
        // No need to do anything here.
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);

        gl.glClearDepthf(1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);

        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,
                GL10.GL_NICEST);
    }
}