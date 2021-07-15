##################################################
# - clean: deletes cache
# - compile: compiles java code
# - package: creates .app for macOS
# - assembly_single: Creates jar
# - Dmaven.test.skip=true : Skips tests
##################################################
mvn clean compile package assembly:single -Dmaven.test.skip=true -B
