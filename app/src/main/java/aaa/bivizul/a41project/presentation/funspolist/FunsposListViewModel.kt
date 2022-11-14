package aaa.bivizul.a41project.presentation.funspolist

import aaa.bivizul.a41project.domain.model.Funspos
import aaa.bivizul.a41project.domain.repository.FunsposRepository
import androidx.lifecycle.*
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FunsposListViewModel @Inject constructor(
    private val funsposRepository: FunsposRepository,
) : ViewModel() {

    private val _funspos = MutableLiveData<List<Funspos>>()
    val funspos: LiveData<List<Funspos>> = _funspos

    init {
        getFunspos()
    }

    fun getFunspos() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = funsposRepository.getFunspos()
            if (response.isSuccessful) {
                response.body()?.let {
                    _funspos.postValue(it)
                }
            } else {
                _funspos.postValue(emptyList())
            }
        }
    }

}

class FunsposViewModelFactory @AssistedInject constructor(
    private val funsposRepository: FunsposRepository,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FunsposListViewModel(funsposRepository) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(): FunsposViewModelFactory
    }

}