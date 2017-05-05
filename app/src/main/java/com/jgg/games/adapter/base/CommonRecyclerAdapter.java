package com.jgg.games.adapter.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jgg.refreshview.recyclerview.BaseRecyclerAdapter;

import java.util.List;


public abstract class CommonRecyclerAdapter<T> extends BaseRecyclerAdapter<RecyclerViewHolder>
{
	protected LayoutInflater mInflater;
	protected Context mContext;
	protected List<T> mDatas;
	protected final int mItemLayoutId;


	public CommonRecyclerAdapter(Context context, List<T> mDatas, int itemLayoutId)
	{
		this.mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
		this.mDatas = mDatas;
		this.mItemLayoutId = itemLayoutId;
	}


    public T getItem(int position)
    {
        return mDatas.get(position);
    }

	@Override
	public void onBindViewHolder(RecyclerViewHolder holder, int position, boolean isItem) {
		holder.setmPosition(position);
		convert(holder, getItem(position));
	}

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i,boolean is) {
        View view=mInflater.inflate(mItemLayoutId,viewGroup,false);
        RecyclerViewHolder holder = getViewHolder(view);
        return holder;
    }

    @Override
    public int getAdapterItemCount() {
        return mDatas.size();
    }

	@Override
	public long getItemId(int position)
	{
		return position;
	}
    /**
     * 显示数据抽象函数
     * @param viewHolder 基类ViewHolder,需要向下转型为对应的ViewHolder（example:MainRecyclerViewHolder mainRecyclerViewHolder=(MainRecyclerViewHolder) viewHolder;）
     * @param item 对象
     */
   public abstract void convert(RecyclerViewHolder viewHolder, T item);


	@Override
	public RecyclerViewHolder getViewHolder(View view) {
		return new RecyclerViewHolder(mContext,view);
	}


	public void setDatas(List<T> mDatas) {
		this.mDatas = mDatas;
		notifyDataSetChanged();
	}

	public List<T> getDatas() {
		return mDatas;
	}

	public void updateAdapter(List<T> mDatas) {
		this.mDatas = mDatas;
		notifyDataSetChanged();
	}

}
