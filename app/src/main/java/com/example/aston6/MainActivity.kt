package com.example.aston6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aston6.databinding.ActivityMainBinding
import com.example.aston6.model.ContactInfo
import com.example.aston6.model.ContactService
import com.example.aston6.model.ContactsListerner

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ContactsAdapter

    private val contactService: ContactService
        get() = (applicationContext as App).contactService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ContactsAdapter(object : ContactActionListener {
            override fun onUseDetails(contact: ContactInfo) {
            }

            override fun onUseDelete(contact: ContactInfo, moveby: Int) {
            }
        }

        )

        val layoutManager = LinearLayoutManager(this)
        binding.rec.layoutManager = layoutManager
        binding.rec.adapter = adapter

        contactService.addListerner(contactsListerner)
    }

    override fun onDestroy() {
        super.onDestroy()
        contactService.removeListerner(contactsListerner)
    }

    private val contactsListerner: ContactsListerner = {
        adapter.contacts = it
    }
}