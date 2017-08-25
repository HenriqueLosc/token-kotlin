package com.token.dvco.token_kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val getrequest : Request = Request()
        val recyclerview = findViewById(R.id.list) as RecyclerView
        val layoutmanager = LinearLayoutManager(this)

        doAsync {
            val jsonstring = getrequest.run()
            uiThread {
                val gson = Gson()
                val strings = gson.fromJson(jsonstring, gameList::class.java)

                recyclerview.layoutManager = layoutmanager

                val adapter = RecyclerAdapter(strings.games)
                recyclerview.adapter = adapter

            }
        }

    }

    class Request {

        fun run() : String {
            val jsonstring = URL("https://dl.dropboxusercontent.com/s/1b7jlwii7jfvuh0/games").readText()
            return jsonstring
        }
    }

    class gameList {
        val games : List<Game> = emptyList()
    }

    class Game{
        var id : Int = 0
        var name : String = String()
        var image : String = String()
        var release : String = String()
        var trailer : String = String()
        var platforms: List<String>? = null
    }
}
