package io.threethirtythree.effectivekotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.threethirtythree.effectivekotlin.model.Word
import io.threethirtythree.effectivekotlin.recyclerview.WordListAdapter
import io.threethirtythree.effectivekotlin.repository.db.WordRoomDatabase
import io.threethirtythree.effectivekotlin.viewmodel.WordViewModel
import io.threethirtythree.effectivekotlin.viewmodel.WordViewModelFactory
import kotlinx.coroutines.CoroutineScope

class MainActivity : AppCompatActivity() {
    private val newWordActivityRequestCode = 1
    private val viewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as MVVMApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.allWords.observe(this, {
            it?.let { adapter.submitList(it) }
        })
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(NewActivity.EXTRA_REPLY)?.let {
                viewModel.insert(Word(word = it))
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }

}