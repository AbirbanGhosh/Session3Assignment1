package ListFile;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
/*
* Will show the list of files, links and sub-directories recursively for the given
* paths
*/
public class TaskThree {
	public static void main (String [] args)throws Exception{
		if(args.length < 1){
			System.out.println("Please give atlist one HDFS path for file listing");
			System.exit(-1);
		}
		
		for(String strPath : args)
		{
			System.out.println("**************************************");
			System.out.println("Given Path: " + strPath);			
			Path path = new Path(strPath);		
			fileStatus(path);
			System.out.println("**************************************");
		}
	}
	
	//Check the file status and print the file/link/directory recursively
	private static void fileStatus(Path path)throws Exception{
		try{
		    
			Configuration conf = new Configuration();
			FileSystem fs = FileSystem.get(path.toUri(), conf);
			FileStatus []arrFSts = fs.listStatus(path);
			for(FileStatus eachFSts : arrFSts){
				if(eachFSts.isDirectory() == true){
					System.out.println("Directory: \t" + eachFSts.getPath());
					//recursively
					fileStatus(eachFSts.getPath());
				}
				if(eachFSts.isFile() == true){
					System.out.println("File: \t" + eachFSts.getPath());
				}
				if(eachFSts.isSymlink() == true){
					System.out.println("Link: \t" + eachFSts.getPath());
				}
			}
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}		
	}
}
