package com.github.goldengamer2020.drop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(120);
		config.setWindowedMode(1000, 480);
		config.setTitle("Not Flappy Bird");
		config.useVsync(true);
		config.setResizable(false);
		new Lwjgl3Application(new FlappyBird(), config);
	}
}
