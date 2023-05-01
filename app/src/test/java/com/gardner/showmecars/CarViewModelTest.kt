package com.gardner.showmecars

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gardner.showmecars.data.CarViewModel
import com.gardner.showmecars.data.remote.SnappCarApi
import com.gardner.showmecars.data.remote.dto.Address
import com.gardner.showmecars.data.remote.dto.Car
import com.gardner.showmecars.data.remote.dto.CarSearchResponse
import com.gardner.showmecars.data.remote.dto.Flags
import com.gardner.showmecars.data.remote.dto.PriceInformation
import com.gardner.showmecars.data.remote.dto.Result
import com.gardner.showmecars.data.remote.dto.Sums
import com.gardner.showmecars.data.remote.dto.User
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class CarViewModelTest {
    
    private val api = mockk<SnappCarApi>()
    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var viewModel: CarViewModel
    
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = CarViewModel(api)
    }
    
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    
    @Test
    fun `searchCars with valid city and country`() = runTest {
        val city = "Amsterdam"
        val country = "Netherlands"
        val limit = 10
        val expectedCars = listOf(
            Result(
                Car(
                    accessories = listOf("Bluetooth", "Air Conditioning"),
                    address = Address(
                        city = "Amsterdam",
                        countryCode = "NL",
                        street = "123 Some Road"
                    ),
                    allowed = listOf("PETS"),
                    bodyType = "Sedan",
                    carCategory = "ECONOMY",
                    createdAt = "2022-04-01T12:34:56Z",
                    fuelType = "Gasoline",
                    gear = "Automatic",
                    images = listOf("https://example.com/car.jpg"),
                    make = "Honda",
                    model = "Civic",
                    ownerId = "abc123",
                    reviewCount = 10,
                    seats = 5,
                    year = 2020
                ),
                ci = "abc123",
                flags = Flags(
                    favorite = false,
                    instantBookable = true,
                    isKeyless = true,
                    new = false,
                    previouslyRented = true
                ),
                priceInformation = PriceInformation(
                    freeKilometersPerDay = 100,
                    isoCurrencyCode = "EUR",
                    price = 500.00,
                    pricePerKilometer = 50.00,
                    rentalDays = 1,
                ),
                user = User(
                    city = "Amsterdam",
                    firstName = "John",
                    imageUrl = "https://example.com/user.jpg",
                    street = "123 Some Road"
                ),
                distance = 10.0
            )
        )
        
        coEvery { api.getCars(any(), any(), any(), any(), any()) } returns Response.success(
            CarSearchResponse(
                results = expectedCars,
                searchId = "1234565",
                sums = Sums(10)
            )
        )
        
        viewModel.searchCars(city, country, limit)
        
        assertEquals(expectedCars, viewModel.cars.value)
    }
    
    @Test
    fun `searchCars with invalid city and country`() = runTest {
        val city = "Invalid City"
        val country = "Invalid Country"
        val limit = 10
        
        coEvery { api.getCars(any(), any(), any(), any(), any()) } throws Exception("Error")
        
        viewModel.searchCars(city, country, limit)
        
        assertEquals("Error", viewModel.errorMessage.value)
    }
}