package com.example.aston6

import android.app.Application
import com.example.aston6.model.ContactService

class App: Application() {

    val contactService = ContactService()
}