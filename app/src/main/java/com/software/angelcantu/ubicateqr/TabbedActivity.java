package com.software.angelcantu.ubicateqr;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.software.angelcantu.ubicateqr.entidades.EdificioModelo;
import com.software.angelcantu.ubicateqr.fragments.DetalleEdificioFragment;
import com.software.angelcantu.ubicateqr.fragments.EdificioFragment;
import com.software.angelcantu.ubicateqr.fragments.InformacionFragment;
import com.software.angelcantu.ubicateqr.interfaces.IComunicaFragments;

public class TabbedActivity extends AppCompatActivity implements EdificioFragment.OnFragmentInteractionListener, DetalleEdificioFragment.OnFragmentInteractionListener,IComunicaFragments {


    private SectionsPagerAdapter mSectionsPagerAdapter;


    private ViewPager mViewPager;
    private String info, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_tabbed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      /*  if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tabbed, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    InformacionFragment frag = new InformacionFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("Info", recibirInfo());
                    bundle.putString("id", recibirId());
                    frag.setArguments(bundle);
                    return frag;
                case 1:
                    EdificioFragment frag1 = new EdificioFragment();
                    Bundle bundles = new Bundle();
                    bundles.putString("Info", recibirInfo());
                    bundles.putString("id", recibirId());
                    frag1.setArguments(bundles);
                    return frag1;
            }
            return null;
            //return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }

    public String recibirInfo() {
        Bundle extras = getIntent().getExtras();
        info = extras.getString("Info");
        return info;
    }

    public String recibirId() {
        Bundle extras = getIntent().getExtras();
        id = extras.getString("id");
        return id;
    }

    DetalleEdificioFragment detalleFragment;

    @Override
    public void enviarEdificio(EdificioModelo edificio) {
        detalleFragment=new DetalleEdificioFragment();
        detalleFragment.asignarInformacion(edificio);
        Bundle bundleEnvio=new Bundle();
        bundleEnvio.putSerializable("objeto",edificio);
        detalleFragment.setArguments(bundleEnvio);

    }
}
