package com.example.nefix.profile;

import com.example.nefix.genrealization.controller.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController extends BaseController<Profile, Long>
{
    public ProfileController(ProfileService service)
    {
        super(service);
    }

    @Override
    @PostMapping
    public ResponseEntity<Profile> create(@RequestBody Profile entity)
    {

        if(entity.getProfileImage() == null || entity.getProfileImage().isEmpty())
        {
            String imageUrl;

            try {
                HttpClient httpClient = HttpClient.newBuilder()
                        .followRedirects(HttpClient.Redirect.NORMAL)
                        .build();

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://picsum.photos/512"))
                        .GET()
                        .build();

                HttpResponse<Void> response = httpClient.send(request, HttpResponse.BodyHandlers.discarding());

                List<String> locationHeaders = response.headers().allValues("location");
                if (!locationHeaders.isEmpty()) {
                    imageUrl = locationHeaders.get(0);
                } else if (response.uri() != null) {
                    imageUrl = response.uri().toString();
                } else {
                    throw new RuntimeException("Unable to fetch the redirected URL");
                }

            } catch (Exception e) {
                throw new RuntimeException("Error while fetching image from Picsum Photos", e);
            }

            System.out.println("Image URL: " + imageUrl);

            entity.setProfileImage(imageUrl);
        }

        Profile savedEntity = service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

}
