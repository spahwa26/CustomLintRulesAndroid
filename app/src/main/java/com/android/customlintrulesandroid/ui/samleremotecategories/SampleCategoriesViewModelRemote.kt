package com.android.customlintrulesandroid.ui.samleremotecategories

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.android.customlintrulesandroid.data.models.CategoriesModel
import com.android.customlintrulesandroid.data.models.CustomResult
import com.android.customlintrulesandroid.data.respository.RemoteRepository
import com.android.customlintrulesandroid.utils.Constants.EMPTY_STRING
import javax.inject.Inject

@HiltViewModel
class SampleCategoriesViewModelRemote @Inject constructor(private val repo: RemoteRepository) :
    ViewModel() {

    private val _state = MutableLiveData<UIState>()
    val state: LiveData<UIState> = _state


    init {
        updateCategories()
    }

    private fun updateCategories() {
        viewModelScope.launch {
            _state.value = UIState.Loading(VISIBLE)
            delay(2000)
            when (val result = repo.getCategoriesRemote()) {
                is CustomResult.Success -> {
                    _state.value = UIState.Loading(GONE)
                    _state.value = UIState.Success(result.data)
                }
                is CustomResult.Error -> {
                    _state.value = UIState.Loading(GONE)
                    _state.postValue(UIState.Error(result.exception.message ?: EMPTY_STRING))
                }
            }
        }
    }

    sealed class UIState {
        data class Success(val data: List<CategoriesModel>) : UIState()
        data class Loading(val visibility: Int = GONE) : UIState()
        data class Error(val message: String) : UIState()
    }


}
