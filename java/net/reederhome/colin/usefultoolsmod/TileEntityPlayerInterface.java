package net.reederhome.colin.usefultoolsmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPlayerInterface extends TileEntity implements IInventory {

	String name = null;
	
	private IInventory getInv() {
		if(name!=null) {
			EntityPlayer p = worldObj.getPlayerEntityByName(name);
			if(p!=null) {
				return p.inventory;
			}
		}
		return new IInventory() {
			public void closeInventory() {}
			public ItemStack decrStackSize(int arg0, int arg1) {return null;}
			public String getInventoryName() {return null;}
			public int getInventoryStackLimit() {return 0;}
			public int getSizeInventory() {return 0;}
			public ItemStack getStackInSlot(int arg0) {return null;}
			public ItemStack getStackInSlotOnClosing(int arg0) {return null;}
			public boolean hasCustomInventoryName() {return false;}
			public boolean isItemValidForSlot(int arg0, ItemStack arg1) {return false;}
			public boolean isUseableByPlayer(EntityPlayer arg0) {return false;}
			public void markDirty() {}
			public void openInventory() {}
			public void setInventorySlotContents(int arg0, ItemStack arg1) {}
			
		};
	}
	
	@Override
	public void closeInventory() {
		getInv().closeInventory();
	}

	@Override
	public ItemStack decrStackSize(int arg0, int arg1) {
		return getInv().decrStackSize(arg0, arg1);
	}

	@Override
	public String getInventoryName() {
		return getInv().getInventoryName();
	}

	@Override
	public int getInventoryStackLimit() {
		return getInv().getInventoryStackLimit();
	}

	@Override
	public int getSizeInventory() {
		return getInv().getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int arg0) {
		return getInv().getStackInSlot(arg0);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int arg0) {
		return getInv().getStackInSlotOnClosing(arg0);
	}

	@Override
	public boolean hasCustomInventoryName() {
		return getInv().hasCustomInventoryName();
	}

	@Override
	public boolean isItemValidForSlot(int arg0, ItemStack arg1) {
		return getInv().isItemValidForSlot(arg0, arg1);
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer arg0) {
		return getInv().isUseableByPlayer(arg0);
	}

	@Override
	public void openInventory() {
		getInv().openInventory();
	}

	@Override
	public void setInventorySlotContents(int arg0, ItemStack arg1) {
		getInv().setInventorySlotContents(arg0, arg1);
	}

	public void writeToNBT(NBTTagCompound tag) {
		tag.setString("Owner", name);
	}
	
	public void readFromNBT(NBTTagCompound tag) {
		name = tag.getString("Owner");
	}
}
