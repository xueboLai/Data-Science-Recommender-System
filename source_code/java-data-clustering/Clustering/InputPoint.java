package Clustering;
/**
 * This is a helper class that output the right format of data which needs to be processed by the K means plus plus algorithm
 * implemented by java common math library.
 * 
 * 
 * 
 * @author: Xuebo Lai
 * @version:8/10/2017(last modification)
 * 
 */
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.math3.ml.clustering.Clusterable;

public class InputPoint implements Clusterable {
	String name;
    private double[] points;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Arrays.hashCode(points);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean same = true;
		InputPoint anObj = (InputPoint) obj;
		if(this.name!=anObj.getName()){
			same = false;
		}
		for(int i = 0;i<this.getPoint().length;i++){
			if(this.getPoint()[i]!=anObj.getPoint()[i]){
				same = false;
			}
		}
		return same;
	
	}

	
    public InputPoint(String inputName,ArrayList<Double> aPerson) {
    	this.name = inputName;
    	int length = aPerson.size();
    	this.points = new double[length];
    	for(int i =0;i<length;i++){
    		this.points[i] = aPerson.get(i);
    	}
    }
    
	@Override
	public double[] getPoint() {
		return points;
	}
	//getter to get name
	public String getName(){
		return name;
	}

}
