package net.reederhome.colin.usefultoolsmod;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.Random;

public class BlockRemoteInventory extends BlockContainer {
    public static final String NAME = "remoteInventory";

    public BlockRemoteInventory() {
        super(Material.wood);
        setUnlocalizedName(NAME);
        setHardness(2);
    }

    @Override
    public int getRenderType() {
        return 3;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntityRemoteInventory();
    }

    @Override
    public Item getItemDropped(IBlockState p_getItemDropped_1_, Random p_getItemDropped_2_, int p_getItemDropped_3_) {
        return UsefulToolsMod.itemRemoteInventory;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition p_getPickBlock_1_, World p_getPickBlock_2_, BlockPos p_getPickBlock_3_, EntityPlayer p_getPickBlock_4_) {
        return new ItemStack(UsefulToolsMod.itemRemoteInventory);
    }
}
