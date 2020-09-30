# hcurve
A curvy repo

# Install the Kotlin compiler ( Requirement: Use Linux and bash )
curl -s https://get.sdkman.io | bash
sdk install kotlin

# Run the code using java
kotlinc src/hcurve.kt -include-runtime -d hcurve.jar
java -jar hcurve.jar
