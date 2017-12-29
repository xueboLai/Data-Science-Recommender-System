package Clustering;
/**
 * This a helper class that is used to generate a user to category rating matrix, assuming that the input is in the format
 * of userName to category each roll. For example:
 * original file:
 * user1 	category2		rating1
 * user1 	category4		rating2
 * user2 	category3		rating3
 * user2 	category1		rating4
 * 
 * 
 * file after transformation:
 * 
 * 			c1		c2		c3		c4
 * user1	0		r1		0		r2
 * user2	r4		0		r3		0
 * 
 * 
 * 
 * @author: Xuebo Lai
 * @version:8/10/2017(last modification)
 * 
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.util.Set;
import java.io.File;

public class InputMatrix {
	Hashtable<String, ArrayList<Integer>> usersToOffers;
	//constructor:
	public InputMatrix(String inputCSV){
	//create matrix
	BufferedReader br = null;
	String separator = ",";
	this.usersToOffers = new Hashtable<String,ArrayList<Integer>>();
	try{
		br = new BufferedReader(new FileReader(inputCSV));
        String line = null;
        br.readLine();
		while ((line = br.readLine()) != null) {
			String[] record = line.split(separator);
			if(!usersToOffers.containsKey(record[0])){
				//replace(record[0],)
				String key = record[0];
				ArrayList<Integer> offers = new ArrayList<Integer>();
				offers.add(Integer.parseInt(record[1]));
				usersToOffers.put(key,offers);
			}else{
				ArrayList<Integer> offers = usersToOffers.get(record[0]);
				offers.add(Integer.parseInt(record[1]));
				usersToOffers.replace(record[0], offers);
				System.out.println(usersToOffers.get(record[0]));
			}

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
	ArrayList<String> keys = new ArrayList<String>();
	Set<String> allKeys = usersToOffers.keySet();
	for(String key:allKeys)
		keys.add(key);
	File input_out = new File("inputToMatrix.csv");
	try{
		PrintWriter pw = new PrintWriter(input_out);
		StringBuilder inputString = new StringBuilder();
		inputString.append(" "+",");
		for(int i = 1;i<=25;i++){
			inputString.append("c"+Integer.toString(i)+",");
		}
		
		for(String key:keys){
			inputString.append(key+",");
			for(int i=1;i<=25;i++){
				if(usersToOffers.get(key).contains(i)){
					inputString.append("1"+",");
				}else{
					inputString.append("0"+",");
				}
			}
			if(usersToOffers.get(key).contains(32)){
				inputString.append("1"+"\n");
			}else{
				inputString.append("0"+"\n");
			}
			}
		String realInput = inputString.toString();
        pw.write(realInput);
        pw.close();
        System.out.println("Should be correct");
			
	}
	catch(FileNotFoundException e4){
		System.err.println("can't find the files");
		System.exit(1);
	}catch(IOException e2){
		System.err.println("IOException.");
		System.exit(1);
	}catch(Exception e3){
		System.err.println(e3+" other kind of error");
	}
	}
	//getter
	public Hashtable<String, ArrayList<Integer>> getDataSet(){
		return usersToOffers;
	}
}

