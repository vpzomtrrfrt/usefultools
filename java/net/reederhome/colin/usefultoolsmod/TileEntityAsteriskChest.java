package net.reederhome.colin.usefultoolsmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityAsteriskChest extends TileEntity implements IInventory {

	Item thing;
	int meta;
	ItemStack content;
	@Override
	public void closeInventory() {}
	@Override
	public ItemStack decrStackSize(int arg0, int arg1) {
		if(arg0==0) {
			if(content!=null) {
				ItemStack tr;
				if(content.stackSize<=arg1) {
					tr = content;
					content=null;
				}
				else {
					tr = ItemStack.copyItemStack(content);
					tr.stackSize=arg1;
					content.stackSize-=arg1;
				}
				return tr;
			}
		}
		return null;
	}
	@Override
	public String getInventoryName() {
		return "Asterisk Chest";
	}
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	@Override
	public int getSizeInventory() {
		return 1;
	}
	@Override
	public ItemStack getStackInSlot(int arg0) {
		if(arg0==0) {
			return content;
		}
		return null;
	}
	@Override
	public ItemStack getStackInSlotOnClosing(int arg0) {
		return getStackInSlot(arg0);
	}
	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}
	@Override
	public boolean isItemValidForSlot(int arg0, ItemStack arg1) {
		return (arg0==0&&arg1.getItem().equals(thing)&&arg1.getItemDamage()==meta);
	}
	@Override
	public boolean isUseableByPlayer(EntityPlayer arg0) {
		return false;
	}
	@Override
	public void openInventory() {}
	@Override
	public void setInventorySlotContents(int arg0, ItemStack arg1) {
		if(arg0==0) {
			content=arg1;
		}
	}
	
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("ItemId", Item.getIdFromItem(thing));
		tag.setInteger("Meta", meta);
		if(content!=null) {
			NBTTagCompound sn = new NBTTagCompound();
			content.writeToNBT(sn);
			tag.setTag("Stack", sn);
		}
	}
	
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		thing = Item.getItemById(tag.getInteger("ItemId"));
		meta = tag.getInteger("Meta");
		if(tag.hasKey("Stack")) {
			content = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Stack"));
		}
	}
}
