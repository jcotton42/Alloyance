package me.jcotton42.alloyance.registration

import me.jcotton42.alloyance.Alloyance
import me.jcotton42.alloyance.client.TooltipStyle
import me.jcotton42.alloyance.registration.Metal.*
import net.minecraft.core.Holder
import net.minecraft.core.component.DataComponents
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.ArmorMaterial
import net.minecraft.world.item.AxeItem
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.BucketItem
import net.minecraft.world.item.HoeItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.PickaxeItem
import net.minecraft.world.item.ShovelItem
import net.minecraft.world.item.SwordItem
import net.minecraft.world.item.Tier
import net.minecraft.world.item.component.ItemLore
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.material.Fluid
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredItem
import net.neoforged.neoforge.registries.DeferredRegister

object AlloyanceItems {
    val ITEMS = DeferredRegister.createItems(Alloyance.ID)

    val AXES = mutableMapOf<Metal, DeferredItem<AxeItem>>()
    val BOOTS = mutableMapOf<Metal, DeferredItem<ArmorItem>>()
    val BUCKETS = mutableMapOf<DeferredHolder<Fluid, out Fluid>, DeferredItem<BucketItem>>()
    val CHESTPLATES = mutableMapOf<Metal, DeferredItem<ArmorItem>>()
    val DUSTS = mutableMapOf<Metal, DeferredItem<Item>>()
    val HELMETS = mutableMapOf<Metal, DeferredItem<ArmorItem>>()
    val HOES = mutableMapOf<Metal, DeferredItem<HoeItem>>()
    val INGOTS = mutableMapOf<Metal, DeferredItem<Item>>()
    val LEGGINGS = mutableMapOf<Metal, DeferredItem<ArmorItem>>()
    val NUGGETS = mutableMapOf<Metal, DeferredItem<Item>>()
    val PICKAXES = mutableMapOf<Metal, DeferredItem<PickaxeItem>>()
    val RAW_MATERIALS = mutableMapOf<Metal, DeferredItem<Item>>()
    val SHOVELS = mutableMapOf<Metal, DeferredItem<ShovelItem>>()
    val SWORDS = mutableMapOf<Metal, DeferredItem<SwordItem>>()

    val ALLOYER = block(AlloyanceBlocks.ALLOYER)
    val CRUSHER = block(AlloyanceBlocks.CRUSHER)

    val BIMETAL_STRUCTURE = block(AlloyanceBlocks.BIMETAL_STRUCTURE)

    val COPPER_AXE = axe("copper", MetalTiers.COPPER)
    val COPPER_BOOTS = boots("copper", AlloyanceArmorMaterials.COPPER, 5)
    val COPPER_CHESTPLATE = chestplate("copper", AlloyanceArmorMaterials.COPPER, 5)
    val COPPER_DUST = ITEMS.registerSimpleItem("copper_dust")
    val COPPER_HELMET = helmet("copper", AlloyanceArmorMaterials.COPPER, 5)
    val COPPER_HOE = hoe("copper", MetalTiers.COPPER)
    val COPPER_LEGGINGS = leggings("copper", AlloyanceArmorMaterials.COPPER, 5)
    val COPPER_PICKAXE = pickaxe("copper", MetalTiers.COPPER)
    val COPPER_SHOVEL = shovel("copper", MetalTiers.COPPER)
    val COPPER_SWORD = sword("copper", MetalTiers.COPPER)

    val GOLD_DUST = ITEMS.registerSimpleItem("gold_dust")
    val IRON_DUST = ITEMS.registerSimpleItem("iron_dust")

    val DEEPSLATE_PHOSPHORITE_ORE = block(AlloyanceBlocks.DEEPSLATE_PHOSPHORITE_ORE)
    val PHOSPHORITE_ORE = block(AlloyanceBlocks.PHOSPHORITE_ORE)
    val PHOSPHORUS = ITEMS.registerSimpleItem("phosphorus")

    val DEEPSLATE_POTASH_ORE = block(AlloyanceBlocks.DEEPSLATE_POTASH_ORE)
    val POTASH = ITEMS.registerSimpleItem("potash")
    val POTASH_BLOCK = block(AlloyanceBlocks.POTASH_BLOCK)
    val POTASH_ORE = block(AlloyanceBlocks.POTASH_ORE)

    val DEEPSLATE_SULFUR_ORE = block(AlloyanceBlocks.DEEPSLATE_SULFUR_ORE)
    val SULFUR = ITEMS.registerSimpleItem("sulfur")
    val SULFUR_BLOCK = block(AlloyanceBlocks.SULFUR_BLOCK)
    val SULFUR_ORE = block(AlloyanceBlocks.SULFUR_ORE)

    // TODO fireproof for Infused Ignatius and Thermite Dust? Also, look at potential tags.
    val BITUMEN = ITEMS.registerSimpleItem("bitumen")
    val INFUSED_IGNATIUS = ITEMS.registerSimpleItem("infused_ignatius", Item.Properties().lore("tooltip.alloyance.infused_ignatius"))
    val MOLTEN_TAR_BUCKET = bucket(AlloyanceFluids.MOLTEN_TAR)
    val TAR = ITEMS.registerSimpleItem("tar")
    val TAR_ORE = block(AlloyanceBlocks.TAR_ORE)
    val THERMITE_DUST = ITEMS.registerSimpleItem("thermite_dust", Item.Properties().lore("tooltip.alloyance.thermite_dust"))

    val DEEP_IRON_AXE = axe(DEEP_IRON)
    val DEEP_IRON_BLOCK = block(AlloyanceBlocks.DEEP_IRON_BLOCK, DEEP_IRON)
    val DEEP_IRON_BOOTS = boots(DEEP_IRON, 15)
    val DEEP_IRON_CHESTPLATE = chestplate(DEEP_IRON, 15)
    val DEEP_IRON_DUST = dust(DEEP_IRON)
    val DEEP_IRON_HELMET = helmet(DEEP_IRON, 15)
    val DEEP_IRON_HOE = hoe(DEEP_IRON)
    val DEEP_IRON_INGOT = ingot(DEEP_IRON)
    val DEEP_IRON_LEGGINGS = leggings(DEEP_IRON, 15)
    val DEEP_IRON_NUGGET = nugget(DEEP_IRON)
    val DEEP_IRON_ORE = block(AlloyanceBlocks.DEEP_IRON_ORE, DEEP_IRON)
    val DEEP_IRON_PICKAXE = pickaxe(DEEP_IRON)
    val DEEP_IRON_SHOVEL = shovel(DEEP_IRON)
    val DEEP_IRON_SWORD = sword(DEEP_IRON)
    val DEEPSLATE_DEEP_IRON_ORE = block(AlloyanceBlocks.DEEPSLATE_DEEP_IRON_ORE, DEEP_IRON)
    val RAW_DEEP_IRON = rawMaterial(DEEP_IRON)

    val DEEPSLATE_PROMETHEUM_ORE = block(AlloyanceBlocks.DEEPSLATE_PROMETHEUM_ORE, PROMETHEUM)
    val PROMETHEUM_AXE = axe(PROMETHEUM)
    val PROMETHEUM_BLOCK = block(AlloyanceBlocks.PROMETHEUM_BLOCK, PROMETHEUM)
    val PROMETHEUM_BOOTS = boots(PROMETHEUM, 15)
    val PROMETHEUM_CHESTPLATE = chestplate(PROMETHEUM, 15)
    val PROMETHEUM_DUST = dust(PROMETHEUM)
    val PROMETHEUM_HELMET = helmet(PROMETHEUM, 15)
    val PROMETHEUM_HOE = hoe(PROMETHEUM)
    val PROMETHEUM_INGOT = ingot(PROMETHEUM)
    val PROMETHEUM_LEGGINGS = leggings(PROMETHEUM, 15)
    val PROMETHEUM_NUGGET = nugget(PROMETHEUM)
    val PROMETHEUM_ORE = block(AlloyanceBlocks.PROMETHEUM_ORE, PROMETHEUM)
    val PROMETHEUM_PICKAXE = pickaxe(PROMETHEUM)
    val PROMETHEUM_SHOVEL = shovel(PROMETHEUM)
    val PROMETHEUM_SWORD = sword(PROMETHEUM)
    val RAW_PROMETHEUM = rawMaterial(PROMETHEUM)

    val DEEPSLATE_ZINC_ORE = block(AlloyanceBlocks.DEEPSLATE_ZINC_ORE, ZINC)
    val RAW_ZINC = rawMaterial(ZINC)
    val ZINC_BLOCK = block(AlloyanceBlocks.ZINC_BLOCK, ZINC)
    val ZINC_DUST = dust(ZINC)
    val ZINC_INGOT = ingot(ZINC)
    val ZINC_NUGGET = nugget(ZINC)
    val ZINC_ORE = block(AlloyanceBlocks.ZINC_ORE, ZINC)

    val DEEPSLATE_TIN_ORE = block(AlloyanceBlocks.DEEPSLATE_TIN_ORE, TIN)
    val RAW_TIN = rawMaterial(TIN)
    val TIN_BLOCK = block(AlloyanceBlocks.TIN_BLOCK, TIN)
    val TIN_DUST = dust(TIN)
    val TIN_INGOT = ingot(TIN)
    val TIN_NUGGET = nugget(TIN)
    val TIN_ORE = block(AlloyanceBlocks.TIN_ORE, TIN)

    val BRONZE_AXE = axe(BRONZE)
    val BRONZE_BLOCK = block(AlloyanceBlocks.BRONZE_BLOCK, BRONZE)
    val BRONZE_BOOTS = boots(BRONZE, 25)
    val BRONZE_CHESTPLATE = chestplate(BRONZE, 25)
    val BRONZE_DUST = dust(BRONZE)
    val BRONZE_HELMET = helmet(BRONZE, 25)
    val BRONZE_HOE = hoe(BRONZE)
    val BRONZE_INGOT = ingot(BRONZE)
    val BRONZE_LEGGINGS = leggings(BRONZE, 25)
    val BRONZE_NUGGET = nugget(BRONZE)
    val BRONZE_PICKAXE = pickaxe(BRONZE)
    val BRONZE_SHOVEL = shovel(BRONZE)
    val BRONZE_SWORD = sword(BRONZE)

    val BRASS_AXE = axe(BRASS)
    val BRASS_BLOCK = block(AlloyanceBlocks.BRASS_BLOCK, BRASS)
    val BRASS_BOOTS = boots(BRASS, 14)
    val BRASS_CHESTPLATE = chestplate(BRASS, 14)
    val BRASS_DUST = dust(BRASS)
    val BRASS_HELMET = helmet(BRASS, 14)
    val BRASS_HOE = hoe(BRASS)
    val BRASS_INGOT = ingot(BRASS)
    val BRASS_LEGGINGS = leggings(BRASS, 14)
    val BRASS_NUGGET = nugget(BRASS)
    val BRASS_PICKAXE = pickaxe(BRASS)
    val BRASS_SHOVEL = shovel(BRASS)
    val BRASS_SWORD = sword(BRASS)

    val DAMASCUS_STEEL_BLOCK = block(AlloyanceBlocks.DAMASCUS_STEEL_BLOCK, DAMASCUS_STEEL)
    val DAMASCUS_STEEL_AXE = axe(DAMASCUS_STEEL)
    val DAMASCUS_STEEL_HOE = hoe(DAMASCUS_STEEL)
    val DAMASCUS_STEEL_PICKAXE = pickaxe(DAMASCUS_STEEL)
    val DAMASCUS_STEEL_SHOVEL = shovel(DAMASCUS_STEEL)
    val DAMASCUS_STEEL_SWORD = sword(DAMASCUS_STEEL)
    val DAMASCUS_STEEL_HELMET = helmet(DAMASCUS_STEEL, 16)
    val DAMASCUS_STEEL_CHESTPLATE = chestplate(DAMASCUS_STEEL, 16)
    val DAMASCUS_STEEL_LEGGINGS = leggings(DAMASCUS_STEEL, 16)
    val DAMASCUS_STEEL_BOOTS = boots(DAMASCUS_STEEL, 16)
    val DAMASCUS_STEEL_DUST = dust(DAMASCUS_STEEL)
    val DAMASCUS_STEEL_INGOT = ingot(DAMASCUS_STEEL)
    val DAMASCUS_STEEL_NUGGET = nugget(DAMASCUS_STEEL)

    val DEEPSLATE_OSMIUM_ORE = block(AlloyanceBlocks.DEEPSLATE_OSMIUM_ORE, OSMIUM)
    val OSMIUM_BLOCK = block(AlloyanceBlocks.OSMIUM_BLOCK, OSMIUM)
    val OSMIUM_HELMET = helmet(OSMIUM, 5)
    val OSMIUM_CHESTPLATE = chestplate(OSMIUM, 5)
    val OSMIUM_LEGGINGS = leggings(OSMIUM, 5)
    val OSMIUM_BOOTS = boots(OSMIUM, 5)
    val OSMIUM_DUST = dust(OSMIUM)
    val OSMIUM_INGOT = ingot(OSMIUM)
    val OSMIUM_NUGGET = nugget(OSMIUM)
    val OSMIUM_ORE = block(AlloyanceBlocks.OSMIUM_ORE, OSMIUM)
    val RAW_OSMIUM = rawMaterial(OSMIUM)

    val DEEPSLATE_SILVER_ORE = block(AlloyanceBlocks.DEEPSLATE_SILVER_ORE, SILVER)
    val RAW_SILVER = rawMaterial(SILVER)
    val SILVER_BLOCK = block(AlloyanceBlocks.SILVER_BLOCK, SILVER)
    val SILVER_AXE = axe(SILVER)
    val SILVER_HOE = hoe(SILVER)
    val SILVER_PICKAXE = pickaxe(SILVER)
    val SILVER_SHOVEL = shovel(SILVER)
    val SILVER_SWORD = sword(SILVER)
    val SILVER_HELMET = helmet(SILVER, 8)
    val SILVER_CHESTPLATE = chestplate(SILVER, 8)
    val SILVER_LEGGINGS = leggings(SILVER, 8)
    val SILVER_BOOTS = boots(SILVER, 8)
    val SILVER_DUST = dust(SILVER)
    val SILVER_INGOT = ingot(SILVER)
    val SILVER_NUGGET = nugget(SILVER)
    val SILVER_ORE = block(AlloyanceBlocks.SILVER_ORE, SILVER)

    val DEEPSLATE_INFUSCOLIUM_ORE = block(AlloyanceBlocks.DEEPSLATE_INFUSCOLIUM_ORE, INFUSCOLIUM)
    val INFUSCOLIUM_BLOCK = block(AlloyanceBlocks.INFUSCOLIUM_BLOCK, INFUSCOLIUM)
    val INFUSCOLIUM_DUST = dust(INFUSCOLIUM)
    val INFUSCOLIUM_INGOT = ingot(INFUSCOLIUM)
    val INFUSCOLIUM_NUGGET = nugget(INFUSCOLIUM)
    val INFUSCOLIUM_ORE = block(AlloyanceBlocks.INFUSCOLIUM_ORE, INFUSCOLIUM)
    val RAW_INFUSCOLIUM = rawMaterial(INFUSCOLIUM)

    val DEEPSLATE_MANGANESE_ORE = block(AlloyanceBlocks.DEEPSLATE_MANGANESE_ORE, MANGANESE)
    val MANGANESE_BLOCK = block(AlloyanceBlocks.MANGANESE_BLOCK, MANGANESE)
    val MANGANESE_DUST = dust(MANGANESE)
    val MANGANESE_INGOT = ingot(MANGANESE)
    val MANGANESE_NUGGET = nugget(MANGANESE)
    val MANGANESE_ORE = block(AlloyanceBlocks.MANGANESE_ORE, MANGANESE)
    val RAW_MANGANESE = rawMaterial(MANGANESE)

    val ANGMALLEN_BLOCK = block(AlloyanceBlocks.ANGMALLEN_BLOCK, ANGMALLEN)
    val ANGMALLEN_AXE = axe(ANGMALLEN)
    val ANGMALLEN_HOE = hoe(ANGMALLEN)
    val ANGMALLEN_PICKAXE = pickaxe(ANGMALLEN)
    val ANGMALLEN_SHOVEL = shovel(ANGMALLEN)
    val ANGMALLEN_SWORD = sword(ANGMALLEN)
    val ANGMALLEN_HELMET = helmet(ANGMALLEN, 8)
    val ANGMALLEN_CHESTPLATE = chestplate(ANGMALLEN, 8)
    val ANGMALLEN_LEGGINGS = leggings(ANGMALLEN, 8)
    val ANGMALLEN_BOOTS = boots(ANGMALLEN, 8)
    val ANGMALLEN_DUST = dust(ANGMALLEN)
    val ANGMALLEN_INGOT = ingot(ANGMALLEN)
    val ANGMALLEN_NUGGET = nugget(ANGMALLEN)

    val STEEL_BLOCK = block(AlloyanceBlocks.STEEL_BLOCK, STEEL)
    val STEEL_AXE = axe(STEEL)
    val STEEL_HOE = hoe(STEEL)
    val STEEL_PICKAXE = pickaxe(STEEL)
    val STEEL_SHOVEL = shovel(STEEL)
    val STEEL_SWORD = sword(STEEL)
    val STEEL_HELMET = helmet(STEEL, 21)
    val STEEL_CHESTPLATE = chestplate(STEEL, 21)
    val STEEL_LEGGINGS = leggings(STEEL, 21)
    val STEEL_BOOTS = boots(STEEL, 21)
    val STEEL_DUST = dust(STEEL)
    val STEEL_INGOT = ingot(STEEL)
    val STEEL_NUGGET = nugget(STEEL)

    val HEPATIZON_BLOCK = block(AlloyanceBlocks.HEPATIZON_BLOCK, HEPATIZON)
    val HEPATIZON_AXE = axe(HEPATIZON)
    val HEPATIZON_HOE = hoe(HEPATIZON)
    val HEPATIZON_PICKAXE = pickaxe(HEPATIZON)
    val HEPATIZON_SHOVEL = shovel(HEPATIZON)
    val HEPATIZON_SWORD = sword(HEPATIZON)
    val HEPATIZON_HELMET = helmet(HEPATIZON, 28)
    val HEPATIZON_CHESTPLATE = chestplate(HEPATIZON, 28)
    val HEPATIZON_LEGGINGS = leggings(HEPATIZON, 28)
    val HEPATIZON_BOOTS = boots(HEPATIZON, 28)
    val HEPATIZON_DUST = dust(HEPATIZON)
    val HEPATIZON_INGOT = ingot(HEPATIZON)
    val HEPATIZON_NUGGET = nugget(HEPATIZON)

    val BLACK_STEEL_BLOCK = block(AlloyanceBlocks.BLACK_STEEL_BLOCK, BLACK_STEEL)
    val BLACK_STEEL_AXE = axe(BLACK_STEEL)
    val BLACK_STEEL_HOE = hoe(BLACK_STEEL)
    val BLACK_STEEL_PICKAXE = pickaxe(BLACK_STEEL)
    val BLACK_STEEL_SHOVEL = shovel(BLACK_STEEL)
    val BLACK_STEEL_SWORD = sword(BLACK_STEEL)
    val BLACK_STEEL_HELMET = helmet(BLACK_STEEL, 28)
    val BLACK_STEEL_CHESTPLATE = chestplate(BLACK_STEEL, 28)
    val BLACK_STEEL_LEGGINGS = leggings(BLACK_STEEL, 28)
    val BLACK_STEEL_BOOTS = boots(BLACK_STEEL, 28)
    val BLACK_STEEL_DUST = dust(BLACK_STEEL)
    val BLACK_STEEL_INGOT = ingot(BLACK_STEEL)
    val BLACK_STEEL_NUGGET = nugget(BLACK_STEEL)

    val ELECTRUM_BLOCK = block(AlloyanceBlocks.ELECTRUM_BLOCK, ELECTRUM)
    val ELECTRUM_AXE = axe(ELECTRUM)
    val ELECTRUM_HOE = hoe(ELECTRUM)
    val ELECTRUM_PICKAXE = pickaxe(ELECTRUM)
    val ELECTRUM_SHOVEL = shovel(ELECTRUM)
    val ELECTRUM_SWORD = sword(ELECTRUM)
    val ELECTRUM_HELMET = helmet(ELECTRUM, 32)
    val ELECTRUM_CHESTPLATE = chestplate(ELECTRUM, 32)
    val ELECTRUM_LEGGINGS = leggings(ELECTRUM, 32)
    val ELECTRUM_BOOTS = boots(ELECTRUM, 32)
    val ELECTRUM_DUST = dust(ELECTRUM)
    val ELECTRUM_INGOT = ingot(ELECTRUM)
    val ELECTRUM_NUGGET = nugget(ELECTRUM)

    val ASTRAL_SILVER_BLOCK = block(AlloyanceBlocks.ASTRAL_SILVER_BLOCK, ASTRAL_SILVER)
    val ASTRAL_SILVER_AXE = axe(ASTRAL_SILVER)
    val ASTRAL_SILVER_HOE = hoe(ASTRAL_SILVER)
    val ASTRAL_SILVER_PICKAXE = pickaxe(ASTRAL_SILVER)
    val ASTRAL_SILVER_SHOVEL = shovel(ASTRAL_SILVER)
    val ASTRAL_SILVER_SWORD = sword(ASTRAL_SILVER)
    val ASTRAL_SILVER_HELMET = helmet(ASTRAL_SILVER, 15)
    val ASTRAL_SILVER_CHESTPLATE = chestplate(ASTRAL_SILVER, 15)
    val ASTRAL_SILVER_LEGGINGS = leggings(ASTRAL_SILVER, 15)
    val ASTRAL_SILVER_BOOTS = boots(ASTRAL_SILVER, 15)
    val ASTRAL_SILVER_DUST = dust(ASTRAL_SILVER)
    val ASTRAL_SILVER_INGOT = ingot(ASTRAL_SILVER)
    val ASTRAL_SILVER_NUGGET = nugget(ASTRAL_SILVER)
    val ASTRAL_SILVER_ORE = block(AlloyanceBlocks.ASTRAL_SILVER_ORE, ASTRAL_SILVER)
    val DEEPSLATE_ASTRAL_SILVER_ORE = block(AlloyanceBlocks.DEEPSLATE_ASTRAL_SILVER_ORE, ASTRAL_SILVER)
    val RAW_ASTRAL_SILVER = rawMaterial(ASTRAL_SILVER)

    val IGNATIUS_BLOCK = block(AlloyanceBlocks.IGNATIUS_BLOCK, IGNATIUS)
    val IGNATIUS_AXE = axe(IGNATIUS)
    val IGNATIUS_HOE = hoe(IGNATIUS)
    val IGNATIUS_PICKAXE = pickaxe(IGNATIUS)
    val IGNATIUS_SHOVEL = shovel(IGNATIUS)
    val IGNATIUS_SWORD = sword(IGNATIUS)
    val IGNATIUS_HELMET = helmet(IGNATIUS, 5)
    val IGNATIUS_CHESTPLATE = chestplate(IGNATIUS, 5)
    val IGNATIUS_LEGGINGS = leggings(IGNATIUS, 5)
    val IGNATIUS_BOOTS = boots(IGNATIUS, 5)
    val IGNATIUS_DUST = dust(IGNATIUS)
    val IGNATIUS_INGOT = ingot(IGNATIUS)
    val IGNATIUS_NUGGET = nugget(IGNATIUS)
    val NETHER_IGNATIUS_ORE = block(AlloyanceBlocks.NETHER_IGNATIUS_ORE, IGNATIUS)
    val RAW_IGNATIUS = rawMaterial(IGNATIUS)

    val DEEPSLATE_OURECLASE_ORE = block(AlloyanceBlocks.DEEPSLATE_OURECLASE_ORE, OURECLASE)
    val OURECLASE_BLOCK = block(AlloyanceBlocks.OURECLASE_BLOCK, OURECLASE)
    val OURECLASE_AXE = axe(OURECLASE)
    val OURECLASE_HOE = hoe(OURECLASE)
    val OURECLASE_PICKAXE = pickaxe(OURECLASE)
    val OURECLASE_SHOVEL = shovel(OURECLASE)
    val OURECLASE_SWORD = sword(OURECLASE)
    val OURECLASE_HELMET = helmet(OURECLASE, 36)
    val OURECLASE_CHESTPLATE = chestplate(OURECLASE, 36)
    val OURECLASE_LEGGINGS = leggings(OURECLASE, 36)
    val OURECLASE_BOOTS = boots(OURECLASE, 36)
    val OURECLASE_DUST = dust(OURECLASE)
    val OURECLASE_INGOT = ingot(OURECLASE)
    val OURECLASE_NUGGET = nugget(OURECLASE)
    val OURECLASE_ORE = block(AlloyanceBlocks.OURECLASE_ORE, OURECLASE)
    val RAW_OURECLASE = rawMaterial(OURECLASE)

    val DEEPSLATE_RUBRACIUM_ORE = block(AlloyanceBlocks.DEEPSLATE_RUBRACIUM_ORE, RUBRACIUM)
    val RAW_RUBRACIUM = rawMaterial(RUBRACIUM)
    val RUBRACIUM_BLOCK = block(AlloyanceBlocks.RUBRACIUM_BLOCK, RUBRACIUM)
    val RUBRACIUM_DUST = dust(RUBRACIUM)
    val RUBRACIUM_INGOT = ingot(RUBRACIUM)
    val RUBRACIUM_NUGGET = nugget(RUBRACIUM)
    val RUBRACIUM_ORE = block(AlloyanceBlocks.RUBRACIUM_ORE, RUBRACIUM)

    val NETHER_SHADOW_IRON_ORE = block(AlloyanceBlocks.NETHER_SHADOW_IRON_ORE, SHADOW_IRON)
    val RAW_SHADOW_IRON = rawMaterial(SHADOW_IRON)
    val SHADOW_IRON_BLOCK = block(AlloyanceBlocks.SHADOW_IRON_BLOCK, SHADOW_IRON)
    val SHADOW_IRON_AXE = axe(SHADOW_IRON)
    val SHADOW_IRON_HOE = hoe(SHADOW_IRON)
    val SHADOW_IRON_PICKAXE = pickaxe(SHADOW_IRON)
    val SHADOW_IRON_SHOVEL = shovel(SHADOW_IRON)
    val SHADOW_IRON_SWORD = sword(SHADOW_IRON)
    val SHADOW_IRON_HELMET = helmet(SHADOW_IRON, 17)
    val SHADOW_IRON_CHESTPLATE = chestplate(SHADOW_IRON, 17)
    val SHADOW_IRON_LEGGINGS = leggings(SHADOW_IRON, 17)
    val SHADOW_IRON_BOOTS = boots(SHADOW_IRON, 17)
    val SHADOW_IRON_DUST = dust(SHADOW_IRON)
    val SHADOW_IRON_INGOT = ingot(SHADOW_IRON)
    val SHADOW_IRON_NUGGET = nugget(SHADOW_IRON)

    val QUICKSILVER_BLOCK = block(AlloyanceBlocks.QUICKSILVER_BLOCK, QUICKSILVER)
    val QUICKSILVER_AXE = axe(QUICKSILVER)
    val QUICKSILVER_HOE = hoe(QUICKSILVER)
    val QUICKSILVER_PICKAXE = pickaxe(QUICKSILVER)
    val QUICKSILVER_SHOVEL = shovel(QUICKSILVER)
    val QUICKSILVER_SWORD = sword(QUICKSILVER)
    val QUICKSILVER_HELMET = helmet(QUICKSILVER, 50)
    val QUICKSILVER_CHESTPLATE = chestplate(QUICKSILVER, 50)
    val QUICKSILVER_LEGGINGS = leggings(QUICKSILVER, 50)
    val QUICKSILVER_BOOTS = boots(QUICKSILVER, 50)
    val QUICKSILVER_DUST = dust(QUICKSILVER)
    val QUICKSILVER_INGOT = ingot(QUICKSILVER)
    val QUICKSILVER_NUGGET = nugget(QUICKSILVER)

    val CERUCLASE_BLOCK = block(AlloyanceBlocks.CERUCLASE_BLOCK, CERUCLASE)
    val CERUCLASE_AXE = axe(CERUCLASE)
    val CERUCLASE_HOE = hoe(CERUCLASE)
    val CERUCLASE_PICKAXE = pickaxe(CERUCLASE)
    val CERUCLASE_SHOVEL = shovel(CERUCLASE)
    val CERUCLASE_SWORD = sword(CERUCLASE)
    val CERUCLASE_HELMET = helmet(CERUCLASE, 137)
    val CERUCLASE_CHESTPLATE = chestplate(CERUCLASE, 137)
    val CERUCLASE_LEGGINGS = leggings(CERUCLASE, 137)
    val CERUCLASE_BOOTS = boots(CERUCLASE, 137)
    val CERUCLASE_DUST = dust(CERUCLASE)
    val CERUCLASE_INGOT = ingot(CERUCLASE)
    val CERUCLASE_NUGGET = nugget(CERUCLASE)
    val NETHER_CERUCLASE_ORE = block(AlloyanceBlocks.NETHER_CERUCLASE_ORE, CERUCLASE)
    val RAW_CERUCLASE = rawMaterial(CERUCLASE)

    val END_EXIMITE_ORE = block(AlloyanceBlocks.END_EXIMITE_ORE, EXIMITE)
    val EXIMITE_BLOCK = block(AlloyanceBlocks.EXIMITE_BLOCK, EXIMITE)
    val EXIMITE_AXE = axe(EXIMITE)
    val EXIMITE_HOE = hoe(EXIMITE)
    val EXIMITE_PICKAXE = pickaxe(EXIMITE)
    val EXIMITE_SHOVEL = shovel(EXIMITE)
    val EXIMITE_SWORD = sword(EXIMITE)
    val EXIMITE_HELMET = helmet(EXIMITE, 21)
    val EXIMITE_CHESTPLATE = chestplate(EXIMITE, 21)
    val EXIMITE_LEGGINGS = leggings(EXIMITE, 21)
    val EXIMITE_BOOTS = boots(EXIMITE, 21)
    val EXIMITE_DUST = dust(EXIMITE)
    val EXIMITE_INGOT = ingot(EXIMITE)
    val EXIMITE_NUGGET = nugget(EXIMITE)
    val RAW_EXIMITE = rawMaterial(EXIMITE)

    val KALENDRITE_BLOCK = block(AlloyanceBlocks.KALENDRITE_BLOCK, KALENDRITE)
    val KALENDRITE_AXE = axe(KALENDRITE)
    val KALENDRITE_HOE = hoe(KALENDRITE)
    val KALENDRITE_PICKAXE = pickaxe(KALENDRITE)
    val KALENDRITE_SHOVEL = shovel(KALENDRITE)
    val KALENDRITE_SWORD = sword(KALENDRITE)
    val KALENDRITE_HELMET = helmet(KALENDRITE, 15)
    val KALENDRITE_CHESTPLATE = chestplate(KALENDRITE, 15)
    val KALENDRITE_LEGGINGS = leggings(KALENDRITE, 15)
    val KALENDRITE_BOOTS = boots(KALENDRITE, 15)
    val KALENDRITE_DUST = dust(KALENDRITE)
    val KALENDRITE_INGOT = ingot(KALENDRITE)
    val KALENDRITE_NUGGET = nugget(KALENDRITE)
    val NETHER_KALENDRITE_ORE = block(AlloyanceBlocks.NETHER_KALENDRITE_ORE, KALENDRITE)
    val RAW_KALENDRITE = rawMaterial(KALENDRITE)

    val MIDASIUM_BLOCK = block(AlloyanceBlocks.MIDASIUM_BLOCK, MIDASIUM)
    val MIDASIUM_AXE = axe(MIDASIUM)
    val MIDASIUM_HOE = hoe(MIDASIUM)
    val MIDASIUM_PICKAXE = pickaxe(MIDASIUM)
    val MIDASIUM_SHOVEL = shovel(MIDASIUM)
    val MIDASIUM_SWORD = sword(MIDASIUM)
    val MIDASIUM_HELMET = helmet(MIDASIUM, 14)
    val MIDASIUM_CHESTPLATE = chestplate(MIDASIUM, 14)
    val MIDASIUM_LEGGINGS = leggings(MIDASIUM, 14)
    val MIDASIUM_BOOTS = boots(MIDASIUM, 14)
    val MIDASIUM_DUST = dust(MIDASIUM)
    val MIDASIUM_INGOT = ingot(MIDASIUM)
    val MIDASIUM_NUGGET = nugget(MIDASIUM)
    val NETHER_MIDASIUM_ORE = block(AlloyanceBlocks.NETHER_MIDASIUM_ORE, MIDASIUM)
    val RAW_MIDASIUM = rawMaterial(MIDASIUM)

    val DEEPSLATE_ORICHALCUM_ORE = block(AlloyanceBlocks.DEEPSLATE_ORICHALCUM_ORE, ORICHALCUM)
    val ORICHALCUM_BLOCK = block(AlloyanceBlocks.ORICHALCUM_BLOCK, ORICHALCUM)
    val ORICHALCUM_AXE = axe(ORICHALCUM)
    val ORICHALCUM_HOE = hoe(ORICHALCUM)
    val ORICHALCUM_PICKAXE = pickaxe(ORICHALCUM)
    val ORICHALCUM_SHOVEL = shovel(ORICHALCUM)
    val ORICHALCUM_SWORD = sword(ORICHALCUM)
    val ORICHALCUM_HELMET = helmet(ORICHALCUM, 20)
    val ORICHALCUM_CHESTPLATE = chestplate(ORICHALCUM, 20)
    val ORICHALCUM_LEGGINGS = leggings(ORICHALCUM, 20)
    val ORICHALCUM_BOOTS = boots(ORICHALCUM, 20)
    val ORICHALCUM_DUST = dust(ORICHALCUM)
    val ORICHALCUM_INGOT = ingot(ORICHALCUM)
    val ORICHALCUM_NUGGET = nugget(ORICHALCUM)
    val ORICHALCUM_ORE = block(AlloyanceBlocks.ORICHALCUM_ORE, ORICHALCUM)
    val RAW_ORICHALCUM = rawMaterial(ORICHALCUM)

    val DEEPSLATE_PLATINUM_ORE = block(AlloyanceBlocks.DEEPSLATE_PLATINUM_ORE, PLATINUM)
    val PLATINUM_BLOCK = block(AlloyanceBlocks.PLATINUM_BLOCK, PLATINUM)
    val PLATINUM_AXE = axe(PLATINUM)
    val PLATINUM_HOE = hoe(PLATINUM)
    val PLATINUM_PICKAXE = pickaxe(PLATINUM)
    val PLATINUM_SHOVEL = shovel(PLATINUM)
    val PLATINUM_SWORD = sword(PLATINUM)
    val PLATINUM_HELMET = helmet(PLATINUM, 20)
    val PLATINUM_CHESTPLATE = chestplate(PLATINUM, 20)
    val PLATINUM_LEGGINGS = leggings(PLATINUM, 20)
    val PLATINUM_BOOTS = boots(PLATINUM, 20)
    val PLATINUM_DUST = dust(PLATINUM)
    val PLATINUM_INGOT = ingot(PLATINUM)
    val PLATINUM_NUGGET = nugget(PLATINUM)
    val PLATINUM_ORE = block(AlloyanceBlocks.PLATINUM_ORE, PLATINUM)
    val RAW_PLATINUM = rawMaterial(PLATINUM)

    val NETHER_VULCANITE_ORE = block(AlloyanceBlocks.NETHER_VULCANITE_ORE, VULCANITE)
    val RAW_VULCANITE = rawMaterial(VULCANITE)
    val VULCANITE_BLOCK = block(AlloyanceBlocks.VULCANITE_BLOCK, VULCANITE)
    val VULCANITE_AXE = axe(VULCANITE)
    val VULCANITE_HOE = hoe(VULCANITE)
    val VULCANITE_PICKAXE = pickaxe(VULCANITE)
    val VULCANITE_SHOVEL = shovel(VULCANITE)
    val VULCANITE_SWORD = sword(VULCANITE)
    val VULCANITE_HELMET = helmet(VULCANITE, 111)
    val VULCANITE_CHESTPLATE = chestplate(VULCANITE, 111)
    val VULCANITE_LEGGINGS = leggings(VULCANITE, 111)
    val VULCANITE_BOOTS = boots(VULCANITE, 111)
    val VULCANITE_DUST = dust(VULCANITE)
    val VULCANITE_INGOT = ingot(VULCANITE)
    val VULCANITE_NUGGET = nugget(VULCANITE)

    val CELENEGIL_BLOCK = block(AlloyanceBlocks.CELENEGIL_BLOCK, CELENEGIL)
    val CELENEGIL_AXE = axe(CELENEGIL)
    val CELENEGIL_HOE = hoe(CELENEGIL)
    val CELENEGIL_PICKAXE = pickaxe(CELENEGIL)
    val CELENEGIL_SHOVEL = shovel(CELENEGIL)
    val CELENEGIL_SWORD = sword(CELENEGIL)
    val CELENEGIL_HELMET = helmet(CELENEGIL, 42)
    val CELENEGIL_CHESTPLATE = chestplate(CELENEGIL, 42)
    val CELENEGIL_LEGGINGS = leggings(CELENEGIL, 42)
    val CELENEGIL_BOOTS = boots(CELENEGIL, 42)
    val CELENEGIL_DUST = dust(CELENEGIL)
    val CELENEGIL_INGOT = ingot(CELENEGIL)
    val CELENEGIL_NUGGET = nugget(CELENEGIL)

    val AMORDRINE_BLOCK = block(AlloyanceBlocks.AMORDRINE_BLOCK, AMORDRINE)
    val AMORDRINE_AXE = axe(AMORDRINE)
    val AMORDRINE_HOE = hoe(AMORDRINE)
    val AMORDRINE_PICKAXE = pickaxe(AMORDRINE)
    val AMORDRINE_SHOVEL = shovel(AMORDRINE)
    val AMORDRINE_SWORD = sword(AMORDRINE)
    val AMORDRINE_HELMET = helmet(AMORDRINE, 55)
    val AMORDRINE_CHESTPLATE = chestplate(AMORDRINE, 55)
    val AMORDRINE_LEGGINGS = leggings(AMORDRINE, 55)
    val AMORDRINE_BOOTS = boots(AMORDRINE, 55)
    val AMORDRINE_DUST = dust(AMORDRINE)
    val AMORDRINE_INGOT = ingot(AMORDRINE)
    val AMORDRINE_NUGGET = nugget(AMORDRINE)

    val CARMOT_BLOCK = block(AlloyanceBlocks.CARMOT_BLOCK, CARMOT)
    val CARMOT_AXE = axe(CARMOT)
    val CARMOT_HOE = hoe(CARMOT)
    val CARMOT_PICKAXE = pickaxe(CARMOT)
    val CARMOT_SHOVEL = shovel(CARMOT)
    val CARMOT_SWORD = sword(CARMOT)
    val CARMOT_HELMET = helmet(CARMOT, 19)
    val CARMOT_CHESTPLATE = chestplate(CARMOT, 19)
    val CARMOT_LEGGINGS = leggings(CARMOT, 19)
    val CARMOT_BOOTS = boots(CARMOT, 19)
    val CARMOT_DUST = dust(CARMOT)
    val CARMOT_INGOT = ingot(CARMOT)
    val CARMOT_NUGGET = nugget(CARMOT)
    val CARMOT_ORE = block(AlloyanceBlocks.CARMOT_ORE, CARMOT)
    val DEEPSLATE_CARMOT_ORE = block(AlloyanceBlocks.DEEPSLATE_CARMOT_ORE, CARMOT)
    val RAW_CARMOT = rawMaterial(CARMOT)

    val LEMURITE_BLOCK = block(AlloyanceBlocks.LEMURITE_BLOCK, LEMURITE)
    val LEMURITE_DUST = dust(LEMURITE)
    val LEMURITE_INGOT = ingot(LEMURITE)
    val LEMURITE_NUGGET = nugget(LEMURITE)
    val NETHER_LEMURITE_ORE = block(AlloyanceBlocks.NETHER_LEMURITE_ORE, LEMURITE)
    val RAW_LEMURITE = rawMaterial(LEMURITE)

    val END_MEUTOITE_ORE = block(AlloyanceBlocks.END_MEUTOITE_ORE, MEUTOITE)
    val MEUTOITE_BLOCK = block(AlloyanceBlocks.MEUTOITE_BLOCK, MEUTOITE)
    val MEUTOITE_DUST = dust(MEUTOITE)
    val MEUTOITE_INGOT = ingot(MEUTOITE)
    val MEUTOITE_NUGGET = nugget(MEUTOITE)
    val RAW_MEUTOITE = rawMaterial(MEUTOITE)

    val DEEPSLATE_MITHRIL_ORE = block(AlloyanceBlocks.DEEPSLATE_MITHRIL_ORE, MITHRIL)
    val MITHRIL_BLOCK = block(AlloyanceBlocks.MITHRIL_BLOCK, MITHRIL)
    val MITHRIL_AXE = axe(MITHRIL)
    val MITHRIL_HOE = hoe(MITHRIL)
    val MITHRIL_PICKAXE = pickaxe(MITHRIL)
    val MITHRIL_SHOVEL = shovel(MITHRIL)
    val MITHRIL_SWORD = sword(MITHRIL)
    val MITHRIL_HELMET = helmet(MITHRIL, 21)
    val MITHRIL_CHESTPLATE = chestplate(MITHRIL, 21)
    val MITHRIL_LEGGINGS = leggings(MITHRIL, 21)
    val MITHRIL_BOOTS = boots(MITHRIL, 21)
    val MITHRIL_DUST = dust(MITHRIL)
    val MITHRIL_INGOT = ingot(MITHRIL)
    val MITHRIL_NUGGET = nugget(MITHRIL)
    val MITHRIL_ORE = block(AlloyanceBlocks.MITHRIL_ORE, MITHRIL)
    val RAW_MITHRIL = rawMaterial(MITHRIL)

    val NETHER_SANGUINITE_ORE = block(AlloyanceBlocks.NETHER_SANGUINITE_ORE, SANGUINITE)
    val RAW_SANGUINITE = rawMaterial(SANGUINITE)
    val SANGUINITE_BLOCK = block(AlloyanceBlocks.SANGUINITE_BLOCK, SANGUINITE)
    val SANGUINITE_AXE = axe(SANGUINITE)
    val SANGUINITE_HOE = hoe(SANGUINITE)
    val SANGUINITE_PICKAXE = pickaxe(SANGUINITE)
    val SANGUINITE_SHOVEL = shovel(SANGUINITE)
    val SANGUINITE_SWORD = sword(SANGUINITE)
    val SANGUINITE_HELMET = helmet(SANGUINITE, 175)
    val SANGUINITE_CHESTPLATE = chestplate(SANGUINITE, 175)
    val SANGUINITE_LEGGINGS = leggings(SANGUINITE, 175)
    val SANGUINITE_BOOTS = boots(SANGUINITE, 175)
    val SANGUINITE_DUST = dust(SANGUINITE)
    val SANGUINITE_INGOT = ingot(SANGUINITE)
    val SANGUINITE_NUGGET = nugget(SANGUINITE)

    val NETHER_VYROXERES_ORE = block(AlloyanceBlocks.NETHER_VYROXERES_ORE, VYROXERES)
    val RAW_VYROXERES = rawMaterial(VYROXERES)
    val VYROXERES_BLOCK = block(AlloyanceBlocks.VYROXERES_BLOCK, VYROXERES)
    val VYROXERES_AXE = axe(VYROXERES)
    val VYROXERES_HOE = hoe(VYROXERES)
    val VYROXERES_PICKAXE = pickaxe(VYROXERES)
    val VYROXERES_SHOVEL = shovel(VYROXERES)
    val VYROXERES_SWORD = sword(VYROXERES)
    val VYROXERES_HELMET = helmet(VYROXERES, 37)
    val VYROXERES_CHESTPLATE = chestplate(VYROXERES, 37)
    val VYROXERES_LEGGINGS = leggings(VYROXERES, 37)
    val VYROXERES_BOOTS = boots(VYROXERES, 37)
    val VYROXERES_DUST = dust(VYROXERES)
    val VYROXERES_INGOT = ingot(VYROXERES)
    val VYROXERES_NUGGET = nugget(VYROXERES)

    val SHADOW_STEEL_BLOCK = block(AlloyanceBlocks.SHADOW_STEEL_BLOCK, SHADOW_STEEL)
    val SHADOW_STEEL_AXE = axe(SHADOW_STEEL)
    val SHADOW_STEEL_HOE = hoe(SHADOW_STEEL)
    val SHADOW_STEEL_PICKAXE = pickaxe(SHADOW_STEEL)
    val SHADOW_STEEL_SHOVEL = shovel(SHADOW_STEEL)
    val SHADOW_STEEL_SWORD = sword(SHADOW_STEEL)
    val SHADOW_STEEL_HELMET = helmet(SHADOW_STEEL, 21)
    val SHADOW_STEEL_CHESTPLATE = chestplate(SHADOW_STEEL, 21)
    val SHADOW_STEEL_LEGGINGS = leggings(SHADOW_STEEL, 21)
    val SHADOW_STEEL_BOOTS = boots(SHADOW_STEEL, 21)
    val SHADOW_STEEL_DUST = dust(SHADOW_STEEL)
    val SHADOW_STEEL_INGOT = ingot(SHADOW_STEEL)
    val SHADOW_STEEL_NUGGET = nugget(SHADOW_STEEL)

    val HADEROTH_BLOCK = block(AlloyanceBlocks.HADEROTH_BLOCK, HADEROTH)
    val HADEROTH_AXE = axe(HADEROTH)
    val HADEROTH_HOE = hoe(HADEROTH)
    val HADEROTH_PICKAXE = pickaxe(HADEROTH)
    val HADEROTH_SHOVEL = shovel(HADEROTH)
    val HADEROTH_SWORD = sword(HADEROTH)
    val HADEROTH_HELMET = helmet(HADEROTH, 5)
    val HADEROTH_CHESTPLATE = chestplate(HADEROTH, 5)
    val HADEROTH_LEGGINGS = leggings(HADEROTH, 5)
    val HADEROTH_BOOTS = boots(HADEROTH, 5)
    val HADEROTH_DUST = dust(HADEROTH)
    val HADEROTH_INGOT = ingot(HADEROTH)
    val HADEROTH_NUGGET = nugget(HADEROTH)

    val DESICHALKOS_BLOCK = block(AlloyanceBlocks.DESICHALKOS_BLOCK, DESICHALKOS)
    val DESICHALKOS_AXE = axe(DESICHALKOS)
    val DESICHALKOS_HOE = hoe(DESICHALKOS)
    val DESICHALKOS_PICKAXE = pickaxe(DESICHALKOS)
    val DESICHALKOS_SHOVEL = shovel(DESICHALKOS)
    val DESICHALKOS_SWORD = sword(DESICHALKOS)
    val DESICHALKOS_HELMET = helmet(DESICHALKOS, 112)
    val DESICHALKOS_CHESTPLATE = chestplate(DESICHALKOS, 112)
    val DESICHALKOS_LEGGINGS = leggings(DESICHALKOS, 112)
    val DESICHALKOS_BOOTS = boots(DESICHALKOS, 112)
    val DESICHALKOS_DUST = dust(DESICHALKOS)
    val DESICHALKOS_INGOT = ingot(DESICHALKOS)
    val DESICHALKOS_NUGGET = nugget(DESICHALKOS)

    val ATLARUS_BLOCK = block(AlloyanceBlocks.ATLARUS_BLOCK, ATLARUS)
    val ATLARUS_AXE = axe(ATLARUS)
    val ATLARUS_HOE = hoe(ATLARUS)
    val ATLARUS_PICKAXE = pickaxe(ATLARUS)
    val ATLARUS_SHOVEL = shovel(ATLARUS)
    val ATLARUS_SWORD = sword(ATLARUS)
    val ATLARUS_HELMET = helmet(ATLARUS, 55)
    val ATLARUS_CHESTPLATE = chestplate(ATLARUS, 55)
    val ATLARUS_LEGGINGS = leggings(ATLARUS, 55)
    val ATLARUS_BOOTS = boots(ATLARUS, 55)
    val ATLARUS_DUST = dust(ATLARUS)
    val ATLARUS_INGOT = ingot(ATLARUS)
    val ATLARUS_NUGGET = nugget(ATLARUS)
    val ATLARUS_ORE = block(AlloyanceBlocks.ATLARUS_ORE, ATLARUS)
    val DEEPSLATE_ATLARUS_ORE = block(AlloyanceBlocks.DEEPSLATE_ATLARUS_ORE, ATLARUS)
    val RAW_ATLARUS = rawMaterial(ATLARUS)

    val ADAMANTINE_BLOCK = block(AlloyanceBlocks.ADAMANTINE_BLOCK, ADAMANTINE)
    val ADAMANTINE_AXE = axe(ADAMANTINE)
    val ADAMANTINE_HOE = hoe(ADAMANTINE)
    val ADAMANTINE_PICKAXE = pickaxe(ADAMANTINE)
    val ADAMANTINE_SHOVEL = shovel(ADAMANTINE)
    val ADAMANTINE_SWORD = sword(ADAMANTINE)
    val ADAMANTINE_HELMET = helmet(ADAMANTINE, 96)
    val ADAMANTINE_CHESTPLATE = chestplate(ADAMANTINE, 96)
    val ADAMANTINE_LEGGINGS = leggings(ADAMANTINE, 96)
    val ADAMANTINE_BOOTS = boots(ADAMANTINE, 96)
    val ADAMANTINE_DUST = dust(ADAMANTINE)
    val ADAMANTINE_INGOT = ingot(ADAMANTINE)
    val ADAMANTINE_NUGGET = nugget(ADAMANTINE)
    val ADAMANTINE_ORE = block(AlloyanceBlocks.ADAMANTINE_ORE, ADAMANTINE)
    val DEEPSLATE_ADAMANTINE_ORE = block(AlloyanceBlocks.DEEPSLATE_ADAMANTINE_ORE, ADAMANTINE)
    val RAW_ADAMANTINE = rawMaterial(ADAMANTINE)

    val ALDUORITE_BLOCK = block(AlloyanceBlocks.ALDUORITE_BLOCK, ALDUORITE)
    val ALDUORITE_DUST = dust(ALDUORITE)
    val ALDUORITE_INGOT = ingot(ALDUORITE)
    val ALDUORITE_NUGGET = nugget(ALDUORITE)
    val NETHER_ALDUORITE_ORE = block(AlloyanceBlocks.NETHER_ALDUORITE_ORE, ALDUORITE)
    val RAW_ALDUORITE = rawMaterial(ALDUORITE)

    val LUTETIUM_BLOCK = block(AlloyanceBlocks.LUTETIUM_BLOCK, LUTETIUM)
    val LUTETIUM_HELMET = helmet(LUTETIUM, 50)
    val LUTETIUM_CHESTPLATE = chestplate(LUTETIUM, 50)
    val LUTETIUM_LEGGINGS = leggings(LUTETIUM, 50)
    val LUTETIUM_BOOTS = boots(LUTETIUM, 50)
    val LUTETIUM_DUST = dust(LUTETIUM)
    val LUTETIUM_INGOT = ingot(LUTETIUM)
    val LUTETIUM_NUGGET = nugget(LUTETIUM)
    val NETHER_LUTETIUM_ORE = block(AlloyanceBlocks.NETHER_LUTETIUM_ORE, LUTETIUM)
    val RAW_LUTETIUM = rawMaterial(LUTETIUM)

    val INOLASHITE_BLOCK = block(AlloyanceBlocks.INOLASHITE_BLOCK, INOLASHITE)
    val INOLASHITE_AXE = axe(INOLASHITE)
    val INOLASHITE_HOE = hoe(INOLASHITE)
    val INOLASHITE_PICKAXE = pickaxe(INOLASHITE)
    val INOLASHITE_SHOVEL = shovel(INOLASHITE)
    val INOLASHITE_SWORD = sword(INOLASHITE)
    val INOLASHITE_HELMET = helmet(INOLASHITE, 61)
    val INOLASHITE_CHESTPLATE = chestplate(INOLASHITE, 61)
    val INOLASHITE_LEGGINGS = leggings(INOLASHITE, 61)
    val INOLASHITE_BOOTS = boots(INOLASHITE, 61)
    val INOLASHITE_DUST = dust(INOLASHITE)
    val INOLASHITE_INGOT = ingot(INOLASHITE)
    val INOLASHITE_NUGGET = nugget(INOLASHITE)

    val KRIK_BLOCK = block(AlloyanceBlocks.KRIK_BLOCK, KRIK)
    val KRIK_AXE = axe(KRIK)
    val KRIK_HOE = hoe(KRIK)
    val KRIK_PICKAXE = pickaxe(KRIK)
    val KRIK_SHOVEL = shovel(KRIK)
    val KRIK_SWORD = sword(KRIK)
    val KRIK_HELMET = helmet(KRIK, 36)
    val KRIK_CHESTPLATE = chestplate(KRIK, 36)
    val KRIK_LEGGINGS = leggings(KRIK, 36)
    val KRIK_BOOTS = boots(KRIK, 36)
    val KRIK_DUST = dust(KRIK)
    val KRIK_INGOT = ingot(KRIK)
    val KRIK_NUGGET = nugget(KRIK)

    val TARTARITE_BLOCK = block(AlloyanceBlocks.TARTARITE_BLOCK, TARTARITE)
    val TARTARITE_AXE = axe(TARTARITE)
    val TARTARITE_HOE = hoe(TARTARITE)
    val TARTARITE_PICKAXE = pickaxe(TARTARITE)
    val TARTARITE_SHOVEL = shovel(TARTARITE)
    val TARTARITE_SWORD = sword(TARTARITE)
    val TARTARITE_HELMET = helmet(TARTARITE, 298)
    val TARTARITE_CHESTPLATE = chestplate(TARTARITE, 298)
    val TARTARITE_LEGGINGS = leggings(TARTARITE, 298)
    val TARTARITE_BOOTS = boots(TARTARITE, 298)
    val TARTARITE_DUST = dust(TARTARITE)
    val TARTARITE_INGOT = ingot(TARTARITE)
    val TARTARITE_NUGGET = nugget(TARTARITE)

    val ETHERIUM_BLOCK = block(AlloyanceBlocks.ETHERIUM_BLOCK, ETHERIUM)
    val ETHERIUM_AXE = axe(ETHERIUM)
    val ETHERIUM_HOE = hoe(ETHERIUM)
    val ETHERIUM_PICKAXE = pickaxe(ETHERIUM)
    val ETHERIUM_SHOVEL = shovel(ETHERIUM)
    val ETHERIUM_SWORD = sword(ETHERIUM)
    val ETHERIUM_HELMET = helmet(ETHERIUM, 273)
    val ETHERIUM_CHESTPLATE = chestplate(ETHERIUM, 273)
    val ETHERIUM_LEGGINGS = leggings(ETHERIUM, 273)
    val ETHERIUM_BOOTS = boots(ETHERIUM, 273)
    val ETHERIUM_DUST = dust(ETHERIUM)
    val ETHERIUM_INGOT = ingot(ETHERIUM)
    val ETHERIUM_NUGGET = nugget(ETHERIUM)

    fun register(bus: IEventBus) {
        ITEMS.register(bus)
    }

    private fun block(block: Holder<Block>): DeferredItem<BlockItem> {
        return ITEMS.registerSimpleBlockItem(block)
    }

    private fun block(block: Holder<Block>, metal: Metal): DeferredItem<BlockItem> {
        val blockName = block.unwrapKey().orElseThrow().location().path
        return ITEMS.register(blockName) { -> BlockItem(block.value(), Item.Properties().tooltipColor(metal.color)) }
    }

    private fun rawMaterial(metal: Metal): DeferredItem<Item> {
        val item = ITEMS.register("raw_${metal.id}") { -> Item(Item.Properties().tooltipColor(metal.color)) }
        RAW_MATERIALS[metal] = item
        return item
    }

    private fun ingot(metal: Metal): DeferredItem<Item> {
        val item = ITEMS.register("${metal.id}_ingot") { -> Item(Item.Properties().tooltipColor(metal.color)) }
        INGOTS[metal] = item
        return item
    }

    private fun nugget(metal: Metal): DeferredItem<Item> {
        val item = ITEMS.register("${metal.id}_nugget") { -> Item(Item.Properties().tooltipColor(metal.color)) }
        NUGGETS[metal] = item
        return item
    }

    private fun dust(metal: Metal): DeferredItem<Item> {
        val item = ITEMS.register("${metal.id}_dust") { -> Item(Item.Properties().tooltipColor(metal.color)) }
        DUSTS[metal] = item
        return item
    }

    private fun bucket(fluid: DeferredHolder<Fluid, out Fluid>): DeferredItem<BucketItem> {
        val item = ITEMS.register("${fluid.id.path}_bucket") { ->
            BucketItem(fluid.get(), Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1))
        }
        BUCKETS[fluid] = item
        return item
    }

    private fun axe(metal: Metal): DeferredItem<AxeItem> {
        val item = axe(metal.id, MetalTiers.TIERS.getValue(metal), metal.color)
        AXES[metal] = item
        return item
    }

    private fun axe(name: String, tier: Tier, color: Int? = null): DeferredItem<AxeItem> {
        val item = ITEMS.register("${name}_axe") { ->
            val props = Item.Properties().attributes(AxeItem.createAttributes(
                tier,
                4f,
                -3.4f
            ))
            color?.let(props::tooltipColor)
            AxeItem(
                tier,
                props
            )
        }
        return item
    }

    private fun hoe(metal: Metal): DeferredItem<HoeItem> {
        val item = hoe(metal.id, MetalTiers.TIERS.getValue(metal), metal.color)
        HOES[metal] = item
        return item
    }

    private fun hoe(name: String, tier: Tier, color: Int? = null): DeferredItem<HoeItem> {
        val item = ITEMS.register("${name}_hoe") { ->
            val props = Item.Properties().attributes(HoeItem.createAttributes(
                tier,
                3f,
                -2.4f
            ))
            color?.let(props::tooltipColor)
            HoeItem(
                tier,
                props
            )
        }
        return item
    }

    private fun pickaxe(metal: Metal): DeferredItem<PickaxeItem> {
        val item = pickaxe(metal.id, MetalTiers.TIERS.getValue(metal), metal.color)
        PICKAXES[metal] = item
        return item
    }

    private fun pickaxe(name: String, tier: Tier, color: Int? = null): DeferredItem<PickaxeItem> {
        val item = ITEMS.register("${name}_pickaxe") { ->
            val props = Item.Properties().attributes(PickaxeItem.createAttributes(
                tier,
                1.0f,
                -2.8f
            ))
            color?.let(props::tooltipColor)
            PickaxeItem(
                tier,
                props
            )
        }
        return item
    }

    private fun shovel(metal: Metal): DeferredItem<ShovelItem> {
        val item = shovel(metal.id, MetalTiers.TIERS.getValue(metal), metal.color)
        SHOVELS[metal] = item
        return item
    }

    private fun shovel(name: String, tier: Tier, color: Int? = null): DeferredItem<ShovelItem> {
        val item = ITEMS.register("${name}_shovel") { ->
            val props = Item.Properties().attributes(ShovelItem.createAttributes(
                tier,
                1.5f,
                -3f
            ))
            color?.let(props::tooltipColor)
            ShovelItem(
                tier,
                props
            )
        }
        return item
    }

    private fun sword(metal: Metal): DeferredItem<SwordItem> {
        val item = sword(metal.id, MetalTiers.TIERS.getValue(metal), metal.color)
        SWORDS[metal] = item
        return item
    }

    private fun sword(name: String, tier: Tier, color: Int? = null): DeferredItem<SwordItem> {
        val item = ITEMS.register("${name}_sword") { ->
            val props = Item.Properties().attributes(SwordItem.createAttributes(
                tier,
                3,
                -2.4f
            ))
            color?.let(props::tooltipColor)
            SwordItem(
                tier,
                props
            )
        }
        return item
    }

    private fun helmet(metal: Metal, durability: Int): DeferredItem<ArmorItem> {
        val item = helmet(metal.id, AlloyanceArmorMaterials.MATERIALS.getValue(metal), durability, metal.color)
        HELMETS[metal] = item
        return item
    }

    private fun helmet(name: String, material: Holder<ArmorMaterial?>, durability: Int, color: Int? = null): DeferredItem<ArmorItem> {
        val item = ITEMS.register("${name}_helmet") { ->
            val props = Item.Properties()
                .durability(ArmorItem.Type.HELMET.getDurability(durability))
            color?.let(props::tooltipColor)
            ArmorItem(
                material,
                ArmorItem.Type.HELMET,
                props
            )
        }
        return item
    }

    private fun chestplate(metal: Metal, durability: Int): DeferredItem<ArmorItem> {
        val item = chestplate(metal.id, AlloyanceArmorMaterials.MATERIALS.getValue(metal), durability, metal.color)
        CHESTPLATES[metal] = item
        return item
    }

    private fun chestplate(name: String, material: Holder<ArmorMaterial?>, durability: Int, color: Int? = null): DeferredItem<ArmorItem> {
        val item = ITEMS.register("${name}_chestplate") { ->
            val props = Item.Properties()
                .durability(ArmorItem.Type.CHESTPLATE.getDurability(durability))
            color?.let(props::tooltipColor)
            ArmorItem(
                material,
                ArmorItem.Type.CHESTPLATE,
                props
            )
        }
        return item
    }

    private fun leggings(metal: Metal, durability: Int): DeferredItem<ArmorItem> {
        val item = leggings(metal.id, AlloyanceArmorMaterials.MATERIALS.getValue(metal), durability, metal.color)
        LEGGINGS[metal] = item
        return item
    }

    private fun leggings(name: String, material: Holder<ArmorMaterial?>, durability: Int, color: Int? = null): DeferredItem<ArmorItem> {
        val item = ITEMS.register("${name}_leggings") { ->
            val props = Item.Properties()
                .durability(ArmorItem.Type.LEGGINGS.getDurability(durability))
            color?.let(props::tooltipColor)
            ArmorItem(
                material,
                ArmorItem.Type.LEGGINGS,
                props
            )
        }
        return item
    }

    private fun boots(metal: Metal, durability: Int): DeferredItem<ArmorItem> {
        val item = boots(metal.id, AlloyanceArmorMaterials.MATERIALS.getValue(metal), durability, metal.color)
        BOOTS[metal] = item
        return item
    }

    private fun boots(name: String, material: Holder<ArmorMaterial?>, durability: Int, color: Int? = null): DeferredItem<ArmorItem> {
        val item = ITEMS.register("${name}_boots") { ->
            val props = Item.Properties()
                .durability(ArmorItem.Type.BOOTS.getDurability(durability))
            color?.let(props::tooltipColor)
            ArmorItem(
                material,
                ArmorItem.Type.BOOTS,
                props
            )
        }
        return item
    }
}

private fun Item.Properties.lore(translationKey: String): Item.Properties {
    return component(
        DataComponents.LORE, ItemLore(listOf(Component.translatable(translationKey)))
    )
}

private fun Item.Properties.tooltipColor(color: Int): Item.Properties {
    return component(
        AlloyanceDataComponents.TOOLTIP_STYLE, TooltipStyle(color)
    )
}