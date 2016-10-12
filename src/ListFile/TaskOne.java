package ListFile;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/*
* Will show the list of files, links and sub-directories for given path
*/
public class TaskOne {
	public static void main (String [] args)throws Exception{
		if(args.length != 1){
			System.out.println("Please give an HDFS path for file listing");
			System.exit(-1);
		}
		System.out.println("Given Path: " + args[0]);
		
		Path path = new Path(args[0]);

		
		fileStatus(path);
	}
	
	//Check the file status and print the file/link/directory accordingly
	private static void fileStatus(Path path)throws Exception{
		try{
		    
			Configuration conf = new Configuration();
			FileSystem fs = FileSystem.get(path.toUri(), conf);
			FileStatus []arrFSts = fs.listStatus(path);
			for(FileStatus eachFSts : arrFSts){
				if(eachFSts.isDirectory() == true){
					System.out.println("Directory: \t" + eachFSts.getPath());
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
