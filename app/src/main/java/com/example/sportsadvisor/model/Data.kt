package com.example.sportsadvisor.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import org.bson.types.ObjectId

open class Data(
    @PrimaryKey
    var _id: ObjectId = ObjectId(),
    @Required
    var _pkey:String = "",




):RealmObject()

