.PHONY: up down logs test typecheck backend-test verify

up:
	docker compose up --build

down:
	docker compose down -v

logs:
	docker compose logs -f --tail=150

test:
	pnpm test

typecheck:
	pnpm typecheck

backend-test:
	mvn -f backend/pom.xml test

verify:
	./scripts/verify.sh
