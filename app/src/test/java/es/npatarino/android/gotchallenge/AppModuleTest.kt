package es.npatarino.android.gotchallenge

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.nhaarman.mockito_kotlin.mock
import es.npatarino.android.gotchallenge.base.test.AbstractModuleTest
import es.npatarino.android.gotchallenge.home.presentation.ui.SectionsPagerAdapter
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.test.check.checkModules

@RunWith(JUnit4::class)
class AppModuleTest : AbstractModuleTest() {

    @Test
    fun check() = startKoin{ modules(appModules + mockedContextModule) }.checkModules {
        create<SectionsPagerAdapter> { parametersOf(mock<Context>(), mock<FragmentManager>()) }
    }
}