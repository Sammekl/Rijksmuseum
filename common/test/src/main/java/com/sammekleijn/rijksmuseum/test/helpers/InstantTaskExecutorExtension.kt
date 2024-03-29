package com.sammekleijn.rijksmuseum.test.helpers

import android.annotation.SuppressLint
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

/**
 * Add this JUnit 5 extension to your test class using
 * @JvmField
 * @RegisterExtension
 * val instantTaskExecutor = InstantTaskExecutorExtension()
 */
@SuppressLint("RestrictedApi")
class InstantTaskExecutorExtension : BeforeEachCallback, AfterEachCallback {
    override fun beforeEach(context: ExtensionContext) {
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) = runnable.run()
            override fun postToMainThread(runnable: Runnable) = runnable.run()
            override fun isMainThread() = true
        })
    }

    override fun afterEach(context: ExtensionContext?) {
        ArchTaskExecutor.getInstance().setDelegate(null)
    }
}
