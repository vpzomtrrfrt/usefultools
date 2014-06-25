package net.reederhome.colin.usefultoolsmod;

import java.util.List;

import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemBlockSucker extends Item {

	IIcon iconEmpty;
	IIcon iconFull;
	
	public ItemBlockSucker() {
		super();
		setMaxStackSize(1);
		setMaxDamage(0);
	}
	
	public IIcon getIconFromDamage(int meta) {
		return meta>0?iconFull:iconEmpty;
	}
	
	public void registerIcons(IIconRegister ir) {
		iconEmpty = ir.registerIcon(UsefulToolsMod.MODID+":blockSuckerEmpty");
		iconFull  = ir.registerIcon(UsefulToolsMod.MODID+":blockSuckerFull");
	}
	
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if(par1ItemStack.hasTagCompound()) {
			NBTTagCompound tag = par1ItemStack.stackTagCompound;
			Block block = Block.getBlockById(tag.getInteger("BlockId"));
			int meta = tag.getInteger("BlockData");
			par3List.add(I18n.format(Item.getItemFromBlock(block).getUnlocalizedName(new ItemStack(block, 1, meta))));
			par1ItemStack.setItemDamage(1);
		}
		else {
			par1ItemStack.setItemDamage(0);
		}
	}
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		if(par1ItemStack.hasTagCompound()) {
			NBTTagCompound tag = par1ItemStack.stackTagCompound;
			Block field_150939_a = Block.getBlockById(tag.getInteger("BlockId"));
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

	        if (par1ItemStack.stackSize == 0)
	        {
	            return false;
	        }
	        else if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
	        {
	            return false;
	        }
	        else if (par5 == 255 && field_150939_a.getMaterial().isSolid())
	        {
	            return false;
	        }
	        else if (par3World.canPlaceEntityOnSide(field_150939_a, par4, par5, par6, false, par7, par2EntityPlayer, par1ItemStack))
	        {
	            int var12 = tag.getInteger("BlockData");
	            int var13 = field_150939_a.onBlockPlaced(par3World, par4, par5, par6, par7, par8, par9, par10, var12);

	            if (par3World.setBlock(par4, par5, par6, field_150939_a, var13, 3))
	            {
	                if (par3World.getBlock(par4, par5, par6) == field_150939_a)
	                {
	                	if(tag.hasKey("BlockEntity")) {
	                		TileEntity te = TileEntity.createAndLoadEntity(tag.getCompoundTag("BlockEntity"));
	                		par3World.setTileEntity(par4, par5, par6, te);
	                	}
	                    field_150939_a.onBlockPlacedBy(par3World, par4, par5, par6, par2EntityPlayer, par1ItemStack);
	                    field_150939_a.onPostBlockPlaced(par3World, par4, par5, par6, var13);
	                }

	                par3World.playSoundEffect((double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F), field_150939_a.stepSound.func_150496_b(), (field_150939_a.stepSound.getPitch() + 1.0F) / 2.0F, field_150939_a.stepSound.getVolume() * 0.8F);
	                par1ItemStack.stackTagCompound=null;
	                par1ItemStack.setItemDamage(0);
	            }

	            return true;
	        }
	        else
	        {
	            return false;
	        }
		}
		else {
			NBTTagCompound tag = new NBTTagCompound();
			tag.setInteger("BlockId", Block.getIdFromBlock(par3World.getBlock(par4, par5, par6)));
			tag.setInteger("BlockData", par3World.getBlockMetadata(par4, par5, par6));
			TileEntity te = par3World.getTileEntity(par4, par5, par6);
			if(te!=null) {
				NBTTagCompound tet = new NBTTagCompound();
				te.writeToNBT(tet);
				tag.setTag("BlockEntity", tet);
			}
			par1ItemStack.setTagCompound(tag);
			par3World.removeTileEntity(par4, par5, par6);
			par3World.setBlock(par4, par5, par6, Blocks.air, 0, 3);
			par1ItemStack.setItemDamage(1);
			return true;
		}
	}
}
