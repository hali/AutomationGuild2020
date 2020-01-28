package com.sample.automation.helper;

/*	This class is an interface to the CSV files with test data
**/
class DataProvider {
	// Load test data from a file, skip the first line (because it's a header - keeping it in for easier updates to the file)
	static def loadSampleTestData() {
		def testData = new File('src/test/groovy/resources/SampleTestData.csv').readLines()*.split(',(?=([^\"]*\"[^\"]*\")*[^\"]*$)')[1..-1];
		return testData;
	}
	
}