package com.bypv.presentation.viewModel

import android.util.Log
import com.bypv.meliapp.core.Logger
import com.bypv.meliapp.core.Resource
import com.bypv.meliapp.data.model.ProductResponse
import com.bypv.meliapp.domain.ProductRepository
import com.bypv.meliapp.presentation.viewModels.ProductListViewModel
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class ProductListViewModelTest {

    @MockK
    private lateinit var repository: ProductRepository
    @MockK
    private lateinit var logger: Logger

    private lateinit var viewModel: ProductListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mockkStatic(Log::class)
        Dispatchers.setMain(Dispatchers.Unconfined)
        logger = mockk(relaxed = true)
        viewModel = ProductListViewModel(repository,logger)
    }

    @After
    fun tearDown() {
        unmockkAll()
        Dispatchers.resetMain()
        clearAllMocks()
    }


    @Test
    fun `searchCategoryId should update state with Success when repository call is successful`() = runBlocking {
        val categoryId = "MLA123"
        coEvery { repository.getProducts(categoryId) } returns ProductResponse()

        viewModel.searchCategoryId(categoryId)

        assert(viewModel.state.value is Resource.Success)

        coVerify { repository.getProducts(categoryId) }
    }


    @Test
    fun `searchCategoryId should update state with Failure when repository call throws an exception`() = runBlocking {
        val categoryId = "MLA123"
        coEvery { repository.getProducts(categoryId) } throws  Exception("Failed")

        viewModel.searchCategoryId(categoryId)

        assert(viewModel.state.value is Resource.Failure)

        verify { logger.e(any(), any()) }
    }
}