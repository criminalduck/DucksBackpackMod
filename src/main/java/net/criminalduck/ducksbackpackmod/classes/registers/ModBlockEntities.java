package net.criminalduck.ducksbackpackmod.classes.registers;

import net.criminalduck.ducksbackpackmod.DucksBackpackMod;
import net.criminalduck.ducksbackpackmod.classes.backpack.BackpackBlockEntity;
import net.criminalduck.ducksbackpackmod.classes.workbench.WorkbenchBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, DucksBackpackMod.MODID);

    public static final RegistryObject<BlockEntityType<BackpackBlockEntity>> BACKPACK_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("backpack_block_entity", () ->
                    BlockEntityType.Builder.of(BackpackBlockEntity::new,
                            ModBlocks.BACKPACK.get()).build(null));

    public static final RegistryObject<BlockEntityType<WorkbenchBlockEntity>> WORKBENCH_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("workbench_block_entity", () ->
                    BlockEntityType.Builder.of(WorkbenchBlockEntity::new,
                            ModBlocks.WORKBENCH.get()).build(null));
}
