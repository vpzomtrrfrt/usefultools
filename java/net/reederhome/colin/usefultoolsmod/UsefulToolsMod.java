package net.reederhome.colin.usefultoolsmod;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.reederhome.colin.usefultoolsmod.client.UsefulToolsClient;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid=UsefulToolsMod.MODID,version=UsefulToolsMod.VERSION,name="Useful Tools")
public class UsefulToolsMod {

	public static final String MODID = "usefultools";
	public static final String VERSION = "0.6.0pre";
	
	static Block autoClicker = new BlockAutoClicker();
	static Block obsidiPlate = new BlockObsidiPlate();
	static Block glowstoneCrops = new BlockGlowstoneCrops();
	static Block remoteInventory = new BlockRemoteInventory();
	static Block asteriskChest = new BlockAsteriskChest();
	static Block cobbleGen = new BlockCobblegen().setBlockTextureName(MODID+":cobbleGen").setBlockName("cobbleGen");
	static Block digitalCabinet = new BlockDigitalCabinet();
	
	static Item itemRemoteInventory = new ItemRemoteInventory();
	static Item blockSucker = new ItemBlockSucker();
	static Item entitySucker= new ItemEntitySucker();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent ev) {
		GameRegistry.registerBlock(autoClicker, "autoClicker");
		GameRegistry.registerBlock(obsidiPlate, "obsidiPlate");
		GameRegistry.registerBlock(glowstoneCrops, "glowstoneCrops");
		GameRegistry.registerBlock(remoteInventory, "blockRemoteInventory");
		GameRegistry.registerBlock(asteriskChest, "asteriskChest");
		GameRegistry.registerBlock(cobbleGen, "cobbleGen");
		GameRegistry.registerBlock(digitalCabinet, "digitalCabinet");
		
		GameRegistry.registerItem(itemRemoteInventory, "remoteInventory");
		GameRegistry.registerItem(blockSucker, "blockSucker");
		GameRegistry.registerItem(entitySucker, "entitySucker");
		
		GameRegistry.registerTileEntity(TileEntityRemoteInventory.class, "remoteInventory");
		GameRegistry.registerTileEntity(TileEntityAsteriskChest.class, "asteriskChest");
		GameRegistry.registerTileEntity(TileEntityCobblegen.class, "cobbleGen");
		GameRegistry.registerTileEntity(TileEntityDigitalCabinet.class, "digitalCabinet");
		MinecraftForge.EVENT_BUS.register(this);
		
		//GameRegistry.addRecipe(new ItemStack(autoClicker), "www", "wrw", "rpr", 'w', Blocks.planks, 'r', Blocks.cobblestone, 'p', Items.redstone);
		GameRegistry.addRecipe(new ItemStack(obsidiPlate), "oo", 'o', Blocks.obsidian);
		GameRegistry.addRecipe(new ShapedOreRecipe(autoClicker, "www", "wrw", "rpr", 'w', "plankWood", 'r', Blocks.cobblestone, 'p', Items.redstone));
		GameRegistry.addRecipe(new ShapedOreRecipe(itemRemoteInventory, "wiw", "wew", "wiw", 'w', "plankWood", 'i', "ingotIron", 'e', Items.ender_pearl));
		GameRegistry.addRecipe(new ShapedOreRecipe(asteriskChest, "cwg", "ghg", "gwc", 'c', Blocks.cobblestone, 'w', "plankWood", 'g', Items.gold_nugget, 'h', Blocks.chest));
		GameRegistry.addRecipe(new ShapedOreRecipe(cobbleGen, "ccc", "wrl", "ccc", 'c', Blocks.cobblestone, 'w', Items.water_bucket, 'r', Items.redstone, 'l', Items.lava_bucket));
		GameRegistry.addRecipe(new ShapedOreRecipe(digitalCabinet, "www", "wcw", "www", 'w', "plankWood", 'c', Blocks.chest));
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
	
	@SubscribeEvent
	public void onInteract(PlayerInteractEvent ev) {
		ItemStack stack = ev.entityLiving.getHeldItem();
		if(stack!=null) {
			if(stack.getItem().equals(Items.glowstone_dust)&&ev.entity.worldObj.getBlock(ev.x, ev.y, ev.z).equals(Blocks.farmland)) {
				ev.entity.worldObj.setBlock(ev.x, ev.y+1, ev.z, glowstoneCrops);
				stack.stackSize--;
			}
		}
	}
	
	@SubscribeEvent
	public void onEntityInteract(EntityInteractEvent ev) {
		ItemStack stack = ev.entityLiving.getHeldItem();
		if(stack!=null) {
			if(stack.getItem().equals(entitySucker)) {
				if(ev.target!=null && !(ev.target instanceof EntityPlayer) && !stack.hasTagCompound()) {
					NBTTagCompound tag = new NBTTagCompound();
					NBTTagCompound et  = new NBTTagCompound();
					ev.target.writeToNBT(et);
					tag.setTag("Entity", et);
					tag.setInteger("EntityId", EntityList.getEntityID(ev.target));
					stack.setTagCompound(tag);
					ev.target.worldObj.removeEntity(ev.target);
					stack.getItem().addInformation(stack, ev.entityPlayer, new ArrayList(), false);
				}
			}
		}
	}
}
