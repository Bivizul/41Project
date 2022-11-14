package aaa.bivizul.a41project.presentation

import aaa.bivizul.a41project.R
import aaa.bivizul.a41project.appComponent
import android.content.Context
import androidx.fragment.app.Fragment

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

}