/*******************************************************************************
 * Copyright 2019 metaphore
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.crashinvaders.vfx.framebuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Disposable;
import com.crashinvaders.vfx.utils.ScreenQuadMesh;

/**
 * Simple renderer that is capable of drawing
 * {@link VfxFrameBuffer}'s texture onto the screen or into another buffer.
 * <p>
 * This is a lightweight {@link com.badlogic.gdx.graphics.g2d.SpriteBatch} replacement for the library's needs.
 */
public class VfxFrameBufferRenderer implements Disposable {

    private final ScreenQuadMesh mesh;
    private final ShaderProgram shader;

    public VfxFrameBufferRenderer() {
        mesh = new ScreenQuadMesh();

        shader = new ShaderProgram(
                "#ifdef GL_ES\n" +
                "    #define PRECISION mediump\n" +
                "    precision PRECISION float;\n" +
                "#else\n" +
                "    #define PRECISION\n" +
                "#endif\n" +
                "attribute vec4 a_position;\n" +
                "attribute vec2 a_texCoord0;\n" +
                "varying vec2 v_texCoords;\n" +
                "void main() {\n" +
                "    v_texCoords = a_texCoord0;\n" +
                "    gl_Position = a_position;\n" +
                "}",
                "#ifdef GL_ES\n" +
                "    #define PRECISION mediump\n" +
                "    precision PRECISION float;\n" +
                "#else\n" +
                "    #define PRECISION\n" +
                "#endif\n" +
                "varying vec2 v_texCoords;\n" +
                "uniform sampler2D u_texture0;\n" +
                "void main() {\n" +
                "    gl_FragColor = texture2D(u_texture0, v_texCoords);\n" +
                "}"
        );

        rebind();
    }

    @Override
    public void dispose() {
        shader.dispose();
        mesh.dispose();
    }

    public void rebind() {
        shader.begin();
        shader.setUniformi("u_texture0", 0);
        shader.end();
    }

    public void renderToScreen(VfxFrameBuffer input) {
        renderToScreen(input, 0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());
    }

    public void renderToScreen(VfxFrameBuffer input, int x, int y, int width, int height) {
        input.getFbo().getColorBufferTexture().bind(0);
        Gdx.graphics.getGL20().glViewport(x, y, width, height);

        shader.begin();
        mesh.render(shader);
        shader.end();
    }

    public void renderToFbo(VfxFrameBuffer input, VfxFrameBuffer output) {
        input.getFbo().getColorBufferTexture().bind(0);

        // Viewport will be set from VfxFrameBuffer#begin() method.

        output.begin();
        shader.begin();
        mesh.render(shader);
        shader.end();
        output.end();
    }

    public ScreenQuadMesh getMesh() {
        return mesh;
    }
}
