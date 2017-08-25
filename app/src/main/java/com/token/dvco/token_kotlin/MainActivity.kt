package com.token.dvco.token_kotlin

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Adapter
import android.widget.ListView
import android.widget.Toast
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val req : Request = Request()
        var str : String = String()
        val rec_view = findViewById(R.id.list) as RecyclerView
        val lin_man = LinearLayoutManager(this)
        doAsync {
            str = req.run()
            uiThread {
                val gson = Gson()

                val strings = gson.fromJson(str, Game::class.java)

                rec_view.layoutManager = lin_man

                val adapter = RecyclerAdapter(strings.games)

                rec_view.adapter = adapter

            }
        }


    }

    class Request {

        fun run() : String {
            val jsonstr = URL("https://dl.dropboxusercontent.com/s/1b7jlwii7jfvuh0/games").readText()
            return jsonstr
        }
    }

    class Game {
        val games : List<Gaem> = emptyList()
    }

    class Gaem{
        var id : Int = 0
        var name : String = String()
        var image : String = String()
        var release : String = String()
        var trailer : String = String()
        var platforms: List<String>? = null
    }
}
