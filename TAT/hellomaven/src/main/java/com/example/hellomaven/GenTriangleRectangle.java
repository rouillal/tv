package com.example.hellomaven;

public class GenTriangleRectangle {
	
	
	public static Point genTriangleRectangle(Point a, Point b, float q){
		double ab = Math.sqrt(Math.pow((a.getX() - b.getX()), 2)+Math.pow((a.getY() - b.getY()), 2));
		double ac = ab/q; //2ème hypothèse
		double bc = Math.sqrt(Math.pow(ab, 2)+Math.pow(ac, 2));
		
		boolean trouve=false;

		double xc = 0.0;
		double yc = 0.0;
		for(xc=0.0;xc<10000.0;xc+=0.01){ //Méthode qui peut être lente mais efficace.
			for(yc=0.0;xc<10000.0;xc+=0.01){
				if(Math.sqrt(Math.pow((xc - b.getX()), 2)+Math.pow((yc - b.getY()), 2)) == bc){
					trouve = true;
					break;
				}
			}
		}
		
		if(trouve){
			Point c = new Point(xc,yc);
			return c;
		} 		
		return null;	//Pas de solution	
	}

}
