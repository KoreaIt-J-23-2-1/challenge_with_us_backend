package com.dogtiger.challus.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class LettersResDto {
    private int letterId;
    private int senderUserId;
    private String senderNickname;
    private int receiverUserId;
    private String title;
    private String letterTitle;
    private String content;
    private String sendDateTime;
    private int isRead;
    private int letterType;
    private String targetUrl;
    private int targetId;
    private int challengeId;
    private int acceptState;
    private String challengeName;
}