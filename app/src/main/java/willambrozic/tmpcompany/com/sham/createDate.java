package willambrozic.tmpcompany.com.sham;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;

public class createDate extends Fragment {

    private ImageButton selectedImage;
    private Button minTimeButton;
    private Button maxTimeButton;
    private NumberPicker numberPicker;
    private Button setNotification;

    public createDate() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.create_date_fragment, container, false);
        selectedImage = (ImageButton) v.findViewById(R.id.place_img_button);
        minTimeButton = (Button) v.findViewById(R.id.set_former);
        maxTimeButton = (Button) v.findViewById(R.id.set_later);
        numberPicker = (NumberPicker) v.findViewById(R.id.number_picker);
        setNotification = (Button) v.findViewById(R.id.set_notification_button);
        setNotification.setVisibility(View.INVISIBLE);
        selectedImage.setImageResource(R.drawable.place_img_icon);
        numberPicker.setMaxValue(9);
        numberPicker.setMinValue(1);
        return v;
    }

    //this method is used to change the image in the create notification tab so it is the one selected by the user
    public void setSelectedImageBack(int i) {
        selectedImage.setImageResource(i);
    }

    public void setMinTimeButtonText (String s) {
        minTimeButton.setText(s);
    }
    public void setMaxTimeButtonText (String s) {
        maxTimeButton.setText(s);
    }
    public int getNumberPickerInt () {
        return numberPicker.getValue();
    }
    public void hideSetButton() {
        setNotification.setVisibility(View.INVISIBLE);
    }
    public void showSetButton() {
        setNotification.setVisibility(View.VISIBLE);
    }
}
