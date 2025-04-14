package net.criminalduck.ducksbackpackmod.classes.registers;

import net.criminalduck.ducksbackpackmod.DucksBackpackMod;
import net.criminalduck.ducksbackpackmod.classes.backpack.BackpackItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DucksBackpackMod.MODID);

    public static final RegistryObject<Item> BACKPACK = ITEMS.register("backpack",
            () -> new BackpackItem(ModBlocks.BACKPACK.get(), new Item.Properties().fireResistant().stacksTo(1)));

    // UPGRADES
        // STORAGE
    public static final RegistryObject<Item> STORAGE_UPGRADE = ITEMS.register("storage_upg",
            () -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ENDER_UPGRADE = ITEMS.register("ender_upg",
            () -> new Item(new Item.Properties().stacksTo(1)));
        // PASSIVE
    public static final RegistryObject<Item> LANTERN_UPGRADE = ITEMS.register("lantern_upg",
            () -> new Item(new Item.Properties().stacksTo(1)));
        // ABILITY
    public static final RegistryObject<Item> TELEPORTER_UPGRADE = ITEMS.register("teleporter_upg",
            () -> new Item(new Item.Properties().stacksTo(1)));
}
