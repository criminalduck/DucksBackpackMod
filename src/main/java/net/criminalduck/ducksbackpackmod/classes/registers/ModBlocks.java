package net.criminalduck.ducksbackpackmod.classes.registers;

import net.criminalduck.ducksbackpackmod.DucksBackpackMod;
import net.criminalduck.ducksbackpackmod.classes.backpack.BackpackBlock;
import net.criminalduck.ducksbackpackmod.classes.workbench.WorkbenchBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, DucksBackpackMod.MODID);

    public static final RegistryObject<Block> BACKPACK =
            BLOCKS.register("backpack", () -> new BackpackBlock(BlockBehaviour.Properties.copy(Blocks.SHULKER_BOX).noOcclusion().strength(0.5f)));

    public static final RegistryObject<Block> WORKBENCH =
            registerBlock("workbench", () -> new WorkbenchBlock(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)));

    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties()));
        return toReturn;
    }
}
