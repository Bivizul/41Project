package aaa.bivizul.a41project.presentation.dofunspo

import aaa.bivizul.a41project.R
import aaa.bivizul.a41project.appComponent
import aaa.bivizul.a41project.domain.model.Funspo
import aaa.bivizul.a41project.domain.util.*
import aaa.bivizul.a41project.presentation.FunspoFragment.Companion.FUNSPOKOR
import aaa.bivizul.a41project.presentation.dofunspo.DofunspoViewModel.Companion.ERROR_MESSAGE
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.appsflyer.AppsFlyerLib
import com.google.firebase.analytics.FirebaseAnalytics
import com.onesignal.OneSignal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DofunspoFragment : Fragment(R.layout.fragment_dofunspo) {

    @Inject
    lateinit var factory: DofunspoViewModelFactory.Factory

    val viewModel: DofunspoViewModel by viewModels {
        factory.create(
            Funspo(
                getFunspomm(),
                getFunsposim(requireContext()),
                getFunspoid(requireContext()),
                getFunspol(),
                getFunspot()
            )
        )
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.funspog.observe(viewLifecycleOwner) { funspog ->
            CoroutineScope(Dispatchers.Main).launch {
                if (funspog.funspog == ERROR_MESSAGE) {
                    getFunspodlg(requireContext(), requireActivity())
                } else if (funspog.funspog == "no") {
                    ///
                    findNavController().navigate(R.id.action_dofunspoFragment_to_mainFragment)
                } else if (funspog.funspog == "nopush") {
                    sigFunspooff()
                    ///
                    findNavController().navigate(R.id.action_dofunspoFragment_to_mainFragment)
                } else {
                    val funspovid: String = getFunspofit(funspog.funspog)
                    OneSignal.setExternalUserId(funspovid)
                    AppsFlyerLib.getInstance().setCustomerUserId(funspovid)
                    FirebaseAnalytics.getInstance(requireContext()).setUserId(funspovid)
                    ///
                    findNavController().navigate(
                        R.id.action_dofunspoFragment_to_funspoFragment,
                        bundleOf(FUNSPOKOR to funspog.funspog)
                    )
                }
            }
        }
    }
}