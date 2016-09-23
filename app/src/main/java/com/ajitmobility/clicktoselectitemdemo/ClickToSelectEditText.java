package com.ajitmobility.clicktoselectitemdemo;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

public class ClickToSelectEditText<T extends Listable> extends TextInputEditText {

    List<T>      mItems;
    String[]     mListableItems;
    CharSequence mHint;
    int selectedPosition        = -1;
    int runtimeSelectedPosition = -1;

    OnItemSelectedListener<T> onItemSelectedListener;

    public ClickToSelectEditText(Context context) {
        super(context);

        mHint = getHint();
    }

    public ClickToSelectEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        mHint = getHint();
    }

    public ClickToSelectEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mHint = getHint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setFocusable(false);
        setClickable(true);
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setItems(List<T> items) {
        this.mItems = items;
        this.mListableItems = new String[items.size()];

        int i = 0;
        selectedPosition = -1;

        for (T item : mItems) {
            mListableItems[i++] = item.getLabel();
        }

        configureOnClickListener();
    }

    private void configureOnClickListener() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItems.size() > 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle(Html.fromHtml("<b>" + mHint + "</b>"));
                    builder.setSingleChoiceItems(mListableItems, selectedPosition, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int selectedIndex) {
                            runtimeSelectedPosition = selectedIndex;

                        }
                    });

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            if (runtimeSelectedPosition > -1) {
                                selectedPosition = runtimeSelectedPosition;
                                setText(mListableItems[selectedPosition]);
                            }

                            if (onItemSelectedListener != null && selectedPosition > -1) {
                                onItemSelectedListener.onItemSelectedListener(mItems.get(selectedPosition), selectedPosition);
                            }
                        }
                    });
                    builder.create().show();
                }
            }
        });
    }

    public void setOnItemSelectedListener(OnItemSelectedListener<T> onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public interface OnItemSelectedListener<T> {
        void onItemSelectedListener(T item, int selectedIndex);
    }
}