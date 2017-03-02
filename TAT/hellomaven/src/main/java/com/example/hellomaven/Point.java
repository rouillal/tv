package com.example.hellomaven;

public class Point {
	private double x;
	private double y;
	
	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public void setX(float x){
		this.x=x;
	}
	
	public void setY(float y){
		this.y=y;
	}
}
