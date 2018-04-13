package com.example.app2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * autour : lbing
 * date : 2018/4/13 0013 09:16
 * className :
 * version : 1.0
 * description :
 * <p>
 * 1 设置 小圆圈的宽度为20dp
 * 2 左右的短横线10px
 * 3.圆圈文字 14sp
 * 4.上下view的宽度 50px
 * 5.上下view的高度 22px
 * 6.留出上下两部分和中间部分的距离
 * 7.上下部分的文字的大小10sp
 */


public class MyCalendarView extends View {

    private Context mContext;
    private ArrayList<DataEntity> datas = new ArrayList();
    private int currentMonth = 4;
    //    圆内的文字画笔
    private Paint mCircleTextPaint;
    //    上下部分文字的画笔
    private Paint mTopAndBottomTextPaint;
    //    绘制圆的画笔
    private Paint mCriclePaint;
    private Rect mRect;
    //    1 设置 小圆圈的宽度为20dp
    private int circleWidth = 20;
    // * 2 左右的短横线10px
    private int leftOrRightLineWidth = 20;
    // * 3.圆圈文字 14sp
    private int circleTextSize = 14;
    //     * 4.上下view的宽度 50px
    private int topOrBottomWidth = 50;
    //     * 5.上下view的高度 22px
    private int topOrBottomHeight = 22;
    //     * 6.留出上下两部分和中间部分的距离 2dp
    private int topAndBottomSpacing = 2;
    //     * 7.上下部分的文字的大小10sp
    private int topAndBottomTextSize = 10;
    //  文字绘制的起始距离
    private int circleTextStartX = leftOrRightLineWidth;
    //  线绘制的起始距离
    private int lineStartX = 0;
    //  上部view绘制的起始距离5dp
    private int topViewStartX = 5;

    //圆圈文字的颜色
    private int circle_text_color;
    //上下view的文字颜色
    private int top_bottom_text_right_color;
    private int top_bottom_text_error_color;
    //当前月前的圆圈的颜色
    private int pre_circle_color;
    //当前月的圆圈的颜色
    private int current_circle_color;
    //当前月后的圆圈的颜色
    private int post_circle_color;
    //当前线颜色
    private int line_color;
    //当前线高度
    private float line_height;


    public MyCalendarView(Context context) {
        this(context, null);
    }

    public MyCalendarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mRect = new Rect();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CalendarAttrs);

        circle_text_color = typedArray.getColor(R.styleable.CalendarAttrs_circle_text_color, Color.parseColor("#ffffff"));
        top_bottom_text_right_color = typedArray.getColor(R.styleable.CalendarAttrs_top_bottom_text_right_color, Color.parseColor("#66B61D"));
        top_bottom_text_error_color = typedArray.getColor(R.styleable.CalendarAttrs_top_bottom_text_error_color, Color.parseColor("#F28D02"));
        pre_circle_color = typedArray.getColor(R.styleable.CalendarAttrs_pre_circle_color, Color.parseColor("#c9c9c9"));
        current_circle_color = typedArray.getColor(R.styleable.CalendarAttrs_current_circle_color, Color.parseColor("#867eed"));
        post_circle_color = typedArray.getColor(R.styleable.CalendarAttrs_post_circle_color, Color.parseColor("#e6e6e6"));
        line_color = typedArray.getColor(R.styleable.CalendarAttrs_line_color, Color.parseColor("#ededed"));
        line_height = typedArray.getFloat(R.styleable.CalendarAttrs_line_height, 0.8f);

        mCircleTextPaint = new Paint();
        mCircleTextPaint.setAntiAlias(true);
        mCircleTextPaint.setTextSize(sp2px(circleTextSize));

        mCriclePaint = new Paint();
        mCriclePaint.setAntiAlias(true);

        mTopAndBottomTextPaint = new Paint();
        mTopAndBottomTextPaint.setAntiAlias(true);
        mTopAndBottomTextPaint.setTextSize(sp2px(topAndBottomTextSize));


        Log.e("TAG", "sp2px(circleTextSize)---" + sp2px(circleTextSize));
    }

    /**
     * sp转换成px
     */
    private int sp2px(float spValue) {
        float fontScale = mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //文字绘制的起始距离
        int circleTextStartXTemp = dp2Px(circleTextStartX);
//        线绘制的起始距离
        int lineStartXTemp = dp2Px(lineStartX);
//        线绘上面view的起始距离
        int topViewStartXTemp = dp2Px(topViewStartX);


//        Log.e("TAG", "fontMetrics.ascent==="+fontMetrics.ascent);
//        Log.e("TAG", "fontMetrics.descent==="+fontMetrics.descent);
//        Log.e("TAG", "fontMetrics.top==="+fontMetrics.top);
//        Log.e("TAG", "fontMetrics.bottom==="+fontMetrics.bottom);
//        上面都是相对于基线的距离 上面为负  下面为正
//        获取基线的位置((-top)+bottom)/2-bottom+中间位置

        for (int i = 0; i < datas.size(); i++) {

            if (i < currentMonth) {
                drawTopAndBottomView(canvas, i, topViewStartXTemp);
            }

            drawCenterView(canvas, i, circleTextStartXTemp, lineStartXTemp);

            circleTextStartXTemp = circleTextStartXTemp + dp2Px(circleWidth) + dp2Px(leftOrRightLineWidth);
            lineStartXTemp = lineStartXTemp + dp2Px(circleWidth) + dp2Px(leftOrRightLineWidth);
            topViewStartXTemp = topViewStartXTemp + dp2Px(circleWidth + leftOrRightLineWidth);
        }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = 0;
        int height = 0;
        int tempWidth = 0;
        int tempHeight = 0;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        for (int i = 0; i < datas.size(); i++) {
            tempWidth += (dp2Px(circleWidth) + dp2Px(leftOrRightLineWidth));
        }
        //在最后加一个横线的长度
        tempWidth += dp2Px(leftOrRightLineWidth);

        tempHeight = dp2Px(circleWidth) + 2 * dp2Px(topOrBottomHeight) + 2 * dp2Px(topAndBottomSpacing);

        Log.e("TAG", "tempWidth---" + tempWidth + "   tempHeight---" + tempHeight);
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = tempWidth;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = tempHeight;
        }

        setMeasuredDimension(width, height);
    }

    public int dp2Px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    private void drawTopAndBottomView(Canvas canvas, int i, int topViewStartXTemp) {

/*---------------------------------
         * 绘制图片
         * @param       x屏幕上的x坐标
         * @param       y屏幕上的y坐标
         * @param       w要绘制的图片的宽度
         * @param       h要绘制的图片的高度
         * @param       bx图片上的x坐标
         * @param       by图片上的y坐标
     ------------------------------------*/

        String textContent = datas.get(i).getPreCount() + "/" + datas.get(i).getSufCount();
        float textWidth = mTopAndBottomTextPaint.measureText(textContent);
        Paint.FontMetrics fontMetrics = mTopAndBottomTextPaint.getFontMetrics();
//上部分
        if (i % 2 == 0) {
            Bitmap bitmap = null;
            if (datas.get(i).getPreCount().equals(datas.get(i).getSufCount())) {
                bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.icon_round_green_top);
                mTopAndBottomTextPaint.setColor(top_bottom_text_right_color);
            } else {
                bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.icon_round_red_top);
                mTopAndBottomTextPaint.setColor(top_bottom_text_error_color);
            }


            int x = topViewStartXTemp;
            int y = 0;
            int w = dp2Px(topOrBottomWidth);
            int h = dp2Px(topOrBottomHeight);
            int bx = x;
            int by = 0;
            drawImage(canvas, bitmap, x, y, w, h, bx, by);

            float textX = topViewStartXTemp + dp2Px(topOrBottomWidth) / 2 - textWidth / 2;
            //        获取基线的位置((-top)+bottom)/2-bottom+中间位置
//            这里上下有个偏移
            float textY = dp2Px(topOrBottomHeight) / 2 + (-fontMetrics.top + fontMetrics.bottom) / 2 - fontMetrics.bottom - 5;

            drawTopAndBottomText(canvas, textContent, textX, textY);


        } else {
            //下部分
            Bitmap bitmap = null;
            if (datas.get(i).getPreCount().equals(datas.get(i).getSufCount())) {
                bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.icon_round_green_bottom);
                mTopAndBottomTextPaint.setColor(top_bottom_text_right_color);
            } else {
                bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.icon_round_red_bottom);
                mTopAndBottomTextPaint.setColor(top_bottom_text_error_color);
            }
            int x = topViewStartXTemp;
            int y = dp2Px(topOrBottomHeight + circleWidth) + dp2Px(topAndBottomSpacing) * 2;
            int w = dp2Px(topOrBottomWidth);
            int h = dp2Px(topOrBottomHeight);
            int bx = x;
            int by = dp2Px(topOrBottomHeight + circleWidth);
            drawImage(canvas, bitmap, x, y, w, h, bx, by);

            float textX = topViewStartXTemp + dp2Px(topOrBottomWidth) / 2 - textWidth / 2;
            //        获取基线的位置((-top)+bottom)/2-bottom+中间位置
//            这里上下有个偏移
            float textY = (2 * y + dp2Px(topOrBottomHeight)) / 2 + (-fontMetrics.top + fontMetrics.bottom) / 2 - fontMetrics.bottom + 5;
            drawTopAndBottomText(canvas, textContent, textX, textY);
        }

    }

    private void drawCenterView(Canvas canvas, int i, int circleTextStartXTemp, int lineStartXTemp) {
        Paint.FontMetrics fontMetrics = mCircleTextPaint.getFontMetrics();
//            绘制圆圈
        if ((i + 1) < currentMonth) {
            mCriclePaint.setColor(pre_circle_color);
        } else if ((i + 1) == currentMonth) {
            mCriclePaint.setColor(current_circle_color);
        } else {
            mCriclePaint.setColor(post_circle_color);
        }
        canvas.drawCircle(circleTextStartXTemp + dp2Px(circleWidth) / 2, dp2Px(circleWidth) / 2 + dp2Px(topOrBottomHeight) + dp2Px(topAndBottomSpacing), dp2Px(circleWidth) / 2, mCriclePaint);

//           绘制中间圆圈的文字
        mCircleTextPaint.getTextBounds(datas.get(i).getContent(), 0, datas.get(i).getContent().length(), mRect);
//        int textWidth = mRect.width();
//        不用上面的这个方法，测量的不准确
        int textWidth = (int) mCircleTextPaint.measureText(datas.get(i).getContent());
        int textOffset = (dp2Px(circleWidth) / 2 - textWidth / 2);
        mCircleTextPaint.setColor(circle_text_color);
        canvas.drawText(datas.get(i).getContent(), circleTextStartXTemp + textOffset, dp2Px(circleWidth) / 2 + ((fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom) + dp2Px(topOrBottomHeight) + dp2Px(topAndBottomSpacing), mCircleTextPaint);
//            绘制中间的连线  如果是第一条线那么不绘制
        if (i != 0) {
            mCircleTextPaint.setColor(line_color);
            canvas.drawRect(lineStartXTemp, dp2Px(circleWidth) / 2 + dp2Px(topOrBottomHeight) + dp2Px(topAndBottomSpacing) - dp2Px(line_height) / 2, lineStartXTemp + dp2Px(leftOrRightLineWidth), dp2Px(circleWidth) / 2 + dp2Px(topOrBottomHeight) + dp2Px(topAndBottomSpacing) + dp2Px(line_height) / 2, mCircleTextPaint);
        }


    }

    public static void drawImage(Canvas canvas, Bitmap blt, int x, int y,
                                 int w, int h, int bx, int by) {
        Rect src = new Rect();// 图片 >>原矩形
        Rect dst = new Rect();// 屏幕 >>目标矩形

        src.left = bx;
        src.top = by;
        src.right = bx + w;
        src.bottom = by + h;

        dst.left = x;
        dst.top = y;
        dst.right = x + w;
        dst.bottom = y + h;
        // 画出指定的位图，位图将自动--》缩放/自动转换，以填补目标矩形
        // 这个方法的意思就像 将一个位图按照需求重画一遍，画后的位图就是我们需要的了
//        Rect src	指定绘制图片的区域
//        Rect dst 或RectF dst	指定图片在屏幕上显示(绘制)的区域

        canvas.drawBitmap(blt, null, dst, null);
    }

    private void drawTopAndBottomText(Canvas canvas, String textContent, float textX, float textY) {

        canvas.drawText(textContent, textX, textY, mTopAndBottomTextPaint);
    }

    public void setCalendarData(ArrayList<DataEntity> datas, int currentMonth) {
        this.datas.clear();
        this.datas.addAll(datas);
        this.currentMonth = currentMonth;
        Log.e("TAG", "刷新");
        requestLayout();
        invalidate();

    }
}
