package com.bypv.presentation.viewModel

import android.util.Log
import com.bypv.meliapp.core.Resource
import com.bypv.meliapp.data.model.PicturesProductModel
import com.bypv.meliapp.data.model.ProductItemDescriptionModel
import com.bypv.meliapp.domain.ProductRepository
import com.bypv.meliapp.presentation.viewModels.ProductListDetailViewModel
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
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

class ProductListDetailViewModelTest {

    @MockK
    private lateinit var repository: ProductRepository

    private lateinit var viewModel: ProductListDetailViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mockkStatic(Log::class)
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = ProductListDetailViewModel(repository)
    }

    @After
    fun tearDown() {
        unmockkAll()
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun `getDescription should update state with Success when repository call is successful`() = runBlocking {
        val categoryId = "MLA123"
        coEvery { repository.getDescriptionProduct(categoryId) } returns ProductItemDescriptionModel("", PicturesProductModel(""))

        viewModel.getDescription(categoryId)

        assert(viewModel.state.value is Resource.Success)

        coVerify { repository.getDescriptionProduct(categoryId) }
    }

    @Test
    fun `getDescription should update state with Failure when repository call throws an exception`() = runBlocking {
        val categoryId = "MLA123"
        coEvery { repository.getDescriptionProduct(categoryId) } throws Exception("Failed")
        every { Log.e(any(), any()) } returns 0

        viewModel.getDescription(categoryId)

        assert(viewModel.state.value is Resource.Failure)

        verify { Log.e(any(), any()) }
    }

    @Test
    fun `getPictures should update state with Success when repository call is successful`() {
        val categoryId = "MLA123"
        coEvery { repository.getPicturesProduct(categoryId) } returns ProductItemDescriptionModel("", PicturesProductModel(""))

        viewModel.getPictures(categoryId)

        assert(viewModel.state.value is Resource.Success)

        coVerify { repository.getPicturesProduct(categoryId) }
    }

    @Test
    fun `getPictures should update state with Failure when repository call is successful`() {
        val categoryId = "MLA123"
        coEvery { repository.getPicturesProduct(categoryId) } throws Exception("Failed")
        every { Log.e(any(), any()) } returns 0

        viewModel.getPictures(categoryId)

        verify { Log.e(any(), any()) }
        assert(viewModel.state.value is Resource.Failure)

        coVerify { repository.getPicturesProduct(categoryId) }
    }
}