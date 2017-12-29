package Clustering;
/**
 * This is the class that is used to calculate the Silhouette Score which would be used to determinate how good the 
 * grouping is. The detailed definition, explanation and mathematical formula can be found in Wikipedia.
 * [https://en.wikipedia.org/wiki/Silhouette_(clustering)]
 * 
 * 
 * 
 * @author: Xuebo Lai
 * @version:8/10/2017(last modification)
 * 
 */
import java.util.ArrayList;
import java.util.Hashtable;
import static java.lang.Math.*;


public class SilhouetteScore {
	private double score;
	private ArrayList<ArrayList<InputPoint>> listOfCategoriedElements;
	private double number;
	private int indexNumber=0;
	
	public SilhouetteScore(ArrayList<ArrayList<InputPoint>> keys){
		//get the total number of people
		int dataNum=0;
		for(int i=0; i<keys.size();i++){
			for(int z=0; z<keys.get(i).size();z++){
				dataNum++;
			}
		}
		this.number = (double)dataNum;
		
		this.listOfCategoriedElements = keys;
		double total = 0;
		for(int i=0; i<listOfCategoriedElements.size();i++){
			ArrayList<InputPoint> currentClusters = keys.get(i);
			for(int j = 0;j<(listOfCategoriedElements.get(i)).size();j++){
				InputPoint currentPerson = currentClusters.get(j);
				double a_j = calculateAverageDistanceForOnePoint(currentClusters,currentPerson,0);
				//System.out.println("a(i): "+a_j);
				//double b_j = 
				//calculate the distance of one point to the other cluster
				double smallestDistance=0;
				boolean firstTime = true;
				for(int z=0; z<listOfCategoriedElements.size();z++){
					//System.out.println("ZZZZZZZZZZZZZZZZZZZZvalueZZZZZZZZZZZZZZZZZZZZZ: "+z);
					int temp = z;
					//System.out.println("important value: "+!currentClusters.equals(listOfCategoriedElements.get(z)));
					if(!currentClusters.equals(listOfCategoriedElements.get(z))){
						//System.out.println("ZZZZZZZZZZZZZZZZZZZZvalueZZZZZZZZZZZZZZZZZZZZZ: "+z);
						double distanceToOneCluster = calculateAverageDistanceForOnePoint(keys.get(z),currentPerson,1);
						//System.out.println("2   ZZZZZZZZZZZZZZZZZZZZvalueZZZZZZZZZZZZZZZZZZZZZ: "+z);
						if(firstTime){
							firstTime = false;
							smallestDistance = distanceToOneCluster;
						}
						if(distanceToOneCluster<smallestDistance){
							smallestDistance = distanceToOneCluster;
							indexNumber = z;
						}
					}
				}
				double b_j = smallestDistance;
				//System.out.println("b(j): "+b_j);
				double silhouetteScore = 0;
				if(a_j==b_j){
					silhouetteScore = 0;
				}else{
					if(a_j>b_j){
						silhouetteScore = (b_j-a_j)/a_j;
					}else{
					silhouetteScore = (b_j-a_j)/b_j;
					}
				}
				total+=silhouetteScore;
			}
				
			}
		this.score = (total/number);
		}
		
		/*
	    System.out.println("Cluster " + i);
	    ArrayList<InputPoint> aCluster = new ArrayList<InputPoint>();
	    for (InputPoint person : output.get(i).getPoints()){
	        System.out.println(person.getName());
	    	aCluster.add(person);
	    	}
	    	*/
	
	
	public double calculateAverageDistanceForOnePoint(ArrayList<InputPoint> aCluster, InputPoint aPerson,int mode){
		double sizeOfCluster = aCluster.size();
		double averageScore = 0;
		double totalScore = 0;
		InputPoint currentPerson = aPerson;
		double[] currentPersonOffers = currentPerson.getPoint();
		for (int i=0;i<aCluster.size();i++){
				if(!((aCluster.get(i)).equals(aPerson))){
				totalScore+=euclideanDistance(currentPersonOffers,(aCluster.get(i)).getPoint());
				}
			}
		if(mode == 0){
			if(sizeOfCluster==1){
				return 0;
			}
			averageScore = totalScore/(sizeOfCluster-1) ;
			System.out.println("aver, total, size "+averageScore+"  "+totalScore+"  "+sizeOfCluster);
		}else{
			averageScore = totalScore/(sizeOfCluster) ;
		}
		return averageScore;
		}
	
	
	public double euclideanDistance(double[] a,double[] b){
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
	
//	public double getClusterItemNumber(){
		
	//}
	
	
	
	public double getScore(){
		return score;
	}
	public double getDataNumber(){
		return number;
	}
}
