package net.reederhome.colin.usefultoolsmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IChatComponent;

public class TileEntityPlayerInterface extends TileEntity implements IInventory {

    String name = null;

    private IInventory getInv() {
        if(name!=null) {
            EntityPlayer p = worldObj.getPlayerEntityByName(name);
            if(p!=null) {
                return p.inventory;
            }
        }
        return new EmptyInventory();
    }

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
        return false;
    }

    @Override
    public void openInventory(EntityPlayer entityPlayer) {

    }

    @Override
    public void closeInventory(EntityPlayer entityPlayer) {

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
        return false;
    }

    @Override
    public IChatComponent getDisplayName() {
        return getInv().getDisplayName();
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        name = tag.getString("Owner");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setString("Owner", name);
    }
}
