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
	if (shouldHaveAddress.toBoolean()) {
		if (homePage.getProductAddress() != expectedAddress)
			errors += "Address is incorrect. Expected " + expectedAddress + ", got " + homePage.getProductAddress() + ".\n"
	} else if (homePage.getProductAddress() != "none")
		errors += "Address field is shown when it shouldn't be.\n"
			
	if (homePage.getTestResult() != expectedTestResult)
		errors += "Test Result is incorrect. Expected " + expectedTestResult + ", got " + homePage.getTestResult() + ".\n"
then:
	errors == ""
	
where:
	product     | expectedDescription   | expectedAddress   | expectedTestResult | shouldHaveAddress
	1230978123	| Expected description	| Expected Address	| Working			 | true
	8970192378	| Expected description	| 					| Working			 | false