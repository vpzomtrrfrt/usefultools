package net.reederhome.colin.usefultoolsmod;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityDigitalCabinet extends TileEntity implements IInventory {

	ItemStack[] inv = new ItemStack[36];
	public List<TileEntityDigitalCabinet> getThem() {
		List<TileEntityDigitalCabinet> l = new ArrayList<TileEntityDigitalCabinet>();
		int x = this.xCoord;
		int y = this.yCoord;
		int z = this.zCoord;
		while(true) {
			TileEntity te = this.worldObj.getTileEntity(x, y, z);
			if(te instanceof TileEntityDigitalCabinet) {
				l.add((TileEntityDigitalCabinet) te);
			}
			else {
				break;
			}
			y++;
		}
		return l;
	}
	
	@Override
	public void closeInventory() {}

	@Override
	public ItemStack decrStackSize(int arg0, int arg1) {
		if(arg0>=inv.length) {
			return getThem().get((int)Math.floor(arg0/inv.length)).decrStackSize(arg0%inv.length, arg1);
		}
		if(inv[arg0]==null) {
			return null;
		}
		else if(inv[arg0].stackSize<arg1) {
			ItemStack tr = inv[arg0];
			inv[arg0]=null;
			return tr;
		}
		else {
			ItemStack tr = inv[arg0].copy();
			tr.stackSize=arg1;
			inv[arg0].stackSize-=arg1;
			return tr;
		}
	}

	@Override
	public String getInventoryName() {
		return "Digital Cabinet";
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public int getSizeInventory() {
		return inv.length*getThem().size();
	}

	@Override
	public ItemStack getStackInSlot(int arg0) {
		if(arg0<inv.length) {
			return inv[arg0];
		}
		else {
			return getThem().get((int)(arg0/inv.length)).getStackInSlot(arg0%inv.length);
		}
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
		return true;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer arg0) {
		return true;
	}

	@Override
	public void openInventory() {}

	@Override
	public void setInventorySlotContents(int arg0, ItemStack arg1) {
		if(arg0<inv.length) {
			inv[arg0]=arg1;
		}
		else {
			getThem().get((int)(arg0/inv.length)).setInventorySlotContents(arg0%inv.length, arg1);
		}
	}
	
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		NBTTagList l = new NBTTagList();
		for(int i = 0; i < inv.length; i++) {
			if(inv[i]!=null) {
				NBTTagCompound c = new NBTTagCompound();
				inv[i].writeToNBT(c);
				c.setInteger("Slot", i);
				l.appendTag(c);
			}
		}
		tag.setTag("Inventory", l);
	}
	
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		NBTTagList l = (NBTTagList) tag.getTag("Inventory");
		for(int i = 0; i < l.tagCount(); i++) {
			NBTTagCompound c = l.getCompoundTagAt(i);
			inv[c.getInteger("Slot")]=ItemStack.loadItemStackFromNBT(c);
		}
	}
	
}
