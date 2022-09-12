package datn.cnpm.rcsystem.domain.model

import datn.cnpm.rcsystem.data.entitiy.Product

/*
 * Created by ADMIN on 7/24/2022.
 * Project : Base
 */

class Product(var id: Long?, var productName: String?, var price: String?) {

    override fun toString(): String {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price='" + price + '\'' +
                '}'
    }
}

fun Product.toProduct () = Product(this.id, this.productName, this.price)