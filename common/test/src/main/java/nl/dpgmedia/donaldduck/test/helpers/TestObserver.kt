package nl.dpgmedia.donaldduck.test.helpers

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

class TestObserver<T> : Observer<T> {

    private val observedValues = mutableListOf<T?>()

    override fun onChanged(value: T?) {
        observedValues.add(value)
    }

    fun assertValue(value: T) {
        assertTrue(observedValues.isNotEmpty(), "Test observer observed values are NOT empty")
        assertEquals(value, observedValues.last())
    }
}

fun <T> LiveData<T>.testObserver() = TestObserver<T>().also {
    observeForever(it)
}
