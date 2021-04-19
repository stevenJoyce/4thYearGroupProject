package com.example.sportsadvisor

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import io.realm.mongodb.User
import io.realm.mongodb.mongo.iterable.MongoCursor
import org.bson.Document

class UserHistory : AppCompatActivity() {
    lateinit var app: App
    var list: List<String> = ArrayList()
    var id: List<String> = ArrayList()
    var collectData:String = ""
    lateinit var result: Document
    lateinit var results: MongoCursor<Document>
    lateinit var colResults:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.userhistory_main)
        val appID = "sportsadvisor-gztkm"
        app = App(AppConfiguration.Builder(appID).build())

    }
    fun UserData(){
        var user: User? = null
        user = app.currentUser()

        val mongoClient =
            user!!.getMongoClient("mongodb-atlas") // service for MongoDB Atlas cluster containing custom user data
        val mongoDatabase = mongoClient.getDatabase("Users")
        //val mongoCollection = mongoDatabase.getCollection("Data")
        val mongoCollection = mongoDatabase.getCollection("UserData")

        Log.v("EXAMPLE", "Successfully instantiated the MongoDB collection handle")

        //getting a cluster
        //val queryFilter = Document("_pkey", "datau1")
        val queryFilter = Document("_pkey", "steven2021")
        mongoCollection.findOne(queryFilter)
            .getAsync { task ->
                if (task.isSuccess) {
                    result = task.get()
                    Log.v("EXAMPLE", "successfully found a document: $result")

                    // val commentResponse = gson.fromJson(body,Array<HourlyProcessedDataItem>::class.java)
                    collectData = result.toString()
                    // id =listOf(collectData.split(",").toString())
                    id  = collectData.split(",")
                    println("id: $id")
                    println(id[2])
                } else {
                    Log.e("EXAMPLE", "failed to find document with: ${task.error}")
                }
            }

        val findTask = mongoCollection.find(queryFilter).iterator()
        findTask.getAsync { task ->
            if (task.isSuccess) {
                results = task.get()
                Log.v("EXAMPLE", "successfully found all collections:")
                while (results.hasNext()) {
                    colResults = results.next().toString()
                    list = colResults.split("=",",")
                    Log.v("EXAMPLE", colResults)
                }
            } else {
                Log.e("EXAMPLE", "failed to find documents with: ${task.error}")
            }
            var i:Int = 0
            while(i < list.size)
            {
                println("Stored data: " + list[i])
                i++
            }
        }

        mongoCollection.count().getAsync { task ->
            if (task.isSuccess) {
                val count = task.get()
                Log.v("EXAMPLE", "successfully counted, number of documents in the collection: $count")
            } else {
                Log.e("EXAMPLE", "failed to count documents with: ${task.error}")
            }
        }

    }

}