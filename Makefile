.PHONY: build run down fmt
build:
	mvn -q -DskipTests package
run:
	docker compose up --build
down:
	docker compose down -v
fmt:
	git ls-files '*.java' | xargs -I{} echo {}
