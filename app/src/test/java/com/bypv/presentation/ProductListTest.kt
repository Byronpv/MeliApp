package com.bypv.presentation

import android.content.Intent
import android.os.Build
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bypv.meliapp.R
import com.bypv.meliapp.data.model.ProductModel
import com.bypv.meliapp.presentation.ProductList
import com.bypv.meliapp.presentation.ProductListDetail
import com.bypv.meliapp.presentation.adapter.ProductListAdapter
import com.bypv.meliapp.presentation.adapter.ProductViewHolder
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class ProductListTest {

    private lateinit var activityController: ActivityController<ProductList>
    private lateinit var activity: ProductList

    @Before
    fun setUp() {
        activityController = Robolectric.buildActivity(ProductList::class.java)
        activityController.setup()
        activity = activityController.get()
        activity.setTheme(R.style.Theme_MeliApp)
    }

    @Test
    fun `should not be null after activity creation`() {
        Assert.assertNotNull(activity)
    }

    @Test
    fun `should navigate to detail activity on query text submit with non-empty string`() {
        val actual = activity.onQueryTextSubmit("Test")
        assertEquals(true, actual)
    }

    @Test
    fun `should not navigate to detail activity on query text submit with empty string`() {
        val actual = activity.onQueryTextSubmit("")
        assertEquals(false, actual)
    }

    @Test
    fun `should not navigate to detail activity on query text submit when input is null`() {
        val actual = activity.onQueryTextSubmit(null)
        assertEquals(false, actual)
    }

    @Test
    fun `should navigate to detail activity on query text change with specific query`() {
        val actual = activity.onQueryTextChange("tas")
        assertEquals(true, actual)
    }

    @Test
    fun `should navigate to detail activity on item click in product list`() {
        val recycler = activity.findViewById<RecyclerView>(R.id.rvProducts)
        val adapter = recycler.adapter as ProductListAdapter

        adapter.updateData(
            listOf(
                ProductModel(
                    "123",
                    "MyItem",
                    "123",
                    "test",
                    "test",
                    "",
                    "",
                    "",
                )
            )
        )

        recycler.measure(
            View.MeasureSpec.UNSPECIFIED,
            View.MeasureSpec.UNSPECIFIED
        )

        recycler.layout(0, 0, 1000, 1000)

        val viewHolder = recycler
            .findViewHolderForAdapterPosition(0) as ProductViewHolder

        viewHolder.itemView.performClick()

        val expectedIntent = Intent(activity, ProductListDetail::class.java)
        val actual = shadowOf(RuntimeEnvironment.application).nextStartedActivity

        assertEquals(expectedIntent.component, actual.component)
    }
}