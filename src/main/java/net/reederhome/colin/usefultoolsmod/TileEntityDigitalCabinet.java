package net.reederhome.colin.usefultoolsmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

import java.util.ArrayList;
import java.util.List;

public class TileEntityDigitalCabinet extends TileEntity implements IInventory {
    public static final int SIZE = 36;

    ItemStack[] inv = new ItemStack[SIZE];
    public List<TileEntityDigitalCabinet> getThem() {
        List<TileEntityDigitalCabinet> tr = new ArrayList<TileEntityDigitalCabinet>();
        BlockPos pos = getPos().up();
        while(true) {
            TileEntity te = worldObj.getTileEntity(pos);
            if(te instanceof TileEntityDigitalCabinet) {
                tr.add((TileEntityDigitalCabinet)te);
            }
            else {
                break;
            }
            pos = pos.up();
        }
        return tr;
    }


    @Override
    public int getSizeInventory() {
        int tr = inv.length * (1 + getThem().size());
        System.out.println(tr);
        return tr;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        if(i < inv.length) {
            return inv[i];
        }
        else {
            return getThem().get(i / inv.length - 1).getStackInSlot(i % inv.length);
        }
    }

    @Override
    public ItemStack decrStackSize(int i, int sz) {
        if(i < inv.length) {
            ItemStack stack = inv[i];
            if(sz < stack.stackSize) {
                stack.stackSize -= sz;
                ItemStack tr = stack.copy();
                tr.stackSize = sz;
                return tr;
            }
            else {
                inv[i] = null;
                return stack;
            }
        }
        else {
            return getThem().get(i/inv.length-1).decrStackSize(i%inv.length, sz);
        }
    }

    @Override
    public ItemStack removeStackFromSlot(int i) {
        if(i < inv.length) {
            ItemStack tr = inv[i];
            inv[i] = null;
            return tr;
        }
        else {
            return getThem().get(i/inv.length-1).removeStackFromSlot(i%inv.length);
        }
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack stack) {
        if(i < inv.length) {
            inv[i] = stack;
        }
        else {
            getThem().get(i/inv.length-1).setInventorySlotContents(i%inv.length, stack);
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer entityPlayer) {

    }

    @Override
    public void closeInventory(EntityPlayer entityPlayer) {

    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        return true;
    }

    @Override
    public int getField(int i) {
        return 0;
    }

    @Override
    public void setField(int i, int i1) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        for(int i = 0; i < inv.length; i++) {
            inv[i] = null;
        }
        getThem().get(0).clear();
    }

    @Override
    public String getName() {
        return "tile.digitalCabinet.name";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public IChatComponent getDisplayName() {
        return new ChatComponentTranslation(getName());
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        NBTTagList l = new NBTTagList();
        for(int i = 0; i < inv.length; i++) {
            if(inv[i] != null) {
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setByte("Slot", (byte)i);
                inv[i].writeToNBT(nbt);
                l.appendTag(nbt);
            }
        }
        tag.setTag("Items", l);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        NBTTagList l = (NBTTagList) tag.getTag("Items");
        for(int i = 0; i < l.tagCount(); i++) {
            NBTTagCompound nbt = l.getCompoundTagAt(i);
            int j = nbt.getByte("Slot");
            inv[j] = ItemStack.loadItemStackFromNBT(nbt);
        }
    }
}
