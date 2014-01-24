package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ImportController {

	private static ImportController instance = new ImportController();

	private ImportController() {

	}

	public static ImportController getInstance() {
		return instance;
	}

	public void readCSV(File file) throws IOException {

		BufferedReader CSVFile = new BufferedReader(new FileReader(
				file));

		String dataRow = CSVFile.readLine(); 	// Read first line.
												// The while checks to see if the data is null. If
												// it is, we've hit the end of the file. If not,
												// process the data.

		while (dataRow != null) {
			String[] dataArray = dataRow.split(",");
			for (String item : dataArray) {
				System.out.print(item + "\t");
			}
			System.out.println(); // Print the data line.
			dataRow = CSVFile.readLine(); // Read next line of data.
		}
		// Close the file once all data has been read.
		CSVFile.close();
	}
}
