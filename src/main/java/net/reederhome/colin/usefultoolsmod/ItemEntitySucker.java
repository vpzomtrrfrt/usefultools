package net.reederhome.colin.usefultoolsmod;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.util.List;

public class ItemEntitySucker extends Item {
    public static final String NAME = "entitySucker";

    public ItemEntitySucker() {
        super();
        setMaxStackSize(1);
        setUnlocalizedName(NAME);
        setCreativeTab(UsefulToolsMod.tab);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer p, World w, BlockPos pos, EnumFacing facing, float p_onItemUse_6_, float p_onItemUse_7_, float p_onItemUse_8_) {
        if(stack.hasTagCompound()) {
            NBTTagCompound tag = stack.getTagCompound();
            if(tag.hasKey("EntityId")) {
                pos = pos.offset(facing);
                if(!w.isRemote) {
                    Entity e = EntityList.createEntityByID(tag.getInteger("EntityId"), w);
                    e.readFromNBT(tag.getCompoundTag("Entity"));
                    e.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                    w.spawnEntityInWorld(e);
                }
                tag.removeTag("EntityId");
                tag.removeTag("Entity");
                stack.setItemDamage(0);
                return true;
            }
        }
        return false;
    }

    @Override
    public int getColorFromItemStack(ItemStack stack, int pass) {
        boolean fail = false;
        if(stack.hasTagCompound()) {
            NBTTagCompound tag = stack.getTagCompound();
            if(tag.hasKey("EntityId")) {
                EntityList.EntityEggInfo info = EntityList.entityEggs.get(tag.getInteger("EntityId"));
                if (info != null) {
                    if (pass == 0) {
                        return info.primaryColor;
                    } else if (pass == 1) {
                        return info.secondaryColor;
                    }
                }
                else {
                    fail = true;
                }
            }
        }
        if(pass == 2) {
            return 0xFFFFFF;
        }
        else {
            if(fail || pass == 0) {
                return 0;
            }
            else {
                return 0x9900AA;
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer p, List<String> l, boolean p_addInformation_4_) {
        super.addInformation(stack, p, l, p_addInformation_4_);
        if(stack.hasTagCompound()) {
            NBTTagCompound tag = stack.getTagCompound();
            if(tag.hasKey("EntityId")) {
                l.add(I18n.format("entity." + EntityList.getStringFromID(tag.getInteger("EntityId")) + ".name"));
            }
        }
    }
}
