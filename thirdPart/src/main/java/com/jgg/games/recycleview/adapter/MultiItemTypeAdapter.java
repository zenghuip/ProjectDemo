package com.jgg.games.recycleview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.jgg.games.recycleview.base.ItemViewDelegate;
import com.jgg.games.recycleview.base.ItemViewDelegateManager;
import com.jgg.games.recycleview.base.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhy on 16/4/9.
 */
public class MultiItemTypeAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder>
{
    protected Context mContext;
    protected List<T> mDatas;

    protected ItemViewDelegateManager mItemViewDelegateManager;
    protected OnItemClickListener<T> mOnItemClickListener;
    public int offset = 0;


    public MultiItemTypeAdapter(Context context, List<T> datas)
    {
        mContext = context;
        mDatas = datas;
        mItemViewDelegateManager = new ItemViewDelegateManager();
    }

    public MultiItemTypeAdapter(Context context)
    {
        this(context, new ArrayList<T>());
    }

    @Override
    public int getItemViewType(int position)
    {
        if (!useItemViewDelegateManager()) return super.getItemViewType(position);
        return mItemViewDelegateManager.getItemViewType(mDatas.get(position), position);
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        int layoutId = mItemViewDelegateManager.getItemViewLayoutId(viewType);
        RecyclerViewHolder holder = RecyclerViewHolder.createViewHolder(mContext, parent, layoutId);
        setListener(parent, holder, viewType);
        return holder;
    }

    public void convert(RecyclerViewHolder holder, T t)
    {
        mItemViewDelegateManager.convert(holder, t, holder.getAdapterPosition());
    }

    protected boolean isEnabled(int viewType)
    {
        return true;
    }


    protected void setListener(final ViewGroup parent, final RecyclerViewHolder recyclerViewHolder, int viewType)
    {
        if (!isEnabled(viewType)) return;
        recyclerViewHolder.getConvertView().setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mOnItemClickListener != null)
                {
                    int position = recyclerViewHolder.getAdapterPosition();
                    mOnItemClickListener.onItemClick(v, recyclerViewHolder, mDatas.get(position - offset), position);
                }
            }
        });

        recyclerViewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                if (mOnItemClickListener != null)
                {
                    int position = recyclerViewHolder.getAdapterPosition();
                    return mOnItemClickListener.onItemLongClick(v, recyclerViewHolder, mDatas.get(position - offset), position);
                }
                return false;
            }
        });
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position)
    {
        convert(holder, mDatas.get(position));
    }

    @Override
    public int getItemCount()
    {
        int itemCount = mDatas.size();
        return itemCount;
    }


    public List<T> getDatas()
    {
        return mDatas;
    }

    public MultiItemTypeAdapter addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate)
    {
        mItemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }

    public MultiItemTypeAdapter addItemViewDelegate(int viewType, ItemViewDelegate<T> itemViewDelegate)
    {
        mItemViewDelegateManager.addDelegate(viewType, itemViewDelegate);
        return this;
    }

    protected boolean useItemViewDelegateManager()
    {
        return mItemViewDelegateManager.getItemViewDelegateCount() > 0;
    }

    public interface OnItemClickListener<T>
    {
        void onItemClick(View view, RecyclerView.ViewHolder holder, T o, int position);

        boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, T o, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setDatas(List<T> data) {
        mDatas.addAll(data);
        notifyDataSetChanged();
    }

    public void clearData() {
        mDatas.clear();
    }
}
