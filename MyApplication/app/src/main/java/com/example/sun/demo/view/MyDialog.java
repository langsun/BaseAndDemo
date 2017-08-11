package com.example.sun.demo.view;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sun.demo.R;


/**
 * 使用场景：
 *
 * 在设计模式中对Builder模式的定义是用于构建复杂对象的一种模式，
 * 所构建的对象往往需要多步初始化或赋值才能完成。那么，在实际的开发过程中，
 * 我们哪些地方适合用到Builder模式呢？其中使用Builder模式来替代多参数构造函数是一个比较好的实践法则。
 *
 * Created by sun on 16/2/17.
 */

public class MyDialog extends Dialog {

    public MyDialog(Context context) {
        super(context);
    }

    public MyDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private TextView textViewTitle;
        private TextView textViewMessage;
        private Context context;
        private String title;
        private String message;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;

        private boolean alginLife;
        private boolean isSetTitleSize = false;
        private float setTitleSizeFloat;
        private boolean isSetTitleColor = false;
        private int setTitleColorInt;
        private boolean isSetMessageSize = false;
        private float setMessageSizeFloat;
        private boolean isSetMessageColor = false;
        private int setMessageColorint;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * Set the Dialog message from resource
         *
         * @param
         * @return
         */
        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        //设置字体居左显示
        public void setMessageAlignLeft(boolean b) {
            this.alginLife = b;
        }

        /**
         * 设置字体大小颜色
         *
         * @param size 字体大小 float型
         */
        public void setMassageSize(float size) {
            this.isSetMessageSize = true;
            this.setMessageSizeFloat = size;
        }

        public void setMassageColor(int color) {
            this.isSetMessageColor = true;
            this.setMessageColorint = color;
        }

        public void setTitleSize(float size) {
            this.isSetTitleSize = true;
            this.setTitleSizeFloat = size;
        }

        public void setTitleColor(int color) {
            this.isSetTitleColor = true;
            this.setTitleColorInt = color;
        }

        /**
         * Set the Dialog title from resource
         *
         * @param title
         * @return
         */
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        /**
         * Set the Dialog title from String
         *
         * @param title
         * @return
         */

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        /**
         * Set the positive button resource and it's listener
         *
         * @param positiveButtonText
         * @return
         */
        public Builder setPositiveButton(int positiveButtonText,
                                         OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText,
                                         OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText,
                                         OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText,
                                         OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }


        public MyDialog create() {
            final MyDialog dialog = new MyDialog(context, R.style.mydialog);

            LinearLayout layout = (LinearLayout) View.inflate(context, R.layout.my_dialog, null);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            textViewTitle = (TextView) layout.findViewById(R.id.title);
            textViewMessage = (TextView) layout.findViewById(R.id.message);
            if (isSetMessageSize) {
                textViewMessage.setTextSize(setMessageSizeFloat);
            }
            if (isSetMessageColor) {
                textViewMessage.setTextColor(setMessageColorint);
            }
            if (isSetTitleSize) {
                textViewTitle.setTextSize(setTitleSizeFloat);
            }
            if (isSetTitleColor) {
                textViewTitle.setTextColor(setTitleColorInt);
            }
            dialog.addContentView(layout, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            ((TextView) layout.findViewById(R.id.title)).setText(title);
            // 设置确定按钮及点击事件
            if (positiveButtonText != null) {
                ((Button) layout.findViewById(R.id.positiveButton)).setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    layout.findViewById(R.id.positiveButton).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            positiveButtonClickListener.onClick(dialog,
                                    DialogInterface.BUTTON_POSITIVE);
                        }
                    });
                }
            } else {
                layout.findViewById(R.id.positiveButton).setVisibility(View.GONE);
            }
            // 设置取消按钮及点击事件
            if (negativeButtonText != null) {
                ((Button) layout.findViewById(R.id.negativeButton))
                        .setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    layout.findViewById(R.id.negativeButton).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            negativeButtonClickListener.onClick(dialog,
                                    DialogInterface.BUTTON_NEGATIVE);
                        }
                    });
                }
            } else {
                layout.findViewById(R.id.negativeButton).setVisibility(View.GONE);
            }
            /**
             *  当title为空时，隐藏title控件
             */
            if (title != null) {

                if (alginLife) {
                    ((TextView) layout.findViewById(R.id.title)).setGravity(Gravity.LEFT);
                }
                ((TextView) layout.findViewById(R.id.title)).setText(title);
            } else {
                layout.findViewById(R.id.title).setVisibility(View.GONE);
            }
            /**
             * 当message为空时，可以另外加载一个自定义布局，也可以隐藏message控件
             */
            if (message != null) {

                if (alginLife) {
                    ((TextView) layout.findViewById(R.id.message)).setGravity(Gravity.LEFT);
                }
                ((TextView) layout.findViewById(R.id.message)).setText(message);
            } else if (contentView != null) {
                // if no message set, add the contentView to the dialog body
                ((LinearLayout) layout.findViewById(R.id.content)).removeAllViews();
                ((LinearLayout) layout.findViewById(R.id.content)).addView(contentView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
            } else {
                layout.findViewById(R.id.content).setVisibility(View.GONE);
            }
            dialog.setContentView(layout);
            return dialog;
        }

    }
}