package net.reederhome.colin.usefultoolsmod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.reederhome.colin.usefultoolsmod.UsefulToolsMod;


public class TileEntityRemoteInventory extends TileEntity implements IInventory {

	int rx, ry, rz;
	public void updateEntity() {
		super.updateEntity();
		getRemoteTileEntity();
	}
	private IInventory getRemoteTileEntity() {
		if(rx!=0||ry!=0||rz!=0) {
			TileEntity thing = worldObj.getTileEntity(rx, ry, rz);
			if(!(thing instanceof IInventory)) {
				worldObj.setBlockToAir(xCoord, yCoord, zCoord);
				UsefulToolsMod.remoteInventory.dropBlockAsItem(worldObj, rx, ry, rz, blockMetadata, 0);
			}
			else {
				return (IInventory) thing;
			}
		}
		return new IInventory() {

			@Override
			public void closeInventory() {}

			@Override
			public ItemStack decrStackSize(int arg0, int arg1) {return null;}

			@Override
			public String getInventoryName() {return null;}

			@Override
			public int getInventoryStackLimit() {return 0;}

			@Override
			public int getSizeInventory() {return 0;}

			@Override
			public ItemStack getStackInSlot(int arg0) {return null;}

			@Override
			public ItemStack getStackInSlotOnClosing(int arg0) {return null;}

			@Override
			public boolean hasCustomInventoryName() {return false;}

			@Override
			public boolean isItemValidForSlot(int arg0, ItemStack arg1) {return false;}

			@Override
			public boolean isUseableByPlayer(EntityPlayer arg0) {return false;}

			@Override
			public void markDirty() {}

			@Override
			public void openInventory() {}

			@Override
			public void setInventorySlotContents(int arg0, ItemStack arg1) {}
			
		};
	}
	@Override
	public void closeInventory() {
		getRemoteTileEntity().closeInventory();
	}
	@Override
	public ItemStack decrStackSize(int arg0, int arg1) {
		return getRemoteTileEntity().decrStackSize(arg0, arg1);
	}
	@Override
	public String getInventoryName() {
		return getRemoteTileEntity().getInventoryName();
	}
	@Override
	public int getInventoryStackLimit() {
		return getRemoteTileEntity().getInventoryStackLimit();
	}
	@Override
	public int getSizeInventory() {
		return getRemoteTileEntity().getSizeInventory();
	}
	@Override
	public ItemStack getStackInSlot(int arg0) {
		return getRemoteTileEntity().getStackInSlot(arg0);
	}
	@Override
	public ItemStack getStackInSlotOnClosing(int arg0) {
		return getRemoteTileEntity().getStackInSlotOnClosing(arg0);
	}
	@Override
	public boolean hasCustomInventoryName() {
		return getRemoteTileEntity().hasCustomInventoryName();
	}
	@Override
	public boolean isItemValidForSlot(int arg0, ItemStack arg1) {
		return getRemoteTileEntity().isItemValidForSlot(arg0, arg1);
	}
	@Override
	public boolean isUseableByPlayer(EntityPlayer arg0) {
		return getRemoteTileEntity().isUseableByPlayer(arg0);
	}
	@Override
	public void openInventory() {
		getRemoteTileEntity().openInventory();
	}
	@Override
	public void setInventorySlotContents(int arg0, ItemStack arg1) {
		getRemoteTileEntity().setInventorySlotContents(arg0, arg1);
	}
	
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		int[] thing = tag.getIntArray("Remote");
		rx=thing[0];
		ry=thing[1];
		rz=thing[2];
	}
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setIntArray("Remote", new int[]{rx,ry,rz});
	}
}
