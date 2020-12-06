package com.example.tafebotappfinalversion;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import android.app.Instrumentation.ActivityMonitor;

import androidx.test.core.app.ActivityScenario;

import java.io.IOException;

import static java.util.regex.Pattern.matches;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;


public class ServoControlMainTest {


        @Test
    public void testIntent(){
        assertTrue("true != true", true);
        assertFalse("false != false", false);

        final String extraName = "IamAnExtra";
        final String extraValue = "IamAValue";
        Intent intent = new Intent();
        intent.putExtra(extraName, extraValue);

        assertFalse("hasExtra==false", intent.hasExtra(extraName));
    }







    }

