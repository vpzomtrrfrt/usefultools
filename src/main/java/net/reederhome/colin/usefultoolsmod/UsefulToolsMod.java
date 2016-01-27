package net.reederhome.colin.usefultoolsmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
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
    public static BlockDigitalCabinet blockDigitalCabinet = new BlockDigitalCabinet();

    public static ItemRemoteInventory itemRemoteInventory = new ItemRemoteInventory();
    public static ItemEntitySucker itemEntitySucker = new ItemEntitySucker();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent ev) {
        System.out.println("Hello World!");

        GameRegistry.registerBlock(blockPlayerInterface, blockPlayerInterface.NAME);
        GameRegistry.registerBlock(blockCobblegen, blockCobblegen.NAME);
        GameRegistry.registerBlock(blockRemoteInventory, blockRemoteInventory.NAME);
        GameRegistry.registerBlock(blockObsidiPlate, blockObsidiPlate.NAME);
        GameRegistry.registerBlock(blockDigitalCabinet, blockDigitalCabinet.NAME);

        GameRegistry.registerItem(itemRemoteInventory, itemRemoteInventory.NAME);
        GameRegistry.registerItem(itemEntitySucker, itemEntitySucker.NAME);

        GameRegistry.registerTileEntity(TileEntityPlayerInterface.class, "PlayerInterface");
        GameRegistry.registerTileEntity(TileEntityCobblegen.class, "Cobblegen");
        GameRegistry.registerTileEntity(TileEntityRemoteInventory.class, "RemoteInventory");
        GameRegistry.registerTileEntity(TileEntityDigitalCabinet.class, "DigitalCabinet");

        GameRegistry.addRecipe(new ShapedOreRecipe(blockCobblegen, "ccc", "wrl", "ccc", 'c', "cobblestone", 'w', Items.water_bucket, 'r', "dustRedstone", 'l', Items.lava_bucket));
        GameRegistry.addRecipe(new ShapedOreRecipe(itemRemoteInventory, "wiw", "wew", "wiw", 'w', "plankWood", 'i', "ingotIron", 'e', Items.ender_pearl));
        GameRegistry.addShapelessRecipe(new ItemStack(blockPlayerInterface), itemRemoteInventory, Items.rotten_flesh);
        GameRegistry.addRecipe(new ShapedOreRecipe(blockObsidiPlate, "oo", 'o', Blocks.obsidian));

        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventHandler
    @SideOnly(Side.CLIENT)
    public void clientInit(FMLInitializationEvent ev) {
        UsefulToolsClient.registerClientThings();
    }

    @SubscribeEvent
    public void onEntityInteract(EntityInteractEvent ev) {
        ItemStack held = ev.entityLiving.getHeldItem();
        if(held != null) {
            if(held.getItem().equals(itemEntitySucker)) {
                if(ev.target != null && !(ev.target instanceof EntityPlayer) && !(held.hasTagCompound() && held.getTagCompound().hasKey("EntityId"))) {
                    NBTTagCompound tag;
                    if(held.hasTagCompound()) {
                        tag = held.getTagCompound();
                    }
                    else {
                        tag = new NBTTagCompound();
                        held.setTagCompound(tag);
                    }
                    NBTTagCompound et = new NBTTagCompound();
                    ev.target.writeToNBT(et);
                    tag.setTag("Entity", et);
                    tag.setInteger("EntityId", EntityList.getEntityID(ev.target));
                    held.setItemDamage(1);
                    ev.target.worldObj.removeEntity(ev.target);
                }
            }
        }
    }
}
