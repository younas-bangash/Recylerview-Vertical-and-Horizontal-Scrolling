package com.uowd.sport.nestdrecylerviews_vert_horizontal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by belladunovska on 17/07/15.
 */
public class MyVerticalAdapter extends RecyclerView.Adapter<MyVerticalAdapter.ViewHolder> {
    private final ArrayList<String> dataset;
    private final ArrayList<String> mImageNames;
    private Context context;
    private ArrayList<GetMixCatalougeData> mImageArrayData=new ArrayList<>();
    private HeaderViewHolder groupViewHolder;
    private CenterViewHolder groupViewHolder_center;
    private static final int TYPE_Footer=200;
    private static final int TYPE_Center=300;
    private Map<Integer, Parcelable> scrollStatePositionsMap = new HashMap<>();

    public MyVerticalAdapter(Context context, ArrayList<String> data, ArrayList<String> dataset, ArrayList<GetMixCatalougeData> imagesdata) {
        this.context = context;
        this.mImageNames=dataset;
        this.dataset = data;
        this.mImageArrayData=imagesdata;
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup,int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());
        switch ( viewType ) {
            case TYPE_Footer:
                ViewGroup vfooter = ( ViewGroup ) mInflater.inflate ( R.layout.footer, viewGroup, false );
                return new FooterViewHolder  ( vfooter );
            case 0://Header item
                ViewGroup vGroup = ( ViewGroup ) mInflater.inflate ( R.layout.vertical_item, viewGroup, false );
                return new HeaderViewHolder ( vGroup );
            case TYPE_Center:
                ViewGroup vCenter = ( ViewGroup ) mInflater.inflate ( R.layout.vertical_item_small, viewGroup, false );
                return new CenterViewHolder ( vCenter );
        }
        return  null;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case TYPE_Footer:
                final FooterViewHolder FViewHolder = (FooterViewHolder) holder;
                FViewHolder.setIsRecyclable(false);
                String mHeadCategory="Entertainment";
                SetTextViewTittles(mHeadCategory,FViewHolder,0);



                break;
            case 0:
                 groupViewHolder = (HeaderViewHolder) holder;
                /*ArrayList<String> dataset1 = new ArrayList<>();

                for (int i = 0; i < 10; i++) {
                    dataset1.add(String.valueOf(i));
                }*/
                groupViewHolder.mTopPager.setAdapter(new ImagePagerAdapterMixCatalouge(context, mImageNames));
                groupViewHolder.mNextImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        groupViewHolder.mTopPager.setCurrentItem(groupViewHolder.mTopPager.getCurrentItem() + 1);
                    }
                });
                groupViewHolder.mPreviousImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        groupViewHolder.mTopPager.setCurrentItem(groupViewHolder.mTopPager.getCurrentItem() - 1);
                    }
                });



                break;
            case TYPE_Center:
                groupViewHolder_center = (CenterViewHolder) holder;
                LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context);
                linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
                groupViewHolder_center.recyclerView.addItemDecoration(new SpacesItemDecoration(10));
                /*ArrayList<String> dataset2 = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    dataset2.add(String.valueOf(i));
                }*/
                groupViewHolder_center.recyclerView.setLayoutManager(linearLayoutManager1);
                MyHorizontalAdapter mCenterAdapter=new MyHorizontalAdapter(context,mImageNames);
                groupViewHolder_center.recyclerView.setAdapter(mCenterAdapter);
                //groupViewHolder_center.recyclerView.setAdapter(new MyHorizontalAdapter(context, dataset2));
                groupViewHolder_center.setPosition(position);
                if (scrollStatePositionsMap.containsKey(position)) {
                    groupViewHolder_center.recyclerView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                        @Override
                        public boolean onPreDraw() {
                            groupViewHolder_center.recyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
                            groupViewHolder_center.recyclerView.getLayoutManager().onRestoreInstanceState(scrollStatePositionsMap.get(position));
                            return false;
                        }
                    });
                }
                mCenterAdapter.setOnItemClickListener(new MyHorizontalAdapter.MyClickListener() {
                    @Override
                    public void onItemClick(int position, View v) {
                        //Toast.makeText(context,"Click"+position,Toast.LENGTH_SHORT).show();
                        groupViewHolder_center.recyclerView.setSelected(true);
                        groupViewHolder.mTopPager.setCurrentItem(position);

                    }
                });

                break;

        }
    }

    @SuppressLint("SetTextI18n")
    private void SetTextViewTittles(String headcategory,FooterViewHolder holder,int pos) {
        switch (headcategory){
            case "Entertainment":
                holder.Item1Title.setText("Price");
                holder.Item1Value.setText(mImageArrayData.get(pos).getItem1Value());

                holder.Item2Title.setText("Type");
                holder.Item2Value.setText(mImageArrayData.get(pos).getItem2Value());

                holder.Item3Title.setText("Reference");
                holder.Item3Value.setText(mImageArrayData.get(pos).getItem3Value());

                holder.Item4Title.setText("Bedrooms");
                holder.Item4Value.setText(mImageArrayData.get(pos).getItem4Value());

                holder.Item5Title.setText("Bathrooms");
                holder.Item5Value.setText(mImageArrayData.get(pos).getItem5Value());

                holder.Item6Title.setText("Area");
                holder.Item6Value.setText(mImageArrayData.get(pos).getItem6Value());

                holder.Item7Title.setText("Price/Sqft");
                holder.Item7Value.setText(mImageArrayData.get(pos).getItem7Value());

                holder.mFooterDetails.setText(mImageArrayData.get(pos).getFooterDetails());
                break;
            default:

                break;
        }

    }

    @Override
    public int getItemViewType(int position) {
         if(position==2)
            return TYPE_Footer;
         if(position==1)
             return TYPE_Center;
        else
            return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public void setPosition(int position) {
            this.position = position;
        }
        public int position;
        public ViewHolder(final View itemView) {
            super(itemView);
            }
    }
    public class HeaderViewHolder extends ViewHolder { //HeaderViewHolder
        public ViewPager mTopPager;
        public ImageView mNextImage,mPreviousImage;

        public HeaderViewHolder(View itemView) {
            super (itemView);
            mTopPager = (ViewPager) itemView.findViewById(R.id.pager);
            mNextImage= (ImageView) itemView.findViewById(R.id.nextimage);
            mPreviousImage= (ImageView) itemView.findViewById(R.id.backimage);



        }
    }
    public class FooterViewHolder extends ViewHolder { //HeaderViewHolder
        private TextView Item1Title,Item2Title,Item3Title,Item4Title,Item5Title,Item6Title,Item7Title;
        private TextView Item1Value,Item2Value,Item3Value,Item4Value,Item5Value,Item6Value,Item7Value;
        private TextView mFooterDetails;
        public FooterViewHolder(View itemView) {
            super (itemView);
            mFooterDetails= (TextView) itemView.findViewById(R.id.FooterDetails);

            Item1Title= (TextView) itemView.findViewById(R.id.Item1Title);
            Item2Title= (TextView) itemView.findViewById(R.id.Item2Title);
            Item3Title= (TextView) itemView.findViewById(R.id.Item3Title);
            Item4Title= (TextView) itemView.findViewById(R.id.Item4Title);
            Item5Title= (TextView) itemView.findViewById(R.id.Item5Title);
            Item6Title= (TextView) itemView.findViewById(R.id.Item6Title);
            Item7Title= (TextView) itemView.findViewById(R.id.Item7Title);

            Item1Value= (TextView) itemView.findViewById(R.id.Item1Value);
            Item2Value= (TextView) itemView.findViewById(R.id.Item2Value);
            Item3Value= (TextView) itemView.findViewById(R.id.Item3Value);
            Item4Value= (TextView) itemView.findViewById(R.id.Item4Value);
            Item5Value= (TextView) itemView.findViewById(R.id.Item5Value);
            Item6Value= (TextView) itemView.findViewById(R.id.Item6Value);
            Item7Value= (TextView) itemView.findViewById(R.id.Item7Value);

        }
    }

    public class CenterViewHolder extends ViewHolder { //CenterViewHolder
        public RecyclerView recyclerView;
        public CenterViewHolder(View itemView) {
            super (itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.horizontal_grid_view);
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                }

                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        scrollStatePositionsMap.put(position, recyclerView.getLayoutManager().onSaveInstanceState());
                    }
                }
            });
        }
    }
}