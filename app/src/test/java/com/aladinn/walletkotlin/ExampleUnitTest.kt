package com.aladinn.walletkotlin

import android.os.Environment
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)

        var strArr: Array<String> = arrayOf("sd", "fdfdf", "x")

//        print(maxStr(strArr))
//        print(maxStrLength(strArr, { a, b -> a.length > b.length }))


//        2.file



    }

    fun <T> maxStrLength(strArr: Array<T>, gen: (T, T) -> Boolean): T? {

        var max: T? = null

        for (item in strArr) {
            if (max == null || gen(item, max)) {
                max = item
            }
        }
        return max
    }

    fun <T> maxStr(array: Array<T>): T? {

        var max: T? = null
        for (item in array) {
            if (max == null || gent(item.toString(), max.toString())) {
                max = item
            }
        }
        return max
    }

    fun gent(a: String, b: String): Boolean {
        return a.length > b.length
    }
}
