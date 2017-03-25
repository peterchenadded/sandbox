REPORTER = list
MOCHA_OPTS = --ui bdd -c

test:
	clear
	echo Starting test ************************************
	./node_modules/mocha/bin/mocha \
	--report $(REPORTER) \
	$(MOCHA_OPTS) \
	tests/*.js
	echo Ending test

# Mark things as not files
.PHONY: test
