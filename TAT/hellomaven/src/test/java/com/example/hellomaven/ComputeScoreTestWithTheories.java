package com.example.hellomaven;

import static org.junit.Assert.assertTrue;

import org.junit.Assume;
import org.junit.contrib.theories.DataPoints;
import org.junit.contrib.theories.Theories;
import org.junit.contrib.theories.Theory;
import org.junit.runner.RunWith;


@RunWith(Theories.class)
public class ComputeScoreTestWithTheories {
    @DataPoints
    public static int[] scores() {
        return new int[]{
            10, 7, 2,10,0,0,6,4,3,3,10,0,1,10,5,4};
    }
    @Theory
    public void a_plus_b_is_greater_than_a_and_greater_than_b(Integer a, Integer b) {
        Assume.assumeTrue(a >0 && b > 0 );
        assertTrue(a + b > a);
        assertTrue(a + b > b);
    }
    @Theory
    public void addition_is_commutative(Integer a, Integer b) {
        assertTrue(a + b == b + a);
    }
}
