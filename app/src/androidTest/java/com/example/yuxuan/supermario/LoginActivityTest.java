package com.example.yuxuan.supermario;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;




public class LoginActivityTest {

    //The Unit Test for Demo 2

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule= new ActivityTestRule<LoginActivity>(LoginActivity.class);
    private LoginActivity mActivity=null;
    private TextView text;

    @Before
    public void setUp() throws Exception{
        mActivity=mActivityTestRule.getActivity();
    }


    @Test
    @UiThreadTest
    public void checkEmail() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.textView6));
        text = mActivity.findViewById(R.id.loginTextUsernameInput);
        text.setText("mingweideng@gmail.com");
        String name=text.getText().toString();
        assertNotEquals("username",name);
    }

    @Test
    @UiThreadTest
    public void checkPassword() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.textView8));
        text = mActivity.findViewById(R.id.loginTextUserPasswordInput);
        text.setText("123456");
        String name=text.getText().toString();
        assertNotEquals("password",name);
    }

}