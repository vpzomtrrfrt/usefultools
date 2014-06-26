package net.reederhome.colin.usefultoolsmod;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemEntitySucker extends Item {

	IIcon iconFull;
	IIcon iconEmpty;
	public ItemEntitySucker() {
		super();
		setMaxStackSize(1);
		setUnlocalizedName("entitySucker");
	}
	
	public IIcon getIconIndex(ItemStack stack) {
		return stack.hasTagCompound()?iconFull:iconEmpty;
	}
	
	public void registerIcons(IIconRegister ir) {
		iconEmpty = ir.registerIcon(UsefulToolsMod.MODID+":entitySuckerEmpty");
		iconFull  = ir.registerIcon(UsefulToolsMod.MODID+":entitySuckerFull");
		this.itemIcon=iconEmpty;
	}
	
	public void updateName(ItemStack par1ItemStack) {
		if(par1ItemStack.hasTagCompound()) {
			NBTTagCompound tag = par1ItemStack.stackTagCompound;
			if(tag.hasKey("Entity")) {
				if(par1ItemStack.hasDisplayName()) {
					tag.getCompoundTag("Entity").setString("CustomName", par1ItemStack.getDisplayName());
					tag.getCompoundTag("Entity").setBoolean("CustomNameVisible", true);
				}
				String name = tag.getCompoundTag("Entity").getString("CustomName");
				if(name!=null&&!name.equals("")) par1ItemStack.setStackDisplayName(name);
			}
		}
	}
	
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if(par1ItemStack.hasTagCompound()&&par1ItemStack.stackTagCompound.hasKey("EntityId")) {
			updateName(par1ItemStack);
			NBTTagCompound tag = par1ItemStack.stackTagCompound;
			par3List.add(I18n.format("entity."+EntityList.getStringFromID(tag.getInteger("EntityId"))+".name"));
		}
	}
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		updateName(par1ItemStack);
		if(!par3World.isRemote) {
			if(par1ItemStack.hasTagCompound()&&par1ItemStack.stackTagCompound.hasKey("Entity")) {
				par4 += Facing.offsetsXForSide[par7];
	            par5 += Facing.offsetsYForSide[par7];
	            par6 += Facing.offsetsZForSide[par7];
				if(!par2EntityPlayer.onGround&&par2EntityPlayer.isSneaking()) System.out.println(par1ItemStack.stackTagCompound);
				Entity e = EntityList.createEntityByID(par1ItemStack.stackTagCompound.getInteger("EntityId"), par3World);
				e.readFromNBT(par1ItemStack.stackTagCompound.getCompoundTag("Entity"));
				e.setLocationAndAngles(par4, par5, par6, e.rotationYaw, e.rotationPitch);
				par3World.spawnEntityInWorld(e);
				par1ItemStack.setTagCompound(null);
			}
		}
		return false;
	}
}
