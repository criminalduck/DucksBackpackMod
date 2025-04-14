package net.criminalduck.ducksbackpackmod.classes.backpack;

import net.criminalduck.ducksbackpackmod.classes.registers.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class BackpackBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public static final VoxelShape SHAPE_NORTH = Shapes.join(Block.box(2, 0, 2, 14, 10, 14),
            Block.box(2, 10, 2, 14, 12, 14), BooleanOp.OR);
    public static final VoxelShape SHAPE_SOUTH = Shapes.join(Block.box(2, 0, 2, 14, 10, 14),
            Block.box(2, 10, 2, 14, 12, 14), BooleanOp.OR);
    public static final VoxelShape SHAPE_EAST = Shapes.join(Block.box(2, 0, 2, 14, 10, 14),
            Block.box(2, 10, 2, 14, 12, 14), BooleanOp.OR);
    public static final VoxelShape SHAPE_WEST = Shapes.join(Block.box(2, 0, 2, 14, 10, 14),
            Block.box(2, 10, 2, 14, 12, 14), BooleanOp.OR);
    public static final VoxelShape SHAPE_COLLISION = Block.box(2, 0, 2, 14, 16, 14);

    public BackpackBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE_COLLISION;
    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch(state.getValue(FACING)) {
            case NORTH -> SHAPE_NORTH;
            case EAST -> SHAPE_EAST;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
            default -> SHAPE_NORTH;
        };
    }
    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof BackpackBlockEntity backpackEntity) {
                if (!world.isClientSide) {
                    ItemStack itemStack = new ItemStack(this);
                    CompoundTag tag = new CompoundTag();
                    backpackEntity.saveAdditional(tag);
                    BlockItem.setBlockEntityData(itemStack, ModBlockEntities.BACKPACK_BLOCK_ENTITY.get(), tag);
                    popResource(world, pos, itemStack);
                }
            }
            super.onRemove(state, world, pos, newState, isMoving);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof BackpackBlockEntity) {
                NetworkHooks.openScreen((ServerPlayer) player, (BackpackBlockEntity) blockEntity, pos);
            }
        }
        return InteractionResult.sidedSuccess(world.isClientSide());
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new BackpackBlockEntity(pPos, pState);
    }
}
