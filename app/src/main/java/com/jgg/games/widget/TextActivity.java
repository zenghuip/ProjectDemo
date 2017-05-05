package com.jgg.games.widget;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jgg.games.R;
import com.jgg.games.widget.richedittor.EditItem;
import com.jgg.games.widget.richedittor.XCRichEditor;
import com.jgg.games.utils.ImageUtil;
import com.jgg.selectimage.Boxing;
import com.jgg.selectimage.model.entity.BaseMedia;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class TextActivity extends Activity {
    public static final int MULTI_CODE = 1025; // 图片多选
    public ArrayList<BaseMedia> selectImg = new ArrayList<>();
    private XCRichEditor mRichEditor;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text);
        findViewById(R.id.add_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageUtil.intentMulit(TextActivity.this,selectImg,false,9,MULTI_CODE);
            }
        });
        mRichEditor = (XCRichEditor) findViewById(R.id.richEditor);
        final TextView tv = (TextView) findViewById(R.id.tv_header);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("dddd",mRichEditor.getRichText()) ;
                Spanned sp = Html.fromHtml(mRichEditor.getRichText(), new Html.ImageGetter() {
                    @Override
                    public Drawable getDrawable(String source) {
                        InputStream is = null;
                        try {
                            is = (InputStream) new URL(source).getContent();
                            Drawable d = Drawable.createFromStream(is, "src");
                            d.setBounds(0, 0, d.getIntrinsicWidth(),
                                    d.getIntrinsicHeight());
                            is.close();
                            return d;
                        } catch (Exception e) {
                            return null;
                        }
                    }
                }, null);
                tv.setText(sp);
            }
        });
	}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            selectImg.clear();
            selectImg = Boxing.getResult(data);
           if (requestCode == MULTI_CODE){ // 多选图片
               List<EditItem> items = new ArrayList<>();
                for (BaseMedia media:selectImg){
                    EditItem item = new EditItem();
                    item.setUri(Uri.parse(media.getPath()));
                    item.setType(1);
                    item.setContent(media.getPath());
                    items.add(item);
                }
               mRichEditor.addImage(items);
            }

        }
    }
}
