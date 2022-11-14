package aaa.bivizul.a41project.presentation.dofunspo

import aaa.bivizul.a41project.domain.model.Funspo
import aaa.bivizul.a41project.domain.model.Funspog
import aaa.bivizul.a41project.domain.repository.FunspoRepository
import androidx.lifecycle.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DofunspoViewModel @Inject constructor(
    private val funspo: Funspo,
    private val funspoRepository: FunspoRepository,
) : ViewModel() {

    private val _funspog = MutableLiveData<Funspog>()
    val funspog: LiveData<Funspog> = _funspog

    init {
        getFunspog()
    }

    fun getFunspog() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = funspoRepository.getFunspog(funspo)
            if (response.isSuccessful) {
                response.body()?.let {
                    _funspog.postValue(it)
                }
            } else {
                _funspog.postValue(Funspog(ERROR_MESSAGE))
            }
        }
    }

    companion object {
        const val ERROR_MESSAGE = "errorMessage"
    }
}

class DofunspoViewModelFactory @AssistedInject constructor(
    @Assisted("funspo") private val funspo: Funspo,
    private val funspoRepository: FunspoRepository,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DofunspoViewModel(funspo, funspoRepository) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("funspo") funspo: Funspo): DofunspoViewModelFactory
    }

}