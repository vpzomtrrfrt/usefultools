package net.reederhome.colin.usefultoolsmod;

import net.minecraft.block.BlockBasePressurePlate;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class BlockObsidiPlate extends BlockBasePressurePlate {

    public static final PropertyBool POWERED = PropertyBool.create("powered");
    public static final String NAME = "obsidiPlate";

    @Override
    protected int computeRedstoneStrength(World world, BlockPos pos) {
        AxisAlignedBB aabb = getSensitiveAABB(pos);
        List<EntityPlayer> l = world.getEntitiesWithinAABB(EntityPlayer.class, aabb);
        if(!l.isEmpty()) {
            return 15;
        }
        else {
            return 0;
        }
    }

    @Override
    protected int getRedstoneStrength(IBlockState state) {
        return state.getValue(POWERED).booleanValue() ? 15 : 0;
    }

    @Override
    protected IBlockState setRedstoneStrength(IBlockState state, int i) {
        return state.withProperty(POWERED, i > 0);
    }

    public BlockObsidiPlate() {
        super(Material.rock);
        setUnlocalizedName(NAME);
        setHardness(5);
        setHarvestLevel("pickaxe", 0);
        setCreativeTab(UsefulToolsMod.tab);
        setDefaultState(blockState.getBaseState().withProperty(POWERED, false));
    }

    @Override
    public IBlockState getStateFromMeta(int p_getStateFromMeta_1_) {
        return getDefaultState().withProperty(POWERED, p_getStateFromMeta_1_ > 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(POWERED).booleanValue() ? 1 : 0;
    }

    @Override
    public BlockState createBlockState() {
        return new BlockState(this, new IProperty[] {POWERED});
    }
}
