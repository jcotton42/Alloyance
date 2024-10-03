package me.jcotton42.alloyance.registration

enum class Metal(val id: String, val hardness: Float, val blockBlastResistance: Float, val color: Int) {
    // tier 1
    DEEP_IRON("deep_iron", 3.1f, 10.0f, 0x436182),
    PROMETHEUM("prometheum", 2.9f, 10.0f, 0x6E9E61),
    ZINC("zinc", 2.0f, 10.0f, 0xCACE84),
    TIN("tin", 2.5f, 6.0f, 0xA9A295),
    BRONZE("bronze", 10.0f, 15.0f, 0xCB8B4B),
    BRASS("brass", 10.0f, 10.0f, 0xCDAA3A),
    DAMASCUS_STEEL("damascus_steel", 10.0f, 20.0f, 0xC4BCAA),

    // tier 2
    OSMIUM("osmium", 4.0f, 20.0f, 0x737496),
    SILVER("silver", 2.9f, 10.0f, 0xCDD3D3),
    INFUSCOLIUM("infuscolium", 3.2f, 10.0f, 0x6A3359),
    MANGANESE("manganese", 3.2f, 10.0f, 0xB46C79),
    ANGMALLEN("angmallen", 10.0f, 10.0f, 0xC9B080),
    STEEL("steel", 10.0f, 15.0f, 0xBCBCBC),
    HEPATIZON("hepatizon", 10.0f, 15.0f, 0xD54B72),
    BLACK_STEEL("black_steel", 10.0f, 15.0f, 0x494949),
    ELECTRUM("electrum", 10.0f, 6.0f, 0xEFEF57),

    // tier 3
    ASTRAL_SILVER("astral_silver", 4.1f, 10.0f, 0x1D52A3),
    IGNATIUS("ignatius", 3.1f, 6.0f, 0xFD9663),
    OURECLASE("oureclase", 5.1f, 15.0f, 0xEC8D69),
    RUBRACIUM("rubracium", 8.5f, 10.0f, 0xBE3E52),
    SHADOW_IRON("shadow_iron", 4.2f, 6.0f, 0x806C72),
    QUICKSILVER("quicksilver", 10.0f, 15.0f, 0x62AAA2),

    // tier 4
    CERUCLASE("ceruclase", 4.1f, 15.0f, 0x9ECFD5),
    EXIMITE("eximite", 4.7f, 15.0f, 0x58B396),
    KALENDRITE("kalendrite", 4.7f, 15.0f, 0xE381EA),
    MIDASIUM("midasium", 4.2f, 10.0f, 0xFFBD45),
    ORICHALCUM("orichalcum", 6.1f, 20.0f, 0x9ABC51),
    PLATINUM("platinum", 4.4f, 15.0f, 0x7193D4),
    VULCANITE("vulcanite", 6.5f, 15.0f, 0xE45930),
    CELENEGIL("celenegil", 10.0f, 20.0f, 0x89EC87),
    AMORDRINE("amordrine", 10.0f, 10.0f, 0x9E69AF),

    // tier 5
    CARMOT("carmot", 5.1f, 15f, 0xC39C69),
    LEMURITE("lemurite", 5.5f, 6f, 0xD0D0D0),
    MEUTOITE("meutoite", 3.6f, 15f, 0x332E38),
    MITHRIL("mithril", 6.8f, 15f, 0x69D3BE),
    SANGUINITE("sanguinite", 6.6f, 20f, 0xC34F4F),
    VYROXERES("vyroxeres", 4.7f, 15f, 0x5FC83F),
    SHADOW_STEEL("shadow_steel", 10f, 10f, 0xC6BBB9),
    HADEROTH("haderoth", 10f, 20f, 0x351F22),
    DESICHALKOS("desichalkos", 10f, 15f, 0x8159DD),

    // tier 6
    ATLARUS("atlarus", 9.3f, 15f, 0xF2CB59),
    ADAMANTINE("adamantine", 11.5f, 15f, 0xC62C29),
    ALDUORITE("alduorite", 5.3f, 10f, 0x9294BE),
}
