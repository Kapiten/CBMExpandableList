package com.cbm.andy.cbmexpandablelist

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.view.get
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import androidx.core.view.setMargins
import com.cbm.android.cbmexpandablelist.R
import com.cbm.android.cbmexpandablelist.databinding.SimpleListItem1Binding
import org.json.JSONObject

class Items: ScrollView {

    constructor(context: Context?) : super(context) {initView(JSONObject())}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
//        val ta = context!!.obtainStyledAttributes(attrs!!, R.styleable.Items)!!
//        val infoB = org.json.JSONObject(ta.getString(R.styleable.Items_items))
//        initView(infoB)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
//        val ta = context!!.obtainStyledAttributes(attrs!!, R.styleable.Items)!!
//        val infoB = org.json.JSONObject(ta.getString(R.styleable.Items_items))
//        initView(infoB)
    }
    constructor(context: Context?, info: JSONObject) : super(context) { initView(info)}
    constructor(context: Context?, attrs: AttributeSet?, info: JSONObject) : super(context, attrs) {
        if(info==null||info.length()<1) {
            val ta = context!!.obtainStyledAttributes(attrs!!, R.styleable.Items)!!
            val infoB = org.json.JSONObject(ta.getString(R.styleable.Items_items))
            initView(infoB)
//            setCustoms(attrs!!, infoB)
            return
        }

        initView(info);
//        setCustoms(attrs!!, info)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, info: JSONObject) : super(context, attrs, defStyleAttr)
    {initView(info); /*setCustoms(attrs!!, info)*/}

    fun initView(info: JSONObject) { addView(createList(info)) }

    private fun setCustoms(attrs: AttributeSet, info: JSONObject) { initView(info) }

    fun createList(info: JSONObject): LinearLayout {
        var llEL: LinearLayout = LinearLayout(context)
        val lpEl: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

        llEL.layoutParams = lpEl
        llEL.orientation = LinearLayout.VERTICAL

        for (a in 0 .. info.length()-1) {
            var liV = SimpleListItem1Binding.inflate(LayoutInflater.from(context))


            var tv: TextView = liV.tv
            tv.background = context.resources.getDrawable(R.drawable.bgs_black)

//            (tv.layoutParams as LinearLayout.LayoutParams).setMargins(context.resources.getDimensionPixelSize(R.dimen.s8dp))
            tv.setPadding(context.resources.getDimensionPixelSize(R.dimen.s8dp), 0, 0, 0)
            val jn = info.names()!!.get(a).toString()
            tv.text = jn
            llEL.addView(liV.root)
            if(info.getJSONArray(jn)!=null&&info.getJSONArray(jn).length()>0) {
                var llDI: LinearLayout = LinearLayout(context)
                val lpDI: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

                lpDI.topMargin = -context.resources.getDimensionPixelSize(R.dimen.s3dp)
                lpDI.leftMargin = context.resources.getDimensionPixelSize(R.dimen.s8dp)
                lpDI.rightMargin = context.resources.getDimensionPixelSize(R.dimen.s8dp)
                llDI.layoutParams = lpDI
                llDI.orientation = LinearLayout.VERTICAL
                llDI.background = context.resources.getDrawable(R.drawable.bgsbr_black)
//                (llDI.layoutParams as LinearLayout.LayoutParams).leftMargin = context.resources.getDimensionPixelSize(R.dimen.s8dp)
                llDI.visibility = android.view.View.GONE
                for (b in 0 .. info.getJSONArray(jn).length()-1) {
                    var ldiV = SimpleListItem1Binding.inflate(LayoutInflater.from(context))

                    val tvI: TextView = ldiV.tv
                    tvI.background = context.resources.getDrawable(R.drawable.bgs_trans_black)
                    val jnI = info.getJSONArray(jn).getString(b)
                    tvI.text = jnI
                    llDI.addView(ldiV.root)
                }
                tv.setOnClickListener { v ->  if(llDI!=null) {llDI.visibility=if(llDI.visibility==android.view.View.VISIBLE){android.view.View.GONE}else{android.view.View.VISIBLE}}  }
                llEL.addView(llDI)
            }
        }

        return llEL
    }

}