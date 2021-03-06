package com.example.testapp

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.testapp.data.network.NetworkConnectionInterceptor
import com.example.testapp.data.db.AppDatabase
import com.example.testapp.data.network.MyApi
import com.example.testapp.data.repository.CommentsRepository
import com.example.testapp.data.repository.PostRepository
import com.example.testapp.data.repository.UserRepository
import com.example.testapp.ui.activities.postsDetailActivity.PostDetailViewModelFactory
import com.example.testapp.ui.activities.postsActivity.PostsViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class TestApplication  : Application(), KodeinAware, LifecycleObserver {

    companion object {
        var isBackground = true
    }

    override val kodein= Kodein.lazy {
        import(androidXModule(this@TestApplication))

        bind() from singleton {
            NetworkConnectionInterceptor(instance())
        }
        bind() from singleton {
            MyApi(instance())
        }
        bind() from singleton {
            AppDatabase(instance())
        }
        bind() from singleton {
            PostRepository(instance(), instance())
        }
        bind() from singleton {
            UserRepository(instance(), instance())
        }
        bind() from singleton {
            CommentsRepository(instance(), instance())
        }
        bind() from provider { PostsViewModelFactory(instance()) }
        bind() from provider { PostDetailViewModelFactory(instance(),instance()) }

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onMoveToForeground() {
        Log.d("My_Lifecycle", "Returning to foregroundÔÇŽ")
        isBackground = false
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onMoveToBackground() {
        Log.d("My_Lifecycle", "Moving to backgroundÔÇŽ")
        isBackground = true
    }
}