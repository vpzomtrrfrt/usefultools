package net.reederhome.colin.usefultoolsmod.client;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;

public class UsefulToolsClient {

	public static KeyBinding flightKey = new KeyBinding("key.flight", Keyboard.KEY_F4, "key.categories.movement");
	
	public static void registerClientThings() {
		ClientRegistry.registerKeyBinding(flightKey);
	}
}
