package willambrozic.tmpcompany.com.sham;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.InputType;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.prefs.Preferences;

import static android.app.PendingIntent.getActivity;

public class sham extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TabLayout tabLayout;
    private static boolean rndNotification = true;
    private ViewPager viewPage;
    private ViewPageAdapter adapter;
    DrawerLayout drawerLayout;
    //SeekBar notiSeek;
   // SeekBar ringSeek;
    boolean showSpamDialog = false;
   // SeekBar notiRingSeek;
    Dialog supportDialog, addImageToNotification, viewImage, spamMenu, timePickerDialog;
    private EditText nameInput, messageInput;
    private String name, app, message;
    TextView spamCountdown;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ArrayList<IconsAndNames> IconsAndNames = new ArrayList<>();
    Dates fragment;
    MediaPlayer player;
    ImageButton startSpam, stopSpam;
    // GridView gridView;
    createDate fragment_2;
    public static boolean showIntro = false;
    PrefManager prefs;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int timePickerMinuteMin, timePickerHourMin, timePickerMinuteMax, timePickerHourMax, selectedImage;
    boolean imageSelected = false, preTimeSelected = false, posttimeSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sham);

        // hamburger menu
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        // follow on socials dialog
        supportDialog = new Dialog(this);
        addImageToNotification = new Dialog(this);
        viewImage = new Dialog(this);
        spamMenu = new Dialog(this);


        tabLayout = (TabLayout) findViewById(R.id.tabLayout_id);
        viewPage = (ViewPager) findViewById(R.id.viewpager_id);
        adapter = new ViewPageAdapter(getSupportFragmentManager());

        //Adding fragments

        adapter.AddFragment(new Dates(), "");
        adapter.AddFragment(new createDate(), "");
        viewPage.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPage);

        // setting up the tab icons
        View view1 = getLayoutInflater().inflate(R.layout.customtab, null);
        view1.findViewById(R.id.icon).setBackgroundResource(R.drawable.menu_selector);
        View view2 = getLayoutInflater().inflate(R.layout.customtab, null);
        view2.findViewById(R.id.icon).setBackgroundResource(R.drawable.date_selector);
        tabLayout.getTabAt(0).setIcon(R.drawable.menu_selector).setCustomView(view1);
        tabLayout.getTabAt(1).setIcon(R.drawable.date_selector).setCustomView(view2);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null); // enabling colored icons in the drawer
        Menu menu = navigationView.getMenu();

        // Making the drawer titles white rather than the default black

        MenuItem customize= menu.findItem(R.id.Customize);
        SpannableString s = new SpannableString(customize.getTitle());
        s.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance44), 0, s.length(), 0);
        customize.setTitle(s);

        MenuItem help= menu.findItem(R.id.Help);
        SpannableString s1 = new SpannableString(help.getTitle());
        s1.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance44), 0, s1.length(), 0);
        help.setTitle(s1);

        MenuItem about= menu.findItem(R.id.About);
        SpannableString s2 = new SpannableString(about.getTitle());
        s2.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance44), 0, s2.length(), 0);
        about.setTitle(s2);

        // So that I can mess with the fragment and its elements
        fragment = (Dates) adapter.getItem(0);
        fragment_2 = (createDate) adapter.getItem(1);

        sharedPreferences = getSharedPreferences("", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


    }

    public void Facebook(View view) {
        Intent facebookIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Sham-430908133988248"));
        startActivity(facebookIntent);
    }

    public void AddImage(View view) {
        addImageToNotification.setContentView(R.layout.add_from_gallery_fragment);
        addImageToNotification.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        addImageToNotification.show();
        GridView gridView = (GridView) addImageToNotification.findViewById(R.id.gallery_gridview);
        final ImageAdapter imageAdapter = new ImageAdapter(this);
        gridView.setAdapter(imageAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                fragment_2.setSelectedImageBack(imageAdapter.getImage(i));
                selectedImage = imageAdapter.getImage(i); // making image equal to the selected one
                addImageToNotification.dismiss();
                imageSelected = true;
                if (imageSelected && preTimeSelected && posttimeSelected) fragment_2.showSetButton(); // if everything is added > allow for the creation of a notification
            }
        });
    }

    public void ToggleRnd(View view) {
        ImageButton rndImage = (ImageButton) findViewById(R.id.rnd_button);
        ImageButton autoImage = (ImageButton) findViewById(R.id.auto_button);
        rndImage.setAlpha(1.0f);
        autoImage.setAlpha(0.2f);
        rndNotification = true;
    }

    public void ToggleAuto(View view) {
        ImageButton rndImage = (ImageButton) findViewById(R.id.rnd_button);
        ImageButton autoImage = (ImageButton) findViewById(R.id.auto_button);
        rndImage.setAlpha(0.2f);
        autoImage.setAlpha(1.0f);
        rndNotification = false;
    }

    public void SetNotification(View view) {
        if (fragment_2.getNumberPickerInt() != 1) {
            Toast.makeText(sham.this, "Apologies: you are only allowed one instance of notifications in the public alpha", Toast.LENGTH_SHORT).show();
        }
        else {
            nameInput = (EditText) findViewById(R.id.name_input);
            messageInput = (EditText) findViewById(R.id.message_input);

            name = nameInput.getText().toString();
            message = messageInput.getText().toString();

            //if statement dependent on which choice is visible
            if (rndNotification) {
                fragment.addItem(name, message, selectedImage, R.drawable.outline_blue, R.drawable.die_icon, timePickerHourMin + ":" + timePickerMinuteMin,
                        timePickerHourMax + ":" + timePickerMinuteMax, fragment_2.getNumberPickerInt(), createTimes(fragment_2.getNumberPickerInt(),
                                rndNotification, timePickerHourMin, timePickerMinuteMin, timePickerHourMax, timePickerMinuteMax));
                for (int i = 0; i < fragment_2.getNumberPickerInt(); i++) {
                    scheduleNotification(getNotification(message, name, selectedImage), fragment.getListIconsAndNames().get(0).getTimes()[i]);
                    Log.d("checkNoti", "" + fragment.getListIconsAndNames().get(0).getTimes()[i]);
                }
            } else {
                fragment.addItem(name, message, selectedImage, R.drawable.outline_blue, R.drawable.clock_icon, timePickerHourMin + ":" + timePickerMinuteMin,
                        timePickerHourMax + ":" + timePickerMinuteMax, fragment_2.getNumberPickerInt(), createTimes(fragment_2.getNumberPickerInt(),
                                rndNotification, timePickerHourMin, timePickerMinuteMin, timePickerHourMax, timePickerMinuteMax));
                for (int i = 0; i < fragment_2.getNumberPickerInt(); i++) {
                    scheduleNotification(getNotification(message, name, selectedImage), fragment.getListIconsAndNames().get(0).getTimes()[i]);
                    Log.d("checkNoti", "" + fragment.getListIconsAndNames().get(0).getTimes()[i]);
                }
            }
            // Setting elements of the create notification tab back to default for the users next submission
            fragment_2.setSelectedImageBack(R.drawable.place_img_icon);
            fragment_2.setMaxTimeButtonText("Max Time");
            fragment_2.setMinTimeButtonText("Min Time");
            // making it so the user must enter specific data before they can create a notification
            posttimeSelected = false;
            preTimeSelected = false;
            imageSelected = false;
            fragment_2.hideSetButton();
        }
    }

    public void SetMinTime(View view) {
        timePickerDialog = new Dialog(this);
        timePickerDialog.setContentView(R.layout.timepicker);
        timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        timePickerDialog.show();
        final TimePicker timePicker = (TimePicker) timePickerDialog.findViewById(R.id.time_picker);

        //used in order to close the dialog when the user sets the wanted time and saves the time to a variable
        final Button button = (Button) timePickerDialog.findViewById(R.id.set_time);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                timePickerDialog.dismiss();
                // this if statment is placed in order to allow users with sdk < 23 to use the timepicker
                // apparently android changed support
                if (Build.VERSION.SDK_INT >= 23 ) {
                    timePickerHourMin = timePicker.getHour();
                    timePickerMinuteMin = timePicker.getMinute();
                    fragment_2.setMinTimeButtonText(timePicker.getHour() + ":" + timePicker.getMinute());
                }
                else {
                    timePickerHourMin = timePicker.getCurrentHour();
                    timePickerMinuteMin = timePicker.getCurrentMinute();
                    fragment_2.setMinTimeButtonText(timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute());
                }
                preTimeSelected = true;
                if (imageSelected && preTimeSelected && posttimeSelected) fragment_2.showSetButton(); // if everything is added > allow for the creation of a notification
            }
        });

    }
    public void SetMaxTime(View view) {
        timePickerDialog = new Dialog(this);
        timePickerDialog.setContentView(R.layout.timepicker);
        timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        timePickerDialog.show();

        final TimePicker timePicker = (TimePicker) timePickerDialog.findViewById(R.id.time_picker);

        //used in order to close the dialog when the user sets the wanted time and saves the time to a variable
        final Button button = (Button) timePickerDialog.findViewById(R.id.set_time);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                timePickerDialog.dismiss();
                // this if statment is placed in order to allow users with sdk < 23 to use the timepicker
                // apparently android changed support
                if (Build.VERSION.SDK_INT >= 23 ) {
                    timePickerHourMax = timePicker.getHour();
                    timePickerMinuteMax = timePicker.getMinute();
                    fragment_2.setMaxTimeButtonText(timePicker.getHour() + ":" + timePicker.getMinute());
                }
                else {
                    timePickerMinuteMax = timePicker.getCurrentMinute();
                    timePickerHourMax = timePicker.getCurrentHour();
                    fragment_2.setMaxTimeButtonText(timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute());
                }
                posttimeSelected = true;
                if (imageSelected && preTimeSelected && posttimeSelected) fragment_2.showSetButton(); // if everything is added > allow for the creation of a notification
            }
        });
    }

    //this method is used to get the times for the notifications, the long array is going to be placed in each IconsAndnames object
    public long[] createTimes (int occurrences, boolean random, int startHour, int startMinute, int endHour, int endMinute) {
        long[] times = new long[occurrences];
        long maxMilisecondsFromNotification = TimeUnit.MINUTES.toMillis(endMinute) + TimeUnit.HOURS.toMillis(endHour);
        long minMilisecondsFromNotification = TimeUnit.MINUTES.toMillis(startMinute) + TimeUnit.HOURS.toMillis(startHour);
        if (random) {
           // for (int i = 0; i < occurrences; i++) {
                times[0] = ThreadLocalRandom.current().nextLong(minMilisecondsFromNotification, maxMilisecondsFromNotification)
                        - minMilisecondsFromNotification; // generating random long numbers in the given time frame
           // }
       }
        else {
            long difIncrement = (maxMilisecondsFromNotification -  minMilisecondsFromNotification) / occurrences;
            //for (int i = 0; i < occurrences; i++) {
                times[0] = difIncrement;
           // }
        }
        return times;
    }

    public void Twitter(View view) {
        Intent twitterIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/ShamHQappdev"));
        startActivity(twitterIntent);
    }
    public void Insta(View view) {
        Intent instaIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/shamhqappdev"));
        startActivity(instaIntent);
    }
    public void Rate(View view) {
        Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=willambrozic.tmpcompany.com.sham"));
        startActivity(rateIntent);
    }

    public void StartSpamTimer(View view) {
        NotificationPublisher.stopSpam = false;
            new CountDownTimer(5000, 1000) {

                public void onTick(long millisUntilFinished) {
                    //setting the seekbars to locked so that the user cannot mess with them after hitting the spam button
                    //notiRingSeek.setEnabled(false);
                   // notiSeek.setEnabled(false);
                    //ringSeek.setEnabled(false);
                    stopSpam.setVisibility(View.VISIBLE);
                    startSpam.setVisibility(View.GONE);
                    stopSpam.setEnabled(false);
                    spamMenu.setCancelable(false); // prevents the user from exiting the dialog during countdown
                    spamMenu.setCanceledOnTouchOutside(false); // prevents the user from exiting the dialog during countdown
                    spamCountdown.setText("" + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    if (showSpamDialog) {
                        spamCountdown.setTextSize(30);
                        spamCountdown.setText("STOP");
                    }
                     spamMenu.setCancelable(true);
                     spamMenu.setCanceledOnTouchOutside(true);
                        stopSpam.setEnabled(true);
                        Spam();
                }
            }.start();
    }

    private void Spam () {
        int j;
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        player = MediaPlayer.create(this, notification);
        player.setLooping(true);
        player.start();
        if (fragment.getArrayListSize() > 0) {
            j = (int) (Math.random() * (fragment.getArrayListSize()));
            scheduleNotification(getNotification(fragment.getItemMessage(j), fragment.getItemName(j), fragment.getItemImage(j)), 1000);
        }
        else {
            Toast.makeText(sham.this, "You have no notifications to send: please make atleast one in order to spam notifications", Toast.LENGTH_SHORT).show(); // error prevention
        }
    }
    //This method lets me send a notification created by getNotificatoins along with the given delay / the time set by the user (NOT ENTIRELY MY CODE: BUT HAS BEEN MODIFIED)
    private void scheduleNotification(Notification notification, long delay) {

        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        //giving a new key no matter what so that the notifications can overlap (they can happen multiple times)
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE), notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    //getNotification allows me to "make" the notification through sending the required arguments (NOT ENTIRELY MY CODE: BUT HAS BEEN MODIFIED)
    private Notification getNotification(String message, String name, int icon) {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle(name);
        builder.setContentText(message).setSound(notification);
        builder.setSmallIcon(icon);
        return builder.build();
    }


    public void StopSpamTimer(View view) {
        if (player != null) //just in case
            player.stop();
        spamCountdown.setTextSize(50);
        spamCountdown.setText("5");
        stopSpam.setVisibility(View.GONE);
        startSpam.setVisibility(View.VISIBLE);
        NotificationPublisher.stopSpam = true;
        //Letting the user mess with the seekbars before they press the spam button (full release)
        //notiRingSeek.setEnabled(true);
        //notiSeek.setEnabled(true);
       // ringSeek.setEnabled(true);
    }

    public void ShowSpamMenu(View view) {
        showSpamDialog = true;
        spamMenu.setContentView(R.layout.sham_spam_dialog);
        spamMenu.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        spamMenu.show();
        // used to detect if the dialog has been closed
        spamMenu.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (player != null) { // because it is possible for player to not be initialized before
                    // this, I am adding an if statement to prevent a possible crash
                    player.stop();
                }
                showSpamDialog = false;
            }
        });
        spamCountdown = (TextView) spamMenu.findViewById(R.id.spam_countdown); // referencing the button in the dialog spamMenu
       // notiRingSeek = (SeekBar) spamMenu.findViewById(R.id.seekBar_notiring);
       // ringSeek = (SeekBar) spamMenu.findViewById(R.id.seekBar_ring);
        //notiSeek = (SeekBar) spamMenu.findViewById(R.id.seekBar_noti);
        startSpam = (ImageButton) spamMenu.findViewById(R.id.start_spam_button);
        stopSpam = (ImageButton) spamMenu.findViewById(R.id.stop_spam_button);
        spamCountdown.setText("5");
        //notiRingSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //@Override
           // public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //notiRingSeek.setProgress(i);
           // }

           // @Override
           // public void onStartTrackingTouch(SeekBar seekBar) {

           // }

           // @Override
            //public void onStopTrackingTouch(SeekBar seekBar) {

           // }
       // });
    }
    public void DeleteNotification(View view) {
        if (fragment.getArrayListSize() >0 )
        fragment.removeItem(fragment.getItemPosition());

    }


    // saving data when app closes
    @Override
    protected void onPause() {
        super.onPause();
        Gson gson = new Gson();
        String jsonText = gson.toJson(fragment.getListIconsAndNames()); // converting the arraylist of notifications to Json string in order to save the list
        editor.putString("key", jsonText); // saving with a tag to be referenced when retrieved
        editor.apply();
    }

    // retrieving data when app resumes
    @Override
    protected void onResume() {
        super.onResume();
        //if statement prevents an error
        if (fragment.getArrayListSize() >= 0 && !WelcomeActivity.firstRun) {
            Gson gson = new Gson();
            String jsonText = sharedPreferences.getString("key", null); //Retrieving jasonText from preferences using the key (in this case it's just called key because there is only one thing in the preferences
            List<IconsAndNames> test = gson.fromJson(jsonText, new TypeToken<List<IconsAndNames>>() {
            }.getType()); //converting the Json string to my list type
            fragment.setIconsAndNames(test); // setting the list in the Dates method
            reschedule(test);
        }
    }

    //called to save the notifications for next use of the app
    public void reschedule(List<IconsAndNames> test) {
        for (int i = 0; i < test.size(); i++) {
            for (int j = 0; j < test.get(i).getTimes().length; j++)
                scheduleNotification(getNotification(fragment.getItemMessage(i), fragment.getItemName(i), fragment.getItemImage(i)), test.get(i).getTimes()[j]);
        }
    }

    // used to make the hambergur menu
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    //dealing with the drawer dabs and what each will do when clicked
    @SuppressLint("ResourceType")
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gallery_icon) {
            Toast.makeText(sham.this, "Apologies: feature not available in public alpha", Toast.LENGTH_SHORT).show(); // comment this for full release

            /* not available in open alpha (Unfinished)
            Gallery gallery = new Gallery();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.drawer_layout, gallery); // send in gallery in order to access the gridview
            ft.addToBackStack("gallery_tag");
            ft.commit();


            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

            gridView = gallery.getGridView();

            //initializing gridview

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(sham.this, "" + i, Toast.LENGTH_SHORT).show();
                }
            });
            */
        } else if (id == R.id.nav_settings) {
            Toast.makeText(sham.this, "Apologies: feature not available in public alpha", Toast.LENGTH_SHORT).show(); // comment this for full release

        } else if (id == R.id.nav_help) {
            showIntro = true;
            startActivity(new Intent(sham.this, WelcomeActivity.class));

        } else if (id == R.id.nav_follow) {
            supportDialog.setContentView(R.layout.support_popup);
            supportDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            supportDialog.show();

        } else if (id == R.id.nav_about) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.drawer_layout, new AboutSham());
            ft.addToBackStack("tag_back");
            ft.commit();
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            Toast.makeText(sham.this, "Apologies: more to be added after public alpha", Toast.LENGTH_SHORT).show(); // comment this for full release
        }

        return true;
    }
    public class ImageAdapter extends BaseAdapter {
        private Context gridViewContext;

        public ImageAdapter(Context gridViewContext) {
            this.gridViewContext = gridViewContext;
        }

        @Override
        public int getCount() {
            return images.length;
        }

        public Object getItem(int i) {
            return null;
        }
        public int getImage(int i) {
            return images[i];
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView (int position, View ConvertView, ViewGroup parent) {
            ImageView imageView = new ImageView(gridViewContext);
            imageView.setLayoutParams(new GridView.LayoutParams(120,120));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(12,12,12,12);
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


    }




