package aaa.bivizul.a41project.presentation.funspolist

import aaa.bivizul.a41project.R
import aaa.bivizul.a41project.appComponent
import aaa.bivizul.a41project.databinding.FragmentFunsposListBinding
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import javax.inject.Inject

class FunsposListFragment : Fragment(R.layout.fragment_funspos_list) {

    @Inject
    lateinit var factory: FunsposViewModelFactory.Factory
    private val viewModel: FunsposListViewModel by viewModels { factory.create() }
    private val binding by viewBinding(FragmentFunsposListBinding::bind)
    private val funspoListAdapter by lazy { FunsposListAdapter() }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.funsposRV.adapter = funspoListAdapter

        viewModel.funspos.observe(viewLifecycleOwner){
            funspoListAdapter.submitList(it)
        }
    }

}