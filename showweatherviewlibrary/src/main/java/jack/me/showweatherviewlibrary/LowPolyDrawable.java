package jack.me.showweatherviewlibrary;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Random;

/**
 * Created by zjchai on 2016/11/17.
 */

public class LowPolyDrawable extends Drawable {

    private static final String TAG = LowPolyDrawable.class.getSimpleName();
    private int widthIndex = 4;
    private int heightIndex = 4;
    private Random random = new Random();
    private int[] colors = new int[]{0xFF80D8FF, 0xFF40C4FF, 0xFF00B0FF, 0xFF0091EA};
    private Point[][] points = new Point[heightIndex][widthIndex];
    private Paint paint;

    public LowPolyDrawable() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {

        canvas.drawColor(Color.WHITE);

        paint.setColor(Color.RED);
        paint.setStrokeWidth(9);
        for (int i = 0; i < heightIndex; i++) {
            for (int j = 0; j < widthIndex; j++) {
                points[i][j] = new Point(getRandomX(i), getRandomY(j));
                canvas.drawPoint(points[i][j].x, points[i][j].y, paint);
            }
        }


        paint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < heightIndex; i++) {
            for (int j = 0; j < widthIndex - 1; j++) {
                Log.d(TAG, String.format("draw : i:%d,j:%d ", i, j));
                if (i == 0) {
                    drawTriangle(canvas, i, j, true);
                } else if (i == heightIndex - 1) {
                    drawTriangle(canvas, i, j, false);
                } else {
                    drawTriangle(canvas, i, j, true);
                    drawTriangle(canvas, i, j, false);
                }

            }
        }


    }

    private void drawTriangle(@NonNull Canvas canvas, int i, int j, boolean isDown) {
        Path path = getTriangle(i, j, isDown);
        paint.setColor(getRandomColor());
        canvas.drawPath(path, paint);
    }

    private Path getTriangle(int i, int j, boolean isDown) {
        Point point1 = points[i][j];
        Point point2;
        Point point3;
        if (isDown) {
            point2 = points[i][j + 1];
            point3 = points[i + 1][j];
        } else {
            point2 = points[i - 1][j + 1];
            point3 = points[i][j + 1];
        }
        Path path = new Path();
        path.moveTo(point1.x, point1.y);
        path.lineTo(point2.x, point2.y);
        path.lineTo(point3.x, point3.y);
        path.close();
        return path;
    }

    private int getRandomColor() {
        int i = random.nextInt(colors.length);
        return colors[i];
    }

    private int getRandomY(int j) {
        Rect bounds = getBounds();
        int height = bounds.height();
        int h = height / heightIndex;
        int rh = random.nextInt(h);
        return rh + bounds.top + h * j;
    }

    private int getRandomX(int i) {
        Rect bounds = getBounds();
        int width = bounds.width();
        int w = width / widthIndex;
        int rx = random.nextInt(w);
        return bounds.left + rx + w * i;
    }


    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }

    @Override
    public int getIntrinsicWidth() {
        return super.getIntrinsicWidth();
    }

    @Override
    public int getIntrinsicHeight() {
        return super.getIntrinsicHeight();
    }

    @Override
    public int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override
    public int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        Log.d(TAG, "setBounds() called with: left = [" + left + "], top = [" + top + "], right = [" + right + "], bottom = [" + bottom + "]");
        super.setBounds(left, top, right, bottom);
    }
}
