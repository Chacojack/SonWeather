package jack.me.commonadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by zjchai on 16/8/31.
 */
public abstract class CommonAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {

    public Context mContext  ;
    public int mLayoutId ;
    public List<T> mData = new ArrayList<>();
    public SupportMultiType<T> mSupportMultiType ;

    public CommonAdapter(Context context, SupportMultiType<T> mSupportMultiType) {
        mContext = context ;
        this.mSupportMultiType = mSupportMultiType;
        init() ;
    }

    public CommonAdapter(Context mContext, int mLayoutId) {
        this(mContext,mLayoutId,null) ;
        init() ;
    }

    public CommonAdapter(Context mContext, int mLayoutId, List<T> mData) {
        this.mContext = mContext;
        this.mLayoutId = mLayoutId;
        if (mData != null) {
            this.mData.addAll(mData) ;
        }
        init() ;
    }

    protected void init(){}

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = mSupportMultiType ==null ? mLayoutId : mSupportMultiType.getLayoutId(viewType) ;
        return CommonViewHolder.getInstance(mContext,layoutId,parent) ;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        bind(holder,mData.get(position),position) ;
    }

    @Override
    public int getItemViewType(int position) {
        return mSupportMultiType == null ? super.getItemViewType(position) : mSupportMultiType.getType(mData.get(position));
    }

    public abstract void bind(CommonViewHolder holder, T t, int position);

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addData(T data){
        synchronized (mData){
            mData.add(data) ;
        }
    }

    public void removeData(T data){
        synchronized (mData){
            mData.remove(data) ;
        }
    }

    public void addDatas(@NonNull List<T> datas){
        synchronized (mData){
            mData.addAll(datas) ;
        }
    }

    public void clearData(){
        synchronized (mData){
            mData.clear();
        }
    }

    public List<T> getDatas(){
        return Collections.unmodifiableList(mData) ;
    }

    public interface SupportMultiType<E>{

        int getType(E e) ;

        int getLayoutId(int type) ;

    }

}
