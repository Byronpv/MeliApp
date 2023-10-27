package com.bypv.presentation

import android.app.Activity
import android.os.Build
import androidx.recyclerview.widget.RecyclerView
import com.bypv.meliapp.R
import com.bypv.meliapp.presentation.ProductList
import com.bypv.meliapp.presentation.ProductListDetail
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class ProductListTest {

    private lateinit var activityController: ActivityController<ProductList>
    private lateinit var activity: Activity

    @Before
    fun setUp() {
        activityController = Robolectric.buildActivity(ProductList::class.java)
        activity = activityController.get()
        activity.setTheme(R.style.Theme_MeliApp)
    }

    @Test
    fun should_create_activity() {
        Assert.assertNotNull(activity)
    }

    @Test
    fun should_create_navigate_to_detail_activity() {
        activity.findViewById<RecyclerView>(R.id.rvProducts)
        val intent = Shadows.shadowOf(activity).peekNextStartedActivity()
        assertEquals(ProductListDetail::class.java.canonicalName, intent.component!!.className)
    }
}