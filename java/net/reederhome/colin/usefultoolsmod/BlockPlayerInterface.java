package net.reederhome.colin.usefultoolsmod;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockPlayerInterface extends BlockContainer {

    public static final String NAME = "playerInterface";

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntityPlayerInterface();
    }

    public BlockPlayerInterface() {
        super(Material.rock);
        setHardness(2);
        setUnlocalizedName(NAME);
    }

    @Override
    public void onBlockPlacedBy(World w, BlockPos pos, IBlockState state, EntityLivingBase p, ItemStack stack) {
        super.onBlockPlacedBy(w, pos, state, p, stack);
        TileEntityPlayerInterface te = (TileEntityPlayerInterface) w.getTileEntity(pos);
        te.name = p.getName();
    }

    @Override
    public int getRenderType() {
        return 3;
    }
}
