package com.abujava.project.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        template.header("ServiceUsername", "usernameEducationService");
        template.header("ServicePassword", "educationServicePassword8434323212");
        template.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5NjE3ZTU4OS0wYWM2LTQzZGItM2EwNGE0NGQtN2Y4My00YTQxLTg4NWItM2ZkMGEzMDM4NTQwLTlmZmQtNThjZmZmZTI4NWY2IiwiaWF0IjoxNjcxMDgzNjcyLCJleHAiOjE2NzE2ODg0NzJ9.VO0HLN2QB7a1uas_ns2fyAUhSlf5TD5bPGk09jAR6oM");
    }
}

