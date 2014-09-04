import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;


public class FileDriver {
	private Set<String> acceptedFileType;
	public FileDriver(){

	    acceptedFileType = new TreeSet<String>();
	    String[] fileTypes = new String[]{
	    						"mp4",
	    						"mov",
	    						"mkv"
	    					};
	    for(String type : fileTypes){
	    	acceptedFileType.add(type);
	    }
	}
	
	public String run(String path,Scanner scn) throws IOException{
		System.out.println(path);
		File folder = new File(path);
	    File[] listOfFiles = folder.listFiles();
		List<String> paths = new ArrayList<String>();
		Set<String> folders = new TreeSet<String>();
		int found = 1;
		for (File file : listOfFiles) {
			String filename = file.getName();
    		String extension = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
    		String absPath = file.getAbsolutePath();
	    	if (file.isFile()) {  
	    		
	    		if(acceptedFileType.contains(extension.toLowerCase())){
	    			System.out.println(found + ") " + file.getName());
	    			paths.add(absPath);
	    			found++;
	        	}
	        } else if (file.isDirectory()) {
	        	folders.add(absPath);
	        	System.out.println(found + ") >" +file.getName());
	        	paths.add(file.getAbsolutePath());
	        	found++;
	        }
	    	
	    }
		
		int pos = scn.nextInt()-1;
		
		return folders.contains(paths.get(pos))? run(paths.get(pos),scn) : paths.get(pos);
	}
	public static void main(String[] args) throws IOException{
	    FileDriver driver = new FileDriver();
	    Scanner scn = new Scanner(System.in);
	    String path = driver.run("/Users/Eric/Downloads",scn);
	    scn.close();
	    System.out.println(path);
	}
}
