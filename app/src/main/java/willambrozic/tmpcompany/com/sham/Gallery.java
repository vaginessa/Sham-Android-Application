package willambrozic.tmpcompany.com.sham;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by william on 2018-05-13.
 */


public class Gallery extends Fragment {
    public Gallery() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.gallery_fragment, container, false);

        //initialize the toolbar
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.gallery_toolbar);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.drawable.back_arrow_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open navigation drawer when click navigation back button
                Intent a = new Intent(view.getContext(),sham.class);
                a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); startActivity(a);
                startActivity(a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
        return view;
    }


    /* meant for full release (Unfinished) - this will hold images from the users photo gallery

    public void gridView () {
        GridView gridView = (GridView) getView().getRootView().findViewById(R.id.gallery_gridview);
        gridView.setAdapter(new Gallery.ImageAdapter(gridView.getContext()));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(view.getContext(), "" + i, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public class ImageAdapter extends BaseAdapter {
        private Context gridViewContext;

        public ImageAdapter(Context gridViewContext) {
            this.gridViewContext = gridViewContext;
        }

        @Override
        public int getCount() {
            return 0;
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView (int position, View ConvertView, ViewGroup parent) {
            ImageView imageView = new ImageView(gridViewContext);
            imageView.setImageResource(images[position]);
            return imageView;
        }
        // this is an image array to hold the default images of the beta application for
        // the grid view
        private Integer[] images = {
                R.drawable.person_1,
                R.drawable.person_2,
                R.drawable.person_3,
                R.drawable.person_4,
                R.drawable.person_5,
                R.drawable.person_6,
                R.drawable.person_7,
                R.drawable.person_8};
    }
    */

}
