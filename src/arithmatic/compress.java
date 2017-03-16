package arithmatic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;

public class compress {
	Vector<DoublePair> d;
	static int freq[] = new int[257];
	public compress() {
		d = new Vector<DoublePair>();
	}	
	public void proccess(String address , String name){
		d.clear();
		String tmp = read(address);
		System.out.println(tmp);
		for(int i=0;i<tmp.length();i++){
			freq[tmp.charAt(i)]++;
		}
		
		for(int i=0;i<257;i++){
			if(freq[i] != 0){
				if(d.isEmpty()){
					DoublePair t = new DoublePair(0.0,(freq[i] / (tmp.length()*1.0)),(char)i);
					d.add(t);
				}else{
					DoublePair t = new DoublePair(d.get(d.size()-1).s,d.get(d.size()-1).s+(freq[i] / (tmp.length()*1.0)),(char)i);
					d.add(t);
				}
			}
		}
		double w = com(tmp);
		write(w,name,tmp.length());
		JOptionPane.showMessageDialog(null, "the file is compressed");
		
	}


	@SuppressWarnings("resource")
	public String read(String address){
		String content = null;
		try {
			content = new Scanner(new File(address)).useDelimiter("\\Z").next();			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;		
	}
	public void write(double c,String name,int sz) {
		File fl = new File(name+".txt");
		try {
			@SuppressWarnings("resource")
			PrintStream ps = new PrintStream(fl);
			ps.println(c);
			ps.println(sz);
			for (int i =0;i<d.size();i++) {
				ps.println(d.get(i).c+"/"+d.get(i).f+"/"+d.get(i).s);
			}
			
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}

	public double com(String x) {
		double low = 0 ,high = 1.0;
		for(int i = 0 ; i < x.length();i++){
			for(int j=0;j<d.size();j++){
				if(d.get(j).c == x.charAt(i)){
					double thigh = low + (high - low) * d.get(j).s;
					double tlow = low + (high - low) * d.get(j).f;
					high = thigh;
					low = tlow;
					break;
				}
			}
		}
		return low;
	}
}
