package net.reederhome.colin.usefultoolsmod;

import java.util.Random;

import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockObsidiPlate extends BlockPressurePlate {

	public BlockObsidiPlate() {
		super("obsidian", Material.rock, Sensitivity.players);
		setBlockName("obsidiPlate");
		setHardness(5);
		setHarvestLevel("pickaxe", 0, 0);
		setCreativeTab(UsefulToolsMod.tab);
	}
	
	public Item getItemDropped(int metadata, Random random, int fortune) {
		return Item.getItemFromBlock(this);
	}
}
