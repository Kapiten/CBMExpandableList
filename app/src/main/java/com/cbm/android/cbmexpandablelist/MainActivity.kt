package com.cbm.android.cbmexpandablelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.cbm.android.cbmexpandablelist.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvELErrorTitle.setOnClickListener { binding.tvELErrorDetails.visibility=if(binding.tvELErrorDetails.visibility==View.VISIBLE){View.GONE}else{View.VISIBLE} }
//JSONObject(resources.openRawResource(R.raw.sample_info).bufferedReader().use { it.readText() })
        var json = JSONObject()
        json = JSONObject().put("Items1", JSONArray().put("Subitem1").put("Subitem2")).put("Items2", JSONArray().put("Subitem1").put("Subitem2"))
        binding.et.text.append(json.toString())
        binding.btn.setOnClickListener { if(!binding.et.text.toString().isEmpty()){try{json=JSONObject(binding.et.text.toString());
            try { binding.items.removeAllViews(); binding.items.addView(binding.items.createList(json))
                binding.tvELErrorTitle.setText("");
                binding.tvELErrorDetails.setText("");
                binding.tvELErrorTitle.visibility= View.GONE}catch (ex:Exception){ex.printStackTrace()}}catch (jsonEx:JSONException){
            binding.tvELErrorTitle.setText("JSON String Error");
            binding.tvELErrorDetails.setText(jsonEx.message);
            binding.tvELErrorTitle.visibility= View.VISIBLE}
        catch (ex:Exception){ex.printStackTrace()
            binding.tvELErrorTitle.setText("String Error");
            binding.tvELErrorDetails.setText(ex.message);
            binding.tvELErrorTitle.visibility= View.VISIBLE}}
        }
        if(json!=null&&json.length()>0){
        try { binding.items.addView(binding.items.createList(json)) }catch (ex:Exception){ex.printStackTrace()}}
    }
}