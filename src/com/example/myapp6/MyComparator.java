package com.example.myapp6;

import android.content.Context;
import com.example.myapp6.models.Contact;

import java.util.Comparator;

/**
 * Created by pc on 2016/4/13.
 */
public class MyComparator implements Comparator<Contact> {
    @Override
    public int compare(Contact c1, Contact c2) {
        return c1.getLetter().compareTo(c2.getLetter());
    }
}
