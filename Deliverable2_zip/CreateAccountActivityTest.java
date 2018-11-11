package com.example.yuxuan.supermario;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;




public class CreateAccountActivityTest {

    @Rule
    public ActivityTestRule<CreateAccountActivity> mActivityTestRule= new ActivityTestRule<CreateAccountActivity>(CreateAccountActivity.class);
    private CreateAccountActivity mActivity=null;
    private TextView text;

    @Before
    public void setUp() throws Exception{
        mActivity=mActivityTestRule.getActivity();
    }


    @Test
    @UiThreadTest
    public void checkName() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.textView3));
        text = mActivity.findViewById(R.id.createAccAccName);
        text.setText("mingweideng@gmail.com");
        String name=text.getText().toString();
        assertNotEquals("username",name);
    }

    @Test
    @UiThreadTest
    public void checkPassword() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.textView4));
        text = mActivity.findViewById(R.id.createAccAccPassword);
        text.setText("123456");
        String name=text.getText().toString();
        assertNotEquals("password",name);
    }

    @Test
    @UiThreadTest
    public void checkVerification() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.textView5));
        text = mActivity.findViewById(R.id.createAccReAccPassword);
        text.setText("123456");
        String name=text.getText().toString();
        assertNotEquals("password",name);
    }

}