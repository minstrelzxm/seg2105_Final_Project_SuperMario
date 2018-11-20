package com.example.yuxuan.supermario;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class ServiceProviderInfoPageActivityTest {

    @Rule
    public ActivityTestRule<ServiceProviderInfoPageActivity> mActivityTestRule= new ActivityTestRule<ServiceProviderInfoPageActivity>(ServiceProviderInfoPageActivity.class);
    private ServiceProviderInfoPageActivity mActivity=null;
    private TextView text;

    @Before
    public void setUp() throws Exception{
        mActivity=mActivityTestRule.getActivity();
    }


    @Test
    @UiThreadTest
    public void checkAddress() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.Address));
        text = mActivity.findViewById(R.id.EditAddress);
        text.setText("University of Ottawa");
        String name=text.getText().toString();
        assertNotEquals("address",name);
    }

    @Test
    @UiThreadTest
    public void checkLiensed() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.Licensed));
        text = mActivity.findViewById(R.id.BooleanLicensed);
        text.setText("Yes");
        String name=text.getText().toString();
        assertNotEquals("liense",name);
    }

    @Test
    @UiThreadTest
    public void checkCompanyName() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.NameOfCompany));
        text = mActivity.findViewById(R.id.EditNameOfCompany);
        text.setText("IBM");
        String name=text.getText().toString();
        assertNotEquals("company",name);
    }

}