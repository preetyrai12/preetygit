/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
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

package com.badlogic.gdx.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.tests.utils.GdxTest;
import com.badlogic.gdx.utils.ScreenUtils;

public class PixmapBlendingTest extends GdxTest {
	private SpriteBatch spriteBatch;
	private Sprite logoSprite;
	private Pixmap pixD, pixS1, pixS2;

	@Override
	public void create () {
		if (spriteBatch != null) return;
		spriteBatch = new SpriteBatch();

		Matrix4 transform = new Matrix4();
		transform.setToTranslation(0, Gdx.graphics.getHeight(), 0);
		transform.mul(new Matrix4().setToScaling(1, -1, 1));
		spriteBatch.setTransformMatrix(transform);

		pixS1 = new Pixmap(Gdx.files.internal("data/test4.png"));
		pixS2 = new Pixmap(Gdx.files.internal("data/test3.png"));
		pixD = new Pixmap(512, 1024, Pixmap.Format.RGBA8888);

		pixD.setBlending(Pixmap.Blending.SourceOver);
		pixD.setFilter(Pixmap.Filter.NearestNeighbour);

		pixD.drawPixmap(pixS1, 0, 0, 38, 76, 0, 0, 512, 1024);
		pixD.drawPixmap(pixS2, 0, 0, 38, 76, 0, 0, 512, 1024);

		logoSprite = new Sprite(new Texture(pixD));
		logoSprite.flip(false, true);

		pixS1.dispose();
		pixS2.dispose();
		pixD.dispose();
	}

	@Override
	public void render () {

		ScreenUtils.clear(0, 1, 0, 1);

		spriteBatch.begin();
		logoSprite.setSize(256, 256);
		logoSprite.draw(spriteBatch);
		spriteBatch.end();

	}

	public boolean needsGL20 () {
		return false;
	}
}
