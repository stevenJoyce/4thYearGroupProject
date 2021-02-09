package com.example.sportsadvisor.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Users : RealmObject(){
    @PrimaryKey
    private var _id: Long = 0
    private lateinit var username:String
    private lateinit var password:String
    
}