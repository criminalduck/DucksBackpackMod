package net.criminalduck.ducksbackpackmod.classes.backpack;

import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;

public class BackpackContainer implements MenuProvider {
    private final Container container;

    public BackpackContainer (ItemStack stack) {
        this.container = new SimpleContainer(27);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Portable Chest");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
        return new ChestMenu(MenuType.GENERIC_9x3, id, playerInventory, container, 3);
    }
}