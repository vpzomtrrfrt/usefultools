package net.reederhome.colin.usefultoolsmod;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.reederhome.colin.usefultoolsmod.client.UsefulToolsClient;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid=UsefulToolsMod.MODID,version=UsefulToolsMod.VERSION,name="Useful Tools")
public class UsefulToolsMod {

	public static final String MODID = "usefultools";
	public static final String VERSION = "0.1";
	
	static Block autoClicker = new BlockAutoClicker();
	static Block obsidiPlate = new BlockObsidiPlate();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent ev) {
		GameRegistry.registerBlock(autoClicker, "autoClicker");
		GameRegistry.registerBlock(obsidiPlate, "obsidiPlate");
		MinecraftForge.EVENT_BUS.register(this);
		
		GameRegistry.addRecipe(new ItemStack(autoClicker), "www", "wrw", "rpr", 'w', Blocks.planks, 'r', Blocks.cobblestone, 'p', Items.redstone);
		GameRegistry.addRecipe(new ItemStack(obsidiPlate), "oo", 'o', Blocks.obsidian);
	}
	
	private int registerEntity(Class cl, String name) {
		int teid = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(cl, name, teid);
		EntityRegistry.registerModEntity(cl, name, teid, MODID, 128, 1, true);
		return teid;
	}
	
	@EventHandler
	@SideOnly(Side.CLIENT)
	public void clientInit(FMLInitializationEvent ev) {
		UsefulToolsClient.registerRenderers();
	}
}
