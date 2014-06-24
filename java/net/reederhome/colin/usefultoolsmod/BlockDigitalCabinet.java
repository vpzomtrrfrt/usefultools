package net.reederhome.colin.usefultoolsmod;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockDigitalCabinet extends BlockContainer {

	public BlockDigitalCabinet() {
		super(Material.wood);
		setBlockName("digitalCabinet");
		setBlockTextureName(UsefulToolsMod.MODID+":digitalCabinet");
		setHardness(12);
		setHarvestLevel("axe", 0, 1);
	}
	
	public Item getItemDropped(int metadata, Random random, int fortune) {
		return Item.getItemFromBlock(this);
	}
	
	public void breakBlock(World world, int x, int y, int z, Block p_149749_5_, int p_149749_6_) {
		TileEntityDigitalCabinet te = (TileEntityDigitalCabinet) world.getTileEntity(x, y, z);
		for(int i = 0; i < te.inv.length; i++) {
			if(te.inv[i]!=null) {
				EntityItem it = new EntityItem(world);
				it.setEntityItemStack(te.inv[i]);
				it.setLocationAndAngles(x, y, z, 0, 0);
				world.spawnEntityInWorld(it);
			}
		}
		super.breakBlock(world, x, y, z, p_149749_5_, p_149749_6_);
	}

	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		return new TileEntityDigitalCabinet();
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		player.displayGUIChest(new InventorySingleDigitalCabinet((TileEntityDigitalCabinet) world.getTileEntity(x, y, z)));
		return true;
	}

}
