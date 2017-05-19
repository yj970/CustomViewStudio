package cn.flyrise.android.library.view.svg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.flyrise.android.library.utility.FELog;

/**
 * Created by yangjie on 2017/5/19.
 */

public class SvgView extends View {
    private Paint paint;
    private List<List<Point>> traceList = new ArrayList<>();

    public SvgView(Context context, AttributeSet attrs) {
        super(context, attrs);


        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(7);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (traceList != null) {
            for (List<Point> trace : traceList){
                Path p = new Path();
                for (int i = 0; i < trace.size(); i++) {
                    if (i == 0) {
                        p.moveTo(trace.get(i).x, trace.get(i).y);
                    } else {
                        p.lineTo(trace.get(i).x, trace.get(i).y);
                    }
                }
                canvas.drawPath(p, paint);
            }
        }
    }

    // json 格式 : {"lines":[[[59,77.47],[59,79.47]],[[112,71.47],[112,71.47]]]}, 建议width:400 ,height:200
    // http://keith-wood.name/signature.html
    public void setTrace(String json) {
        traceList.clear();
        try {
            JSONObject jsonObject = new JSONObject(json);
            String line = jsonObject.getString("lines");

            for (int i = 0; i < line.split("]],").length; i++) {
                List< Point> l = new ArrayList<>();
                for (int j = 0; j < line.split("]],")[i].split("],").length; j ++) {
                    String str =  line.split("]],")[i].split("],")[j];
                    str = str.replace("[", "");
                    str = str.replace("]", "");
                    Point point = new Point(Float.parseFloat(str.split(",")[0]), Float.parseFloat(str.split(",")[1]));
                    l.add(point);
                }
                traceList.add(l);
            }
            invalidate();
        } catch (JSONException e) {
            e.printStackTrace();
            FELog.e("SvgView.setTrace（）方法解析数据出错！");
        }
    }


    public static class Point {
       private float x;
       private float y;

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }






}
