package net.reederhome.colin.usefultoolsmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;

public abstract class TileEntityInventoryWrapper extends TileEntity implements ISidedInventory {

    public abstract IInventory getInv();

    @Override
    public int getSizeInventory() {
        return getInv().getSizeInventory();
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return getInv().getStackInSlot(i);
    }

    @Override
    public ItemStack decrStackSize(int i, int i1) {
        return getInv().decrStackSize(i, i1);
    }

    @Override
    public ItemStack removeStackFromSlot(int i) {
        return getInv().removeStackFromSlot(i);
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
        getInv().setInventorySlotContents(i, itemStack);
    }

    @Override
    public int getInventoryStackLimit() {
        return getInv().getInventoryStackLimit();
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return getInv().isUseableByPlayer(entityPlayer);
    }

    @Override
    public void openInventory(EntityPlayer entityPlayer) {
        getInv().openInventory(entityPlayer);
    }

    @Override
    public void closeInventory(EntityPlayer entityPlayer) {
        getInv().closeInventory(entityPlayer);
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        return getInv().isItemValidForSlot(i, itemStack);
    }

    @Override
    public int getField(int i) {
        return getInv().getField(i);
    }

    @Override
    public void setField(int i, int i1) {
        getInv().setField(i, i1);
    }

    @Override
    public int getFieldCount() {
        return getInv().getFieldCount();
    }

    @Override
    public void clear() {
        getInv().clear();
    }

    @Override
    public String getName() {
        return getInv().getName();
    }

    @Override
    public boolean hasCustomName() {
        return getInv().hasCustomName();
    }

    @Override
    public IChatComponent getDisplayName() {
        return getInv().getDisplayName();
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemStack, EnumFacing enumFacing) {
        IInventory inv = getInv();
        if(inv instanceof ISidedInventory) {
            return ((ISidedInventory)inv).canExtractItem(i, itemStack, enumFacing);
        }
        else {
            return true;
        }
    }

    @Override
    public boolean canInsertItem(int i, ItemStack itemStack, EnumFacing enumFacing) {
        IInventory inv = getInv();
        if(inv instanceof ISidedInventory) {
            return ((ISidedInventory)inv).canInsertItem(i, itemStack, enumFacing);
        }
        else {
            return isItemValidForSlot(i, itemStack);
        }
    }

    @Override
    public int[] getSlotsForFace(EnumFacing enumFacing) {
        IInventory inv = getInv();
        if(inv instanceof ISidedInventory) {
            return ((ISidedInventory)inv).getSlotsForFace(enumFacing);
        }
        else {
            int[] tr = new int[getSizeInventory()];
            for(int i = 0; i < tr.length; i++) {
                tr[i] = i;
            }
            return tr;
        }
    }
}
