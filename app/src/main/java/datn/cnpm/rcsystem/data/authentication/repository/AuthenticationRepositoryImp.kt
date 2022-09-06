package datn.cnpm.rcsystem.data.authentication.repository

import datn.cnpm.rcsystem.data.authentication.datastore.AuthenticationDataSource
import datn.cnpm.rcsystem.data.authentication.entitiy.Product
import javax.inject.Inject

/*
 * Created by ADMIN on 7/24/2022.
 * Project : Base
 */

class AuthenticationRepositoryImp @Inject constructor(private val authenticationDataSource: AuthenticationDataSource) :
    AuthenticationRepository {
    override suspend fun login(): List<Product> {

        val response = authenticationDataSource.login()
        if (response.isSuccess) {
            return response.requireData
        } else {
            throw Exception(response.requireError)
        }
    }
}