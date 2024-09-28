package me.jcotton42.alloyance.registration

enum class Metal(val id: String, val hardness: Float, val blockBlastResistance: Float) {
    DEEP_IRON("deep_iron", 3.1f, 10.0f),
    PROMETHEUM("prometheum", 2.9f, 10.0f),
    ZINC("zinc", 2.0f, 10.0f),
    TIN("tin", 2.5f, 6.0f),
    BRONZE("bronze", 10.0f, 15.0f),
    BRASS("brass", 10.0f, 10.0f),
    DAMASCUS_STEEL("damascus_steel", 10.0f, 20.0f),
}
