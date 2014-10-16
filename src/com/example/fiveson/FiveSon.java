package com.example.fiveson;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class FiveSon extends View {

	public FiveSon(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context, null);
	}

	public FiveSon(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context, attrs);
	}

	public FiveSon(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		init(context, attrs);
	}
	
	private Paint paint = new Paint();
	private Paint circlePaint = new Paint();
	
	// 旗子： 信息  代表 第几行 第几列？
	private List<int[]> chesses = new LinkedList<int[]>();
	
	private void init(Context context, AttributeSet attrs)
	{
		chesses.add(new int[]{3,5});
		chesses.add(new int[]{3,6});
	}
	
	
	int spacing = 50;
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		int width = getWidth();
		int height = getHeight();
		
		
		//1 绘制 X 坐标
		
		int num = width / spacing;
		
		paint.setColor(Color.BLACK);
		for(int i=0;i<=num;i++)
		{
			canvas.drawLine(spacing*i, 0, spacing*i, height, paint);
		}
		
		num = height/spacing;
		
		for(int i=0;i<=num;i++)
		{
			canvas.drawLine(0,spacing*i, width,spacing*i, paint);
		}
		
		//2 绘制Y 坐标
		
		
		
		//3 绘制 旗子
		
		if(!chesses.isEmpty())
		{
			
			int count = chesses.size();
			
			for(int i=0;i<count;i++)
			{
				// 行列 信息
				int[] location = chesses.get(i);
				
				int rawX = location[1] * spacing;
				int rawY = location[0] * spacing;
				
				if(i%2==0)
				{
					circlePaint.setColor(Color.BLACK);
				}else {
					circlePaint.setColor(Color.GREEN);
				}
				
				canvas.drawCircle(rawX, rawY, 20, circlePaint);
			}
			
		}
	}
	
	
	/**
	 * 完全自定义空间：
	 * 
	 * 处理你恶不处理时间爱你的方法:
	 * onTouchEvent()
	 * onKeyEvent()
	 * 
	 * 当然 也可以通过 
	 * setOnTouchListener()
	 * setOnKeyListener();
	 * 
	 * 外部操作：
	 * setOnTouchListener(OnTouchListener l) 
	 * setOnKeyListener(OnKeyListener l) 
	 * 
	 * 
	 * 
	 * */
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		boolean isComsumed = false;
		
		int action = event.getAction();
		
		if(action == MotionEvent.ACTION_DOWN)
		{
//			分析点击的坐标 来判断 点击的 行数和 列数
			
			int ex =  (int)event.getX();
			int ey =  (int)event.getY();
			
			int colNum = (int)(ex / spacing);
			int rowNum = (int)(ey / spacing);
			
			int  colcc = ex % spacing;
			int  rowcc = ey % spacing;
			
			if(colcc>=(spacing/2))
			{
				colcc++;
			}
			if(rowcc>=(spacing/2))
			{
				rowcc++;
			}
			
//			判断什么时候add 
			int len = chesses.size();
			
			if(len>0)
			{
				boolean has = false;
				
				// 判断是否 已经点击过了!  
				for(int i = len-1;i>=0;i--)
				{
					int[] chs = chesses.get(i);
					if(rowNum == chs[0] && colNum == chs[1])
					{
						has = true;
						break;
					}
				}
				
				if(!has)
				{
					chesses.add(new int[]{rowNum,colNum});
					invalidate();
				}

			}else {
				chesses.add(new int[]{rowNum,colNum});
				invalidate();
			}
			
			isComsumed = true;
			
		}else {
			isComsumed = super.onTouchEvent(event);
		}
		
		return isComsumed;
		
	}
	
}
