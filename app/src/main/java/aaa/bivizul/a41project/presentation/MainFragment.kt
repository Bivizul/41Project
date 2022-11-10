package aaa.bivizul.a41project.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import aaa.bivizul.a41project.R
import aaa.bivizul.a41project.appComponent
import android.content.Context

class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }


}