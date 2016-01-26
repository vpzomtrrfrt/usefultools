package net.reederhome.colin.usefultoolsmod.client;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.reederhome.colin.usefultoolsmod.UsefulToolsMod;

public class UsefulToolsClient {
    public static void registerClientThings() {
        registerBlock(UsefulToolsMod.blockPlayerInterface, UsefulToolsMod.blockPlayerInterface.NAME);
        registerBlock(UsefulToolsMod.blockCobblegen, UsefulToolsMod.blockCobblegen.NAME);
        registerBlock(UsefulToolsMod.blockRemoteInventory, UsefulToolsMod.blockRemoteInventory.NAME);
        registerItem(UsefulToolsMod.itemRemoteInventory, UsefulToolsMod.itemRemoteInventory.NAME);
    }

    private static void registerBlock(Block block, String name) {
        registerItem(Item.getItemFromBlock(block), name);
    }

    private static void registerItem(Item item, String name) {
        registerItem(item, new ModelResourceLocation(UsefulToolsMod.MODID+":"+name, "inventory"));
    }

    private static void registerItem(Item item, ModelResourceLocation loc) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, loc);
    }
}
