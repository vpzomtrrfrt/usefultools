package usefultoolsmod;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockCobblegen extends BlockContainer {

    public static final String NAME = "cobblegen";

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntityCobblegen();
    }

    public BlockCobblegen() {
        super(Material.rock);
        setUnlocalizedName(NAME);
        setHardness(10);
        setHarvestLevel("pickaxe", 0);
    }

    @Override
    public int getRenderType() {
        return 3;
    }
}
