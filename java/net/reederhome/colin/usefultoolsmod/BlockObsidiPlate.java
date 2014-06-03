package net.reederhome.colin.usefultoolsmod;

import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.material.Material;

public class BlockObsidiPlate extends BlockPressurePlate {

	public BlockObsidiPlate() {
		super("obsidian", Material.rock, Sensitivity.players);
		setBlockName("obsidiPlate");
	}
}
