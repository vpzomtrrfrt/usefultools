package net.reederhome.colin.usefultoolsmod;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCobblegen extends Block implements ITileEntityProvider {
	
	protected BlockCobblegen() {
		super(Material.rock);
	}

	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		player.inventory.addItemStackToInventory(new ItemStack(Blocks.cobblestone,1));
	}
	
	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		return new TileEntityCobblegen();
	}
	
}
