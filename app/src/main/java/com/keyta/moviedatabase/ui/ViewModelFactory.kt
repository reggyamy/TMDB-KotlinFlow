package com.keyta.moviedatabase.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.keyta.moviedatabase.domain.UseCase
import com.keyta.moviedatabase.di.Injection

class ViewModelFactory private constructor(
    private val useCase: UseCase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    companion object{
        private val instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this){
                instance ?: ViewModelFactory(Injection.provideUseCase(context))
            }
    }
}