package jack.me.commonadapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by zjchai on 16/8/31.
 */
public class CommonViewHolder extends RecyclerView.ViewHolder{

    SparseArray<View> views = new SparseArray<>();

    public CommonViewHolder(View itemView) {
        super(itemView);
    }

    public static CommonViewHolder getInstance(Context context, int layoutId, ViewGroup parent){
        View view = LayoutInflater.from(context).inflate(layoutId,parent,false) ;
        return new CommonViewHolder(view);
    }

    public <T extends View> T getView(int id){
        View view = views.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            views.put(id,view);
        }
        return (T) view;
    }

    public void setText(int id,String text){
        TextView textView = getView(id) ;
        textView.setText(text);
    }

    public void setImage(int id, Bitmap bitmap){
        ImageView imageView = getView(id);
        imageView.setImageBitmap(bitmap);
    }

    public void setImage(int id, int res){
        ImageView imageView = getView(id);
        imageView.setImageResource(res);
    }

    public void setImage(int id, Drawable drawable){
        ImageView imageView = getView(id) ;
        imageView.setImageDrawable(drawable) ;
    }

    public void setOnClickedListener(int id,View.OnClickListener listener){
        getView(id).setOnClickListener(listener);
    }

    public void setVisibility(int id,int visibility){
        getView(id).setVisibility(visibility);
    }

    public void setBackgroundResource(int id,int resId){
        getView(id).setBackgroundResource(resId);
    }

    public void setText(int id ,int stringId){
        TextView textView = getView(id) ;
        textView.setText(stringId);
    }

    public void setText(int id, Spannable spannable) {
        TextView textView = getView(id) ;
        textView.setText(spannable);
    }

    public void setTextColor(int id, int color) {
        TextView textView = getView(id) ;
        textView.setTextColor(color);
    }

    public void setOnLongClickListener(int id,View.OnLongClickListener onLongClickListener){
        getView(id).setOnLongClickListener(onLongClickListener);
    }
}
