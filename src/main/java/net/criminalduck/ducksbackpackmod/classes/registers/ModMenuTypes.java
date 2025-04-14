package net.criminalduck.ducksbackpackmod.classes.registers;

import net.criminalduck.ducksbackpackmod.DucksBackpackMod;
import net.criminalduck.ducksbackpackmod.classes.backpack.BackpackMenu;
import net.criminalduck.ducksbackpackmod.classes.workbench.WorkbenchMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, DucksBackpackMod.MODID);

    public static final RegistryObject<MenuType<BackpackMenu>> BACKPACK_MENU =
            MENUS.register("backpack_menu", () ->
                    IForgeMenuType.create((id, inv, data) -> new BackpackMenu(id, inv, data)));

    public static final RegistryObject<MenuType<WorkbenchMenu>> WORKBENCH_MENU =
            MENUS.register("workbench_menu", () -> IForgeMenuType.create(WorkbenchMenu::new));

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }
}
