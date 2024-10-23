def call(directoryPath, pattern) {
    def dir = new File(directoryPath)

    // Ensure the directory exists and is valid
    if (!dir.exists() || !dir.isDirectory()) {
        error("Invalid directory: ${directoryPath}")
        return null
    }

    // Use regex to match file names with the pattern
    def regexPattern = pattern.replace(".", "\\.").replace("*", ".*")
    def jarFiles = dir.listFiles().findAll { it.name ==~ regexPattern }

    return jarFiles
}
