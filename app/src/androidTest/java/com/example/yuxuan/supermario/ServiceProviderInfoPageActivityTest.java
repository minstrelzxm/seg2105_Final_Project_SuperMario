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

    //The Unit Test for Demo 4
    //Three Unit Test Cases
    @Rule
    public ActivityTestRule<ServiceProviderInfoPageActivity> mActivityTestRule= new ActivityTestRule<ServiceProviderInfoPageActivity>(ServiceProviderInfoPageActivity.class);
    private ServiceProviderInfoPageActivity mActivity=null;
    private TextView text;

    //Test in ServicesProviderInfoPageActivity
    @Before
    public void setUp() throws Exception{
        mActivity=mActivityTestRule.getActivity();
    }


    @Test
    @UiThreadTest
    //Test the address
    public void checkAddress() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.Address));
        text = mActivity.findViewById(R.id.EditAddress);
        text.setText("University of Ottawa");
        String name=text.getText().toString();
        assertNotEquals("address",name);
    }

    @Test
    @UiThreadTest
    //Test the Lienses
    public void checkLiensed() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.Licensed));
        text = mActivity.findViewById(R.id.BooleanLicensed);
        text.setText("Yes");
        String name=text.getText().toString();
        assertNotEquals("liense",name);
    }

    @Test
    @UiThreadTest
    //Test the companyName
    public void checkCompanyName() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.NameOfCompany));
        text = mActivity.findViewById(R.id.EditNameOfCompany);
        text.setText("IBM");
        String name=text.getText().toString();
        assertNotEquals("company",name);
    }

    @Test
    @UiThreadTest
    public void checkGeneralInfo() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.GeneralInfo));
        text = mActivity.findViewById(R.id.EditGeneralInfo);
        text.setText("Info");
        String name=text.getText().toString();
        assertNotEquals("software",name);
    }

    @Test
    @UiThreadTest
    public void checkPhoneNum() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.PhoneNum));
        text = mActivity.findViewById(R.id.EditPhoneNum);
        text.setText("6132767325");
        String name=text.getText().toString();
        assertNotEquals("phone",name);
    }
}