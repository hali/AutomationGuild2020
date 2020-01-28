setup:
	def shortcuts = new Shortcuts()
	def homePage = new HomePage()
when: 
	shortcuts.loginCleanupAndSearchFor(product)
	def errors = homePage.verifyProductInfo(expectedDescription, shouldHaveAddress, expectedAddress, expectedTestResult)
then:
	errors == "no errors detected"
	
where:
	[product, expectedDescription, expectedAddress, expectedTestResult, shouldHaveAddress] <<
		new DataProvider().loadProductsForBasicTest()