package net.reederhome.colin.usefultoolsmod;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


public class BlockRemoteInventory extends Block implements ITileEntityProvider {

	public BlockRemoteInventory() {
		super(Material.wood);
	}
	
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return UsefulToolsMod.itemRemoteInventory;
	}
	
	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		return new TileEntityRemoteInventory();
	}

}