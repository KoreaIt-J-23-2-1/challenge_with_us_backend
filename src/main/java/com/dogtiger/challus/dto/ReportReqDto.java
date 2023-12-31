package com.dogtiger.challus.dto;

import com.dogtiger.challus.entity.Letter;
import lombok.Data;

@Data
public class ReportReqDto {
    private int senderUserId;
    private String title;
    private int receiverUserId;
    private int feedId;
    private int challengeId;
    private String content;
    private String targetUrl;

    public Letter toLetterEntity() {
        return Letter.builder()
                .challengeId(challengeId)
                .content(content)
                .senderUserId(senderUserId)
                .receiverUserId(receiverUserId)
                .title(title)
                .targetId(challengeId)
                .targetUrl(targetUrl)
                .build();
    }
}
