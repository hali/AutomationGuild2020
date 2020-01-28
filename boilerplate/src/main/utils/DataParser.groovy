package com.sample.automation.helper;

/*	This class is an interface to the CSV files with test data
**/
class DataParser {

	// Compare two maps of lists for equality
	static def boolean mapOfListsEqual(m1, m2) {
		if (m1.keySet() != m2.keySet())
			return false
		m1.keySet().each { key ->
			if (m1[key] == m2[key])
				return false
		}
		return true
	}

	// Takes a quoted string e.g. "key1:value 1;;key2: value 2, with a comma in the middle;;key3:value 3"
	// Returns a map e.g. [key1:"value1",key2:" value 2, with a comma in the middle", ke3:"value 3"]
	def stringToMap(input) {
		return input[1..-2].split(';;').collectEntries { 
				entry -> def pair = entry.split(':')
				[(pair.first()): pair.last()]
			}
	}
	
	// Takes a quoted string e.g. "group1:value1,value2,value3;;group2:value1,value4"
	// Returns a map e.g. [group1:["value1", "value2", "value3"], group2:["value1", "value4"]]
	def stringToListOfMapsOfLists(input) {
		return input[1..-2].split(';;').collectEntries { 
				entry -> def pair = entry.split(':')
				ArrayList value = pair.last().split(',').sort()
				[(pair.first()): value]
			}
	}
}