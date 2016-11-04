package demo.yj.cn.customviewstudio.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import demo.yj.cn.customviewstudio.util.DensityUtils;

/**
 * Created by yj on 2016/11/3.
 * 钟表
 */
public class ClockView extends View {
    private int radius;
    private int clockWidth;
    private Paint clockPaint;
    private Paint clockPaint2;
    private Paint numPaint;
    private Paint corePaint;
    private Paint corePaint2;
    private Paint hourPaint;
    private Paint minPaint;
    private Paint secPaint;

    // 圆心（x，y）
    private int coreX;
    private int coreY;
    private float numSize;
    // adjustSize1调整12,3,6,9的位置,adjustSize2调整其余数字的位置
    private int adjustSize1;
    private int adjustSize2;
    // 时针，分针，秒针的长度（高度）
    private int hourHeight;
    private int minHeight;
    private int secHeight;

    private float hourAngle;
    private float minAngle;
    private float secAngle;
    private Context context;
    // 时针, 分针， 秒针的宽度（厚度）
    private int hourPaintWidth;
    private int minPaintWidth;
    private int secPaintWidth;
    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    private void initData() {
        numSize = DensityUtils.dip2px(context, radius/15);
        adjustSize1 = radius/5;
        adjustSize2 = radius/14;

        hourHeight = 2*radius/5;
        minHeight = 2*radius/4;
        secHeight = 2*radius/3;

        hourPaintWidth = radius/10;
        minPaintWidth = radius/10;
        secPaintWidth = radius/20;
    }

    private void initPaint() {
        clockPaint = new Paint();
        clockPaint.setColor(Color.parseColor("#5B5C5C"));
        clockPaint.setAntiAlias(true);
        clockPaint.setStrokeWidth(5.0f);

        clockPaint2 = new Paint();
        clockPaint2.setAntiAlias(true);
        clockPaint2.setColor(Color.parseColor("#5B5C5C"));
        clockPaint2.setStyle(Paint.Style.STROKE);
        clockPaint2.setStrokeWidth(1.0f);

        numPaint = new Paint();
        numPaint.setAntiAlias(true);
        numPaint.setColor(Color.WHITE);
        numPaint.setTextSize(numSize);

        corePaint = new Paint();
        corePaint.setColor(Color.WHITE);
        corePaint.setAntiAlias(true);

        corePaint2 = new Paint();
        corePaint2.setColor(Color.parseColor("#BA4949"));
        corePaint2.setAntiAlias(true);

        hourPaint = new Paint();
        hourPaint.setAntiAlias(true);
        hourPaint.setStrokeCap(Paint.Cap.ROUND);
        hourPaint.setStyle(Paint.Style.STROKE);
        hourPaint.setStrokeWidth(hourPaintWidth);
        hourPaint.setStrokeJoin(Paint.Join.ROUND);
        hourPaint.setColor(Color.WHITE);

        minPaint = new Paint();
        minPaint.setAntiAlias(true);
        minPaint.setStrokeCap(Paint.Cap.ROUND);
        minPaint.setStyle(Paint.Style.STROKE);
        minPaint.setStrokeWidth(minPaintWidth);
        minPaint.setStrokeJoin(Paint.Join.ROUND);
        minPaint.setColor(Color.WHITE);


        secPaint = new Paint();
        secPaint.setAntiAlias(true);
        secPaint.setStrokeCap(Paint.Cap.ROUND);
        secPaint.setStyle(Paint.Style.STROKE);
        secPaint.setStrokeWidth(secPaintWidth);
        secPaint.setStrokeJoin(Paint.Join.ROUND);
        secPaint.setColor(Color.parseColor("#BA4949"));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(coreX, coreY, radius + 10, clockPaint2);
        canvas.drawCircle(coreX, coreY, radius, clockPaint);
        canvas.drawText("12", coreX - numPaint.measureText("12")/2, coreY - radius + adjustSize1, numPaint);
        canvas.drawText("1", coreX + clockWidth / 6 + adjustSize2 - numPaint.measureText("1")/2, coreY - (2 * radius) / 3 - adjustSize2, numPaint);
        canvas.drawText("2", coreX + 2 * clockWidth / 6 + adjustSize2 - numPaint.measureText("2")/2, coreY - (1 * radius) / 3 - adjustSize2, numPaint);
        canvas.drawText("3", coreX + 3 * clockWidth / 6 - adjustSize1/2 - numPaint.measureText("3")/2, coreY - (0 * radius) / 3, numPaint);
        canvas.drawText("4", coreX + 2 * clockWidth / 6 + adjustSize2 - numPaint.measureText("4") / 2, coreY + (1 * radius) / 3 + adjustSize2, numPaint);
        canvas.drawText("5", coreX + clockWidth / 6 + adjustSize2 - numPaint.measureText("5") / 2, coreY + (2 * radius) / 3 + adjustSize2, numPaint);
        canvas.drawText("6", coreX - numPaint.measureText("6")/2, coreY + radius - adjustSize1 / 2, numPaint);
        canvas.drawText("7", coreX - clockWidth / 6 - adjustSize2 - numPaint.measureText("7")/2, coreY + (2 * radius) / 3 + adjustSize2, numPaint);
        canvas.drawText("8", coreX - 2 * clockWidth / 6 - adjustSize2 - numPaint.measureText("8")/2, coreY + (1 * radius) / 3 + adjustSize2, numPaint);
        canvas.drawText("9", coreX - 3 * clockWidth / 6 + adjustSize1 / 2 - numPaint.measureText("9")/2, coreY - (0 * radius) / 3, numPaint);
        canvas.drawText("10", coreX - 2 * clockWidth / 6 - adjustSize2 - numPaint.measureText("10")/2, coreY - (1 * radius) / 3 - adjustSize2, numPaint);
        canvas.drawText("11", coreX - clockWidth / 6 - adjustSize2 - numPaint.measureText("11")/2, coreY - (2 * radius) / 3 - adjustSize2, numPaint);

        // 分针
        canvas.save();
        Path minPath = new Path();
        minPath.moveTo(coreX, coreY);
        minPath.lineTo(coreX, coreY - minHeight);
        canvas.rotate(minAngle, coreX, coreY);
        canvas.drawPath(minPath, minPaint);
        canvas.restore();
        // 时针
        canvas.save();
        Path hourPath = new Path();
        hourPath.moveTo(coreX, coreY);
        hourPath.lineTo(coreX, coreY - hourHeight);
        canvas.rotate(hourAngle, coreX, coreY);
        canvas.drawPath(hourPath, hourPaint);
        canvas.restore();
        // 中心装饰
        canvas.drawCircle(coreX, coreY, radius / 10, corePaint);
        canvas.drawCircle(coreX, coreY, radius / 20, corePaint2);
        // 秒针
        canvas.save();
        Path secPath = new Path();
        secPath.moveTo(coreX, coreY);
        secPath.lineTo(coreX, coreY + secHeight /5);
        secPath.lineTo(coreX, coreY - secHeight);
        canvas.rotate(secAngle, coreX, coreY);
        canvas.drawPath(secPath, secPaint);
        canvas.restore();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        int c = w > h ? h : w;
        radius = c/2 - 20;
        clockWidth = 2 * radius;
        coreX = w/2;
        coreY = h/2;


        initData();
        initPaint();
        start();
    }

    private void start() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                SimpleDateFormat sDateFormat = new SimpleDateFormat("hh:mm:ss");
                String date = sDateFormat.format(new java.util.Date());
                setAngle(Integer.parseInt(date.split(":")[0]), Integer.parseInt(date.split(":")[1]), Integer.parseInt(date.split(":")[2]));
                postInvalidate();
            }
        },0,1000);
    }

    // 根据传入的时间为时针，分针，秒针设置角度
    private void setAngle(int hour, int min, int sec) {
        hourAngle = hour*360/12;
        minAngle = min*360/60;
        secAngle = sec*360/60;
    }
}
