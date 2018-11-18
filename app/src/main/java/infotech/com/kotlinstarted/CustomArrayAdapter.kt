package infotech.com.kotlinstarted

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomArrayAdapter(var mCtx: Context, var resource : Int, var list:List<Hero> )
    : ArrayAdapter<Hero>(mCtx,resource,list){


    override fun getCount(): Int {
        return list.size;
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var inflater = LayoutInflater.from(mCtx);
        val view :View = inflater.inflate(resource,null)
        val imageView :ImageView = view.findViewById(R.id.img_pics);
        val textView :TextView = view.findViewById(R.id.tv_name);

        var hero = list.get(position)
        imageView.setImageResource(hero.image)
        textView.text = hero.name
        return view;
    }
}