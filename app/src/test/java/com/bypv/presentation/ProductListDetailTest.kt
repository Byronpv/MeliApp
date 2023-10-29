package com.bypv.presentation

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import com.bypv.meliapp.R
import com.bypv.meliapp.presentation.ProductList
import com.bypv.meliapp.presentation.ProductListDetail
import io.mockk.MockKAnnotations
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class ProductListDetailTest {

    private lateinit var activityController: ActivityController<ProductListDetail>
    private lateinit var activity: ProductListDetail

    private lateinit var context: Context

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        activityController = Robolectric.buildActivity(ProductListDetail::class.java)
        activityController.setup()
        activity = activityController.get()
        context = ApplicationProvider.getApplicationContext()
        activity.setTheme(R.style.Theme_MeliApp)
    }

    @Test
    fun `should create activity`() {
        Assert.assertNotNull(activity)
    }

    @Test
    fun `Should send info to ProductListDetail`() {
        val intent = Intent(context, ProductList::class.java)
        intent.putExtra("KEY", "Some Data")

        val yourActivity: ProductListDetail = Robolectric.buildActivity(ProductListDetail::class.java, intent).create().get()

        val receivedData: String? = yourActivity.intent.getSerializableExtra("KEY") as? String
        assertEquals("Some Data", receivedData)
    }
}