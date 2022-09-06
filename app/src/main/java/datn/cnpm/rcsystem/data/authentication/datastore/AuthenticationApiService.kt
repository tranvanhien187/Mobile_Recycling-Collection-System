package datn.cnpm.rcsystem.data.authentication.datastore

import datn.cnpm.rcsystem.core.SBResponse
import datn.cnpm.rcsystem.data.authentication.entitiy.Product
import retrofit2.http.GET

interface AuthenticationApiService {
    @GET("/api/v1/Products/getAllProduct")
    suspend fun login() : SBResponse<List<Product>>
}