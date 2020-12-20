package io.threethirtythree.ricknmorty

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.threethirtythree.ricknmorty.app.CustomApp
import io.threethirtythree.ricknmorty.view.recyclerview.CharacterListAdapter
import io.threethirtythree.ricknmorty.viewmodel.CharacterViewModel
import io.threethirtythree.ricknmorty.viewmodel.CharacterViewModelFactory
import io.threethirtythree.ricknmorty.viewmodel.ServiceViewModel
import io.threethirtythree.ricknmorty.viewmodel.ServiceViewModelFactory
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private val characterViewModel: CharacterViewModel by viewModels {
        CharacterViewModelFactory((application as CustomApp).characterRepository)
    }
    private val serviceViewModel: ServiceViewModel by viewModels{
        ServiceViewModelFactory((application as CustomApp).serviceRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = CharacterListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        characterViewModel.characters.observe(this) {
            adapter.submitList(it)
        }

        serviceViewModel.characters.observe(this) {
            characterViewModel.insertAll(it)
            Timber.i(it.toString())

        }

        if (savedInstanceState == null) {
            serviceViewModel.load()
        }


        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            characterViewModel.deleteAll()
        }

        findViewById<FloatingActionButton>(R.id.fab2).setOnClickListener {
            serviceViewModel.load()
        }
    }
}