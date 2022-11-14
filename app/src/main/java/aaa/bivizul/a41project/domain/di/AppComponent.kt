package aaa.bivizul.a41project.domain.di

import aaa.bivizul.a41project.presentation.*
import aaa.bivizul.a41project.presentation.dofunspo.DofunspoFragment
import aaa.bivizul.a41project.presentation.funspolist.FunsposListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)
    fun inject(dofunspoFragment: DofunspoFragment)
    fun inject(funspoFragment: FunspoFragment)
    fun inject(funsposListFragment: FunsposListFragment)
    fun inject(settingsFragment: SettingsFragment)

}