package net.reederhome.colin.usefultoolsmod;

import net.minecraft.inventory.IInventory;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class InventorySingleDigitalCabinet extends TileEntityInventoryWrapper {

    private BlockPos pos;

    @Override
    public IInventory getInv() {
        return ((IInventory)worldObj.getTileEntity(pos));
    }

    @Override
    public int getSizeInventory() {
        return TileEntityDigitalCabinet.SIZE;
    }

    public InventorySingleDigitalCabinet(BlockPos pos, World world) {
        this.pos = pos;
        setWorldObj(world);
    }
}
