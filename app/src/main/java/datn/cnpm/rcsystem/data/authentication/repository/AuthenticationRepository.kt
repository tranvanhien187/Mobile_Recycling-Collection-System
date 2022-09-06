package datn.cnpm.rcsystem.data.authentication.repository

import datn.cnpm.rcsystem.data.authentication.entitiy.Product

/*
 * Created by ADMIN on 7/24/2022.
 * Project : Base
 */

interface AuthenticationRepository {
    suspend fun login() : List<Product>
}