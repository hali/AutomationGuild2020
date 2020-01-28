setup:
	def loginPage = new LoginPage()
	def homePage = new HomePage()
when: 
	loginPage.login()
then:
	loginPage.searchFieldIsEmpty()
	
when:
	homePage.searchFor(product)
	def errors = ""
	if (homePage.getProductDescription != expectedDescription)
		errors += "Description is incorrect. Expected " + expectedDescription + ", got " + homePage.getProductDescription() + ".\n"
	if (homePage.getProductAddress() != expectedAddress)
		errors += "Address is incorrect. Expected " + expectedAddress + ", got " + homePage.getProductAddress() + ".\n"
	if (homePage.getTestResult() != expectedTestResult)
		errors += "Test Result is incorrect. Expected " + expectedTestResult + ", got " + homePage.getTestResult() + ".\n"
then:
	errors == ""
	
where:
	product     | expectedDescription   | expectedAddress   | expectedTestResult
	1230978123	| Expected description	| Expected Address	| Working	