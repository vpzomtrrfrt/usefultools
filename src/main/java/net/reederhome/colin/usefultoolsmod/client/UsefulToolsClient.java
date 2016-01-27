package net.reederhome.colin.usefultoolsmod.client;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.reederhome.colin.usefultoolsmod.UsefulToolsMod;

public class UsefulToolsClient {
    public static void registerClientThings() {
        registerBlock(UsefulToolsMod.blockPlayerInterface, UsefulToolsMod.blockPlayerInterface.NAME);
        registerBlock(UsefulToolsMod.blockCobblegen, UsefulToolsMod.blockCobblegen.NAME);
        registerBlock(UsefulToolsMod.blockRemoteInventory, UsefulToolsMod.blockRemoteInventory.NAME);
        registerBlock(UsefulToolsMod.blockObsidiPlate, UsefulToolsMod.blockObsidiPlate.NAME);
        registerBlock(UsefulToolsMod.blockDigitalCabinet, UsefulToolsMod.blockDigitalCabinet.NAME);
        registerItem(UsefulToolsMod.itemRemoteInventory, UsefulToolsMod.itemRemoteInventory.NAME);
        registerItemVariants(UsefulToolsMod.itemEntitySucker, UsefulToolsMod.itemEntitySucker.NAME+"Empty", UsefulToolsMod.itemEntitySucker.NAME+"Full");
        registerItem(UsefulToolsMod.itemEntitySucker, 0, UsefulToolsMod.itemEntitySucker.NAME+"Empty");
        registerItem(UsefulToolsMod.itemEntitySucker, 1, UsefulToolsMod.itemEntitySucker.NAME+"Full");
    }

    private static void registerBlock(Block block, String name) {
        registerItem(Item.getItemFromBlock(block), 0, name);
    }

    private static void registerItem(Item item, int meta, String name) {
        registerItem(item, meta, new ModelResourceLocation(UsefulToolsMod.MODID+":"+name, "inventory"));
    }

    private static void registerItem(Item item, String name) {
        registerItem(item, 0, name);
    }

    private static void registerItem(Item item, int meta, ModelResourceLocation loc) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, loc);
    }

    private static void registerItemVariants(Item item, String... names) {
        for(String name : names) {
            ModelBakery.registerItemVariants(item, new ModelResourceLocation(UsefulToolsMod.MODID+":"+name, "inventory"));
        }
    }
}
