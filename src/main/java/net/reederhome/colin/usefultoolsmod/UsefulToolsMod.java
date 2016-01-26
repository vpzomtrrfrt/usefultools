package net.reederhome.colin.usefultoolsmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.reederhome.colin.usefultoolsmod.client.UsefulToolsClient;

@Mod(modid=UsefulToolsMod.MODID,name="Useful Tools")
public class UsefulToolsMod {
    public static final String MODID = "usefultools";

    public static CreativeTabs tab = new CreativeTabs(MODID) {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(blockPlayerInterface);
        }
    };

    public static BlockPlayerInterface blockPlayerInterface = new BlockPlayerInterface();
    public static BlockCobblegen blockCobblegen = new BlockCobblegen();
    public static BlockRemoteInventory blockRemoteInventory = new BlockRemoteInventory();
    public static BlockObsidiPlate blockObsidiPlate = new BlockObsidiPlate();

    public static ItemRemoteInventory itemRemoteInventory = new ItemRemoteInventory();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent ev) {
        System.out.println("Hello World!");

        GameRegistry.registerBlock(blockPlayerInterface, blockPlayerInterface.NAME);
        GameRegistry.registerBlock(blockCobblegen, blockCobblegen.NAME);
        GameRegistry.registerBlock(blockRemoteInventory, blockRemoteInventory.NAME);
        GameRegistry.registerBlock(blockObsidiPlate, blockObsidiPlate.NAME);

        GameRegistry.registerItem(itemRemoteInventory, itemRemoteInventory.NAME);

        GameRegistry.registerTileEntity(TileEntityPlayerInterface.class, "PlayerInterface");
        GameRegistry.registerTileEntity(TileEntityCobblegen.class, "Cobblegen");
        GameRegistry.registerTileEntity(TileEntityRemoteInventory.class, "RemoteInventory");

        GameRegistry.addRecipe(new ShapedOreRecipe(blockCobblegen, "ccc", "wrl", "ccc", 'c', "cobblestone", 'w', Items.water_bucket, 'r', "dustRedstone", 'l', Items.lava_bucket));
        GameRegistry.addRecipe(new ShapedOreRecipe(itemRemoteInventory, "wiw", "wew", "wiw", 'w', "plankWood", 'i', "ingotIron", 'e', Items.ender_pearl));
        GameRegistry.addShapelessRecipe(new ItemStack(blockPlayerInterface), itemRemoteInventory, Items.rotten_flesh);
        GameRegistry.addRecipe(new ShapedOreRecipe(blockObsidiPlate, "oo", 'o', Blocks.obsidian));
    }

    @Mod.EventHandler
    @SideOnly(Side.CLIENT)
    public void clientInit(FMLInitializationEvent ev) {
        UsefulToolsClient.registerClientThings();
    }
}
