package me.jcotton42.alloyance.registration

enum class Metal(val id: String, val hardness: Float, val blockBlastResistance: Float) {
    // tier 1
    DEEP_IRON("deep_iron", 3.1f, 10.0f),
    PROMETHEUM("prometheum", 2.9f, 10.0f),
    ZINC("zinc", 2.0f, 10.0f),
    TIN("tin", 2.5f, 6.0f),
    BRONZE("bronze", 10.0f, 15.0f),
    BRASS("brass", 10.0f, 10.0f),
    DAMASCUS_STEEL("damascus_steel", 10.0f, 20.0f),

    // tier 2
    OSMIUM("osmium", 4.0f, 20.0f),
    SILVER("silver", 2.9f, 10.0f),
    INFUSCOLIUM("infuscolium", 3.2f, 10.0f),
    MANGANESE("manganese", 3.2f, 10.0f),
    ANGMALLEN("angmallen", 10.0f, 10.0f),
    STEEL("steel", 10.0f, 15.0f),
    HEPATIZON("hepatizon", 10.0f, 15.0f),
    BLACK_STEEL("black_steel", 10.0f, 15.0f),
    ELECTRUM("electrum", 10.0f, 6.0f),

    // tier 3
    ASTRAL_SILVER("astral_silver", 4.1f, 10.0f),
    IGNATIUS("ignatius", 3.1f, 6.0f),
    OURECLASE("oureclase", 5.1f, 15.0f),
    RUBRACIUM("rubracium", 8.5f, 10.0f),
    SHADOW_IRON("shadow_iron", 4.2f, 6.0f),
    QUICKSILVER("quicksilver", 10.0f, 15.0f),

    // tier 4
    CERUCLASE("ceruclase", 4.1f, 15.0f),
    EXIMITE("eximite", 4.7f, 15.0f),
    KALENDRITE("kalendrite", 4.7f, 15.0f),
}
