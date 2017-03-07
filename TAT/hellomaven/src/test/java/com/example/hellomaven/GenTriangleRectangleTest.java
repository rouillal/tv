package com.example.hellomaven;

import static org.junit.Assert.assertTrue;

import org.junit.Assume;
import org.junit.contrib.theories.DataPoints;
import org.junit.contrib.theories.Theories;
import org.junit.contrib.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class GenTriangleRectangleTest {
	@DataPoints
    public static Point[] points() {
        return new Point[]{
            new Point(0.0,0.0), new Point(0.0,3.0), new Point(3.0,6.0), new Point(-20.0,0.0),new Point(2225.0,-65984.0)}; 
    }
	
	@DataPoints
    public static float[] q() {
        return new float[]{
            3/4, (float)2.6, (float)8.0, (float)69.7,(float)18.72,(float)198215.31122122,(float)-9.6511}; 
    }
	
	
	@Theory
	public void theoremePyth(Point a,Point b,float q) {
		Assume.assumeTrue(q>0);
		Point c = GenTriangleRectangle.genTriangleRectangle(a, b, q);
		Assume.assumeTrue(c != null);
		
		double normeBC = Math.sqrt(Math.pow((c.getX() - b.getX()), 2)+Math.pow((c.getY() - b.getY()), 2));
		double normeBA = Math.sqrt(Math.pow((a.getX() - b.getX()), 2)+Math.pow((a.getY() - b.getY()), 2));
		double normeAC = Math.sqrt(Math.pow((c.getX() - a.getX()), 2)+Math.pow((c.getY() - a.getY()), 2));
		
		assertTrue(normeBA + normeAC == normeBC);
	}
}
