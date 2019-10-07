package es.npatarino.android.gotchallenge

import es.npatarino.android.gotchallenge.base.test.AbstractModuleTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.test.check.checkModules

@RunWith(JUnit4::class)
class AppModuleTest : AbstractModuleTest() {

    @Test
    fun check() = startKoin{ modules(appModules + mockedContextModule) }.checkModules()
}