package com.example.testapp.ui.activities.postsActivity

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testapp.data.db.AppDatabase
import com.example.testapp.data.network.MyApi
import com.example.testapp.data.network.NetworkConnectionInterceptor
import com.example.testapp.data.repository.PostRepository
import com.example.testapp.getOrAwaitValue
import com.example.testapp.utils.Coroutines
import junit.framework.TestCase
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PostsViewModelTest : TestCase() {

    private lateinit var viewModel: PostsViewModel
    private val context = ApplicationProvider.getApplicationContext<Context>()
    @Before
    public override fun setUp() {
        super.setUp()
        val db = Room.inMemoryDatabaseBuilder(context,AppDatabase::class.java).allowMainThreadQueries().build()
        val networkInterceptor = NetworkConnectionInterceptor(context)
        val myApi = MyApi(networkInterceptor)
        val dataSource = PostRepository(myApi,db)
        viewModel = PostsViewModel(dataSource)
    }

    @Test
    fun testAPiCall(){
        viewModel.getPostsApiCall()
        observerForPosts()

    }
    @Test
    fun getPostsFromLocalDb(){
        viewModel.getPostsLocally()
        observerForPosts()
    }

    private fun observerForPosts(){
        Coroutines.main {
            viewModel.postList()?.getOrAwaitValue()?.let { it ->
                if (it.isEmpty()){
                    assertTrue(false)
                }else {
                    assertTrue(true)
                }
            }
        }
    }

}