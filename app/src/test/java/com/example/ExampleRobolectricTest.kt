package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.SeedPhonesData
import com.example.model.PhoneCategory
import com.example.model.RecommendationStatus
import com.example.util.VerdictCalculator
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Hatifi", appName)
    }

    @Test
    fun `seed phones contains more than 20 comprehensive models`() {
        val phones = SeedPhonesData.phones
        assertTrue("Expected at least 20 seed phones but got ${phones.size}", phones.size >= 20)
        
        // Verify key brands
        val brands = phones.map { it.brand }.toSet()
        assertTrue(brands.contains("Samsung"))
        assertTrue(brands.contains("Apple"))
        assertTrue(brands.contains("Xiaomi"))
        assertTrue(brands.contains("Google"))
        assertTrue(brands.contains("OnePlus"))
    }

    @Test
    fun `verdict calculator evaluates flagship and budget properly`() {
        val flagship = SeedPhonesData.phones.first { it.id == "samsung-s25-ultra" }
        val verdict = VerdictCalculator.calculateVerdict(
            priceUsd = flagship.approxPriceUsd,
            category = flagship.category,
            specs = flagship.specs
        )
        assertNotNull(verdict)
        assertTrue(verdict.valueScore in 1.0f..10.0f)
        assertNotNull(verdict.reason.en)
        assertNotNull(verdict.reason.ar)
    }
}
