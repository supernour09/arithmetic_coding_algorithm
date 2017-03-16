package arithmatic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JOptionPane;

public class decompress {
	Vector<DoublePair> d;
	double com;
	int sz;
	public decompress() {
		d = new Vector<DoublePair>();
	}

	public void proccess(String adress, String name) {
		d.clear();
		read(adress);
		String t = decomp();
		write(t, name);
		JOptionPane.showMessageDialog(null, "the file is decompressed");

	}

	public void write(String x, String name) {
		try {
			  BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(name)));
              bwr.write(x);
              bwr.flush();
              bwr.close();
            } catch (Exception ex) {

		}

	}

	public String decomp() {
		String ans ="";
		double val = com;
		for(int i=0;i<sz;i++){
			System.out.println(val);
			for(int j=0;j<d.size();j++){
				
				if(val > d.get(j).f &&  val < d.get(j).s ){
					ans+=d.get(j).c;
					System.out.println(d.get(j).f + " " +d.get(j).s);
					val = (val - d.get(j).f)/(d.get(j).s - d.get(j).f);
					val+=0.0001;
					break;
				}
			}
		}
		return ans;
	}

	public void read(String x) {
		File f = new File(x);
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			try {
				com = Double.parseDouble(br.readLine());
				sz = Integer.parseInt(br.readLine());
				String tmp ;
				while(br.ready()){
					tmp = br.readLine();
					Character t = tmp.charAt(0);
					String ff="",ss="";
					int i=2;
					for(;i<tmp.length();i++){
						if(tmp.charAt(i) == '/'){
							i++;
							break;
						}else ff+=tmp.charAt(i);
					}
					for(;i<tmp.length();i++){
						ss+=tmp.charAt(i);
					}
					
					DoublePair test = new DoublePair(Double.parseDouble(ff) , Double.parseDouble(ss) , t);
					
					d.add(test);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			br.close();
			
		} catch (IOException e) {
			
			//e.printStackTrace();
		}
		
		
	}
}
