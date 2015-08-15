package net.reederhome.colin.usefultoolsmod;

import net.minecraft.block.BlockCrops;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockGlowstoneCrops extends BlockCrops {

	public BlockGlowstoneCrops() {
		super();
		this.setBlockTextureName(UsefulToolsMod.MODID+":glowstoneCrops");
		setBlockName("glowstoneCrops");
	}
	
	public Item func_149866_i()
    {
        return Items.glowstone_dust;
    }
	
	public Item func_149865_P()
    {
        return Items.glowstone_dust;
    }

}
