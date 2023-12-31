package com.dogtiger.challus.dto;

import com.dogtiger.challus.entity.Feed;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedReqDto {
    private int challengeId;
    private int time;
    private String text;
    private String image;
    private String categoryName;
    private int userId;
    private int challengeLayout;
    private int layout;

    public Feed toFeedEntity() {
        String modifiedImage = (image != null) ? image.replace("blob:", "") : null;

        return Feed.builder()
                .challengeId(challengeId)
                .stopWatch(time)
                .feedContent(text)
                .img(modifiedImage)
                .categoryName(categoryName)
                .userId(userId)
                .challengeLayout(challengeLayout)
                .layout(layout)
                .build();
    }
}
