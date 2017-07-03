package sg.vinova.aki.compoundview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Aki on 6/27/2017.
 */

public class CompoundView extends LinearLayout implements View.OnClickListener {
    Button btnLeft;
    Button btnRight;
    EditText editText;

    private CharSequence[] data = null;
    private int selectedPos = -1;

    public CompoundView(Context context) {
        super(context);
        init(context);
    }

    public CompoundView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.compound_view, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        editText = (EditText) this.findViewById(R.id.edit_text);

        btnLeft = (Button) this.findViewById(R.id.btn_left);
        btnLeft.setBackgroundResource(android.R.drawable.ic_media_previous);
        btnRight = (Button) this.findViewById(R.id.btn_right);
        btnRight.setBackgroundResource(android.R.drawable.ic_media_next);

        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (after > 1) {
                    editText.setTextColor(Color.RED);
                } else {
                    editText.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 1) {
                    editText.setTextColor(Color.RED);
                } else {
                    editText.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void setData(@NonNull CharSequence[] data) {
        this.data = data;
        setSelectedPos(0);
    }

    public void setSelectedPos(int index) {
        if (data == null || data.length == 0) {
            return;
        }
        if (index < 0 || index >= data.length) {
            return;
        }
        selectedPos = index;
        editText.setText(data[index]);
        editText.setSelection(data[index].length());

        if (selectedPos == 0) {
            btnLeft.setVisibility(INVISIBLE);
        } else {
            btnLeft.setVisibility(VISIBLE);
        }

        if (selectedPos == data.length - 1) {
            btnRight.setVisibility(INVISIBLE);
        } else {
            btnRight.setVisibility(VISIBLE);
        }
    }

    public CharSequence getSelectedValue() {
        if (data == null || data.length == 0) {
            return "";
        }
        if (selectedPos < 0 || selectedPos >= data.length) {
            return "";
        }
        return data[selectedPos];
    }

    public int getSelectedPos() {
        return selectedPos;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_left:
                if (selectedPos > 0) {
                    setSelectedPos(selectedPos - 1);
                }
                break;
            case R.id.btn_right:
                if (data != null && selectedPos < data.length) {
                    setSelectedPos(selectedPos + 1);
                }
                break;

        }
    }
}
