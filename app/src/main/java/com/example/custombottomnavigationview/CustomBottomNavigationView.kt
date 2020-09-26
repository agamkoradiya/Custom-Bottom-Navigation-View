package com.example.custombottomnavigationview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView

class CustomBottomNavigationView : BottomNavigationView {
    private var mPath: Path? = null
    private var mPaint: Paint? = null

    /** the CURVE_CIRCLE_RADIUS represent the radius of the fab button  */
    private val CURVE_CIRCLE_RADIUS = 128 / 2

    // the coordinates of the first curve
    private val mFirstCurveStartPoint: Point = Point()
    private val mFirstCurveEndPoint: Point = Point()

    //the coordinates of the second curve
    private var mSecondCurveStartPoint: Point = Point()
    private val mSecondCurveEndPoint: Point = Point()

    private var mNavigationBarWidth = 0
    private var mNavigationBarHeight = 0

    val TAG = "custom"

    constructor(context: Context?) : super(context!!) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        Log.d(TAG, "CURVE_CIRCLE_RADIUS:   $CURVE_CIRCLE_RADIUS")

        mPath = Path()
        mPaint = Paint()
        mPaint!!.style = Paint.Style.FILL_AND_STROKE
        mPaint!!.color = Color.BLACK
        setBackgroundColor(Color.TRANSPARENT)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        // get width and height of navigation bar
        // Navigation bar bounds (width & height)
        mNavigationBarWidth = width
        mNavigationBarHeight = height
        Log.d(TAG, "mNavigationBarWidth:   $mNavigationBarWidth")
        Log.d(TAG, "mNavigationBarHeight:   $mNavigationBarHeight")

        mFirstCurveStartPoint.set(
            mNavigationBarWidth / 2 - CURVE_CIRCLE_RADIUS - CURVE_CIRCLE_RADIUS / 4,
            0
        )
        Log.d(TAG, "   ")
        Log.d(TAG, "mFirstCurveStartPoint :   $mFirstCurveStartPoint")

        mFirstCurveEndPoint.set(
            mNavigationBarWidth / 2,
            CURVE_CIRCLE_RADIUS + CURVE_CIRCLE_RADIUS / 4
        )
        Log.d(TAG, "   ")
        Log.d(TAG, "mFirstCurveEndPoint :   $mFirstCurveEndPoint")

        mSecondCurveStartPoint = mFirstCurveEndPoint
        Log.d(TAG, "   ")
        Log.d(TAG, "mSecondCurveStartPoint :   $mSecondCurveStartPoint")

        mSecondCurveEndPoint.set(
            mNavigationBarWidth / 2 + CURVE_CIRCLE_RADIUS + CURVE_CIRCLE_RADIUS / 4,
            0
        )
        Log.d(TAG, "   ")
        Log.d(TAG, "mSecondCurveEndPoint :   $mSecondCurveEndPoint")

        mPath?.reset()
        mPath?.moveTo(0F, 0F)

        mPath?.lineTo(mFirstCurveStartPoint.x.toFloat(), 0F)
        mPath?.lineTo(mFirstCurveEndPoint.x.toFloat(), mFirstCurveEndPoint.y.toFloat())
        mPath?.lineTo(mSecondCurveEndPoint.x.toFloat(), mSecondCurveEndPoint.y.toFloat())

        mPath?.lineTo(mNavigationBarWidth.toFloat(), 0F)
        mPath?.lineTo(mNavigationBarWidth.toFloat(), mNavigationBarHeight.toFloat())
        mPath?.lineTo(0F, mNavigationBarHeight.toFloat())
        mPath?.close()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(mPath!!, mPaint!!)
    }
}