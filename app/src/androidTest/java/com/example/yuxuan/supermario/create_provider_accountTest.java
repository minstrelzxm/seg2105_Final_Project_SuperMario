package com.example.yuxuan.supermario;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class create_provider_accountTest {

    //The Unit Test for Demo 4
    //three Unit Test Cases
    @Rule
    public ActivityTestRule<create_owner_account> mActivityTestRule= new ActivityTestRule<create_owner_account>(create_owner_account.class);
    private create_owner_account mActivity=null;
    private TextView text;

    //Test in HomeOwnerMainPageActivity
    @Before
    public void setUp() throws Exception{
        mActivity=mActivityTestRule.getActivity();
    }


    @Test
    @UiThreadTest
    //Test the username
    public void checkAccountName() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.textView3));
        text = mActivity.findViewById(R.id.createAccAccName);
        text.setText("jjj@gmail.com");
        String name=text.getText().toString();
        assertNotEquals("username",name);
    }

    @Test
    @UiThreadTest
    //Test the password
    public void checkPassword() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.textView4));
        text = mActivity.findViewById(R.id.createAccAccPassword);
        text.setText("12345");
        String name=text.getText().toString();
        assertNotEquals("password",name);
    }

    @Test
    @UiThreadTest
    //Test the re-password
    public void checkRePassword() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.textView5));
        text = mActivity.findViewById(R.id.createAccReAccPassword);
        text.setText("12345");
        String name=text.getText().toString();
        assertNotEquals("password",name);
    }

}