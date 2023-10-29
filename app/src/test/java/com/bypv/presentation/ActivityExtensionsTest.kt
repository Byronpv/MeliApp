package com.bypv.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import com.bypv.meliapp.presentation.callErrorActivity
import com.bypv.meliapp.presentation.error.TypeError
import com.bypv.meliapp.presentation.isNetworkAvailable
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(
    sdk = [Build.VERSION_CODES.R]
)
class ActivityExtensionsTest {

    @MockK(relaxed = true)
    private lateinit var mockActivity: Activity

    @MockK(relaxed = true)
    private lateinit var mockConnectivityManager: ConnectivityManager

    @MockK(relaxed = true)
    private lateinit var mockNetwork: Network

    @MockK(relaxed = true)
    private lateinit var mockNetworkCapabilities: NetworkCapabilities

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        every { mockActivity.getSystemService(Context.CONNECTIVITY_SERVICE) } returns mockConnectivityManager
    }

    @Test
    fun testCallErrorActivity() {

        val intent = slot<Intent>()
        every { mockActivity.packageName } returns "com.bypv.meliapp.presentation"
        every { mockActivity.startActivity(capture(intent)) } just Runs

        mockActivity.callErrorActivity(TypeError.NO_INTERNET_ERROR)

        verify { mockActivity.startActivity(any()) }

    }

    @Test
    fun testIsNetworkAvailable() {
        every { mockConnectivityManager.activeNetwork } returns mockNetwork
        every { mockConnectivityManager.getNetworkCapabilities(mockNetwork) } returns mockNetworkCapabilities
        every { mockNetworkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) } returns true

        val isNetworkAvailable = mockActivity.isNetworkAvailable()
        Assert.assertTrue(isNetworkAvailable)
    }

}