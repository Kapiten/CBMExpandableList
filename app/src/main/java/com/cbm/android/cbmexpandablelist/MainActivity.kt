package com.cbm.android.cbmexpandablelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cbm.android.cbmexpandablelist.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//JSONObject(resources.openRawResource(R.raw.sample_info).bufferedReader().use { it.readText() })
        try { binding.items.addView(binding.items.createList(JSONObject().put("Items1", JSONArray().put("Subitem1").put("Subitem1")).put("Items2", JSONArray().put("Subitem1").put("Subitem1")))) }catch (ex:Exception){ex.printStackTrace()}
    }
}