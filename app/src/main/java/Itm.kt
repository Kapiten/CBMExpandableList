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
import org.json.JSONObject



class Itm : ScrollView {
    constructor(context: Context?, info: JSONObject) : super(context) { initView(info)}
    constructor(context: Context?, attrs: AttributeSet?, info: JSONObject) : super(context, attrs) {
        if(info==null||info.length()<1) {
            val ta = context!!.obtainStyledAttributes(attrs!!, R.styleable.Items)!!
                    val infoB = org.json.JSONObject(ta.getString(R.styleable.Items_items))
            initView(infoB)
            setCustoms(attrs!!, infoB)
            return
        }

        initView(info);
        setCustoms(attrs!!, info)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, info: JSONObject) : super(context, attrs, defStyleAttr)
    {initView(info); setCustoms(attrs!!, info)}

    fun initView(info: JSONObject) { addView(createList(info)) }

    private fun setCustoms(attrs: AttributeSet, info: JSONObject) { initView(info) }

    fun createList(info: JSONObject): LinearLayout {
        var llEL: LinearLayout = LinearLayout(context)
        llEL.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        llEL.orientation = LinearLayout.VERTICAL

        for (a in 0 .. info.length()) {
            var liV = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, null)

            var tv: TextView = (liV as TextView).findViewById(android.R.id.text1)
            tv.background = context.resources.getDrawable(R.drawable.bgs_black)
            (tv.layoutParams as LinearLayout.LayoutParams).setMargins(context.resources.getDimensionPixelSize(R.dimen.s8dp))
            val jn = info.names()!!.get(a).toString()
            tv.text = jn
            llEL.addView(tv)
            if(info.getJSONArray(jn)!=null&&info.getJSONArray(jn).length()>0) {
                var llDI: LinearLayout = LinearLayout(context)
                llDI.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                llDI.orientation = LinearLayout.VERTICAL
                        (llDI.layoutParams as LinearLayout.LayoutParams).leftMargin = context.resources.getDimensionPixelSize(R.dimen.s8dp)
                llDI.visibility = android.view.View.GONE
                for (b in 0 .. info.getJSONArray(jn).length()) {
                    var ldiV = LayoutInflater.from(context)
                            .inflate(android.R.layout.simple_list_item_1, null)

                    val tvI: TextView = (ldiV as TextView).findViewById(android.R.id.text1)
                    val jnI = info.getJSONArray(jn).getString(b)
                    tvI.text = jnI
                    llDI.addView(tvI)
                }
                tv.setOnClickListener { v -> { if(llDI!=null) {llDI.visibility=if(llDI.visibility==android.view.View.VISIBLE){android.view.View.GONE}else{android.view.View.VISIBLE}} }}
                llEL.addView(llDI)
            }
        }

        return llEL
    }

}
