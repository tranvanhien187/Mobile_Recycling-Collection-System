package datn.cnpm.rcsystem.data.authentication.datastore

import datn.cnpm.rcsystem.core.SBResponse
import datn.cnpm.rcsystem.data.authentication.entitiy.Product
import javax.inject.Inject

class AuthenticationDataSource @Inject constructor(private val authenticationApiService: AuthenticationApiService) {
    suspend fun login() : SBResponse<List<Product>> {
        return authenticationApiService.login()
    }
}