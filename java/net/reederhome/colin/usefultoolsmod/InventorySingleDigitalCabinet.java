package net.reederhome.colin.usefultoolsmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventorySingleDigitalCabinet implements IInventory {

	protected TileEntityDigitalCabinet cab;
	public InventorySingleDigitalCabinet(TileEntityDigitalCabinet te) {
		cab = te;
	}
	
	@Override
	public void closeInventory() {}

	@Override
	public ItemStack decrStackSize(int arg0, int arg1) {
		return cab.decrStackSize(arg0, arg1);
	}

	@Override
	public String getInventoryName() {
		return cab.getInventoryName();
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public int getSizeInventory() {
		return cab.inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int arg0) {
		return cab.getStackInSlot(arg0);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int arg0) {
		return cab.getStackInSlotOnClosing(arg0);
	}

	@Override
	public boolean hasCustomInventoryName() {
		return cab.hasCustomInventoryName();
	}

	@Override
	public boolean isItemValidForSlot(int arg0, ItemStack arg1) {
		return true;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer arg0) {
		return true;
	}

	@Override
	public void markDirty() {
		cab.markDirty();
	}

	@Override
	public void openInventory() {}

	@Override
	public void setInventorySlotContents(int arg0, ItemStack arg1) {
		cab.setInventorySlotContents(arg0, arg1);
	}

}
