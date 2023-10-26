package com.bypv.domain

import com.bypv.meliapp.data.model.PicturesProductModel
import com.bypv.meliapp.data.model.ProductItemDescriptionModel
import com.bypv.meliapp.data.model.ProductResponse
import com.bypv.meliapp.data.remote.RemoteDataSource
import com.bypv.meliapp.domain.ProductRepository
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import net.bytebuddy.matcher.ElementMatchers.any
import org.junit.After
import org.junit.Before
import org.junit.Test

class ProductRepositoryTest {

    @MockK
    private lateinit var remoteDataSourceMock: RemoteDataSource

    private lateinit var productRepository: ProductRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        productRepository = ProductRepository(remoteDataSourceMock)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `When getProducts() is called, remoteDataSource getProducts() isCalled`() = runBlocking {
        coEvery { remoteDataSourceMock.getProducts("MLA123") } returns ProductResponse()

        productRepository.getProducts("MLA123")

        coVerify { remoteDataSourceMock.getProducts("MLA123") }
    }

    @Test
    fun `When getDescriptionProduct() is called, remoteDataSource getDescriptionProduct() isCalled`() = runBlocking {
        coEvery { remoteDataSourceMock.getDescriptionProduct("MLA123") } returns ProductItemDescriptionModel("", PicturesProductModel(""))

        productRepository.getDescriptionProduct("MLA123")

        coVerify { remoteDataSourceMock.getDescriptionProduct("MLA123") }
    }

    @Test
    fun `When getPicturesProduct() is called, remoteDataSource getPicturesProduct() isCalled`() = runBlocking {
        coEvery { remoteDataSourceMock.getPicturesProduct("MLA123") } returns ProductItemDescriptionModel("", PicturesProductModel(""))

        productRepository.getPicturesProduct("MLA123")

        coVerify { remoteDataSourceMock.getPicturesProduct("MLA123") }
    }
}