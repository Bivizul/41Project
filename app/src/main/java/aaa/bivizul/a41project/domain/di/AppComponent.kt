package aaa.bivizul.a41project.domain.di

import aaa.bivizul.a41project.presentation.MainActivity
import aaa.bivizul.a41project.presentation.MainFragment
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)

}