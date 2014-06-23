package net.reederhome.colin.usefultoolsmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCobblegen extends TileEntity implements IInventory {

	protected Item thing = Item.getItemFromBlock(Blocks.cobblestone);
	
	@Override
	public void closeInventory() {}

	@Override
	public ItemStack decrStackSize(int arg0, int arg1) {
		return new ItemStack(thing, arg1);
	}

	@Override
	public String getInventoryName() {
		return "Cobblegen";
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
		return new ItemStack(thing, 64);
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
		return false;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer arg0) {
		return false;
	}

	@Override
	public void openInventory() {}

	@Override
	public void setInventorySlotContents(int arg0, ItemStack arg1) {}

}
