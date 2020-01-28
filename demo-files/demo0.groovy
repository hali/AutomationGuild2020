when: 
	@By("#login").setValue("user1")
	@By("#password").setValue("password1")
	@By("#btnLogin").click()
	@By("#search").waitUntil(Condition.Enabled, 5000)
then:
	@By("#search").text() == ""
	
when:
	@By("#search").setValue("1230978123")
	@By("#btnSearch").click()
then:
	@By("#productInfo").text() == "Expected description"
	@By("#productAddress").text() == "Expected Address"
	@By("#testResult").text() == "Working"