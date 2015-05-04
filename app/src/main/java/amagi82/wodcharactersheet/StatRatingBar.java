/*
 * Copyright (C) 2015 James Pekarek
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package amagi82.wodcharactersheet;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.RatingBar;

public class StatRatingBar extends RatingBar {

    private int colorOutlineOn;
    private int colorOutlineOff;
    private int colorOutlinePressed;
    private int colorOutlineInactive = Color.rgb(220,220,220);
    private int colorFill;
    private int colorFillPressed;
    private int maxRating = getNumStars();
    private int healthBashing;
    private int healthLethal;
    private int healthAgg;
    private enum Healthbox {EMPTY, CHECK, X}
    private enum BarType {CIRCLE, SQUARE, HEALTHBAR}
    private BarType barType;
    private Paint paintInside = new Paint();
    private Paint paintOutline = new Paint();
    private Path path = new Path();
    private RectF rectangle = new RectF();
    private float dp = getResources().getDisplayMetrics().density;
    private boolean vertical = false;

    public StatRatingBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getXmlAttrs(context, attrs);
    }

    public StatRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        getXmlAttrs(context, attrs);
    }

    public StatRatingBar(Context context) {
        super(context);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int desiredLength = (int)(50 * dp * getNumStars());
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredLength, widthSize);
        } else {
            //Be whatever you want
            width = desiredLength;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(heightSize, vertical? desiredLength : width/getNumStars());
        } else {
            //Be whatever you want
            height = vertical? desiredLength : width/getNumStars();
        }
        if(vertical && widthMode != MeasureSpec.EXACTLY) width = Math.min(widthSize, height/getNumStars());

        //MUST CALL THIS
        setMeasuredDimension(width, height);
    }

    private Path createPathCircle(float size) {
        path.addOval(new RectF(1, 1, size, size), Path.Direction.CW);
        return path;
    }

    private Path createPathSquare(float size) {
        path.addRect(0, 0, size, size, Path.Direction.CW);
        return path;
    }

    private Path createPathHealthBox(float size, Healthbox mode) {
        path.addRect(0, 0, size, size, Path.Direction.CW);
        if(mode != Healthbox.EMPTY) path.lineTo(size,size);
        if(mode == Healthbox.X) {
            path.moveTo(0, size);
            path.lineTo(size, 0);
        }
        return path;
    }

    @Override
    public void onDraw(@NonNull Canvas canvas) {
        if(getRating() > maxRating) setRating(maxRating); //make sure rating cannot exceed max rating
        if(getRating() < 0) setRating(0); //or minimum rating
        float shapeSize = Math.min(getHeight(), getWidth()/getNumStars()) *.90f;
        if(vertical) shapeSize = Math.min(getWidth(), getHeight()/getNumStars()) *.90f;

        paintOutline.setStrokeWidth(shapeSize * .06f);
        paintOutline.setStyle(Paint.Style.STROKE);
        paintOutline.setAntiAlias(true);

        paintInside.setStyle(Paint.Style.FILL);

        path.rewind();
        if(barType != BarType.HEALTHBAR) path = barType == BarType.SQUARE? createPathSquare(shapeSize) : createPathCircle(shapeSize);

        for (int i=0;i<getNumStars();++i) {
            path.computeBounds(rectangle, true);
            if(!vertical) path.offset((i + .5F) * getWidth() / getNumStars() - rectangle.centerX(), getHeight() / 2 - rectangle.centerY());

            paintOutline.setColor(isPressed() ? colorOutlinePressed : i >= maxRating ? colorOutlineInactive : i < getRating()? colorOutlineOn : colorOutlineOff);
            paintInside.setColor(isPressed()? colorFillPressed : colorFill);

            if(barType == BarType.HEALTHBAR){
                path.reset();
//                if(healthAgg > 0 && i<=healthAgg){
//                    path = createPathHealthBox(shapeSize, Healthbox.EMPTY);
//                    path.offset(getWidth() / 2 - rectangle.centerX(), (i + .5F) * getHeight() / getNumStars() - rectangle.centerY());
//                    canvas.drawPath(path, paintInside);
//                    canvas.drawPath(path, paintOutline);
//                    break;
//                }else if(healthLethal > 0 && i<= healthAgg+healthLethal){
//                    path = createPathHealthBox(shapeSize, Healthbox.X);
//                }else if(healthBashing > 0 && i<= healthAgg+healthLethal+healthBashing){
//                    path = createPathHealthBox(shapeSize, Healthbox.CHECK);
//                }else{
                    path = createPathHealthBox(shapeSize, Healthbox.EMPTY);
//                }
                path.offset(getWidth() / 2 - rectangle.centerX(), (i + .5F) * getHeight() / getNumStars() - rectangle.centerY());
                canvas.drawPath(path, paintOutline);

            }else {
                if (i < getRating()) canvas.drawPath(path, paintInside);
                canvas.drawPath(path, paintOutline);
            }
        }
    }

    //Set any XML attributes that may have been specified
    private void getXmlAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.StatRatingBar, 0, 0);
        try {
            colorOutlineOn = a.getInt(R.styleable.StatRatingBar_colorOutlineOn, getResources().getColor(R.color.outlineDark));
            colorOutlineOff = a.getInt(R.styleable.StatRatingBar_colorOutlineOff, getResources().getColor(R.color.outlineMed));
            colorOutlinePressed = a.getInt(R.styleable.StatRatingBar_colorOutlinePressed, getResources().getColor(R.color.darkRed));
            colorFill = a.getInt(R.styleable.StatRatingBar_colorFill, getResources().getColor(R.color.fillDark));
            colorFillPressed = a.getInt(R.styleable.StatRatingBar_colorFillPressed, getResources().getColor(R.color.darkRed));
            vertical = a.getBoolean(R.styleable.StatRatingBar_vertical, false);
            int type = a.getInt(R.styleable.StatRatingBar_barType, 0);
            barType = (type == 0)? BarType.CIRCLE : type == 1? BarType.SQUARE : BarType.HEALTHBAR;
        } finally {
            a.recycle();
        }
    }

    public void setColorOutlineOn(int colorOutlineOn) {
        this.colorOutlineOn = colorOutlineOn;
    }

    public void setColorOutlineOff(int colorOutlineOff) {
        this.colorOutlineOff = colorOutlineOff;
    }

    public void setColorOutlinePressed(int colorOutlinePressed) {
        this.colorOutlinePressed = colorOutlinePressed;
    }

    public void setColorOutlineInactive(int colorOutlineInactive) {
        this.colorOutlineInactive = colorOutlineInactive;
    }

    public void setColorFill(int colorFill) {
        this.colorFill = colorFill;
    }

    public void setColorFillPressed(int colorFillPressed) {
        this.colorFillPressed = colorFillPressed;
    }

    public void setMaxRating(int maxRating) {
        this.maxRating = maxRating;
    }

    public void setHealthBashing(int healthBashing) {
        this.healthBashing = healthBashing;
    }

    public void setHealthLethal(int healthLethal) {
        this.healthLethal = healthLethal;
    }

    public void setHealthAgg(int healthAgg) {
        this.healthAgg = healthAgg;
    }
}
