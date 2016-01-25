package usefultoolsmod;

import net.minecraft.init.Items;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.ShapedOreRecipe;
import usefultoolsmod.client.UsefulToolsClient;

@Mod(modid=UsefulToolsMod.MODID,name="Useful Tools")
public class UsefulToolsMod {
    public static final String MODID = "usefultools";

    public static BlockPlayerInterface blockPlayerInterface = new BlockPlayerInterface();
    public static BlockCobblegen blockCobblegen = new BlockCobblegen();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent ev) {
        System.out.println("Hello World!");

        GameRegistry.registerBlock(blockPlayerInterface, blockPlayerInterface.NAME);
        GameRegistry.registerBlock(blockCobblegen, blockCobblegen.NAME);

        GameRegistry.registerTileEntity(TileEntityPlayerInterface.class, "PlayerInterface");
        GameRegistry.registerTileEntity(TileEntityCobblegen.class, "Cobblegen");

        GameRegistry.addRecipe(new ShapedOreRecipe(blockCobblegen, "ccc", "wrl", "ccc", 'c', "cobblestone", 'w', Items.water_bucket, 'r', "dustRedstone", 'l', Items.lava_bucket));
    }

    @Mod.EventHandler
    @SideOnly(Side.CLIENT)
    public void clientInit(FMLInitializationEvent ev) {
        UsefulToolsClient.registerClientThings();
    }
}
