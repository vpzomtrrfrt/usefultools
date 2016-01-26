package net.reederhome.colin.usefultoolsmod;

import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

public class TileEntityRemoteInventory extends TileEntityInventoryWrapper {

    BlockPos dest = null;

    @Override
    public IInventory getInv() {
        if(dest != null) {
            TileEntity thing = worldObj.getTileEntity(dest);
            if(thing instanceof IInventory) {
                return (IInventory) thing;
            }
        }
        return new EmptyInventory();
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setLong("Dest", dest.toLong());
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        dest = BlockPos.fromLong(tag.getLong("Dest"));
    }
}
