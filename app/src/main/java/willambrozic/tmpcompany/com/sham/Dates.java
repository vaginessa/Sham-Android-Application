package willambrozic.tmpcompany.com.sham;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Dates extends Fragment {

    View v;
    private  RecyclerView myRecyclerView;
    private static List<IconsAndNames> listIconsAndNames = new ArrayList<>();
    private static RecyclerViewAdapter recyclerAdapter;

    public Dates() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.date_fragment, container, false);
        myRecyclerView = (RecyclerView) v.findViewById(R.id.date_recycler);
        recyclerAdapter = new RecyclerViewAdapter(getContext(), listIconsAndNames);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(recyclerAdapter);
        //adding dividers to the recylcerview for stylistic reasons
        myRecyclerView.addItemDecoration(new DividerItemDecoration(myRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
        // Inflate the layout for this fragment
        return v;
    }

    public int getItemPosition() {
        return recyclerAdapter.getItemPosition();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (WelcomeActivity.firstRun == true){
            long times[] = new long[1];
            times[0] = 1000;
            listIconsAndNames.add(0, new IconsAndNames("Sham Welcome", "Welcome to Sham", R.drawable.person_4,
                    R.drawable.outline_blue, R.drawable.die_icon, "0:00", "24:00", 1, times));
        }
    }

    public List<IconsAndNames> getListIconsAndNames() {
        return listIconsAndNames;
    }
    public void setIconsAndNames (List<IconsAndNames> iconsAndNames) {
        listIconsAndNames = iconsAndNames;
    }
    public void removeItem (int index) {
        listIconsAndNames.remove(index);
        recyclerAdapter.notifyItemRemoved(index);
        recyclerAdapter.notifyItemRangeChanged(index, listIconsAndNames.size());
        recyclerAdapter.notifyDataSetChanged();
        recyclerAdapter.closeNotificationDialog();
    }
    public int getArrayListSize() {
        return listIconsAndNames.size();
    }
    public void addItem (String name, String message, int drawable, int outline, int repeatType, String preClock, String postClock, int occurNum, long[] times) {
        // adding a new notification to the top of the arrayList
        listIconsAndNames.add(0, new IconsAndNames(name, message, drawable, outline, repeatType, preClock, postClock, occurNum, times));
           recyclerAdapter.notifyItemInserted(listIconsAndNames.size());
           recyclerAdapter.notifyItemRangeChanged(listIconsAndNames.size(), listIconsAndNames.size());
           recyclerAdapter.notifyDataSetChanged();
    }
    public int getItemImage(int i) {
        return listIconsAndNames.get(i).getIcon();
    }
    public String getItemMessage(int i) {
        return listIconsAndNames.get(i).getMessage();
    }
    public String getItemName(int i) {
        return listIconsAndNames.get(i).getName();
    }
    public long[] getTimes(int i) {
        return listIconsAndNames.get(i).getTimes();
    }
    public void setTimes(int i, long[] times) {
        listIconsAndNames.get(i).setTimes(times);
    }

}
