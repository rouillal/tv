package com.example.hellomaven;

public class ComputeScore {

	public static int computeScore(int[] liste) throws IllegalArgumentException{
		int score = 0;
		boolean isStrike=false;
		
		for (int i=0; i<liste.length;i++){
			if(liste[i] <0 || liste[i] > 10){
				throw new IllegalArgumentException();
			}
			if(liste[i] == 10){ //Strike 
				int tmpS = liste[i+1]+liste[i+2];
				score += tmpS;
				isStrike = true;
			}
			
			if(i <= liste.length-2){
				int tmpS = liste[i]+liste[i+1];
				if(tmpS > 10 && !isStrike){
					throw new IllegalArgumentException();
				}
				if(tmpS == 10){ //Spare
					score+=liste[i+2];
				}
			}
			
			score+=liste[i];
			isStrike = false;
		}



		return score;
	}	


	private static boolean verify(int[] liste){

		for(int i = 0; i < liste.length; i++)
			if(liste[i] < 0 || liste[i] > 10) return false;

		return true;
	}
}
