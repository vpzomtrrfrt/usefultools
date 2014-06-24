package net.reederhome.colin.usefultoolsmod;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class BlockAsteriskChest extends BlockContainer {

	public BlockAsteriskChest() {
		super(Material.wood);
		setBlockTextureName(UsefulToolsMod.MODID+":asteriskChest");
		setBlockName("asteriskChest");
	}
	
	public Item getItemDropped(int metadata, Random random, int fortune) {
		return Item.getItemFromBlock(this);
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		TileEntityAsteriskChest te = (TileEntityAsteriskChest) world.getTileEntity(x, y, z);
		ItemStack held = player.getHeldItem();
		if(held==null) {
			if(!world.isRemote) {
				if(te.thing!=null) {
					player.addChatMessage(new ChatComponentText(I18n.format(te.thing.getUnlocalizedName()+".name", new Object[]{})));
				}
				else {
					player.addChatMessage(new ChatComponentText("Click with an item to setup"));
				}
			}
		}
		else {
			if(!world.isRemote) {
				te.thing=held.getItem();
				te.meta=held.getItemDamage();
				if(te.content!=null) {
					EntityItem plop = new EntityItem(world);
					plop.setEntityItemStack(te.content);
					plop.setLocationAndAngles(x, y, z, 0, 0);
					te.content=null;
					world.spawnEntityInWorld(plop);
				}
				player.addChatMessage(new ChatComponentText("Item set to "+I18n.format(te.thing.getUnlocalizedName()+".name", new Object[]{})));
			}
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		return new TileEntityAsteriskChest();
	}
}
