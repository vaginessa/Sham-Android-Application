package willambrozic.tmpcompany.com.sham;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by William on 2018-05-05.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<IconsAndNames> mData;
    Dialog notificationItemDialog;
    Dialog viewImageDialog;
    // this variable is used to know what the last clicked item is in order to delete the correlating object in the arraylist
    private static int lastClickedNum = 0;

    public RecyclerViewAdapter(Context mContext, List<IconsAndNames> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_dates,parent,false);
        final MyViewHolder vHolder = new MyViewHolder(v);

        //Initializing the dialog for each notification

        notificationItemDialog = new Dialog(mContext);
        notificationItemDialog.setContentView(R.layout.click_on_notification_popup);

        // This is the code for dialog that shows up when you press on a notification
        vHolder.item_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView dialog_name = (TextView) notificationItemDialog.findViewById(R.id.notification_name);
                TextView dialog_company_name = (TextView) notificationItemDialog.findViewById(R.id.notification_company_name);
                ImageView dialog_notification_icon = (ImageView) notificationItemDialog.findViewById(R.id.notification_icon);
                dialog_name.setText(mData.get(vHolder.getAdapterPosition()).getName());
                dialog_company_name.setText(mData.get(vHolder.getAdapterPosition()).getMessage());
                dialog_notification_icon.setImageResource(mData.get(vHolder.getAdapterPosition()).getIcon());
                notificationItemDialog.show();
                lastClickedNum = vHolder.getAdapterPosition();
            }
        });

        viewImageDialog = new Dialog(mContext);
        viewImageDialog.setContentView(R.layout.view_image);
        viewImageDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        vHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView image = viewImageDialog.findViewById(R.id.image_click);
                image.setImageResource(mData.get(vHolder.getAdapterPosition()).getIcon());
                viewImageDialog.show();
                lastClickedNum = vHolder.getAdapterPosition();
            }
        });

        return vHolder;
    }

    public int getItemPosition() {
        return lastClickedNum;
    }

    public void closeNotificationDialog () {
        notificationItemDialog.cancel();
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
    holder.name.setText(mData.get(position).getName());
    holder.img.setImageResource(mData.get(position).getIcon());
    holder.outline.setImageResource(mData.get(position).getOutline());
    holder.message.setText(mData.get(position).getMessage());
    holder.repeatType.setImageResource(mData.get(position).getRepeatType());
    holder.preClockText.setText(mData.get(position).getPreClockText());
    holder.postClockText.setText(mData.get(position).getPostClockText());
    holder.occurNum.setText(String.valueOf(mData.get(position).getOccurNum()));
    holder.times = mData.get(position).getTimes();
    }

    @Override
    public int getItemCount() {
            return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout item_notification;
        private TextView name;
        private TextView message;
        private ImageView img;
        private ImageView outline;
        private ImageView repeatType;
        private TextView preClockText;
        private TextView postClockText;
        private TextView occurNum;
        private long[] times;

        public MyViewHolder(View itemView) {
            super(itemView);
            item_notification = (LinearLayout) itemView.findViewById(R.id.notification_item);
            name = (TextView) itemView.findViewById(R.id.name_for_notification);
            message = (TextView) itemView.findViewById(R.id.message_for_notification);
            img = (ImageView) itemView.findViewById(R.id.img_dates);
            outline = (ImageView) itemView.findViewById(R.id.img_outline);
            repeatType = (ImageView) itemView.findViewById(R.id.repeat_type);
            preClockText = (TextView) itemView.findViewById(R.id.text_clock_prior);
            postClockText = (TextView) itemView.findViewById(R.id.text_clock_post);
            occurNum = (TextView) itemView.findViewById(R.id.occur_num);

        }
    }

    public List<IconsAndNames> getMData() {
        return mData;
    }

}
