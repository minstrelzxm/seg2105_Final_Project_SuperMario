package com.example.yuxuan.supermario;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class HomeOwnerMainPageActivityTest {

    //The Unit Test for Demo 4
    //three Unit Test Cases
    @Rule
    public ActivityTestRule<HomeOwnerMainPageActivity> mActivityTestRule= new ActivityTestRule<HomeOwnerMainPageActivity>(HomeOwnerMainPageActivity.class);
    private HomeOwnerMainPageActivity mActivity=null;
    private TextView text;

    //Test in HomeOwnerMainPageActivity
    @Before
    public void setUp() throws Exception{
        mActivity=mActivityTestRule.getActivity();
    }


    @Test
    @UiThreadTest
    //Test the username
    public void checkServiceName() throws Exception {
        assertNotNull(mActivity.findViewById(R.id.homeOwnerMainSearchTextView));
        text = mActivity.findViewById(R.id.homeOwnerMainSearchField);
        text.setText("jjj@gmail.com");
        String name = text.getText().toString();
        assertNotEquals("username", name);
    }

}