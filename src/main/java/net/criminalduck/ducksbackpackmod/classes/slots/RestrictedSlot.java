package net.criminalduck.ducksbackpackmod.classes.slots;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.function.Predicate;

public class RestrictedSlot extends SlotItemHandler {
    private final ResourceLocation iconTexture;
    private final Predicate<ItemStack> validator;

    public RestrictedSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition, Predicate<ItemStack> validator, ResourceLocation iconTexture) {
        super(itemHandler, index, xPosition, yPosition);
        this.validator = validator;
        this.iconTexture = iconTexture;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return validator.test(stack);
    }

    public ResourceLocation getIconTexture() {
        return this.iconTexture;
    }
}