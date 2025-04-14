package net.criminalduck.ducksbackpackmod.classes.backpack;

import com.mojang.logging.LogUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.network.NetworkHooks;
import org.slf4j.Logger;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;


public class BackpackItem extends BlockItem implements ICurioItem {
    public BackpackItem(Block block, Properties properties) {
        super(block, properties);
    }

    private static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        if (player != null && !player.isShiftKeyDown()) {
            return InteractionResult.FAIL;
        }
        return super.useOn(context);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!player.isShiftKeyDown() && !level.isClientSide) {
            ItemStack stack = player.getItemInHand(hand);

            MenuProvider provider = new SimpleMenuProvider(
                    (id, playerInv, playerEntity) -> new BackpackMenu(id, playerInv, stack),
                    stack.getHoverName() // This makes the title use the item name (like a named shulker box)
            );

            NetworkHooks.openScreen((ServerPlayer) player, provider, buf -> buf.writeItem(stack));

            return InteractionResultHolder.success(stack);
        }
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {

    }

    @Override
    public boolean canFitInsideContainerItems() {
        return false;
    }

    /*
    @Override
    public void onDestroyed(ItemEntity itemEntity) {
        if (this.getBlock() instanceof BackpackBlock) {
            ItemStack itemStack = itemEntity.getItem();
            CompoundTag blockEntityTag = BlockItem.getBlockEntityData(itemStack);
            if (blockEntityTag != null && blockEntityTag.contains("Items", Tag.TAG_LIST)) {
                ListTag itemList = blockEntityTag.getList("Items", Tag.TAG_COMPOUND);
                Stream<CompoundTag> itemStream = itemList.stream().map(CompoundTag.class::cast);
                ItemUtils.onContainerDestroyed(itemEntity, itemStream.map(ItemStack::of));
            }
        }
    }

    @Override
    protected boolean updateCustomBlockEntityTag(BlockPos pos, Level level, @Nullable Player player, ItemStack stack, BlockState state) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof BackpackBlockEntity backpackEntity) {
            CompoundTag tag = BlockItem.getBlockEntityData(stack);
            if (tag != null) {
                backpackEntity.load(tag);
            }
        }
        return super.updateCustomBlockEntityTag(pos, level, player, stack, state);
    }
     */
}
