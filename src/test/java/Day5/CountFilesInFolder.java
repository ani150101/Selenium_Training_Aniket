package Day5;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CountFilesInFolder {
	public static void main(String args[]) {
		
		/*
		 * File download and check if the file exists, file count should increase by 1
		 * Then delete the file
		 */
		
		List<String> fileNames = new ArrayList<>();
		try {
			DirectoryStream<Path> directoryStream = Files
					.newDirectoryStream(Paths.get("C:\\Users\\anisingh12\\Downloads"));
			for (Path path : directoryStream) {
				fileNames.add(path.toString());
			}
		} catch (IOException ex) {
		}
		System.out.println("File Count:" + fileNames.size());
	}
}