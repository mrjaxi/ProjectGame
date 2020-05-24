package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Main;

import static com.mygdx.game.Main.V_HEIGHT;
import static com.mygdx.game.Main.V_WIDTH;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = Main.TITLE + " v." + Main.VERSION;
		config.width = V_WIDTH;
		config.height = V_HEIGHT;
		config.backgroundFPS = 60;
		config.foregroundFPS = 60;
		new LwjglApplication(new Main(), config);
	}
}
