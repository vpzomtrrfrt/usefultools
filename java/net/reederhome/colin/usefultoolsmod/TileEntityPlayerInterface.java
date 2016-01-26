package net.reederhome.colin.usefultoolsmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityPlayerInterface extends TileEntityInventoryWrapper {

    String name;

    @Override
    public IInventory getInv() {
        if(name!=null) {
            EntityPlayer p = worldObj.getPlayerEntityByName(name);
            if(p!=null) {
                return p.inventory;
            }
        }
        return new EmptyInventory();
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
