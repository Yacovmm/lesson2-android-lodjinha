package com.example.aula2_iesb_lodjinha

import org.junit.Test
import com.google.common.truth.Truth.assertThat


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    fun sum(x: Int, y: Int): Int {
        return x + y
    }

    @Test
    fun addition_isCorrect() {

        val expected = 4
        val result = sum(1, 2)


        assertThat(result).isEqualTo(expected)
//        assertEquals(expected, result)
    }
}