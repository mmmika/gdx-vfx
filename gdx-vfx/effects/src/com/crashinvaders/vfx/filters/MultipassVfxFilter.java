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

package com.crashinvaders.vfx.filters;

import com.badlogic.gdx.utils.Disposable;
import com.crashinvaders.vfx.utils.ScreenQuadMesh;
import com.crashinvaders.vfx.framebuffer.PingPongBuffer;
import com.crashinvaders.vfx.VfxFilter;

/**
 * The base class for any multi-pass filter.
 * Usually a multi-pass filter will make use of one or more single-pass filters,
 * promoting composition over inheritance.
 */
public abstract class MultipassVfxFilter implements Disposable {

    /** @see VfxFilter#resize(int, int)  */
    public abstract void resize(int width, int height);

    /** @see VfxFilter#rebind() */
    public abstract void rebind();

    public abstract void render(ScreenQuadMesh mesh, PingPongBuffer pingPongBuffer);
}
