package com.example.hellomaven;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.contrib.theories.DataPoints;
import org.junit.contrib.theories.Theories;
import org.junit.contrib.theories.Theory;
import org.junit.runner.RunWith;


@RunWith(Theories.class)
public class ComputeScoreTestWithTheories {
	@DataPoints
	public static int[][] carreaux() {
		return new int[][]{{},{2},{2,7},{3,6,2,5},{3,7,2,5},{10,2,5},{10, 0, 0, 2}};
	}
	@Theory
	public void verify(int []carreau) {
		Assume.assumeTrue(carreau.length <= 10*2); //moins de 10 carreaux à 2 lancers
		for (int i = 0; i <carreau.length; i++)
			Assume.assumeTrue(carreau[i] >= 0 && carreau[i] <= 10); //chaque lancer est compris entre 0 et 10
		assertTrue(ComputeScore.computeScore(carreau) >= 0); //score positif
		assertTrue(ComputeScore.computeScore(carreau) >= scoreMin(carreau));//score au moins supérieur à la somme de tous les lancers
	}
	
	@Theory
	   public void testBlankArrays(int[] carreau)
	   {
	      Assume.assumeTrue(carreau.length == 0);
	      Assert.assertEquals(ComputeScore.computeScore(carreau),0);
	   }
	
	public int scoreMin(int []carreau){
		int score = 0;
		for(int i = 0; i<carreau.length; i++)
			score += carreau[i];
		return score;
	}
}
