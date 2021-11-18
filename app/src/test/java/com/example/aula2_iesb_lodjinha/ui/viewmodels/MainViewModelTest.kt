package com.example.aula2_iesb_lodjinha.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.ViewModel
import com.androiddevs.shoppinglisttestingyt.MainCoroutineRule
import com.example.aula2_iesb_lodjinha.getOrAwaitValueTest
import com.example.aula2_iesb_lodjinha.repositories.LodjinhaRepository
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import retrofit2.Response


class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    lateinit var viewModel: MainViewModel

    val repository: LodjinhaRepository = mockk()

    @Before
    fun setUp() {
        viewModel = MainViewModel(repository)
    }


    @Test
    fun `validar que o mainHomestate eh loading ao iniciar o getMainHomeData`() {
        mainCoroutineRule.runBlockingTest {

            val result = viewModel.homeDataLiveData.getOrAwaitValueTest(
                positionOfValueToBeCatch = 1
            ) {
                coEvery { repository.getBanner() } returns Response.success(null)
                coEvery { repository.getCategoria() } returns Response.success(null)
                coEvery { repository.getMaisVendidos() } returns Response.success(null)

                viewModel.getMainHomeData()
            }

            assertThat(result[0].loading).isTrue()
            assertThat(result[1].loading).isFalse()

        }
    }

    @Test
    fun `validar que o mainState retorne com Erro caso um dos endpoints retorne com erro`() {

    }

    @Test
    fun `Validar que o data State nao eh null quando os 3 requests vierem com success`() {

    }

//    @Test
//     fun `validar que sum retorne a soma correta`() {
//
//
//     }

}