package net.reederhome.colin.usefultoolsmod;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


public class BlockRemoteInventory extends Block implements ITileEntityProvider {

	public BlockRemoteInventory() {
		super(Material.wood);
		setBlockName("blockRemoteInventory");
		setBlockTextureName(UsefulToolsMod.MODID+":remoteInventory");
	}
	
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return UsefulToolsMod.itemRemoteInventory;
	}
	
	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		return new TileEntityRemoteInventory();
	}
	
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		TileEntityRemoteInventory te = (TileEntityRemoteInventory) w.getTileEntity(x, y, z);
		te.getRemoteTileEntity();
		Block block = w.getBlock(te.rx, te.ry, te.rz);
		return block.onBlockActivated(w, te.rx, te.ry, te.rz, player, par6, par7, par8, par9);
	}

}