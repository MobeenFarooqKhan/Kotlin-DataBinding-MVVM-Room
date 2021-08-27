package com.example.testapp.ui.activities.postsActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class PostsActivity : AppCompatActivity() , KodeinAware {
    override val kodein by kodein()
    private val factory : PostsViewModel by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}