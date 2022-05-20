package com.idnp.openglsample;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Piramide {

    private FloatBuffer verticeBuffer;
    private FloatBuffer colorBuffer;
    private ByteBuffer indexBuffer;


    private float[] vertices = {
            -1.0f,  -1.0f,  -1.0f,    // 0. left-bottom-back
            1.0f,   -1.0f,  -1.0f,     // 1. right-bottom-back
            1.0f,   -1.0f,  1.0f,     // 2. right-bottom-front
            -1.0f,  -1.0f,  1.0f,    // 3. left-bottom-front
            0.0f,   1.0f,   0.0f      // 4. top
    };

    private float[] colores = {
            0.0f, 0.0f, 1.0f, 1.0f,  // 0. blue
            0.0f, 1.0f, 0.0f, 1.0f,  // 1. green
            1.0f, 0.0f, 1.0f, 1.0f,  // 2. violet
            1.0f, 0.5f, 0.0f, 1.0f,  // 3. orange
            1.0f, 0.0f, 0.0f, 1.0f   // 4. red
    };

    private byte[] indices = {
            2, 4, 3,   // front face
            1, 4, 2,   // right face
            0, 4, 1,   // back face
            4, 0, 3    // left face
    };

    public Piramide()
    {
        ByteBuffer buffer = ByteBuffer.allocateDirect(vertices.length*4);
        buffer.order(ByteOrder.nativeOrder());
        verticeBuffer = buffer.asFloatBuffer();
        verticeBuffer.put(vertices);
        verticeBuffer.position(0);


        ByteBuffer cbb = ByteBuffer.allocateDirect(colores.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        colorBuffer = cbb.asFloatBuffer();
        colorBuffer.put(colores);
        colorBuffer.position(0);

        indexBuffer = ByteBuffer.allocateDirect(indices.length);
        indexBuffer.put(indices);
        indexBuffer.position(0);
    }


    public void draw(GL10 gl) {
        gl.glFrontFace(GL10.GL_CCW);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, verticeBuffer);

        gl.glEnable(GL10.GL_COLOR_MATERIAL);

        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_BYTE,indexBuffer);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }

}
