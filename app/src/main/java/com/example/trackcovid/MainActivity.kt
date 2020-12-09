package com.example.trackcovid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadCovidData()
    }
    private fun loadCovidData(){
        progressBar.visibility = View.VISIBLE
        val queue = Volley.newRequestQueue(this)
        val url = "https://corona.lmao.ninja/v2/all"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url,null,
            Response.Listener { response ->
                progressBar.visibility = View.GONE
                total.text = response.getString("cases")
                recovered.text = response.getString("recovered")
                critical.text = response.getString("critical")
                active.text =  response.getString("active")
                total_deaths.text =  response.getString("deaths")
                today_cases.text =  response.getString("todayCases")
                today_deaths.text =  response.getString("todayDeaths")

            },Response.ErrorListener {
                    Toast.makeText(this,"Something went wrong", Toast.LENGTH_LONG).show()
                })

// Add the request to the RequestQueue.
                queue.add(jsonObjectRequest)

    }
}