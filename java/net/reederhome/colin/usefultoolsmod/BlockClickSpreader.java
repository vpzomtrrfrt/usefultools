package net.reederhome.colin.usefultoolsmod;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockClickSpreader extends Block {

	public BlockClickSpreader() {
		super(Material.rock);
		setBlockName("clickSpreader");
		setHardness(3);
		setBlockTextureName(UsefulToolsMod.MODID+":clickSpreader");
	}
	
	public IIcon getIcon(int side, int meta) {
		return side==0?UsefulToolsMod.autoClicker.getIcon(0,0):(side==1?Blocks.furnace.getIcon(1,1):blockIcon);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		int nx = x+new Random().nextInt(5)-2;
		int nz = z+new Random().nextInt(5)-2;
		int ny = y-1;
		while(y>=0) {
			Block b = world.getBlock(nx, ny, nz);
			if(b!=null && b!=Blocks.air) {
				b.onBlockActivated(world, nx, ny, nz, player, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
				break;
			}
			y--;
		}
		return true;
	}
}