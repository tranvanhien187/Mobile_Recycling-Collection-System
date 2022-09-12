package datn.cnpm.rcsystem.data.entitiy

/*
 * Created by ADMIN on 7/24/2022.
 * Project : Base
 */

class Product {
    var id: Long? = null
    var productName: String? = null
    var price: String? = null

    constructor() {}
    constructor(productName: String?, price: String?) {
        this.productName = productName
        this.price = price
    }

    override fun toString(): String {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price='" + price + '\'' +
                '}'
    }
}