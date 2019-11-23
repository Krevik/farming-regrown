package krevik.github.io.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

/**
 * @author Krevik
 */
public class BlockCropsWithTraits extends CropsBlock {
    public static final IntegerProperty COLOUR = BlockStateProperties.AGE_0_15;
    public static final IntegerProperty NUTRITION = BlockStateProperties.AGE_0_15;
    public static final IntegerProperty SIZE = BlockStateProperties.AGE_0_15;

    protected BlockCropsWithTraits(Properties builder) {
        super(builder);
        this.setDefaultState(this.stateContainer.getBaseState().with(this.getAgeProperty(), Integer.valueOf(0))
        .with(this.getColourProperty(), Integer.valueOf(0)).with(this.getNutritionProperty(), Integer.valueOf(0)));
    }

    protected static float getGrowthChance(Block blockIn, IBlockReader worldIn, BlockPos pos) {
        float f = 1.0F;
        BlockPos blockpos = pos.down();

        for(int i = -1; i <= 1; ++i) {
            for(int j = -1; j <= 1; ++j) {
                float f1 = 0.0F;
                BlockState blockstate = worldIn.getBlockState(blockpos.add(i, 0, j));
                if (blockstate.canSustainPlant(worldIn, blockpos.add(i, 0, j), net.minecraft.util.Direction.UP, (net.minecraftforge.common.IPlantable)blockIn)) {
                    f1 = 1.0F;
                    if (blockstate.isFertile(worldIn, blockpos.add(i, 0, j))) {
                        f1 = 3.0F;
                    }
                }

                if (i != 0 || j != 0) {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        BlockPos blockpos1 = pos.north();
        BlockPos blockpos2 = pos.south();
        BlockPos blockpos3 = pos.west();
        BlockPos blockpos4 = pos.east();
        boolean flag = blockIn == worldIn.getBlockState(blockpos3).getBlock() || blockIn == worldIn.getBlockState(blockpos4).getBlock();
        boolean flag1 = blockIn == worldIn.getBlockState(blockpos1).getBlock() || blockIn == worldIn.getBlockState(blockpos2).getBlock();
        if (flag && flag1) {
            f /= 2.0F;
        } else {
            boolean flag2 = blockIn == worldIn.getBlockState(blockpos3.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.south()).getBlock() || blockIn == worldIn.getBlockState(blockpos3.south()).getBlock();
            if (flag2) {
                f /= 2.0F;
            }
        }

        return f;
    }

    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return !this.isMaxAge(state);
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE).add(SIZE).add(NUTRITION);
    }

    public IntegerProperty getSizeProperty() {
        return SIZE;
    }

    public int getMaxSize() {
        return 15;
    }

    protected int getSize(BlockState state) {
        return state.get(this.getSizeProperty());
    }

    public BlockState withSize(int size) {
        return this.getDefaultState().with(this.getSizeProperty(), Integer.valueOf(size));
    }

    public boolean isMaxSize(BlockState state) {
        return state.get(this.getSizeProperty()) >= this.getMaxSize();
    }

    public IntegerProperty getColourProperty() {
        return COLOUR;
    }

    public int getMaxColour() {
        return 15;
    }

    protected int getColour(BlockState state) {
        return state.get(this.getColourProperty());
    }

    public BlockState withColour(int colour) {
        return this.getDefaultState().with(this.getColourProperty(), Integer.valueOf(colour));
    }

    public boolean isMaxColour(BlockState state) {
        return state.get(this.getColourProperty()) >= this.getMaxColour();
    }

    public IntegerProperty getNutritionProperty() {
        return SIZE;
    }

    public int getMaxNutrition() {
        return 15;
    }

    protected int getNutrition(BlockState state) {
        return state.get(this.getNutritionProperty());
    }

    public BlockState withNutrition(int nutrition) {
        return this.getDefaultState().with(this.getNutritionProperty(), Integer.valueOf(nutrition));
    }

    public boolean isMaxNutrition(BlockState state) {
        return state.get(this.getNutritionProperty()) >= this.getMaxNutrition();
    }

}
