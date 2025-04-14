package net.criminalduck.ducksbackpackmod.classes.registers;

import net.criminalduck.ducksbackpackmod.DucksBackpackMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DucksBackpackMod.MODID);

    public static final RegistryObject<CreativeModeTab> BACKPACKMOD_TAB = CREATIVE_MODE_TABS.register("backpackmod_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BACKPACK.get()))
                    .title(Component.translatable("creativetab.ducksbackpackmod_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.BACKPACK.get());
                        output.accept(ModItems.STORAGE_UPGRADE.get());
                        output.accept(ModItems.ENDER_UPGRADE.get());
                        output.accept(ModItems.LANTERN_UPGRADE.get());
                        output.accept(ModItems.TELEPORTER_UPGRADE.get());
                        output.accept(ModBlocks.WORKBENCH.get());
                    })
                    .build());
}
