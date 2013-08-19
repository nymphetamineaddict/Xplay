package com.xuan.lx.xplayer.adapter;

import com.xuan.lx.xplayer.R;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

public class TabMenu extends PopupWindow {
	private GridView gridView;
	private LinearLayout mLayout;
	int clickMenus = 0;
	public TabMenu(Context context, OnItemClickListener bodyClick,
			MenuBodyAdapter bodyAdapter) {
		super(context);

		mLayout = new LinearLayout(context);
		mLayout.setOrientation(LinearLayout.VERTICAL);

		gridView = new GridView(context);
		gridView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));// 选中的时候为透明色
		gridView.setNumColumns(bodyAdapter.resID.length);
		gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
		gridView.setVerticalSpacing(0);
		gridView.setHorizontalSpacing(0);
		gridView.setPadding(0, 0, 0, 0);
		gridView.setGravity(Gravity.CENTER);
		gridView.setOnItemClickListener(bodyClick);
		//gridView.setBackgroundResource(R.drawable.button);
		
		mLayout.addView(gridView);
		// 设置默认项
		this.setContentView(mLayout);
		this.setWidth(LayoutParams.MATCH_PARENT);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setFocusable(true);// menu菜单获得焦点 如果没有获得焦点menu菜单中的控件事件无法响应
		
	}
	
	
	 
	public void setBodySelect(int index, int colorSelBody) {
		int count = gridView.getChildCount();
		for (int i = 0; i < count; i++) {
			if (i != index)
				((LinearLayout) gridView.getChildAt(i))
						.setBackgroundColor(Color.TRANSPARENT);
		}
		((LinearLayout) gridView.getChildAt(index))
				.setBackgroundColor(colorSelBody);
	}
     
	public void setBodyAdapter(MenuBodyAdapter bodyAdapter) {
		gridView.setAdapter(bodyAdapter);
	}

	/**
	 * 自定义Adapter，TabMenu的每个分页的主体
	 * 
	 */
	static public class MenuBodyAdapter extends BaseAdapter {
		private Context mContext;
		private int[] resID;
		private String[] titles;
		/**
		 * 设置TabMenu的分页主体
		 * 
		 * @param context
		 *            调用方的上下文
		 * @param resID
		 */
		public MenuBodyAdapter(Context context, int[] resID, String[] titles) {
			this.mContext = context;
			this.resID = resID;
			this.titles = titles;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return resID.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return makeMenyBody(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			return makeMenyBody(position);
		}

		private LinearLayout makeMenyBody(int position) {
			LinearLayout result = new LinearLayout(this.mContext);
			result.setOrientation(LinearLayout.VERTICAL);
			result.setGravity(Gravity.CENTER_HORIZONTAL
					| Gravity.CENTER_VERTICAL);
			result.setPadding(0, 0, 0, 0);

			ImageView img = new ImageView(this.mContext);
			img.setBackgroundResource(resID[position]);
			result.addView(img, new LinearLayout.LayoutParams(new LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)));
			
			TextView text = new TextView(this.mContext);
			text.setText(titles[position]);
			result.addView(text, new LinearLayout.LayoutParams(new LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)));
			return result;
		}

	}

}
