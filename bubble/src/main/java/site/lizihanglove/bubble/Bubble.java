package site.lizihanglove.bubble;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * 自定义气泡变化情况
 */
public class Bubble extends RelativeLayout {

    private int bubbleColor = Color.argb(255, 75, 194, 194);
    private Path bubblePath;
    private Paint bubblePaint;
    private int width;
    private int height;

    private float mOffset = 1;


    public Bubble(Context context) {
        this(context, null);
    }

    public Bubble(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Bubble(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initBubble(context, attrs);
    }

    /**
     * 初始化视图
     *
     * @param context 上下文
     * @param attrs   属性
     */
    private void initBubble(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.Bubble);
        bubbleColor = array.getColor(R.styleable.Bubble_bubbleColor, bubbleColor);
        this.setBackgroundColor(Color.parseColor("#FEFEFE"));
        bubblePaint = new Paint();
        bubblePaint.setAntiAlias(true);
        bubblePaint.setStyle(Paint.Style.FILL);
        bubblePaint.setColor(bubbleColor);

        bubblePath = new Path();
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int x = width / 2 - 5;
        int y = height - 5;
        bubblePath.reset();
        //H
        bubblePath.moveTo(x, y);

        float AX = x * (1 - mOffset);
        float AY = y - y * mOffset / 2 + 200 * mOffset;
        float BX = x * (1 - mOffset);
        float BY = y - y * mOffset / 2;
        //A B
        bubblePath.quadTo(AX, AY, BX, BY);

        float CX = x * (1 - mOffset);
        float CY = y * (1 - mOffset);
        float DX = x;
        float DY = y * (1 - mOffset);
        //C D
        bubblePath.quadTo(CX, CY, DX, DY);

        float EX = x * (1 + mOffset);
        float EY = y * (1 - mOffset);
        float FX = x * (1 + mOffset);
        float FY = y - y * mOffset / 2;
        //E F
        bubblePath.quadTo(EX, EY, FX, FY);

        float GX = x * (1 + mOffset);
        float GY = y - y * mOffset / 2 + 200 * mOffset;
        float HX = x;
        float HY = y;
        //G H
        bubblePath.quadTo(GX, GY, HX, HY);

        bubblePaint.setColor(bubbleColor);
        canvas.drawPath(bubblePath, bubblePaint);
        float top = HY - (HY - 100) * mOffset;
        float bottom = HY - (HY - 50) * mOffset;
        float left = HX - 100 * mOffset;
        float right = HX + 100 * mOffset;
        @SuppressLint("DrawAllocation")
        RectF oval = new RectF(left, top, right, bottom);
        bubblePaint.setColor(Color.WHITE);
        canvas.drawOval(oval,bubblePaint);
    }

    public void setBubbleColor(int bubbleColor) {
        this.bubbleColor = bubbleColor;
        invalidate();
    }

    /**
     * 设置气泡大小系数 （0-1）数值越大、气泡越大
     *
     * @param offset 系数
     */
    public void setOffset(float offset) {
        if (offset > 1 || offset < 0) {
            throw new IllegalArgumentException("偏离系数在0-1之间");
        }
        mOffset = offset;
        invalidate();
    }
}
