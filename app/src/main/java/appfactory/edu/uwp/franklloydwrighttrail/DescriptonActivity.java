package appfactory.edu.uwp.franklloydwrighttrail;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;



public class DescriptonActivity extends AppCompatActivity {
protected View view;
    private ViewPager _mViewPager;
    private ImageViewPagerAdapter _adapter;
    private ImageView _btn1, _btn2, _btn3;
    private ImageView fullScreen;
    private LinearLayout wholeLayout;

public static String value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        value = intent.getStringExtra("Title");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripton);


        wholeLayout = (LinearLayout) findViewById(R.id.whole_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fullScreen = (ImageView) findViewById(R.id.fullscreen);
        setUpView();
        setTab();
        onCircleButtonClick();

        TextView name = (TextView) findViewById(R.id.name);
        TextView built = (TextView) findViewById(R.id.built);
        TextView description = (TextView) findViewById(R.id.description);
        description.setMovementMethod(new ScrollingMovementMethod());
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        switch(value)
        {
            case "SC Johnson Administration Building and Research Tower":

                name.setText("SC Johnson Administration Building");
                built.setText("The SC Johnson Administration Building, designed\nby Frank Lloyd Wright in 1936.");
                description.setText("Far outside the corridors of high-tech industry and startup spaces, the SC Johnson company headquarters in Racine, Wisconsin, still provides a dashing vision of the modern American workplace, despite having recently celebrated its 75th anniversary. The SC Johnson Administration Building, designed by Frank Lloyd Wright in 1936, eschews business clichés: workers are greeted by a streamlined, muscular exterior made from ribbons of glass and brick, more campus than corporate, before entering a light-filled interior, with rows of organic, curved columns creating an abstract forest surrounding the secretary pool. The 15-story Research Tower, completed in 1950, was built on a \"taproot\" system. A core of elevators, heating, and ductwork formed a spine at the center on the tower, supported by a foundation sunk 54-feet-deep into the ground. Disc-shaped mezzanines branched off the core, alternating with full sized, 40-foot wide floors. Walls of horizontal Pyrex tubes, the same used in the Administration Building, let in sunlight but block the view. The effect is of a tree enclosed in glass.");

                break;
            case "Wingspread":

                name.setText("Wingspread");
                built.setText("Wingspread designed for SC Johnson owner\nHerbert Fisk Johnson, Jr. in 1936");
                description.setText("A private residence Wright designed for SC Johnson owner Herbert Fisk Johnson, Jr. in 1936, Wingspread lives up to its names, a low-slung streamlined brick home with four wings spreading across the property. The central living room, a dome-shaped room, features a 30-foot-tall vertical fireplace and original furniture designed by Wright. Considered the last of the Prairie Houses, it’s filled with unique touches, including a dramatic “Romeo and Juliet” balcony that cantilevers over the landscape and a crow’s nest lookout built above the home, a favorite play place for the Johnson children.");
                break;
            case "MononaTerrace":

                name.setText("Monona Terrace");
                built.setText("Monona Terrace completed in 1997");
                description.setText("A sprawling, curved convention center on the shores of Lake Monona in Madison, this building was a posthumous addition to the Wright canon, rejected by officials during the architect’s lifetime, but finally approved via a referendum put local voters in the early ‘90s. The curvilinear “dream civic center,” which recalls the shape of the Guggenheim, was first proposed by Wright in 1938, and was a project he constantly altered and updated throughout his life. His former apprentice Anthony Puttnam would finish the designs for the interior before it was finally completed in 1997.");
                break;
            case "Meeting House":

                name.setText("First Unitarian Society Meeting House");
                built.setText("A landmark in church architecture from 1951");
                description.setText("A landmark in church architecture from 1951, Wright’s ship-like design for the First Unitarian Society, with a sharp prow jutting out from the earth towards the sky, offers a sense of transcendence, one he would often enjoy as a member of the congregation. His extensive use of copper and glass throughout the building provide a timeless feel to the unorthodox house of worship.");
                break;
            case "FLW Visitor Center":

                name.setText("Taliesin and Frank Loyd Wright Visitor Center");
                built.setText("Wright’s longtime home and studio.");
                description.setText("Wright’s longtime home and studio, named after a Welsh term that means “Shining Brow,” is an icon of Prairie School design that saw numerous tragedies and rebirths over the decades as the architect continually renovated, rebuilt and expanded. Set on a 700-acre estate in the rolling hills of Spring Green where he grew up, it was the site where he created and designed many of his masterpieces beginning in 1911 and contains a collection of Wright-designed structures.");
                break;
            case "German Warehouse":

                name.setText("A.D. German Warehouse");
                built.setText("A.D. German Warehouse built in 1921.");
                description.setText("A small brick structure used by a local commodity wholesaler, Albert Dell German, this warehouse, finished in 1921, features an elaborate Mayan Revival exterior. Built in the town of Richland Center, where Wright was born, this offers a rare example of a project the architect designed in the late 1910’s that’s still standing.");
                break;
            default:
                description.setText("Other Place");
                getSupportActionBar().setTitle("Other");

        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.brown)));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Thank you for visiting", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        _mViewPager.setOnTouchListener(new View.OnTouchListener() {
            private float pointX;
            private float pointY;
            private int tolerance = 50;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:return false;
                    case MotionEvent.ACTION_DOWN:
                        pointX = event.getX();
                        pointY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        boolean sameX = pointX + tolerance > event.getX() && pointX - tolerance < event.getX();
                        boolean sameY = pointY + tolerance > event.getY() && pointY - tolerance < event.getY();
                        if(sameX && sameY)
                        {
                            int pos = _mViewPager.getCurrentItem();
                            switch (pos)
                            {
                                case 0: fullScreen.setImageDrawable(ImageOneFragment.imageOne.getDrawable());
                                    break;
                                case 1: fullScreen.setImageDrawable(ImageTwoFragment.imageTwo.getDrawable());
                                    break;
                                case 2: fullScreen.setImageDrawable(ImageThreeFragment.imageThree.getDrawable());
                            }

                            fullScreen.setVisibility(View.VISIBLE);
                            wholeLayout.setVisibility(View.GONE);
                        }
                }
                return false;
            }
        });
    }
    @Override
    public void onBackPressed(){
        if(fullScreen.getVisibility()== View.VISIBLE){
            fullScreen.setImageDrawable(null);
            fullScreen.setVisibility(View.GONE);
            wholeLayout.setVisibility(View.VISIBLE);
        }else{
            super.onBackPressed();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(fullScreen.getVisibility()== View.VISIBLE){
            fullScreen.setImageDrawable(null);
            fullScreen.setVisibility(View.GONE);
            wholeLayout.setVisibility(View.VISIBLE);
            return true;
        }else{
            super.onBackPressed();
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.home) {
            this.finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }






    private void onCircleButtonClick()
    {
        _btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                _btn1.setImageResource(R.drawable.selecteditem_dot);
                _mViewPager.setCurrentItem(0);
            }
        });
        _btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                _btn2.setImageResource(R.drawable.selecteditem_dot);
                _mViewPager.setCurrentItem(1);
            }
        });
        _btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                _btn3.setImageResource(R.drawable.selecteditem_dot);
                _mViewPager.setCurrentItem(2);
            }
        });

    }

    private void setUpView()
    {
        _mViewPager = (ViewPager) findViewById(R.id.imageviewPager);
        _adapter = new ImageViewPagerAdapter(getSupportFragmentManager());
        _mViewPager.setAdapter(_adapter);
        _mViewPager.setCurrentItem(0);
        initButton();


    }
    private void setTab()
    {
        _mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrollStateChanged(int position){}
            @Override
            public void onPageScrolled(int arg0,float arg1, int arg2){}
            @Override
            public void onPageSelected(int position)
            {
                _btn1.setImageResource(R.drawable.nonselecteditem_dot);
                _btn2.setImageResource(R.drawable.nonselecteditem_dot);
                _btn3.setImageResource(R.drawable.nonselecteditem_dot);

                btnAction(position);
            }
        });
    }

    private void btnAction(int action)
    {
        switch (action)
        {
            case 0: _btn1.setImageResource(R.drawable.selecteditem_dot);
                break;
            case 1: _btn2.setImageResource(R.drawable.selecteditem_dot);
                break;
            case 2: _btn3.setImageResource(R.drawable.selecteditem_dot);
                break;
        }
    }

    private void initButton()
    {
        _btn1 = (ImageView) findViewById(R.id.btn1);
        _btn1.setImageResource(R.drawable.selecteditem_dot);
        _btn2 = (ImageView) findViewById(R.id.btn2);
        _btn3 = (ImageView) findViewById(R.id.btn3);
    }
}
