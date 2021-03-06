package noobanidus.mods.glimmering.init;

import com.tterrag.registrate.util.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.block.*;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.SingleItemRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.ToolType;
import noobanidus.mods.glimmering.GLTags;
import noobanidus.mods.glimmering.Glimmering;
import noobanidus.mods.glimmering.blocks.AndesiteBowlBlock;
import noobanidus.mods.glimmering.blocks.AndesiteCapstone;
import noobanidus.mods.glimmering.blocks.PolishedAndesiteStairs;
import noobanidus.mods.glimmering.blocks.RitualRuneBlock;

import java.util.function.UnaryOperator;

import static noobanidus.mods.glimmering.Glimmering.REGISTRATE;

@SuppressWarnings("unused")
public class ModBlocks {
  private static NonNullUnaryOperator<Block.Properties> STONE_PROPS = (o) -> o.hardnessAndResistance(3f).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE);

  public static RegistryEntry<Block> BRICKS = REGISTRATE.object("polished_andesite_bricks")
      .block(Block::new)
      .properties(STONE_PROPS)
      .item()
        .properties((o) -> o.group(Glimmering.ITEM_GROUP))
        .build()
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shapedRecipe(ctx.getEntry(), 4)
            .patternLine("XX")
            .patternLine("XX")
            .key('X', Items.POLISHED_ANDESITE)
            .addCriterion("has_andesite", p.hasItem(Items.POLISHED_ANDESITE))
            .build(p);
        SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(Items.POLISHED_ANDESITE), ctx.getEntry())
            .addCriterion("has_andesite", p.hasItem(Items.POLISHED_ANDESITE))
            .build(p, new ResourceLocation(Glimmering.MODID, "polished_andesite_bricks_from_stonecutting"));
      })
      .register();

  public static RegistryEntry<PolishedAndesiteStairs> BRICK_STAIRS = REGISTRATE.object("polished_andesite_brick_stairs")
      .block(PolishedAndesiteStairs::new)
      .properties(STONE_PROPS)
      .blockstate((ctx, p) -> p.stairsBlock(ctx.getEntry(), p.modLoc("block/polished_andesite_bricks")))
      .item()
        .properties((o) -> o.group(Glimmering.ITEM_GROUP))
        .build()
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shapedRecipe(ctx.getEntry(), 4)
            .patternLine("X  ")
            .patternLine("XX ")
            .patternLine("XXX")
            .key('X', ModBlocks.BRICKS.get())
            .addCriterion("has_polished_bricks", p.hasItem(ModBlocks.BRICKS.get()))
            .build(p);
        SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ModBlocks.BRICKS.get()), ctx.getEntry())
            .addCriterion("has_polished_bricks", p.hasItem(ModBlocks.BRICKS.get()))
            .build(p, new ResourceLocation(Glimmering.MODID, "polished_andesite_brick_stairs_from_stonecutting"));
      })
      .register();

  public static RegistryEntry<SlabBlock> BRICK_SLABS = REGISTRATE.object("polished_andesite_brick_slab")
      .block(SlabBlock::new)
      .properties(STONE_PROPS)
      .blockstate((ctx, p) -> p.slabBlock(ctx.getEntry(), BRICKS.get().getRegistryName(), p.modLoc("block/polished_andesite_bricks")))
      .item()
        .properties((o) -> o.group(Glimmering.ITEM_GROUP))
        .build()
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shapedRecipe(ctx.getEntry(), 6)
            .patternLine("XXX")
            .key('X', ModBlocks.BRICKS.get())
            .addCriterion("has_polished_bricks", p.hasItem(ModBlocks.BRICKS.get()))
            .build(p);
        SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ModBlocks.BRICKS.get()), ctx.getEntry(), 2)
            .addCriterion("has_polished_bricks", p.hasItem(ModBlocks.BRICKS.get()))
            .build(p, new ResourceLocation(Glimmering.MODID, "polished_andesite_brick_slabs_from_stonecutting"));
      })
      .register();

  public static RegistryEntry<WallBlock> BRICK_WALL = REGISTRATE.object("polished_andesite_brick_wall")
      .block(WallBlock::new)
      .properties(STONE_PROPS)
      .blockstate((ctx, p) -> {
        p.wallBlock(ctx.getEntry(), p.modLoc("block/polished_andesite_bricks"));
        p.wallInventory(ctx.getName() + "_inventory", p.modLoc("block/polished_andesite_bricks"));
      })
      .item()
        .model((ctx, p) -> p.blockWithInventoryModel(NonNullSupplier.of(ModBlocks.BRICK_WALL)))
        .properties((o) -> o.group(Glimmering.ITEM_GROUP))
        .build()
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shapedRecipe(ctx.getEntry(), 6)
            .patternLine("XXX")
            .patternLine("XXX")
            .key('X', ModBlocks.BRICKS.get())
            .addCriterion("has_polished_bricks", p.hasItem(ModBlocks.BRICKS.get()))
            .build(p);
        SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ModBlocks.BRICKS.get()), ctx.getEntry(), 2)
            .addCriterion("has_polished_bricks", p.hasItem(ModBlocks.BRICKS.get()))
            .build(p, new ResourceLocation(Glimmering.MODID, "polished_andesite_brick_wall_from_stonecutting"));
      })
      .register();

  public static RegistryEntry<AndesiteBowlBlock> ANDESITE_BOWL = REGISTRATE.object("andesite_bowl")
      .block(AndesiteBowlBlock::new)
      .blockstate((ctx, p) -> p.simpleBlock(ctx.getEntry(), p.getExistingFile(p.modLoc(ctx.getName()))))
      .properties(STONE_PROPS)
      .item()
        .properties((o) -> o.group(Glimmering.ITEM_GROUP))
        .build()
      .recipe((ctx, p) ->
          ShapedRecipeBuilder.shapedRecipe(ctx.getEntry(), 1)
              .patternLine("X X")
              .patternLine("SSS")
              .patternLine(" X ")
              .key('X', ModBlocks.BRICKS.get())
              .key('S', ModBlocks.BRICK_SLABS.get())
              .addCriterion("has_bricks", p.hasItem(ModBlocks.BRICKS.get()))
              .build(p))
      .register();

  public static RegistryEntry<RitualRuneBlock> RITUAL_RUNE = REGISTRATE.object("ritual_rune")
      .block(RitualRuneBlock::new)
      .blockstate((ctx, p) -> p.getVariantBuilder(ctx.getEntry()).forAllStates(state -> ConfiguredModel.builder().modelFile(p.cubeTop(ctx.getName() + (state.get(RitualRuneBlock.ACTIVE) ? "_active" : ""), new ResourceLocation("block/polished_andesite"), state.get(RitualRuneBlock.ACTIVE) ? p.modLoc("block/andesite_rune_active") : p.modLoc("block/andesite_rune"))).build()))
      .properties(STONE_PROPS)
      .item()
        .properties((o) -> o.group(Glimmering.ITEM_GROUP))
        .build()
      .recipe((ctx, p) ->
          ShapedRecipeBuilder.shapedRecipe(ctx.getEntry(), 1)
              .patternLine("PXP")
              .patternLine("XDX")
              .patternLine("PXP")
              .key('P', Blocks.POLISHED_ANDESITE)
              .key('X', ModBlocks.BRICKS.get())
              .key('D', GLTags.Items.ELIGIBLE_DUSTS)
              .addCriterion("has_bricks", p.hasItem(ModBlocks.BRICKS.get()))
              .build(p)
      )
      .register();

  public static RegistryEntry<AndesiteCapstone> ANDESITE_CAPSTONE = REGISTRATE.object("andesite_capstone")
      .block(AndesiteCapstone::new)
      .blockstate((ctx, p) -> p.getVariantBuilder(ctx.getEntry()).forAllStates(state -> ConfiguredModel.builder().modelFile(p.cubeAll(ctx.getName() + (state.get(RitualRuneBlock.ACTIVE) ? "_active" : ""), state.get(RitualRuneBlock.ACTIVE) ? p.modLoc("block/andesite_capstone_active") : p.modLoc("block/andesite_capstone"))).build()))
      .item().properties(o -> o.group(Glimmering.ITEM_GROUP)).build()
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shapedRecipe(ctx.getEntry(), 4)
            .patternLine("PDP")
            .patternLine("D D")
            .patternLine("PDP")
            .key('P', Blocks.POLISHED_ANDESITE)
            .key('D', GLTags.Items.ELIGIBLE_DUSTS)
            .addCriterion("has_andesite", p.hasItem(Blocks.POLISHED_ANDESITE))
            .addCriterion("has_glimmer_dust", p.hasItem(GLTags.Items.ELIGIBLE_DUSTS))
            .build(p);
      })
      .register();

  public static void load() {
  }
}
