package datn.cnpm.rcsystem.feature.gift.list


data class GiftCategoryModel(val category: GiftCategory, val src: Int, var isCheck: Boolean = false)

enum class GiftCategory(val value: String) {
    ELECTRONIC("Electronic"),
    HOUSEWARES("Housewares"),
    COSMETIC("Cosmetic"),
    FOOD("Food"),
    BEVERAGE("Beverage"),
    VOUCHER("Voucher"),
    FASHION("Fashion"),
}
