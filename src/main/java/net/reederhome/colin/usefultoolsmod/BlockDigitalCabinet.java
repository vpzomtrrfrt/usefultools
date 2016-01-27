package net.reederhome.colin.usefultoolsmod;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockDigitalCabinet extends BlockContainer {

    public static final String NAME = "digitalCabinet";

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntityDigitalCabinet();
    }

    public BlockDigitalCabinet() {
        super(Material.wood);
        setUnlocalizedName(NAME);
        setHardness(12);
        setHarvestLevel("axe", 0);
        setCreativeTab(UsefulToolsMod.tab);
    }

    @Override
    public void breakBlock(World w, BlockPos pos, IBlockState state) {
        TileEntity te = w.getTileEntity(pos);
        if(te instanceof IInventory) {
            InventoryHelper.dropInventoryItems(w, pos, new InventorySingleDigitalCabinet(pos, w));
        }
        super.breakBlock(w, pos, state);
    }

    @Override
    public boolean onBlockActivated(World w, BlockPos pos, IBlockState state, EntityPlayer p, EnumFacing facing, float p_onBlockActivated_6_, float p_onBlockActivated_7_, float p_onBlockActivated_8_) {
        p.displayGUIChest(new InventorySingleDigitalCabinet(pos, w));
        return true;
    }

    @Override
    public int getRenderType() {
        return 3;
    }
}
