package mobile_project.memo;

import okhttp3.*;

class GPTAPI {
    private static final String API_URL = "https://api.openai.com/v1/engines/davinci-codex/completions";
    private static final String API_KEY = "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";


    public String generateText(String prompt) throws Exception {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"prompt\": \"" + prompt + "\", \"max_tokens\": 100}");
        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + API_KEY)
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        return responseBody;
    }
}