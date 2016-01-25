package usefultoolsmod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import usefultoolsmod.client.UsefulToolsClient;

@Mod(modid=UsefulToolsMod.MODID,name="Useful Tools")
public class UsefulToolsMod {
    public static final String MODID = "usefultools";

    public static BlockPlayerInterface blockPlayerInterface = new BlockPlayerInterface();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent ev) {
        System.out.println("Hello World!");
    }

    @Mod.EventHandler
    @SideOnly(Side.CLIENT)
    public void clientInit(FMLInitializationEvent ev) {
        UsefulToolsClient.registerClientThings();
    }
}
