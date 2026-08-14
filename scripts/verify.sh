#!/usr/bin/env bash
set -euo pipefail
root="$(cd "$(dirname "$0")/.." && pwd)"
cd "$root"
pnpm typecheck
pnpm test
mvn -q -f backend/pom.xml test
git diff --check
test -f README.md
test -f DESIGN.md
test -f coverage-matrix.md
test -f SUMMARY.md
for id in B01 B02 B03 B04 B05 B06 B07 B08 B09 B10 B11 B12; do
  test -f "docs/issues/${id}.md"
  test -f "solutions/${id}.md"
done
