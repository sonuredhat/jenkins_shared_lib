def call(artifactPath, jarFile, authToken) {
    def url = new URL(artifactPath)
    HttpURLConnection connection = (HttpURLConnection) url.openConnection()

    // Set request properties
    connection.setRequestMethod("PUT")
    connection.setRequestProperty("Authorization", "Bearer ${authToken}")
    connection.setDoOutput(true)

    // Upload the file
    //def file = new File(jarFile)
    connection.outputStream.withCloseable { out ->
        out.write(file.bytes)
    }

    // Get the response
    def responseCode = connection.getResponseCode()
    def responseMessage = connection.getResponseMessage()

    if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
        println("Artifact uploaded successfully: ${responseMessage}")
    } else {
        println("Failed to upload artifact. Response code: ${responseCode}, Message: ${responseMessage}")
    }
}

