package yelinskyi.addresscoordinates.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import yelinskyi.addresscoordinates.dto.CoordinatesResponseDto;

public class HttpClient {
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CoordinatesResponseDto apiResponseDto = new CoordinatesResponseDto();

    public CoordinatesResponseDto get(String address) {
        HttpGet request = new HttpGet(address);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity httpEntity = response.getEntity();
            String result = EntityUtils.toString(httpEntity);
            List<Map<String, Object>> list = objectMapper.readValue(result, List.class);
            for (Map<String, Object> map : list) {
                apiResponseDto.setLat(map.get("lat").toString());
                apiResponseDto.setLon(map.get("lon").toString());
            }
            return apiResponseDto;
        } catch (IOException e) {
            throw new RuntimeException("Can't get coordinates by address:" + address, e);
        }
    }
}
