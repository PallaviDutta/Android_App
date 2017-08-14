package com.example.userpc.android_app;

//Using GraphView library for programmatically create
//

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;

public class Graph_view extends android.support.v7.widget.AppCompatImageView {

    private double percentage;

    private final static String TAG = Graph_view.class.getSimpleName();

    public Graph_view(Context context) {
        super(context);
        graph(context);
    }

    public Graph_view(Context context, AttributeSet attrs) {
        super(context, attrs);
        graph(context);
        graph(attrs);
    }

    public Graph_view(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        graph(context);
        graph(attrs);
    }

    private void graph(Context context) {
        this.percentage = 0.8;
        invalidate();
    }

    private void graph(AttributeSet attrs) {
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
        invalidate();
    }

    public double getPercentage() {
        return percentage;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        int width = getWidth();
        int height = getHeight();

        Paint paint = new Paint();
        paint.setShader(new LinearGradient(0, height/2, width, height/2, Color.GREEN, Color.RED, Shader.TileMode.MIRROR));
        paint.setAntiAlias(true);

        PointF p1 = new PointF(0, height);
        float x = (float) (percentage * width);
        PointF p2 = new PointF(x, height);

        float y = (width * height - height * x) / (float) width;
        PointF p3 = new PointF(x, y);

        Log.e(TAG, p1.toString());
        Log.e(TAG, p2.toString());
        Log.e(TAG, p3.toString());

        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.reset();
        path.moveTo(p1.x, p1.y);
        path.lineTo(p2.x, p2.y);
        path.lineTo(p3.x, p3.y);
        path.close();

        canvas.drawPath(path, paint);
    }
}