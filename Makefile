.PHONY: all
all: build

.PHONY: build
build:
	@./gradlew assemble --warning-mode all

.PHONY: check
check:
	@./gradlew check --warning-mode all

.PHONY: run
run:
	@./gradlew :run

.PHONY: test
test:
	@./gradlew test