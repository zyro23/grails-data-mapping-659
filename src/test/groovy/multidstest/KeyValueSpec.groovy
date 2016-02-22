package multidstest

import grails.test.mixin.Mock
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import multidstest2.FooTwo
import spock.lang.Specification

@TestMixin(GrailsUnitTestMixin)
@Mock([FooOne, FooTwo])
class KeyValueSpec extends Specification{

	def "test key value store works with domains mapped to multiple datasources"() {
		def foo1 = new FooOne(fooNameOne: "foo 1")
		def foo2 = new FooTwo(fooNameTwo: "foo 2")

		when: "foos are saved"
		foo1.save flush: true, failOnError: true
		foo2.save flush: true, failOnError: true

		then: "everything ok"
		FooOne.first().fooNameOne == "foo 1"
		FooTwo.first().fooNameTwo == "foo 2"
	}

}
