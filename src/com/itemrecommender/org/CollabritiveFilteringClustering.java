//by EuclideanDistanceSimilarity for user 1 number of items =1
//RecommendedItem[item:104, value:4.2743363]
//by PearsonSimilarity for user 1  number of items =1
//RecommendedItem[item:104, value:4.257081]


package com.itemrecommender.org;

import java.util.List;
import java.io.File;
import java.io.IOException;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
 
public class CollabritiveFilteringClustering {
	public static void main(String[] args) {
	
			try {
				DataModel dm = new FileDataModel( new File("data/MoviesList.csv"));
				/*Here we are using Pearson's Correlation
			     * it has limitations such as that it does not account
			     * for the NUMBER of items in which 2 users' preferences overlap.
			     * There are other options such as Euclidean Distance similarity.
			    */
			    UserSimilarity similarity = new PearsonCorrelationSimilarity(dm);
			    //UserSimilarity similarity = new EuclideanDistanceSimilarity(dm);

			    //First parameter - neighborhood size; capped at the number of users in the data model
			    UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, dm);
			    Recommender recommender = new GenericUserBasedRecommender(dm, neighborhood, similarity);
			    //First parameter is UserID, the second is the number of items to be recommended
			    List <RecommendedItem> recommendedItems = recommender.recommend(1, 1);
			    for (RecommendedItem recommendedItem :recommendedItems){
			    	System.out.println(recommendedItem);
			    }
			    
			} catch (IOException e) {
				System.out.println("Datamodel exception");
				e.printStackTrace();
			} catch (TasteException e) {
				System.out.println("Taste exception");
				e.printStackTrace();
			}
			

	}
}


