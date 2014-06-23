package net.reederhome.colin.usefultoolsmod;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
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

	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		return new TileEntityDigitalCabinet();
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		player.displayGUIChest(new InventorySingleDigitalCabinet((TileEntityDigitalCabinet) world.getTileEntity(x, y, z)));
		return true;
	}

}
