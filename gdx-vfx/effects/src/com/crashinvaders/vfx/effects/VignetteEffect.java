/*******************************************************************************
 * Copyright 2012 bmanuel
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

package com.crashinvaders.vfx.effects;

import com.badlogic.gdx.graphics.Texture;
import com.crashinvaders.vfx.utils.ScreenQuadMesh;
import com.crashinvaders.vfx.framebuffer.VfxFrameBuffer;
import com.crashinvaders.vfx.VfxEffect;
import com.crashinvaders.vfx.filters.VignettingFilter;

public final class VignetteEffect extends VfxEffect {
    private VignettingFilter vignetting;
    private boolean controlSaturation;

    public VignetteEffect(boolean controlSaturation) {
        this.controlSaturation = controlSaturation;
        vignetting = new VignettingFilter(controlSaturation);
    }

    @Override
    public void dispose() {
        vignetting.dispose();
    }

    @Override
    public void rebind() {
        vignetting.rebind();
    }

    @Override
    public void resize(int width, int height) {
        vignetting.resize(width, height);
    }

    @Override
    public void render(ScreenQuadMesh mesh, VfxFrameBuffer src, VfxFrameBuffer dst) {
        vignetting.setInput(src).setOutput(dst).render(mesh);
    }

    public boolean doesSaturationControl() {
        return controlSaturation;
    }

    public void setIntensity(float intensity) {
        vignetting.setIntensity(intensity);
    }

    public void setCoords(float x, float y) {
        vignetting.setCoords(x, y);
    }

    public void setSaturation(float saturation) {
        vignetting.setSaturation(saturation);
    }

    public void setSaturationMul(float saturationMul) {
        vignetting.setSaturationMul(saturationMul);
    }

    public void setLutTexture(Texture texture) {
        vignetting.setLut(texture);
    }

    public void setLutIntensity(float value) {
        vignetting.setLutIntensity(value);
    }

    public void setVignetteX(float x) {
        vignetting.setVignetteX(x);
    }

    public void setVignetteY(float vignetteY) {
        vignetting.setVignetteY(vignetteY);
    }

    public void setLut(Texture texture) {
        vignetting.setLut(texture);
    }

    public void setLutIndex1(int value) {
        vignetting.setLutIndex1(value);
    }

    public void setLutIndex2(int value) {
        vignetting.setLutIndex2(value);
    }

    public int getLutIndex1() {
        return vignetting.getLutIndex1();
    }

    public int getLutIndex2() {
        return vignetting.getLutIndex2();
    }

    public float getVignetteX() {
        return vignetting.getVignetteX();
    }

    public float getVignetteY() {
        return vignetting.getVignetteY();
    }

    public void setLutIndexOffset(float value) {
        vignetting.setLutIndexOffset(value);
    }

    /**
     * Specify the center, in normalized screen coordinates.
     */
    public void setCenter(float x, float y) {
        vignetting.setCenter(x, y);
    }

    public float getIntensity() {
        return vignetting.getIntensity();
    }

    public float getLutIntensity() {
        return vignetting.getLutIntensity();
    }

    public Texture getLut() {
        return vignetting.getLut();
    }

    public float getCenterX() {
        return vignetting.getCenterX();
    }

    public float getCenterY() {
        return vignetting.getCenterY();
    }

    public float getSaturation() {
        return vignetting.getSaturation();
    }

    public float getSaturationMul() {
        return vignetting.getSaturationMul();
    }

    public boolean isGradientMappingEnabled() {
        return vignetting.isGradientMappingEnabled();
    }
}
