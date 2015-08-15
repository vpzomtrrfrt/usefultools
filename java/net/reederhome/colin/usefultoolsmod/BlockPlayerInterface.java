package net.reederhome.colin.usefultoolsmod;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPlayerInterface extends BlockContainer {

	public BlockPlayerInterface() {
		super(Material.rock);
		setHardness(2);
		setCreativeTab(UsefulToolsMod.tab);
		setBlockName("playerInterface");
		setBlockTextureName(UsefulToolsMod.MODID+":playerInterface");
	}

	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		return new TileEntityPlayerInterface();
	}
	
	public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase p, ItemStack p_149689_6_) {
		TileEntityPlayerInterface te = (TileEntityPlayerInterface) w.getTileEntity(x, y, z);
		te.name = p.getCommandSenderName();
	}

	
}
