package net.reederhome.colin.usefultoolsmod;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ItemRemoteInventory extends Item {

	public ItemRemoteInventory() {
		setMaxStackSize(1);
		setUnlocalizedName("itemRemoteInventory");
		setTextureName(UsefulToolsMod.MODID+":remoteInventory");
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		if(player.isSneaking()) {
			TileEntity te = par3World.getTileEntity(par4, par5, par6);
			if(te != null && te instanceof IInventory) {
				if(stack.stackTagCompound==null) {stack.setTagCompound(new NBTTagCompound());}
				stack.stackTagCompound.setIntArray("Remote", new int[]{par4, par5, par6});
				return true;
			}
			return false;
		}
		else {
			if(stack.stackTagCompound!=null&&stack.stackTagCompound.hasKey("Remote")) {
				Block var11 = par3World.getBlock(par4, par5, par6);

		        if (var11 == Blocks.snow_layer && (par3World.getBlockMetadata(par4, par5, par6) & 7) < 1)
		        {
		            par7 = 1;
		        }
		        else if (var11 != Blocks.vine && var11 != Blocks.tallgrass && var11 != Blocks.deadbush)
		        {
		            if (par7 == 0)
		            {
		                --par5;
		            }

		            if (par7 == 1)
		            {
		                ++par5;
		            }

		            if (par7 == 2)
		            {
		                --par6;
		            }

		            if (par7 == 3)
		            {
		                ++par6;
		            }

		            if (par7 == 4)
		            {
		                --par4;
		            }

		            if (par7 == 5)
		            {
		                ++par4;
		            }
		        }

		        if (stack.stackSize == 0)
		        {
		            return false;
		        }
		        else if (!player.canPlayerEdit(par4, par5, par6, par7, stack))
		        {
		            return false;
		        }
		        else if (par3World.canPlaceEntityOnSide(UsefulToolsMod.remoteInventory, par4, par5, par6, false, par7, player, stack))
		        {
		            int var12 = this.getMetadata(stack.getItemDamage());
		            int var13 = UsefulToolsMod.remoteInventory.onBlockPlaced(par3World, par4, par5, par6, par7, par8, par9, par10, var12);

		            if (par3World.setBlock(par4, par5, par6, UsefulToolsMod.remoteInventory, var13, 3))
		            {
		                par3World.playSoundEffect((double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F), UsefulToolsMod.remoteInventory.stepSound.func_150496_b(), (UsefulToolsMod.remoteInventory.stepSound.getPitch() + 1.0F) / 2.0F, UsefulToolsMod.remoteInventory.stepSound.getVolume() * 0.8F);
		                --stack.stackSize;
		                TileEntityRemoteInventory te = new TileEntityRemoteInventory();
		                par3World.setTileEntity(par4, par5, par6, te);
		                int[] thing = stack.stackTagCompound.getIntArray("Remote");
		                te.rx=thing[0];
		                te.ry=thing[1];
		                te.rz=thing[2];
		            }

		            return true;
		        }
		        else
		        {
		            return false;
		        }
			}
			return false;
		}
	}
}
