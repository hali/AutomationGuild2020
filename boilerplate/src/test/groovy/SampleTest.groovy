package com.sample.automation.project

import org.junit.Test
import spock.lang.*
import com.sample.automation.helper.DataProvider
import com.sample.automation.annotation.*

/*  This class contains test scenarios for magical things
**/
class SampleTest extends spock.lang.Specification {
	def currentPage
	@Shared def baseURL = new PropertyReader().getBaseURL()

	def setupSpec() {
		open(baseURL)
		new Shortcuts().login()
	}
	
	@Smoke
	@Unroll
	def "Search from Home page for #something"() {
		setup:
			open(baseURL)
			currentPage = new HomePage()
		when:
			currentPage.search(something)
			def i = 0
			while (i < 30) {
				sleep(500)
				if (currentPage.isErrorDisplayed())
					i = 30
				i += 1
			}
			
		then:
			currentPage.isErrorDisplayed() == false

		where:
			something << new DataProvider().loadSampleTestData()
	}

	def "Put stuff into the basket"() {
	setup:
		open(baseURL)
		currentPage = new HomePage()		
	when:
		currentPage.search(product)
		currentPage = new ProductListPage()
		def selectedProduct = currentPage.selectRandomProduct()
		currentPage = new ProductPage()
		currentPage.putProductToTheBasket()
	then:
		currentPage.basketPopupIsShown()
		currentPage.basketHasProduct(selectedProduct)
	
	when:
		currentPage.openBasket()
		currentPage = new BasketPage()
	then:
		currentPage.basketHasProduct(selectedProduct)
		
	where:
		product << ["toaster", "black socks"]
		
	}
	
}