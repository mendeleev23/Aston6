package com.example.aston6

import android.net.wifi.p2p.WifiP2pManager.ActionListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.aston6.databinding.ItemContactBinding
import com.example.aston6.model.ContactInfo

interface ContactActionListener {
    fun onUseDelete(contact: ContactInfo, moveby: Int)

    fun onUseDetails(contact: ContactInfo)
}

class ContactsAdapter(
    private val actionListener: ContactActionListener
): RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>(), View.OnClickListener {

    var contacts: List<ContactInfo> = emptyList()
        set(newValue){
            field = newValue
            notifyDataSetChanged()
        }

    override fun onClick(v: View) {
        val contact = v.tag as ContactInfo
        actionListener.onUseDetails(contact)
    }

    override fun getItemCount(): Int =contacts.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemContactBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        return ContactsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val contact = contacts[position]
        with(holder.binding){
            holder.itemView.tag = contact
            name.text = contact.name
            lastname.text = contact.lastname
            number.text = contact.number
            img.load(contact.photo){
                placeholder(R.drawable.ic_contact)
                error(R.drawable.ic_contact)
                transformations(CircleCropTransformation())
            }
        }
    }

    class ContactsViewHolder(
        val binding: ItemContactBinding
    ):RecyclerView.ViewHolder(binding.root)
}