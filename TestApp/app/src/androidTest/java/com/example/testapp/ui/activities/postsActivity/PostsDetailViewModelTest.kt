package com.example.testapp.ui.activities.postsActivity

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testapp.data.db.AppDatabase
import com.example.testapp.data.network.MyApi
import com.example.testapp.data.network.NetworkConnectionInterceptor
import com.example.testapp.data.repository.CommentsRepository
import com.example.testapp.data.repository.PostRepository
import com.example.testapp.data.repository.UserRepository
import com.example.testapp.getOrAwaitValue
import com.example.testapp.ui.activities.postsDetailActivity.PostDetailViewModel
import com.example.testapp.ui.adapters.CommentRecyclerAdapter
import com.example.testapp.utils.Coroutines
import junit.framework.TestCase
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PostsDetailViewModelTest : TestCase() {

    private lateinit var viewModel: PostDetailViewModel
    private val context = ApplicationProvider.getApplicationContext<Context>()
    @Before
    public override fun setUp() {
        super.setUp()
        val db = Room.inMemoryDatabaseBuilder(context,AppDatabase::class.java).allowMainThreadQueries().build()
        val networkInterceptor = NetworkConnectionInterceptor(context)
        val myApi = MyApi(networkInterceptor)
        val userRepo = UserRepository(myApi,db)
        val commentRepo = CommentsRepository(myApi,db)
        viewModel = PostDetailViewModel(userRepo,commentRepo)
    }

    @Test
    fun testUserAPiCall(){
        // 1 is the Id of User
        viewModel.getUserApiCall(id = 1)
        observerForUser()

    }
    @Test
    fun testCommentAPiCall(){
        // 1 is the id of the post
        viewModel.getCommentsApiCall(1)
        observerForComments()
    }
    private fun observerForComments(){
        Coroutines.main {
            viewModel.getCommentsSpecificToPost()?.getOrAwaitValue()?.let { it ->
                if (it.isEmpty()){
                    assertTrue(false)
                }else {
                    assertTrue(true)
                }
            }
        }
    }

    private fun observerForUser(){
        Coroutines.main {
            viewModel.getSelectedUser()?.getOrAwaitValue().let { it ->
                if (it == null) {
                    assertTrue(false)
                }else {
                    assertTrue(true)
                }
            }
        }
    }

}