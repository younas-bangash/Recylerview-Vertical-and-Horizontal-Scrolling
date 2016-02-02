/*******************************************************************************
 * Copyright 2011-2014 Sergey Tarasevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.uowd.sport.nestdrecylerviews_vert_horizontal;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ImagePagerAdapterMixCatalouge extends android.support.v4.view.PagerAdapter {
	private Context ac;
	private List<String> _mImagesName = new ArrayList<>();
	private LayoutInflater inflater;

	ImagePagerAdapterMixCatalouge(Context context, List<String> mDetials) {

		inflater = LayoutInflater.from(context);
		this.ac=context;
		_mImagesName=mDetials;
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}
	@Override
	public int getCount() {
		return _mImagesName.size();
	}

	@Override
	public Object instantiateItem(ViewGroup view, final int position) {
		final View imageLayout = inflater.inflate(R.layout.horizontal_item, view, false);
		final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.random_image_view);
		final ProgressBar pBar= (ProgressBar) imageLayout.findViewById(R.id.progressBar);
		pBar.setVisibility(View.VISIBLE);
        pBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(ac, R.color.ColorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);
        imageView.setTag(position);

        String mCollectionType="2";

        Picasso.with(ac.getApplicationContext())
				.load("https://gizbo.ae/GizboApp/GizboImages/catalogues/1155/" +mCollectionType + "/" + _mImagesName.get(position).replace("/thumbnails", ""))
                .into(imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        pBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        //pBar.setVisibility(View.GONE);
					}
				});
		view.addView(imageLayout, 0);
		return imageLayout;
	}
	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view.equals(object);
	}
	@Override
	public void restoreState(Parcelable state, ClassLoader loader) {
	}
	@Override
	public Parcelable saveState() {
		return null;
	}
}