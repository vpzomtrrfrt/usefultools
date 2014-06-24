package net.reederhome.colin.usefultoolsmod;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Facing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.mojang.authlib.GameProfile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAutoClicker extends Block {

	public BlockAutoClicker() {
		super(Material.rock);
		setBlockName("autoClicker");
		setCreativeTab(CreativeTabs.tabRedstone);
	}
	public Item getItemDropped(int metadata, Random random, int fortune) {
		return Item.getItemFromBlock(this);
	}
	public void onBlockPlacedBy(World world, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
		int var7 = BlockPistonBase.determineOrientation(world, p_149689_2_, p_149689_3_, p_149689_4_, p_149689_5_);
		world.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, var7, 3);
	}
	public IIcon getIcon(int side, int meta) {
		if(side==meta%Facing.facings.length) {
			return blockIcon;
		}
		else {
			return Blocks.furnace.getIcon(0, 0);
		}
	}
	public void registerBlockIcons(IIconRegister ir) {
		blockIcon = ir.registerIcon(UsefulToolsMod.MODID+":autoClicker");
	}
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		super.onNeighborBlockChange(world, x, y, z, block);
		int meta = world.getBlockMetadata(x, y, z);
		int side=meta%Facing.facings.length;
		int totalPower = 0;
		for(int i = 0; i < Facing.facings.length; i++) {
			if(side!=i) {
				boolean np = world.getIndirectPowerOutput(x+Facing.offsetsXForSide[i], y+Facing.offsetsYForSide[i], z+Facing.offsetsZForSide[i], i);
				totalPower+=np?1:0;
			}
		}
		if(totalPower>0) {
			if(meta<Facing.facings.length) {
				world.setBlockMetadataWithNotify(x, y, z, meta+Facing.facings.length, 3);
				AutoClickerPlayer pl = new AutoClickerPlayer(world, new ChunkCoordinates(x,y,z));
				int cx = x+Facing.offsetsXForSide[side];
				int cy = y+Facing.offsetsYForSide[side];
				int cz = z+Facing.offsetsZForSide[side];
				world.getBlock(cx, cy, cz).onBlockActivated(world, cx, cy, cz, pl, 0, (float)pl.posX, (float)pl.posY, (float)pl.posZ);
				TileEntity te = world.getTileEntity(cx, cy, cz);
				if(te != null) {
					if(te instanceof IInventory) {
						((IInventory) te).openInventory();
					}
				}
			}
		}
		else {
			world.setBlockMetadataWithNotify(x, y, z, meta%Facing.facings.length, 3);
		}
	}
	
	public static class AutoClickerPlayer extends EntityPlayer {

		ChunkCoordinates coords;
		private AutoClickerPlayer(World p_i45324_1_, ChunkCoordinates cc) {
			super(p_i45324_1_, new GameProfile("FAKEPLAYER_NAME", "FAKEPLAYER_NAME"));
			coords=cc;
		}

		@Override
		public void addChatMessage(IChatComponent arg0) {}

		@Override
		public boolean canCommandSenderUseCommand(int arg0, String arg1) {return true;}

		@Override
		public ChunkCoordinates getPlayerCoordinates() {
			return coords;
		}
		
	}

}
