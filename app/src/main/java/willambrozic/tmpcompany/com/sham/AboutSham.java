package willambrozic.tmpcompany.com.sham;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by william on 2018-05-20.
 */

public class AboutSham extends Fragment {

        public AboutSham() {

        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
                final View view = inflater.inflate(R.layout.about_sham_fragment, container, false);

                //initialize the toolbar
                Toolbar toolbar = (Toolbar) view.findViewById(R.id.about_toolbar);
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
}
