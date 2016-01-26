package net.reederhome.colin.usefultoolsmod;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemRemoteInventory extends Item {
    public static final String NAME = "itemRemoteInventory";

    public ItemRemoteInventory() {
        setMaxStackSize(1);
        setUnlocalizedName(NAME);
        setCreativeTab(UsefulToolsMod.tab);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer p, World w, BlockPos pos, EnumFacing facing, float p_onItemUse_6_, float p_onItemUse_7_, float p_onItemUse_8_) {
        if(p.isSneaking()) {
            TileEntity te = w.getTileEntity(pos);
            if(te instanceof IInventory) {
                if(stack.getTagCompound()==null) {
                    stack.setTagCompound(new NBTTagCompound());
                }
                stack.getTagCompound().setLong("Dest", pos.toLong());
                return true;
            }
        }
        else {
            if(stack.getTagCompound() != null && stack.getTagCompound().hasKey("Dest") && stack.stackSize > 0) {
                if(!w.getBlockState(pos).getBlock().isReplaceable(w, pos)) {
                    pos = pos.offset(facing);
                }
                if(w.canBlockBePlaced(UsefulToolsMod.blockRemoteInventory, pos, false, facing, null, stack)) {
                    w.setBlockState(pos, UsefulToolsMod.blockRemoteInventory.getStateFromMeta(0));
                    TileEntityRemoteInventory te = (TileEntityRemoteInventory) w.getTileEntity(pos);
                    te.dest = BlockPos.fromLong(stack.getTagCompound().getLong("Dest"));
                    stack.stackSize--;
                    return true;
                }
            }
        }
        return false;
    }
}
