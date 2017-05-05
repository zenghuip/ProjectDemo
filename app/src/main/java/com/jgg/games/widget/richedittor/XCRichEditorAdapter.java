package com.jgg.games.widget.richedittor;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.jgg.games.R;
import com.jgg.games.utils.ConvertUtils;
import com.jgg.games.utils.ImageUtil;

import java.util.List;

/**
 * Created by caizhiming on 2016/10/21.
 */
public class XCRichEditorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final LayoutInflater mLayoutInflater;
    // 数据集
    private List<EditItem> mData;
    private Context mContext;
    private ComponentAdapterListener mListener;
    private int mImageWidth;

    public XCRichEditorAdapter(Context context) {
        super();
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mImageWidth = 1000;
    }

    public List<EditItem> getData() {
        return mData;
    }

    public void setData(List<EditItem> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if(viewType == ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal()){
            View view = mLayoutInflater.inflate(R.layout.richeditor_img_item, viewGroup, false);
            ImageViewHolder holder = new ImageViewHolder(view);
            return holder;
        }else{
            View view = mLayoutInflater.inflate(R.layout.richeditor_text_item, viewGroup, false);
            TextViewHolder holder = new TextViewHolder(view);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof TextViewHolder) {
            ((TextViewHolder) holder).bindData(mData.get(position).getContent());
        } else if (holder instanceof ImageViewHolder) {
            ((ImageViewHolder) holder).bindData(mData.get(position).getUri());
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (mData.get(position).getType()) {
            case 1:
                return ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal();
            default:
                return ITEM_TYPE.ITEM_TYPE_TEXT.ordinal();
        }
    }

    @Override
    public int getItemCount() {
        if (null != mData && mData.size() > 0) {
            return mData.size();
        } else {
            return 0;
        }
    }

    public void setComponentAdapterListener(ComponentAdapterListener listener) {
        mListener = listener;
    }

    public enum ITEM_TYPE {
        ITEM_TYPE_TEXT,
        ITEM_TYPE_IMAGE,
        ITEM_TYPE_VIDEO
    }

    public interface ComponentAdapterListener {
        void change(int position, EditText editText);

        void delete(int position);
    }

    public class TextViewHolder extends RecyclerView.ViewHolder {

        private EditText text;

        public TextViewHolder(View itemView) {
            super(itemView);
            text = (EditText) itemView.findViewById(R.id.id_item_text_component);
            text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        mListener.change(getAdapterPosition(), text);
                    }
                }
            });

            text.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String content = text.getText().toString().trim();
                    mData.get(getAdapterPosition()).setContent(content);
                }
            });
        }

        public void bindData(String content) {
            text.setText(content);
            int pos = getAdapterPosition();
            if(pos == 0 && text.length() <= 0){
                text.setHint("请输入内容");
            }else{
                text.setHint("");
            }
        }
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        Button deleteBtn;

        public ImageViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.id_item_image_component);
            img.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, ConvertUtils.dp2px(200)));
            deleteBtn = (Button) itemView.findViewById(R.id.delete_btn);
            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.delete(getAdapterPosition());
                    }
                }
            });
        }

        public void bindData(Uri uri) {

            ImageUtil.displayImg(uri.toString(),img);

        }
    }

}
