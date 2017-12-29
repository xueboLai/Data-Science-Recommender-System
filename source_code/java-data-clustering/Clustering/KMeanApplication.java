package Clustering;
/**
 * The goal of this program is to find the best way to put users into groups. The accuracy of grouping is determined
 * Silhoutte score. The higher the Silhoutte score, the better the grouping is. This is because, the Silhoutte score
 * represents how similar a data point (a person in this case) is to the data points in the same group as well as how
 * different the data point is from the data points in the different groups. When the Silhoutte score is high, that means 
 * the data point is very similar to the data points in the same group and very different from the data points in the different
 * groups. This program will use k means plus plus to achieve clustering and use Silhoutte score to pinpoint the best
 * group numbers (best way to group). Then, it will output the best grouping result to console and automatically generate 
 * similarity matrix for users at the end.
 * 
 * 
 * 
 * @author: Xuebo Lai
 * @version:8/10/2017(last modification)
 * 
 * */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;

public class KMeanApplication {
	public static void main(String[] args) {
		// Next Line is to auto-generated user to category csv file. It's optional. Depends on what kind of input file we have in the beginning.
		//InputMatrix a = new InputMatrix("/Users/xuebolai/Documents/java_program/data/History.csv");
		//Hashtable inputMatrix = a.getDataSet();
		//create a list to hold on to all the input entries
		List<InputPoint> clusterInput = new ArrayList<InputPoint>();
		BufferedReader br = null;
		String separator = ",";

		
		try{
			br = new BufferedReader(new FileReader("/Users/xuebolai/Desktop/classes/buffer/Data-Science-Recommender-System/data/UserToCategoryByMEAN.csv"));
	        String line = null;
	        br.readLine();
	        //int countPeople = 0;
			while ((line = br.readLine()) != null) {
				String[] record = line.split(separator);
				String name = record[0];
				
				ArrayList<Double> offers = new ArrayList<Double>();
				for(int i = 1;i<record.length;i++){
					offers.add(Double.parseDouble(record[i]));
					
				}
				InputPoint person = new InputPoint(name,offers);
				//System.out.println("testing: name: "+person.getName()+"; offers"+offers);
				//System.out.println("i'm here");
				/*for(int i = 0;i<record.length-1;i++){
					System.out.print(person.getPoint()[i]);
				}*/

				clusterInput.add(person);
				
	            //System.out.println("name: " + record[0] + ", offer=" + record[1] + ".");
	        }
		}
			catch (FileNotFoundException e1){
				System.err.println("can't find the files");
				System.exit(1);
			}catch(IOException e2){
				System.err.println("IOException.");
				System.exit(1);
			}catch(Exception e3){
				System.err.println(e3+" other kind of error");
			}

		
		ArrayList<Double> testRecord = new ArrayList<Double>();
		
		
		for(int test = 3;test<=6;test++){
			final int EachTestTime = 6;
			double total = 0;
			double average = 0;
			for (int rep = 0; rep<EachTestTime;rep++){
				//get rid of cold start
	
				KMeansPlusPlusClusterer<InputPoint> clusterer = new KMeansPlusPlusClusterer<InputPoint>(test, 1000);
				List<CentroidCluster<InputPoint>> output = clusterer.cluster(clusterInput);
				//examine result:
				//Silhouette Score calculation
				ArrayList<ArrayList<InputPoint>> listOfAllCluster = new ArrayList<ArrayList<InputPoint>>();
				
				for (int i=1; i<output.size(); i++) {
				    System.out.println("Cluster " + i);
				    ArrayList<InputPoint> aCluster = new ArrayList<InputPoint>();
				    for (InputPoint person : output.get(i).getPoints()){
				        System.out.println(person.getName());
				    	aCluster.add(person);
				    	}
				    if(aCluster.size()!=0){
				    	listOfAllCluster.add(aCluster);
				    }
				    System.out.println();
				}
				
				SilhouetteScore silScore = new SilhouetteScore(listOfAllCluster);
				double silhouetteScore = silScore.getScore();
				total+=silhouetteScore;
				//System.out.println("The Silhouette Score is "+silhouetteScore);
			}
			average = total/EachTestTime;
			testRecord.add(average);
		}
		double largest = testRecord.get(0);
		int largestIndex = 0;
		int actualKValue;
		for (int i=0;i<testRecord.size();i++){
			int actual = i+3;
			System.out.println("Silhoutte value for k= "+actual+" is "+testRecord.get(i));
			if(testRecord.get(i)>largest){
				largest = testRecord.get(i);
				largestIndex = i;
			}
		}
		actualKValue=(largestIndex+3);
		System.out.println("The best k is "+actualKValue+"; the silhoutte value for it is "+largest);
		KMeansPlusPlusClusterer<InputPoint> clusterer = new KMeansPlusPlusClusterer<InputPoint>(actualKValue, 1000);
		List<CentroidCluster<InputPoint>> output = clusterer.cluster(clusterInput);
		
		//generate output matrix
		File outputMatrix = new File("outputMatrix.csv");
		try{
			PrintWriter pw = new PrintWriter(outputMatrix);
			StringBuilder inputString = new StringBuilder();
			inputString.append("userName,"+"cluster,"+"\n");
			for (int i=0; i<output.size(); i++) {
				//System.out.println("Cluster " + i);
				for (InputPoint person : output.get(i).getPoints()){

					inputString.append(person.getName()+","+"cluster "+i+",");

					inputString.append("\n");
				}
			}
			String outputString = inputString.toString();
			pw.write(outputString);
			pw.close();
			System.out.println("output to outputMatrix.csv successfully.");
				/*for (int i=1; i<output.size(); i++) {
			    System.out.println("Cluster " + i);
			    ArrayList<InputPoint> aCluster = new ArrayList<InputPoint>();
			    for (InputPoint person : output.get(i).getPoints()){
			        System.out.println(person.getName());
			    	aCluster.add(person);
			    	}
			    if(aCluster.size()!=0){
			    	listOfAllCluster.add(aCluster);
			    }
			    System.out.println();
			}*/
				
		}catch(FileNotFoundException e4){
			System.err.println("can't find the files");
			System.exit(1);
		}catch(IOException e2){
			System.err.println("IOException.");
			System.exit(1);
		}catch(Exception e3){
			System.err.println(e3+" other kind of error");
			System.exit(1);
		}
		
		//end of getting output matrix
		
		generateSimilarityMatrix(clusterInput);
		//new PrincipleComponentAnalysis(clusterInput);
		
		

		
		

		
		

		
		
		
		
		
/*		//create a list to hold on to all the input entries
		List<InputPoint> clusterInput = new ArrayList<InputPoint>();
		//create an ArrayList to hold on to all the input keys (String)
		ArrayList<String> keys = new ArrayList<String>();
		//obtain all the keys into a set 
		Set<String> allKeys = inputMatrix.keySet();
		//put the keys from the set to the ArrayList
		for(String key:allKeys){
			keys.add(key);
		}
		for (int i=0;i<keys.size();i++){
			ArrayList<Integer> intermedia = (ArrayList<Integer>) inputMatrix.get(keys.get(i));
			System.out.println("Testing: "+intermedia);
			ArrayList<Integer> intermedia2 = new ArrayList<Integer>();
			intermedia2.add(1);
			intermedia2.add(2);
			intermedia2.add(3);
			InputPoint person = new InputPoint(keys.get(i),intermedia2);
			System.out.println("testing2: "+(person.getPoint())[2]);
		}
*/			
	}
	
	public static void generateSimilarityMatrix(List<InputPoint> outputMatrix){
		File similarityMatrix = new File("similarityMatrix.csv");
		try{
			PrintWriter pw = new PrintWriter(similarityMatrix);
			StringBuilder inputString = new StringBuilder();
			inputString.append(" ,");
			for(InputPoint aUser:outputMatrix){
				inputString.append(aUser.getName()+",");
			}
			inputString.append("\n");
			
			for(InputPoint yUser:outputMatrix){
				inputString.append(yUser.getName()+",");
				for(InputPoint xUser:outputMatrix){
					double Edistance = euclideanDistance(yUser.getPoint(),xUser.getPoint());
					inputString.append(Edistance+",");
				}
				inputString.append("\n");
			}


			String outputString = inputString.toString();
			pw.write(outputString);
			pw.close();				
		}catch(FileNotFoundException e4){
			System.err.println("can't find the files");
			System.exit(1);
		}catch(IOException e2){
			System.err.println("IOException.");
			System.exit(1);
		}catch(Exception e3){
			System.err.println(e3+" other kind of error");
			System.exit(1);
		}
	}
	public static double euclideanDistance(double[] a,double[] b){
		double totalDifferenceSquare = 0;
		double euclideanDistance = 0;
		if(a.length!=b.length){
			System.err.println("Error: length of both matrix are not equal.");
			System.exit(1);
			return -1;
		}else{
			for(int i=0;i<a.length;i++){
				double difference  = a[i]-b[i];
				double differenceSquare = (double) Math.pow(difference, 2);//difference*difference;
				totalDifferenceSquare += differenceSquare;
			}
			euclideanDistance  = Math.sqrt(totalDifferenceSquare);
			return euclideanDistance;
		}
	}
	
	


}
