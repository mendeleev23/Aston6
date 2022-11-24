package com.example.aston6.model

import com.github.javafaker.Faker

typealias ContactsListerner = (contacts: List<ContactInfo>) -> Unit

class ContactService {

    private var contacts = mutableListOf<ContactInfo>()

    private val listerners = mutableSetOf<ContactsListerner>()

    init {
        val faker = Faker.instance()
        contacts = (1..100).map {
            ContactInfo(
                id = it,
                name = faker.name().firstName(),
                lastname = faker.name().lastName(),
                number = (80000000000..89999999999).random().toString(),
                photo = "https://img3.fonwall.ru/o/kw/stock-running-people-mood.jpeg "
            )
        }.toMutableList()
    }

    fun getContactInfo():List<ContactInfo>{
        return contacts
    }

    fun deleteContactInfo(contact:ContactInfo){
        val indexToDelete = contacts.indexOfFirst { it.id == contact.id }
        if (indexToDelete != -1){
            contacts.removeAt(indexToDelete)
            notifyChanges()
        }
    }

    fun addListerner(listerner: ContactsListerner){
        listerners.add(listerner)
        listerner.invoke(contacts)
    }

    fun removeListerner(listerner: ContactsListerner){
        listerners.remove(listerner)
    }

    private fun notifyChanges(){
        listerners.forEach{ it.invoke(contacts)}
    }
}